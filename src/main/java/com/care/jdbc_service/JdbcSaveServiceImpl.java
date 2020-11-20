package com.care.jdbc_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.jdbc_dao.JdbcDAO;

public class JdbcSaveServiceImpl  implements JdbcService{
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		// controller에서 model에 설정한 속성인 request를 받아올려면 맵으로 받아야 한다.
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		// save_view.jsp에서 입력한 파라미터 값이 여기에 저장되어 있음
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		JdbcDAO dao = new JdbcDAO();
		dao.save(id, name);
	}

}
