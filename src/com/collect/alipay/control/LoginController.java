package com.collect.alipay.control;

import java.util.Collections;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collect.alipay.control.dto.Status;
import com.collect.alipay.domain.Loginer;
import com.collect.alipay.service.LoginerService;
import com.collect.alipay.util.UUIDUtil;

/**
 * 登录控制器
 * 
 * @author zhangkai
 *
 */
@Controller
public class LoginController {

	public static final String LOGIN_ERROR = "用户名或密码错误";

	@Inject
	private LoginerService loginerService;

	/**
	 * 跳转到登录页面
	 * 
	 * @param loginer
	 *            登录对象
	 * @return 登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute Loginer loginer) {
		return "index";
	}

	/**
	 * 处理登录请求
	 * 
	 * @param loginer
	 *            登录数据对象
	 * @param br
	 *            验证错误信息
	 * @param session
	 *            会话对象
	 * @return 相应的页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute Loginer loginer, BindingResult br, HttpSession session) {
		if (br.hasErrors()) {
			return "index";
		}

		Loginer LoginerResult = loginerService.check(loginer);

		if (LoginerResult == null) {
			loginer.setLoginMessage(LOGIN_ERROR);
			return "index";
		}

		session.setAttribute("loginer", LoginerResult);

		return "redirect:html/navigation.html";

	}

	/**
	 * 注销
	 * 
	 * @return 到登录页面
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginer");
		session.invalidate();
		return "redirect:login.do";
	}

	/**
	 * 获取当前的登录用户
	 * 
	 * @param session
	 *            httpSession
	 * @return 已经登录的用户或者null;
	 */
	@RequestMapping(value = "/getloginer", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(Loginer.WithoutPasswordView.class)
	public Loginer getLoginer(HttpSession session) {
		Loginer loginer = (Loginer) session.getAttribute("loginer");
		return loginer;
	}

	/**
	 * 加载树的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login/getZtree", method = RequestMethod.GET)
	@ResponseBody
	public Object getZtreeData(HttpSession session) {
		Loginer loginer = (Loginer) session.getAttribute("loginer");

		if (loginer == null) {
			return Collections.EMPTY_LIST;
		}

		return loginerService.getZtreeData(loginer);
	}

	/**
	 * 根据条件获取登陆者列表
	 * 
	 * @param loginer
	 *            封装条件
	 * @return 数据封装对象
	 */
	@RequestMapping(value = "/login/loginers", method = RequestMethod.POST)
	@ResponseBody
	public Object getLoginers(Loginer loginer, HttpSession session) {

		Loginer loginerFromSession = (Loginer) session.getAttribute("loginer");
		if (loginerFromSession == null) {
			return Collections.EMPTY_LIST;
		}

		if (loginer.getCustOrDistributorId() == null) {
			loginer.setCustOrDistributorId(loginerFromSession.getCustOrDistributorId());
		}

		return loginerService.getLoginers(loginer);
	}

	/**
	 * 新增
	 * 
	 * @param loginer
	 *            封装了实体对象的字段
	 * @return 状态
	 */
	@RequestMapping(value = "/loginer/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(Loginer loginer) {

		Loginer longer = loginerService.getByUsername(loginer.getUsername());
		if (longer != null) {
			return new Status(0, "用户名已存在");
		}

		String passwordMd5 = DigestUtils.md5Hex("111111");
		loginer.setPassword(passwordMd5);
		loginer.setId(UUIDUtil.randomUUID());
		int result = loginerService.save(loginer);
		return new Status(result);
	}

	/**
	 * delete
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/loginer/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable String id) {
		int result = loginerService.delete(id);
		return new Status(result);
	}

	/**
	 * 修改
	 * 
	 * @param loginer
	 * @param newpassword
	 * @return
	 */
	@RequestMapping(value = "/loginer/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(Loginer loginer, String newpassword) {
		if (loginerService.check(loginer) != null) {
			loginer.setPassword(DigestUtils.md5Hex(newpassword));

			int result = loginerService.update(loginer);
			return new Status(result);

		} else {
			return new Status(0, "旧密码错误！");
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 *            id
	 * @return
	 */
	@RequestMapping(value = "/loginer/restPassword/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object restPassword(Loginer loginer) {
		loginer.setPassword(DigestUtils.md5Hex("111111"));

		int result = loginerService.update(loginer);
		return new Status(result);

	}

}
