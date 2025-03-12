import java.io.*;
import java.util.*;

public class Main {

	private static long N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Long.parseLong(st.nextToken());

		long answer[][] = fib(N - 1);
		sb.append(answer[0][0]);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

  // B형 특강에서 배운 행렬을 통해 피보나치 수열 구하기를 적용
  // 관련 설명: https://ohgym.tistory.com/1
	private static long[][] fib(long cur) {
		if (cur == 0 || cur == 1) {
			return new long[][] { { 1, 1 }, { 1, 0 } };
		}

		long[][] temp = fib(cur / 2);

		if (cur % 2 == 0) {
			return calRC(temp, temp);
		} else {
			return calRC(temp, calRC(temp, new long[][] { { 1, 1 }, { 1, 0 } }));
		}
	}

	private static long[][] calRC(long[][] a, long[][] b) {
		long[][] arr = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					arr[i][j] += a[i][k] * b[k][j];
				}
        // 합을 구한 다음에 나머지 연산해주는 거 주의... ^^
				arr[i][j] %= 1_000_000_007;
			}
		}

		return arr;
	}

}
