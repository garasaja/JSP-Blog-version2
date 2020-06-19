package com.cos.apple.action.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.apple.action.Action;
import com.cos.apple.dao.PostDao;

public class PostSaveProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); //인증 된사람 검증
		if(session.getAttribute("principal") == null) {
			response.sendRedirect("member/loginForm.jsp");
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		
		PostDao postDao = new PostDao();
		int result = postDao.글쓰기(memberId,title,content);
		
		if(result == 1) {
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("post/saveForm.jsp");
		}
	}
}
