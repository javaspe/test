package com.khbclass.memo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemoDAO {
	public ArrayList<MemoVO> getList(){
		ArrayList<MemoVO> list = new ArrayList<MemoVO>();
		try {
			Context    con    = new InitialContext();
			Context    envCon = (Context) con.lookup("java:comp/env");
			DataSource ds     = (DataSource) envCon.lookup("jdbc/MYSQL");
			Connection conn   = ds.getConnection();
			String sql = "select * from memo order by n_date desc";
			                         //  �� ���̺� �̸�
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				MemoVO vo = new MemoVO();
				vo.setContent(rs.getString("content"));
				vo.setNo(rs.getInt("num"));
				vo.setN_date(rs.getString("n_date"));
				vo.setFile(rs.getString("file"));
				vo.setUser_name(rs.getString("user_name"));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return list;
	}
	
	public void create(MemoVO vo){
		try {
			Context    con    = new InitialContext();
			Context    envCon = (Context) con.lookup("java:comp/env");
			DataSource ds     = (DataSource) envCon.lookup("jdbc/MYSQL");
			// DBĿ�ؼ�
			Connection conn   = ds.getConnection();
			// ����
			String sql = "insert into memo(content,n_date,user_name,file)";
			                       //  �� ���̺�(�÷���...n)
			sql += "values(?,?,?,?)";
			// ������ ������Ʈ��Ʈ�� ��´�
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ������ ���� �����Ѵ�
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getN_date());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setString(4, vo.getFile());
			
			// ������ �����Ѵ�
			pstmt.executeUpdate();
			// executeQuery = ���� ��ȸ
			// executeUpdate = ���� �Է�, ����, ����
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}
}
