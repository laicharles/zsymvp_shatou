package com.wisewater.bizConfig.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.bizConfig.pojo.BAccessToken;
import com.wisewater.bizConfig.pojo.BCustomer;
import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.bizConfig.repository.BAccessTokenRepository;
import com.wisewater.bizConfig.repository.BCustomerRepository;
import com.wisewater.bizConfig.repository.BCustomerUserRepository;
import com.wisewater.cusConfig.pojo.CMp;
import com.wisewater.cusConfig.repository.CMpRepository;
import com.wisewater.cusConfig.service.CMenuConfigureService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.pojo.SRole;
import com.wisewater.system.repository.SRoleRepository;

@Service
public class BCustomerUserServiceImpl extends BaseService implements
		BCustomerUserService {

	@Autowired
	private BCustomerUserRepository bcustomeruserRepository;

	@Autowired
	private CMpRepository cmpRepository;

	@Autowired
	private BAccessTokenRepository accessTokenRepository;

	@Autowired
	private BCustomerRepository customerRepository;

	@Autowired
	private SRoleRepository roleResRepository;

	@Autowired
	private CMenuConfigureService menuConfigureService;

	@Autowired
	@Qualifier(value = "mailSender")
	private JavaMailSender mailSender;

	@Override
	public BCustomerUserForm findById(String id) {
		if (StringUtils.isEmpty(id))
			return null;
		BCustomerUser user = bcustomeruserRepository.findById(id);
		if (user == null)
			return null;
		BCustomerUserForm userForm = mapper.map(user, BCustomerUserForm.class);
		return userForm;
	}

	@Override
	public BCustomerUserForm findByEmail(String email) {
		if (StringUtils.isEmpty(email))
			return null;
		BCustomerUser user = bcustomeruserRepository.findByEmail(email);
		if (user == null)
			return null;
		BCustomerUserForm userForm = mapper.map(user, BCustomerUserForm.class);
		return userForm;
	}

	@Override
	public BCustomerUserForm findByLoginName(String loginName) {
		if (StringUtils.isEmpty(loginName))
			return null;
		BCustomerUser user = bcustomeruserRepository.findByLoginName(loginName);
		if (user == null)
			return null;
		BCustomerUserForm userForm = mapper.map(user, BCustomerUserForm.class);
		return userForm;
	}

	@Override
	public BCustomerUserForm findByMobile(String mobile) {
		if (StringUtils.isEmpty(mobile))
			return null;
		BCustomerUser user = bcustomeruserRepository.findByMobile(mobile);
		if (user == null)
			return null;
		BCustomerUserForm userForm = mapper.map(user, BCustomerUserForm.class);
		return userForm;
	}

	/**
	 * updated by Alex at 2015/09/25
	 * @param customerUserForm
	 * @return 0代表成功；1代表空表单错误；2代表保存失败；3代表授权码错误或已被使用；
	 * gawen.chen
	 * 2015年4月3日下午4:33:30
	 * 描述：客户管理后台，新注册用户
	 * 
	 */
	@CacheEvict(value = "customerUserCache", allEntries = true)
	@Override
	public int saveRegisterCustomerUser(BCustomerUserForm customerUserForm) {
		
		if (customerUserForm == null)
			return 1;

		BCustomerUser customerUser = mapper.map(customerUserForm,
				BCustomerUser.class);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		customerUser.setPassword(encoder.encodePassword(
				customerUserForm.getPassword(), customerUser.getLoginName()));

		// 生成一条客户记录
		BCustomer customer = new BCustomer();
		customer.setRegisterDate(new Date());
		customer.setCompanyName(customerUserForm.getBCusotmer()
				.getCompanyName());
		customer = customerRepository.save(customer);
		if (customer != null) {

			// 生成一条accesstoken记录
			BAccessToken accessToken = new BAccessToken();
			accessToken.setToken(UUID.randomUUID().toString()
					.replaceAll("-", ""));

			accessToken = accessTokenRepository.save(accessToken);
			if (accessToken != null) {

				// 生成一件公众号记录
				CMp mp = new CMp();
				mp.setAccessToken(accessToken);
				mp.setBCusotmer(customer);
				mp = cmpRepository.save(mp);
				if (mp != null) {

					SRole role = roleResRepository.findByRoleCode("CUS_001"); // 默认角色
					// 生成一条注册用户管理员记录
					customerUser.setRegisterDate(new Date());
					customerUser.setBCusotmer(customer);
					customerUser.setUserName("");
					customerUser.setRole(role);
					customerUser.setToInviteCode(encoder.encodePassword(
							customerUser.getLoginName(),
							customerUser.getLoginName())); // 邀请码

					if (bcustomeruserRepository.save(customerUser) != null) {
						// 统计邀请数
						if (!StringUtils.isEmpty(customerUser
								.getFromInvitedCode())) {
							BCustomerUser inveteCustomerUser = bcustomeruserRepository
									.findByToInviteCode(customerUser
											.getFromInvitedCode());
							if (inveteCustomerUser != null) {
								inveteCustomerUser
										.setToInviteCount(inveteCustomerUser
												.getToInviteCount() + 1);
								bcustomeruserRepository
										.save(inveteCustomerUser);
							}
						}

						return 0;
					}

				}
			}

		}

		return 2;
	}

	@CacheEvict(value = "customerUserCache", allEntries = true)
	@Override
	public boolean saveCustomerUser(BCustomerUserForm customerUserForm) {

		if (customerUserForm.getBCusotmer() == null
				|| customerUserForm.getBCusotmer().getId() == null
				|| "".equals(customerUserForm.getBCusotmer().getId())) {
			return false;
		}

		if (customerUserForm.getRole() == null
				|| customerUserForm.getRole().getRoleCode() == null
				|| "".equals(customerUserForm.getRole().getRoleCode())) {
			return false;
		}

		SRole role = roleResRepository.findByRoleCode(customerUserForm
				.getRole().getRoleCode()); // 默认客户管理后台的运人员角色
		if (role == null) {
			return false;
		}
		BCustomer customer = customerRepository.findById(customerUserForm
				.getBCusotmer().getId());
		if (customer == null) {
			return false;
		}

		BCustomerUser customerUser = mapper.map(customerUserForm,
				BCustomerUser.class);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		customerUser.setPassword(encoder.encodePassword(
				loadConstant.getInitPassword(), customerUser.getLoginName()));

		// 生成一条注册用户管理员记录
		customerUser.setRegisterDate(new Date());
		customerUser.setBCusotmer(customer);
		customerUser.setRole(role);
		customerUser.setToInviteCode(encoder.encodePassword(
				customerUser.getLoginName(), customerUser.getLoginName())); // 邀请码

		if (bcustomeruserRepository.save(customerUser) != null) {
			return true;
		}

		return false;
	}

	@CacheEvict(value = "customerUserCache", allEntries = true)
	@Override
	public boolean updateCustomerUser(BCustomerUserForm customerUserForm) {

		if (customerUserForm.getBCusotmer() == null
				|| customerUserForm.getBCusotmer().getId() == null
				|| "".equals(customerUserForm.getBCusotmer().getId())) {
			return false;
		}

		if (customerUserForm.getRole() == null
				|| customerUserForm.getRole().getRoleCode() == null
				|| "".equals(customerUserForm.getRole().getRoleCode())) {
			return false;
		}

		SRole role = roleResRepository.findByRoleCode(customerUserForm
				.getRole().getRoleCode()); // 默认客户管理后台的运人员角色
		if (role == null) {
			return false;
		}
		BCustomer customer = customerRepository.findById(customerUserForm
				.getBCusotmer().getId());
		if (customer == null) {
			return false;
		}

		BCustomerUser customerUser = mapper.map(customerUserForm,
				BCustomerUser.class);

		customerUser.setBCusotmer(customer);
		customerUser.setRole(role);

		if (bcustomeruserRepository.save(customerUser) != null) {
			return true;
		}

		return false;
	}

	@CacheEvict(value = "customerUserCache", allEntries = true)
	@Override
	public boolean deleteUsers(String ids) {
		if (StringUtils.isEmpty(ids))
			return false;

		String[] strIds = ids.split(",");
		if (strIds != null && strIds.length > 0) {
			List<String> listIds = Arrays.asList(strIds);
			List<BCustomerUser> users = bcustomeruserRepository
					.findByIdsIn(listIds);
			for (BCustomerUser user : users) {
				user.setIsLogicDel(1); // 逻辑删除
			}
			users = bcustomeruserRepository.save(users);
			if (users != null && !users.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@CacheEvict(value = "customerUserCache", allEntries = true)
	@Override
	public boolean disableUser(String ids) {
		if (StringUtils.isEmpty(ids))
			return false;

		String[] strIds = ids.split(",");
		if (strIds != null && strIds.length > 0) {
			List<String> listIds = Arrays.asList(strIds);
			List<BCustomerUser> users = bcustomeruserRepository
					.findByIdsIn(listIds);
			for (BCustomerUser user : users) {
				user.setIsDisabled(1); // 禁用
			}
			users = bcustomeruserRepository.save(users);
			if (users != null && !users.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@CacheEvict(value = "customerUserCache", allEntries = true)
	@Override
	public boolean enableUser(String ids) {
		if (StringUtils.isEmpty(ids))
			return false;

		String[] strIds = ids.split(",");
		if (strIds != null && strIds.length > 0) {
			List<String> listIds = Arrays.asList(strIds);
			List<BCustomerUser> users = bcustomeruserRepository
					.findByIdsIn(listIds);
			for (BCustomerUser user : users) {
				user.setIsDisabled(0); // 禁用
			}
			users = bcustomeruserRepository.save(users);
			if (users != null && !users.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean initPassword(String ids) {
		if (StringUtils.isEmpty(ids))
			return false;

		String[] strIds = ids.split(",");
		if (strIds != null && strIds.length > 0) {
			List<String> listIds = Arrays.asList(strIds);
			List<BCustomerUser> users = bcustomeruserRepository
					.findByIdsIn(listIds);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			for (BCustomerUser user : users) {
				user.setPassword(encoder.encodePassword(
						loadConstant.getInitPassword(), user.getLoginName()));
			}

			users = bcustomeruserRepository.save(users);
			if (users != null && !users.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public JqgridListForm findAll(int pageNo, String sidx, String sord,
			String loginName, final int isDisabled, String mobile,
			String userName, final String customerId) {

		if (StringUtils.isEmpty(loginName)) {
			loginName = "";
		}
		if (StringUtils.isEmpty(mobile)) {
			mobile = "";
		}
		if (StringUtils.isEmpty(userName)) {
			userName = "";
		}

		if (StringUtils.isEmpty(sidx)) {
			sidx = "loginName";
		}
		if (StringUtils.isEmpty(sord)) {
			sord = "asc";
		}

		final String loginNameFinal = "%" + loginName + "%";
		final String mobileFinal = "%" + mobile + "%";
		final String userNameFinal = "%" + userName + "%";

		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1)
			pageNo = 1;

		// 默认不排序
		PageRequest page = new PageRequest(pageNo - 1, pageSize);

		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				page = new PageRequest(pageNo - 1, pageSize, Direction.DESC,
						sidx);
			} else {
				page = new PageRequest(pageNo - 1, pageSize, Direction.ASC,
						sidx);
			}
		}

		// 组合查询条件
		Specification<BCustomerUser> spec = new Specification<BCustomerUser>() {
			public Predicate toPredicate(Root<BCustomerUser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Path<String> loginNamePath = root.get("loginName");
				Path<String> isDisabledPath = root.get("isDisabled");
				Path<String> mobilePath = root.get("mobile");
				Path<String> userNamePath = root.get("userName");
				Path<String> isLogicDelPath = root.get("isLogicDel");
				Path<String> belongCustomerId = root.get("BCusotmer").get("id");

				List<Predicate> predic = new ArrayList<Predicate>();
				predic.add(cb.like(loginNamePath, loginNameFinal));
				predic.add(cb.like(userNamePath, userNameFinal));
				predic.add(cb.like(mobilePath, mobileFinal));
				if (isDisabled != -1) {
					predic.add(cb.equal(isDisabledPath, isDisabled));
				}
				predic.add(cb.equal(isLogicDelPath, 0));
				predic.add(cb.equal(belongCustomerId, customerId));

				Predicate[] preArr = new Predicate[predic.size()];
				predic.toArray(preArr);

				query.where(preArr);

				return null;
			}
		};

		// 组合查询方法
		Page<BCustomerUser> userPage = bcustomeruserRepository.findAll(spec,
				page);

		List<BCustomerUserForm> pageList = new ArrayList<BCustomerUserForm>();
		if (userPage != null && userPage.getContent() != null) {
			for (BCustomerUser user : userPage.getContent()) {
				pageList.add(mapper.map(user, BCustomerUserForm.class));
			}
		}

		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(userPage.getTotalPages());
		jqgridListForm.setRecords(userPage.getTotalElements());

		return jqgridListForm;
	}

	@Override
	public boolean checkUsersInAdmin(String ids) {
		if (StringUtils.isEmpty(ids))
			return false;

		String[] strIds = ids.split(",");
		if (strIds != null && strIds.length > 0) {
			List<String> listIds = Arrays.asList(strIds);
			List<BCustomerUser> users = bcustomeruserRepository
					.findByIdsforAdmin(listIds);
			if (users != null && !users.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean saveResetPassowrd(BCustomerUserForm userForm) {

		if (userForm == null || StringUtils.isEmpty(userForm.getEmail())) {
			return false;
		}
		BCustomerUser customerUser = bcustomeruserRepository
				.findByEmail(userForm.getEmail());
		if (customerUser != null) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			customerUser.setPassword(encoder.encodePassword(
					userForm.getPassword(), customerUser.getLoginName()));
			if (bcustomeruserRepository.save(customerUser) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkOldPassword(String id, String oldPassword) {
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(oldPassword)) {
			return false;
		}

		BCustomerUser customerUser = bcustomeruserRepository.findById(id);

		if (customerUser == null)
			return false;

		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		oldPassword = encoder.encodePassword(oldPassword,
				customerUser.getLoginName());
		if (customerUser.getPassword().equals(oldPassword))
			return true;
		return false;
	}

	@Override
	public BCustomerUser findByBossLoginName(String loginName) {
		List<BCustomerUser> list = bcustomeruserRepository.findByBossLoginName(loginName);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public BCustomerUser findByHigherCustomerUserId(String higherCustomerUserId) {
		BCustomerUser bCustomerUser = bcustomeruserRepository.findByfindByHigherCustomerUserId(higherCustomerUserId);
		return bCustomerUser;
	}

}