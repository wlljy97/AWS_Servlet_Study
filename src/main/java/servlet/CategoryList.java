package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;


@WebServlet("/category") // URL 패턴 "/category"에 매핑
public class CategoryList extends HttpServlet { // CategoryList 클래스를 정의 , HttpServlet 클래스를 확장하여 HTTP 요청을 처리
	private static final long serialVersionUID = 1L;
	
	private String[] categoryArray = {
			"한식",
			"체험관광",
			"카페",
			"자연명소",
			"양식",
			"문화예술"
	};
	
	private class Feed { // 내부 클래스 Feed를 정의, 피드의 이름과 카테고리를 나타냄
		private String feedName;
		private String categoryName;
		
		public Feed(String feedName, String categoryName) { // 피드 객체 배열을 초기화
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedinfo() {
			return "feedName: " + feedName +", categoryName: " + categoryName + "\n";
		}
	}
	
	private Feed[] feedArray = {
		new Feed("1번피드", "한식"),
		new Feed("2번피드", "한식"),
		new Feed("3번피드", "한식"),
		new Feed("4번피드", "체험관광"),
		new Feed("5번피드", "체험관광"),
		new Feed("6번피드", "카페"),
		new Feed("7번피드", "자연명소"),
		new Feed("8번피드", "카페"),
		new Feed("9번피드", "자연명소"),
		new Feed("10번피드", "체험관광"),
		new Feed("11번피드", "문화예술"),
		new Feed("12번피드", "문화예술"),
		new Feed("13번피드", "카페")
		
	};
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 서블릿 GET 요청을 처리
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
		String categoryName = request.getParameter("categoryName");
		System.out.println(categoryName);
		
		// 요청된 카테고리가 존재하는지 확인
		if (!checkCategory(categoryName)) {
			response.setCharacterEncoding("UTF-8");
			response.setStatus(400); //400 오류를 나타냄
			response.getWriter().println("해당 카테고리는 존재하지 않는 카테고리입니다.");
			return;
		}
		
//		//	람다 전용 String
//		AtomicReference<String> responseData = new AtomicReference<String>(""); 
//		
//		findFeedByCategoryName(categoryName).forEach(feed -> {
//			responseData.set(responseData.get() + feed.getFeedinfo()); // 문자열을 합침
//		});
//		//
		
		Gson gson = new Gson();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // "application/json" 컨텐츠의 타입을 json으로 설정
		response.getWriter().println(gson.toJson(findFeedByCategoryName(categoryName)).toString());  // 응답 데이터 return값이 list
		// Feed 객체의 목록을 JSON 표현으로 변환
	}

	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return true;
			}
		}
			return false;
	}
	
	private List<Feed> findFeedByCategoryName(String categoryName) {
		List<Feed> feeds = new ArrayList<>();
		
		for(int i = 0; i < feedArray.length; i++) {
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
			}
		}
		
		return feeds;
	}
}







