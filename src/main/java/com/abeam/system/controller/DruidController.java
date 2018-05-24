package com.abeam.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据源控制器
 * 
 * @author 拍财富
 * 
 */
@Controller
@RequestMapping("/druidController")
public class DruidController extends BaseController {

	/**
	 * 转向到数据源监控页面
	 * 
	 * @return
	 */
	@RequestMapping("/druid")
	public String druid() {
		return "redirect:/druid/index.html";
	}

}
