package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;


@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());
		Map<String, String> responseData = new HashMap<>();
		
		for(User user : UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username")) 
				&& Objects.equals(user.getPassword(), signinUser.get("password"))) {
				
				String token = UUID.randomUUID().toString(); // 로그인 될 때 마다 새로운 토큰을 만듬
				//UUID 랜덤한 키값, 임시적으로 토큰을 만듬, 암호화 키값
				SecurityContextHolder.AddAuth(new Authentication(user, token));
				responseData.put("token", token);
				break;
			}
		}
		
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(responseData)); // Json으로 바꿔서 출력
	}


}
