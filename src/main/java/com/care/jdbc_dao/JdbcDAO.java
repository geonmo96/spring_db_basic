package com.care.jdbc_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.care.controller.MemberController;
import com.care.jdbc_dto.JdbcDTO;

public class JdbcDAO {
	// jdbc:oracle:thin:@210.108.48.214:1521:xe > 강사님 DB
	// system/oracle, java/1234 DB뻑나면 사용 가능
	/*
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##jsp";
	private String password = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	*/
	private JdbcTemplate template;
	
	public int count() {
		String sql = "select count(*) from test_jdbc";
		return template.queryForObject(sql, Integer.class);
	}
	
	public void delete(String id) {
		String sql = "delete from test_jdbc where id =" + id;
		template.update(sql);
		/*
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public void modifySave(String id, String name) {
		String sql = "update test_jdbc set name = ? where id = ?";
		template.update(sql, ps->{
			ps.setString(1, name);
			ps.setString(2, id);
		});
		// 람다식으로 인터페이스 대체한 것
		
		/*
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public JdbcDTO modify(final String id) {
		String sql = "select * from test_jdbc where id =" + id;
		JdbcDTO dto = null;
		dto = template.queryForObject(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		// 하나의 데이터를 반환할 때는 template.queryForObject
		// 리스트, 배열 등 여러개를 반환할 때는 template.query
		// executeQuery의 역할을 함
		// executeUpdate는 template.update
		return dto;
		
		
		/*
		JdbcDTO dto = new JdbcDTO();
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
		*/
	}
	
	public void save(final String id, final String name) {
		String sql = "insert into test_jdbc(id, name) values(?, ?)";
		template.update(sql, new PreparedStatementSetter() { // sql의 변수 넣을 때
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, id);
				ps.setString(2, name);
				// id와 name이 에러인 이유 :
				// 이 변수가 중간에 수정되어 안정성이 위배된다. 
				// 입력받는 매개변수를 final로 수정해 안정성을 확보하면 된다.
			}
		});
		/*
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public ArrayList<JdbcDTO> list(){
		String sql = "select * from test_jdbc";
		ArrayList<JdbcDTO> list = null;
		try {
			list = (ArrayList<JdbcDTO>)template.query(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		/*
		ArrayList<JdbcDTO> list = new ArrayList<JdbcDTO>();
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JdbcDTO dto = new JdbcDTO();
				dto.setName(rs.getString("name"));
				dto.setId(rs.getInt("id"));
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		*/
	}
	
	public JdbcDAO() {
		this.template = MemberController.template;
		/*
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
}
