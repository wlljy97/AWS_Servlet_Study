package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	
//	private int statusCode;
//	private String body;
	
	// response 메서드를 호출하면 ResponseUtilBuildeer 객체를 반환하는 정적 메서드
	public static ResponseUtilBuildeer response(HttpServletResponse response) {
		return new ResponseUtilBuildeer(response);
	}
	
	// ResponseUtilBuildeer 클래스 정의
	public static class ResponseUtilBuildeer {
		private HttpServletResponse response;
		
		// ResponseUtilBuildeer 객체를 생성하는 생성자
		public ResponseUtilBuildeer(HttpServletResponse response) {
			this.response = response;
		}
		
		// HTTP 응답의 상태 코드를 설정하는 메서드
		public ResponseUtilBuildeer of(int statusCode) {
			response.setStatus(statusCode);
			return this;
		}
		
		// HTTP 응답의 본문(body)을 설정하는 메서드
		public void body(Object body) throws IOException {
			response.setContentType("/application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
		}
	}
}
