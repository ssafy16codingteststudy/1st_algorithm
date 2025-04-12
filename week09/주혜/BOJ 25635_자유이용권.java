import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		long max = 0;
		long min = Long.MAX_VALUE;
		long total = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			long a = Long.parseLong(st.nextToken());
			total += a;
			max = Math.max(max, a);
			min = Math.min(min, a);
		}
		
		total -= max;
		
		long answer = 0;
		
		if (N == 1) {
			answer = 1;
		}
		
		else if (max == min) {
			answer += max * N;
		}
		
		else if (total + 1 >= max) {
			answer += total + max;
		}
		else {
			answer += total*2 + 1;
		}
		
		System.out.println(answer);
	}

}
