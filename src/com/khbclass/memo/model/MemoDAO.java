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
			                         //  ↑ 테이블 이름
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
			// DB커넥션
			Connection conn   = ds.getConnection();
			// 쿼리
			String sql = "insert into memo(content,n_date,user_name,file)";
			                       //  ↑ 테이블(컬럼명...n)
			sql += "values(?,?,?,?)";
			// 쿼리를 스테이트먼트에 담는다
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 쿼리에 값을 세팅한다
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getN_date());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setString(4, vo.getFile());
			
			// 쿼리를 실행한다
			pstmt.executeUpdate();
			// executeQuery = 값을 조회
			// executeUpdate = 값을 입력, 수정, 삭제
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}
}
