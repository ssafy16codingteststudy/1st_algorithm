import java.io.*;
import java.util.*;

public class Main {

	private static int answer;

	private static int N, K;
	private static int[] arr, diff;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		diff = new int[N - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// N-1 개의 차이 그룹이 생김
		for (int i = 0; i < N - 1; i++) {
			diff[i] = arr[i + 1] - arr[i];
		}
		Arrays.sort(diff);
		
		// 헷갈렸던 부분
		// -> 5명으로 3그룹을 만든다고 할 때, (1,3,1), (1,2,2)의 경우가 가능함
		// -> 어떤 식으로 묶든 두 그룹을 묶을 때마다 전체 그룹의 수가 하나만 줄어들 뿐임 
		for (int i = 0; i < N - K; i++) {
			answer += diff[i];
		}

		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
