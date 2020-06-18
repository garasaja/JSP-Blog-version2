package com.cos.apple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.apple.action.Action;
import com.cos.apple.action.post.PostListAction;

// http://localhost:8000/apple/member
@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. request utf-8 세팅 = web.xml
		// 2. response utf-8 세팅
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); //text/html은 디폴트값이지만 charset은 기본값 x
		
		String cmd = request.getParameter("cmd");
		Action action = router(cmd);
		action.execute(request,response);
	}

	private Action router(String cmd) {
		if (cmd.equals("list")) {
			return new PostListAction();
		}
		return null;
	}
}
