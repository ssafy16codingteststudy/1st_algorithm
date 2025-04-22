package week11.건휘;

import java.io.*;
import java.util.*;

public class BOJ1107_리모컨 {

	static int n, m;
	static boolean[] broken = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		if(m > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				int button = Integer.parseInt(st.nextToken());
				broken[button] = true;
			}
		}
		
		int result = Math.abs(n - 100); // +, - 만 사용한 경우
		
		for(int i = 0; i <= 999999; i++) {
			int len = check(i);
			if(len > 0) {	// 번호를 누를 수 있다면,
				int press = len + Math.abs(i - n);	// 숫자 누르기 + 이동 횟수
				result = Math.min(result, press);
			}
		}
		
		System.out.println(result);
	}
	
	static int check(int num) {		// 숫자 버튼으로 해당 번호 입력 가능하면 자릿수 리턴, 아니면 0
		if(num == 0) {
			return broken[0] ? 0 : 1;
		}
		int len = 0;
		while(num > 0) {
			if(broken[num % 10]) {
				return 0;
			}
			len++;
			num /= 10;
		}
		return len;
	}
}