package com.by.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.by.service.WxUserService;

@Controller
public class LoginController {
	@Autowired
	private WxUserService wxUserService;

	@RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
	@ResponseBody
	public String wxlogin(String code) {
		return wxUserService.wxlogin(code);
	}

	// 获取微信步数
	// @RequestMapping(value="/runData")
	// @ResponseBody
	// public JSONObject decryptTest(String encryptedData,String iv) {
	// JSONObject data = JSONObject.parseObject(WXCore.decrypt(APPID, encryptedData,
	// sessionKey, iv));
	// System.out.println(data);
	// return data;
	// }

}
