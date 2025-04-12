import java.io.*;
import java.util.*;

public class Main {

	private static String S;
	private static int K;
	private static int[] pi;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		S = st.nextToken();
		K = Integer.parseInt(st.nextToken());

		pi = new int[S.length()];
		for (int i = 1, j = 0; i < S.length(); i++) {
			while (j > 0 && S.charAt(i) != S.charAt(j)) {
				j = pi[j - 1];
			}

			if (S.charAt(i) == S.charAt(j)) {
				pi[i] = ++j;
			}
		}

    // 밑에 주석 추가했더니 12ms 줄었다..?
    
		// 기존에는 패턴 길이 중에 가장 큰 값 max를 구해서
		// S.length() - pi[S.length() - 1] 이 부분에 S.length() - max로 했었음
		// 하지만, 문자열 마지막에 이어서 붙여야하기 때문에 마지막 값을 구해야 함

		// +) 문자열의 길이가 50만, 문자가 등장하는 수가 100만이기 때문에 long 범위로 봐야함
		// +) 그거 처리해주기 위해서 K-1L로 했음
		sb.append(S.length() + (K - 1L) * (S.length() - pi[S.length() - 1]));

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
