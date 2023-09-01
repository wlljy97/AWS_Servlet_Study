package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	
//	private int statusCode;
//	private String body;
	
	public static ResponseUtilBuildeer response(HttpServletResponse response) {
		return new ResponseUtilBuildeer(response);
	}
	
	public static class ResponseUtilBuildeer {
		private HttpServletResponse response;
		
		public ResponseUtilBuildeer(HttpServletResponse response) {
			this.response = response;
		}
		
		public ResponseUtilBuildeer of(int statusCode) {
			response.setStatus(statusCode);
			return this;
		}
		
		public void body(Object body) throws IOException {
			response.setContentType("/application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
		}
	}
}
