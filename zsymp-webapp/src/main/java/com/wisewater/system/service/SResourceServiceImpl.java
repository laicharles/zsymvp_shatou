package com.wisewater.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.controller.SResourceJqgridForm;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.pojo.SResource;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.system.repository.SResourceConfigRepository;
import com.wisewater.system.repository.SResourceRepository;
import com.wisewater.util.tools.ZtreeForm;

@Service
public class SResourceServiceImpl extends BaseService implements
		SResourceService {

	@Autowired
	private SResourceRepository resourcesRepository;

	@Autowired
	private SDictionaryRepository dictionaryRepository;

	// Add By Alex at 20150908
	@Autowired
	private SResourceConfigRepository resourceConfigRepository;

	// 过滤特殊菜单
	public boolean isShowResFilter(String resID, String token) {
		boolean flag = false;
		if (resourceConfigRepository.findResourceByResID(resID) <= 0) {
			// 非特殊菜单
			flag = true;
		} else if (resourceConfigRepository.findResourceByResIDNToken(resID,
				token) > 0) {
			// 特殊菜单且水司token存在与表中
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public SResourceForm findRootResource() {

		SResource rootResource = resourcesRepository
				.findByParentResourceIsNull();
		if (rootResource != null) {
			return mapper.map(rootResource, SResourceForm.class);
		}

		return null;
	}

	@Override
	public SResourceForm findAuthMenu(List<SResourceForm> menus,
			String rootCode, String token) {
		SResource rootResource = resourcesRepository.findByResCode(rootCode);
		if (rootResource != null) {

			if (!checkMenus(rootResource.getId(), menus))
				return null;

			checkRootResource(rootResource.getSubResources(), menus, rootCode,
					token);

			// Update by Alex 排序 20150519 menuSort(rootResource)

			SResourceForm rootResourceForm = mapper.map(menuSort(rootResource),
					SResourceForm.class, "resourcesForMenu");

			return rootResourceForm;
		}
		return null;
	}

	/**
	 * 登陆后菜单排序
	 * 
	 * @param rootResource
	 * @return AlexFung
	 */
	private SResource menuSort(SResource rootResource) {
		SResource menuSort = new SResource();
		menuSort.setAuthUrl(rootResource.getAuthUrl());
		menuSort.setId(rootResource.getId());
		menuSort.setIsLogicDel(rootResource.getIsLogicDel());
		menuSort.setIsShowMenu(rootResource.getIsShowMenu());
		menuSort.setPicName(rootResource.getPicName());
		menuSort.setRemarks(rootResource.getRemarks());
		menuSort.setReqUrl(rootResource.getReqUrl());
		menuSort.setResCode(rootResource.getResCode());
		menuSort.setResName(rootResource.getResName());
		menuSort.setResType(rootResource.getResType());
		List<SResource> source = new ArrayList<SResource>();
		for (SResource s : rootResource.getSubResources()) {
			Collections.sort(s.getSubResources(), new Comparator<SResource>() {
				public int compare(SResource arg0, SResource arg1) {
					return arg0.getResCode().compareTo(arg1.getResCode());
				}
			});
			source.add(s);
		}
		Collections.sort(source, new Comparator<SResource>() {
			public int compare(SResource arg0, SResource arg1) {
				return arg0.getResCode().compareTo(arg1.getResCode());
			}
		});
		menuSort.setSubResources(source);
		return menuSort;
	}

	/**
	 * 运营后台资源管理排序
	 * 
	 * @param rootResource
	 * @return AlexFung 2015年8月31日 下午1:10:26
	 */
	private SResource resourcesSort(SResource rootResource) {
		SResource menuSort = new SResource();
		menuSort.setAuthUrl(rootResource.getAuthUrl());
		menuSort.setId(rootResource.getId());
		menuSort.setIsLogicDel(rootResource.getIsLogicDel());
		menuSort.setIsShowMenu(rootResource.getIsShowMenu());
		menuSort.setPicName(rootResource.getPicName());
		menuSort.setRemarks(rootResource.getRemarks());
		menuSort.setReqUrl(rootResource.getReqUrl());
		menuSort.setResCode(rootResource.getResCode());
		menuSort.setResName(rootResource.getResName());
		menuSort.setResType(rootResource.getResType());
		List<SResource> source = new ArrayList<SResource>();
		for (SResource s : rootResource.getSubResources()) {
			List<SResource> subSource = new ArrayList<SResource>();
			for (SResource sub : s.getSubResources()) {
				Collections.sort(sub.getSubResources(),
						new Comparator<SResource>() {
							public int compare(SResource arg0, SResource arg1) {
								return arg0.getResCode().compareTo(
										arg1.getResCode());
							}
						});
				subSource.add(sub);
			}
			s.setSubResources(subSource);
			source.add(s);
		}
		Collections.sort(source, new Comparator<SResource>() {
			public int compare(SResource arg0, SResource arg1) {
				return arg0.getResCode().compareTo(arg1.getResCode());
			}
		});
		menuSort.setSubResources(source);
		return menuSort;
	}

	private void checkRootResource(List<SResource> resources,
			List<SResourceForm> menus, String prefix, String token) {
		if (menus == null || menus.isEmpty())
			return;
		if (resources == null || resources.isEmpty())
			return;

		List<SResource> resourcesList = new ArrayList<SResource>(resources);
		for (SResource currentResource : resourcesList) {
			boolean isShow = isShowResFilter(currentResource.getId(), token);
			if (!currentResource.getResCode().startsWith(prefix)) { // 不在前缀范围删除
				resources.remove(currentResource);
			} else if (currentResource.getIsShowMenu() == 0) { // 操作不显示成菜单
				if (currentResource.getResType() != null
						&& currentResource.getResType().getDicValue()
								.equals("2")) {
					resources.remove(currentResource);
				}
			} else if (!isShow) { // 操作不显示成菜单
				resources.remove(currentResource);
			} else if (!checkMenus(currentResource.getId(), menus)) { // 不符合权限的删除
				resources.remove(currentResource);
			} else {
				checkRootResource(currentResource.getSubResources(), menus,
						prefix, token);
			}
		}

	}

	private boolean checkMenus(String resourceID, List<SResourceForm> menus) {
		if (menus == null || menus.isEmpty())
			return false;

		for (SResourceForm menu : menus) {
			if (menu.getId().equals(resourceID))
				return true;
		}
		return false;
	}

	@Override
	public List<SResourceJqgridForm> findAllResources() {
		SResource rootResource = resourcesRepository
				.findByParentResourceIsNull();

		rootResource = resourcesSort(rootResource);

		List<SResourceJqgridForm> resourceForms = new ArrayList<SResourceJqgridForm>();
		if (rootResource != null) {
			List<SResource> subResources = rootResource.getSubResources();
			SResourceJqgridForm jqgridForm = mapper.map(rootResource,
					SResourceJqgridForm.class);
			jqgridForm.setLevel("0");
			jqgridForm.setExpanded(true);
			jqgridForm.setIsLeaf((subResources != null && !subResources
					.isEmpty()) ? false : true);
			jqgridForm.setParent("");
			jqgridForm.setLoaded(true);
			resourceForms.add(jqgridForm);
			checkSubResources(subResources, 1, resourceForms);
		}

		return resourceForms;
	}

	private void checkSubResources(List<SResource> currentResources, int level,
			List<SResourceJqgridForm> resourceForms) {
		if (currentResources == null || currentResources.isEmpty())
			return;
		if (resourceForms.isEmpty())
			return;
		++level;

		for (SResource resource : currentResources) {
			List<SResource> subResources = resource.getSubResources();
			SResourceJqgridForm jqgridForm = mapper.map(resource,
					SResourceJqgridForm.class);
			jqgridForm.setLevel(level + "");
			jqgridForm.setExpanded(level <= 2 ? true : false);
			jqgridForm.setIsLeaf((subResources != null && !subResources
					.isEmpty()) ? false : true);
			jqgridForm.setParent(resource.getParentResource().getId());
			jqgridForm.setLoaded(true);
			resourceForms.add(jqgridForm);
			checkSubResources(subResources, level, resourceForms);
		}

	}

	@CacheEvict(value = { "resourceCache", "userCache" }, allEntries = true)
	@Override
	public boolean saveResource(SResourceForm resourceForm) {
		if (resourceForm == null)
			return false;

		SDictionary resType = dictionaryRepository.findByTypeCodeAndDicValue(
				resourceForm.getResType().getTypeCode(), resourceForm
						.getResType().getDicValue());
		SResource parentResource = resourcesRepository.findById(resourceForm
				.getParentResource().getId());

		SResource resource = mapper.map(resourceForm, SResource.class);

		if (resourceForm.getResCode() == null
				|| resourceForm.getResCode().length() == 0) {
			resource.setResCode(getResCode(parentResource.getSubResources(),
					parentResource.getResCode()));
		}

		resource.setParentResource(parentResource);
		resource.setResType(resType);

		resource = resourcesRepository.save(resource);

		if (resource != null)
			return true;
		else
			return false;
	}

	private String getResCode(List<SResource> subResources, String parentResCode) {
		int size = subResources.size();
		String sizeStr = size < 10 ? "00" + size : size < 100 ? "0" + size
				: (size + "");
		String newResCode = parentResCode + sizeStr;

		for (SResource resource : subResources) {
			if (resource.getResCode().equals(newResCode)) {
				++size;
				sizeStr = size < 10 ? "00" + size : size < 100 ? "0" + size
						: (size + "");
				newResCode = parentResCode + sizeStr;
			}
		}

		return newResCode;
	}

	@Override
	public SResourceForm findById(String id) {
		if (id == null || id.length() == 0)
			return null;

		SResource resource = resourcesRepository.findById(id);
		SResourceForm resourceForm = null;
		if (resource != null) {
			resourceForm = mapper.map(resource, SResourceForm.class);
		}
		return resourceForm;
	}

	@Override
	public List<ZtreeForm> findAllMenus() {

		List<SResource> menus = resourcesRepository.findAllMenus();

		List<ZtreeForm> treeMenus = new ArrayList<ZtreeForm>();
		for (SResource resource : menus) {
			ZtreeForm ztreeForm = new ZtreeForm();
			ztreeForm.setId(resource.getId());
			ztreeForm.setName(resource.getResName());
			if (resource.getParentResource() == null) {
				ztreeForm.setpId("0");
			} else {
				ztreeForm.setpId(resource.getParentResource().getId());
			}

			ztreeForm.setNocheck(false);
			ztreeForm.setOpen(resource.getResCode().length() <= 7 ? true
					: false);

			treeMenus.add(ztreeForm);
		}

		return treeMenus;
	}

	@CacheEvict(value = { "resourceCache", "userCache" }, allEntries = true)
	@Override
	public boolean deleteResource(String id) {

		SResource resource = resourcesRepository.findById(id);

		if (resource != null) {
			deleteBatch(resource.getSubResources());
			resource.setIsLogicDel(1);
			if (resourcesRepository.save(resource) != null) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	/**
	 * 
	 * @param resources
	 *            gawen.chen 2015年3月26日下午3:17:15 描述：级联删除下级所有子节点
	 */
	private void deleteBatch(List<SResource> resources) {
		if (resources == null || resources.isEmpty())
			return;

		for (SResource resource : resources) {
			resource.setIsLogicDel(1);
			deleteBatch(resource.getSubResources());
			resourcesRepository.save(resource);
		}
	}

}