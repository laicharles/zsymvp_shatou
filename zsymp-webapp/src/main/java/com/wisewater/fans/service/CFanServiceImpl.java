package com.wisewater.fans.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.fans.controller.CFanForm;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.fans.repository.CFanRepository;
import com.wisewater.fans.repository.CFanUserRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.wechatpublic.model.OpenidForUser;
import com.wisewater.wechatpublic.model.OpenidForUser.OpenidList;
import com.wisewater.wechatpublic.model.UserList;
import com.wisewater.wechatpublic.model.WxUser;
import com.wisewater.wechatpublic.util.DateUtils;
import com.wisewater.wechatpublic.util.WxUserUtil;

@Service
public class CFanServiceImpl extends BaseService implements CFanService {

	@Autowired
	private CFanRepository fanRepository;

	@Autowired
	private CFanUserRepository fanUserRepository;

	@Autowired
	private SDictionaryRepository dictionaryRepository;

	@Autowired
	private BAccessTokenService accessTokenService;

	public JqgridListForm findAll(int pageNo, String sidx, String sord, String nickName, String hasBind, String token) {

		if (StringUtils.isEmpty(nickName)) {
			nickName = "";
		}
		if (StringUtils.isEmpty(hasBind)) {
			hasBind = "";
		}
		if (StringUtils.isEmpty(token)) {
			token = "";
		}

		if (StringUtils.isEmpty(sidx)) {
			sidx = "nickName";
		}
		if (StringUtils.isEmpty(sord)) {
			sord = "asc";
		}

		final String nickNameFinal = "%" + nickName + "%";
		final String hasBindFinal = hasBind;
		final String tokenFinal = token;

		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1)
			pageNo = 1;

		// 默认不排序
		PageRequest page = new PageRequest(pageNo - 1, pageSize);

		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				page = new PageRequest(pageNo - 1, pageSize, Direction.DESC, sidx);
			} else {
				page = new PageRequest(pageNo - 1, pageSize, Direction.ASC, sidx);
			}
		}

		// 组合查询条件
		Specification<CFan> spec = new Specification<CFan>() {
			public Predicate toPredicate(Root<CFan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Path<String> nickNamePath = root.get("nickName");
				Path<List<CFanUser>> fanUsersPath = root.get("fanUsers");
				Path<String> tokenPath = root.get("token");
				Path<String> isLogicDelPath = root.get("isLogicDel");

				List<Predicate> predic = new ArrayList<Predicate>();
				predic.add(cb.like(nickNamePath, nickNameFinal));

				if (hasBindFinal.equalsIgnoreCase("y")) {
					predic.add(cb.isNotEmpty(fanUsersPath));

				} else if (hasBindFinal.equalsIgnoreCase("n")) {
					predic.add(cb.isEmpty(fanUsersPath));
				} else {
					// Nothing to do
				}

				predic.add(cb.equal(tokenPath, tokenFinal));
				predic.add(cb.equal(isLogicDelPath, 0));

				Predicate[] preArr = new Predicate[predic.size()];
				predic.toArray(preArr);

				query.where(preArr);

				return null;
			}
		};

		// 组合查询方法
		Page<CFan> fanPage = fanRepository.findAll(spec, page);

		List<CFanForm> pageList = new ArrayList<CFanForm>();
		if (fanPage != null && fanPage.getContent() != null) {
			for (CFan fan : fanPage.getContent()) {
				CFanForm fanForm = mapper.map(fan, CFanForm.class);
				if (fan.getFanUsers() == null || fan.getFanUsers().size() <= 0) {
					fanForm.setHasBind("无");
				} else {
					fanForm.setHasBind("有");
				}
				pageList.add(fanForm);
			}
		}

		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(fanPage.getTotalPages());
		jqgridListForm.setRecords(fanPage.getTotalElements());

		return jqgridListForm;
	}

	@Override
	public CFanForm findById(String id) {
		if (StringUtils.isEmpty(id))
			return null;
		CFan fan = fanRepository.findById(id);
		if (fan == null)
			return null;
		CFanForm fanForm = mapper.map(fan, CFanForm.class);
		if (fan.getFanUsers() == null || fan.getFanUsers().size() <= 0) {
			fanForm.setHasBind("无");
		} else {
			fanForm.setHasBind("有");
		}
		return fanForm;
	}
	
	@Override
	public CFanForm findByOpenID(String openID) {
		if (StringUtils.isEmpty(openID))
			return null;
		CFan fan = fanRepository.findByOpenID(openID);
		if (fan == null)
			return null;
		CFanForm fanForm = mapper.map(fan, CFanForm.class);
		if (fan.getFanUsers() == null || fan.getFanUsers().size() <= 0) {
			fanForm.setHasBind("无");
		} else {
			fanForm.setHasBind("有");
		}
		return fanForm;
	}

	@Override
	public boolean saveSynAllFans(String token) {
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}
		// 取关注用户列表
		List<String> openIDList = WxUserUtil.getAllUserOpenIdList(accessTokenForm.getAccessToken());
		if (openIDList == null || openIDList.size() == 0) {
			List<CFan> fans = fanRepository.findByAllSubscribe(token);
			// 将粉丝列表中的粉丝更改时取消关注、取消关注日期更新
			for (CFan fan : fans) {
				fan.setIsSubscribe(0);
				if (fan.getUnsubscribeDate() == null) {
					fan.setUnsubscribeDate(new Date());
				}
			}
			if (fans.size() > 0) {
				fanRepository.save(fans);
				// 将粉丝作逻辑删除
				for (CFan fan : fans) {
					saveFanUserLogicDel(fan, 1);
				}
			}
			return true;
		} else {
			System.out.println("正在获取原有粉丝数据...");
			List<CFan> allFan = fanRepository.findByAllByToken(token);
			System.out.println("获取原有粉丝数据完毕！正在处理...");
			Map<String, CFan> allFanMap = new HashMap<String, CFan>();
			if (allFan != null) {
				for (CFan cFan : allFan) {
					allFanMap.put(cFan.getOpenID(), cFan);
				}
			}
			System.out.println("数据处理完毕！开始同步数据...");
			List<String> openIDs = openIDList;
			// 遍历openID
			OpenidForUser openidForUser = null;
			List<OpenidList> openidAndLangList = new ArrayList<OpenidList>();
			OpenidList openidList = null;
			for (int i = 0; i < openIDs.size(); i++) {
				if (openidAndLangList.size() == 100) {
					System.out.println("当前正在同步第" + (i + 1) + "个粉丝！");
					// 遍历取每个openID的粉丝信息
					openidForUser = new OpenidForUser();
					openidForUser.setUser_list(openidAndLangList);
					System.out.println("正在获取openID信息！数量：" + openidAndLangList.size());
					accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
					UserList userList = WxUserUtil.getMoreUserInfo(accessTokenForm.getAccessToken(), openidForUser);
					if (userList != null && userList.getUser_info_list() != null
							&& userList.getUser_info_list().size() > 0) {
						List<CFan> fanList = new ArrayList<CFan>();
						for (WxUser wxUser : userList.getUser_info_list()) {
							fanList = saveFans(allFanMap, fanList, wxUser, token);
						}
						fanRepository.save(fanList);
						System.out.println("插入粉丝成功！数量：" + fanList.size());
					}
					openidAndLangList = new ArrayList<OpenidList>();
				}
				openidList = new OpenidList();
				openidList.setOpenid(openIDs.get(i));
				openidList.setLang("zh-CN");
				openidAndLangList.add(openidList);
			}
			// 插入剩下的粉丝
			if (openidAndLangList != null && openidAndLangList.size() > 0) {
				// 遍历取每个openID的粉丝信息
				openidForUser = new OpenidForUser();
				openidForUser.setUser_list(openidAndLangList);
				accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
				UserList userList = WxUserUtil.getMoreUserInfo(accessTokenForm.getAccessToken(), openidForUser);
				if (userList != null && userList.getUser_info_list() != null
						&& userList.getUser_info_list().size() > 0) {
					List<CFan> fanList = new ArrayList<CFan>();
					for (WxUser wxUser : userList.getUser_info_list()) {
						fanList = saveFans(allFanMap, fanList, wxUser, token);
					}
					fanRepository.save(fanList);
					System.out.println("剩下的粉丝插入成功！数量：" + fanList.size());
				}
			}

			// 将zsy中状态为关注的、但是在微信端已经取消关注的粉丝，设置为取消关注
			List<String> zsyOpenIDs = fanRepository.findOpenIDByAllSubscribe(token);
			if (zsyOpenIDs == null || zsyOpenIDs.isEmpty()) {
				return false;
			}
			zsyOpenIDs.removeAll(openIDs);
			if (!zsyOpenIDs.isEmpty()) {
				List<CFan> toUnSubFans = fanRepository.findByOpenIDIn(zsyOpenIDs, token);
				if (toUnSubFans == null || toUnSubFans.isEmpty()) {
					return false;
				} else {
					for (CFan toUnSubFan : toUnSubFans) {
						toUnSubFan.setIsSubscribe(0);
						toUnSubFan.setUnsubscribeDate(new Date());
					}
				}
				List<CFan> unSubFans = fanRepository.save(toUnSubFans);
				if (unSubFans == null || unSubFans.isEmpty()) {
					return false;
				}
			}
			System.out.println("同步粉丝完成！");
			return true;

		}
	}

	/**
	 * 保存粉丝
	 * 
	 * @param wxUser
	 *            XingXingLvCha 2015年10月23日 下午4:12:40
	 */
	private List<CFan> saveFans(Map<String, CFan> allFanMap, List<CFan> fanList, WxUser wxUser, String token) {
		if (wxUser != null) {
			// 通过openID查找粉丝
			CFan fan = allFanMap.get(wxUser.getOpenid());
			// 如果为空就创建新粉丝
			if (fan == null) {
				fan = new CFan();
			}
			// emoji表情过滤
			// Pattern emojiPattern = Pattern.compile (
			// "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]"
			// ,
			// Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE ) ;
			// Matcher emojiMatcher = emojiPattern.matcher(wxUser.getNickname())
			// ;
			String nickName = wxUser.getNickname();
			nickName = nickName.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+", "");
			fan.setOpenID(wxUser.getOpenid());
			fan.setNickName(nickName);
			fan.setIsSubscribe(1);
			fan.setIsLogicDel(0);
			if (wxUser.getSex() == 1) { // 男
				SDictionary gender = dictionaryRepository.findByLogicID("gender01");
				fan.setGender(gender);
			} else if (wxUser.getSex() == 2) { // 女
				SDictionary gender = dictionaryRepository.findByLogicID("gender02");
				fan.setGender(gender);
			} else { // 未知
				SDictionary gender = dictionaryRepository.findByLogicID("gender03");
				fan.setGender(gender);
			}
			fan.setCountry(wxUser.getCountry());
			fan.setProvinceName(wxUser.getProvince());
			fan.setCityName(wxUser.getCity());
			fan.setSubscribeDate(new Date(DateUtils.transferWxTime(wxUser.getSubscribe_time())));
			fan.setToken(token);
			fanList.add(fan);
		}
		return fanList;
	}

	/**
	 * @param fan
	 * @param isLogicDel
	 */
	public void saveFanUserLogicDel(CFan fan, int isLogicDel) {

		List<CFanUser> fanUsers = fan.getFanUsers();
		if (fanUsers != null && fanUsers.size() > 0) {
			for (CFanUser cFanUser : fanUsers) {
				cFanUser.setIsLogicDel(isLogicDel);
				fanUserRepository.save(cFanUser);
			}
		}
	}

	@Override
	public CFanForm findByOpenIDAndToken(String openID, String token) {
		if (StringUtils.isEmpty(openID))
			return null;
		if (StringUtils.isEmpty(token))
			return null;

		CFan cfan = fanRepository.findByOpenIDAndToken(openID, token);
		if (cfan != null) {
			return mapper.map(cfan, CFanForm.class);
		}
		return null;
	}

	@Override
	public List<CFan> findByNickName(String nickName) {
		if (StringUtils.isEmpty(nickName))
			return null;
		List<CFan> fan = fanRepository.findBynickName(nickName);
		return fan;
	}

}