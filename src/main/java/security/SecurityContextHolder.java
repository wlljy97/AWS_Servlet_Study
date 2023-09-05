package security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SecurityContextHolder {
	
	private static List<Authentication> authentications = new ArrayList<>(); // 인증된 정보
	
	public static void AddAuth(Authentication authentication) { // 로그인 되었을 때 인증코드 받기
		authentications.add(authentication);
	}
	
	public static Boolean isAuthenticated(String token) { //token을 받아서 인증이 되는지 안되는지 확인
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return true;
			}
		}
		return false;
	}
	
	public static Authentication findAuthenticationByToken(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
//				authentications.remove(authentication);
				return authentication;
			}
		}
		return null;
	}

	public static void removeAuth(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				authentications.remove(authentication);
				break;
			}
		}
	}
	
}
