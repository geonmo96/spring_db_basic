package com.care.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.jdbc_service.*;

@Controller
@RequestMapping("jdbc_test")
public class MemberController {
	private JdbcService jdbc;
	
	@RequestMapping("modifySave")
	public String modifySave(@RequestParam("id") String id, @RequestParam("name") String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		jdbc = new JdbcModifySaveServiceImpl();
		jdbc.execute(model);
		return "redirect:content";
	}
	
	@RequestMapping("modify")
	public String modify(Model model, HttpServletRequest request) { // @RequestParam("id") String id
		model.addAttribute("request", request);
		jdbc = new JdbcModifyServiceImpl();
		jdbc.execute(model);
		return "/jdbc_test/modify";
	}
	
	@RequestMapping("content")
	public String content(Model model) {
		jdbc = new JdbcContentServiceImpl();
		jdbc.execute(model);
		return "/jdbc_test/content";
	}
	
	@RequestMapping("save_view")
	public String saveView() {
		
		return "/jdbc_test/save_view";
	}
	
	@RequestMapping("save")
	public String save(Model model, HttpServletRequest request) {
		model.addAttribute("request", request); // request에 파라미터 값이 저장되어 있음, 매개변수를 model하나만 쓰기위해서 이렇게 사용
		jdbc = new JdbcSaveServiceImpl();
		jdbc.execute(model);
		return "redirect:content";
		// return "jdbc_test/content"
		// 위와 같이 사용하면 안된다.
		// 페이지를 바로 이동시키는 기능이기 때문에 content()에서 실행되어야 할 dao.list()가 실행이 되지 않기 때문이다. > content에 아무런 데이터가 없음
		// 그래서 redirect:content의 의미는 위의 content()mapping을 실행하는 것이다.
	}
}
