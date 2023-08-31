package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// EndPoint(요청 주소) 적는 곳
@WebServlet("/study/1")
public class Study1 extends HttpServlet { //extends HttpServlet 상속을 시키면 servlet class 가됨
	
	// 각각의 메소드에는 하나씩 만 만들 수 있다.
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpServletRequest req (요청), HttpServletResponse resp (응답) 
		// 모든 웹 통신은 이녀석들로 이루어짐 , 요청과 응답에 대한 객체(정보)들을 다 가지고 있음
		
		System.out.println("post 요청");
	}
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("get 요청");
		}

}
