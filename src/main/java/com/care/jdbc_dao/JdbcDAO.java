package com.care.jdbc_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.care.jdbc_dto.JdbcDTO;

public class JdbcDAO {
	// jdbc:oracle:thin:@210.108.48.214:1521:xe > 강사님 DB
	// system/oracle, java/1234 DB뻑나면 사용 가능
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##jsp";
	private String password = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void delete(String id) {
		String sql = "delete from test_jdbc where id = ?";
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
	}
	
	public void modifySave(String id, String name) {
		String sql = "update test_jdbc set name = ? where id = ?";
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
	}
	
	public JdbcDTO modify(String id) {
		String sql = "select * from test_jdbc where id = ?";
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
	}
	
	public void save(String id, String name) {
		String sql = "insert into test_jdbc(id, name) values(?, ?)";
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
	}
	
	public ArrayList<JdbcDTO> list(){
		String sql = "select * from test_jdbc";
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
	}
	
	public JdbcDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
