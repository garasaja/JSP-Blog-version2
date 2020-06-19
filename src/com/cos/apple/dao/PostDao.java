package com.cos.apple.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.apple.db.DBConn;
import com.cos.apple.model.Member;
import com.cos.apple.model.Post;

public class PostDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<Post> 글목록() {
		try {
			String sql = "SELECT * FROM post ORDER BY id DESC";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			List<Post> posts = new ArrayList<>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setMemberId(rs.getInt("memberId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setCreateDate(rs.getTimestamp("createDate"));
				posts.add(post);
			}
			return posts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int 글쓰기(int memberId,String title, String content) {
		try {
			String sql = "INSERT INTO post (id,memberid,title,content,createdate) VALUES(post_seq.nextval,?,?,?,sysdate)";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int 회원가입(String username, String password, String email) {
		try {
			String sql = "INSERT INTO member(id, username,password, email, createDate) VALUES(member_seq.nextval,?,?,?,sysdate)";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	
}
