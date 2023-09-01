package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

public class JsonParseUtil {
	
	// JSON 형식의 데이터를 InputStream에서 읽어와 Map<String, Object>으로 변환하는 메서드
	public static Map<String, Object> toMap(InputStream inputStream) {
		
		// JSON 데이터를 저장할 StringBuilder 객체 생성
		StringBuilder jsonData = new StringBuilder("");
		
		// BufferedReader 초기화
		BufferedReader bufferedReader = null;
		
		// inputStream이 null이면 null을 반환
		if(inputStream == null) {
			return null;
		}
		
		// BufferedReader를 생성하고 inputStream으로부터 데이터를 읽어옴
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		while(true) {
			
			String data;
			try {
				data = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return null; // 예외 발생 시 null 반환
			}
			if(data == null) {
				break;
			}
			jsonData.append(data); // 읽어온 데이터를 jsonData에 추가
			
		} 
		
		// Gson 라이브러리를 사용하여 JSON 문자열을 Map으로 변환하여 반환
		Gson gson = new Gson();
		return gson.fromJson(jsonData.toString(), Map.class);
		
	}
	
	// 객체를 JSON 형식의 문자열로 변환하는 메서드
	public static String toJson(Object object) {
		
		Gson gson = new Gson();
		
		return gson.toJson(object);
	}
}
