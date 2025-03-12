import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1629_곱셈 {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());		
		long b = Long.parseLong(st.nextToken());		
		long c = Long.parseLong(st.nextToken());		

		long answer = 1;
		while(b > 0) {
            // 첫 번째 비트 값이 1이라면 제곱해왔던 a 값 저장
			if(b % 2 == 1) {
                // 중간 중간 넘치지 않게 c 로 나누어 줌
				answer = (answer * a) % c;
			}
			
            // a 를 제곱해 나아가며 
			a = (a * a) % c;
            // b 를 2로 나눔 -> 비트적 연산의 의미
            // ex) 10 -> 1010 -> a^8 * a^2
			b /= 2;
		}
		
		System.out.println(answer % c);
	}			
}
