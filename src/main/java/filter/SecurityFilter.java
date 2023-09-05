package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.SecurityContextHolder;
import utils.ResponseUtil;

@WebFilter({"*"})
public class SecurityFilter extends HttpFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String rootPath = "/servlet_study_jiwoo";
		String[] antMatchers = {"/auth"}; // 인증이 필요 없는 path들
		
		String uri = req.getRequestURI();
		
		// 인증이 필요 없는 경우
		for(String antiMatcher : antMatchers) {
			if(uri.startsWith(rootPath + antiMatcher)) {
				chain.doFilter(request, response); // 필터 체인을 계속 진행합니다.
				return;
			}
		}
		
		String token = req.getHeader("Authorization"); // HTTP 요청의 헤더에서 Authorization 토큰을 가져옵니다.
		
		System.out.println(token);
		
		// equalsIgnoreCase : 대소문자 상관없음
		// HTTP 메서드가 "options"가 아니고, 토큰이 인증되지 않은 경우
		// 401 Unauthorized 응답을 생성하고 "인증 실패" 메시지를 설정합니다.
		if(!req.getMethod().equalsIgnoreCase("options") && !SecurityContextHolder.isAuthenticated(token)) { 
			ResponseUtil.response(resp).of(401).body("인증 실패");
			return;
		}
		
		chain.doFilter(request, response);
		
	}
}
