import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static long B;

	private static int[][] arr;

	private static Map<Long, int[][]> map = new HashMap<>();

	private static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		arr = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 왜인지 모르겠는데 여기에서 % 연산을 하지 않으면 틀림
				// 아마 1000을 입력 받았을 때 문제인 것 같기는 함
				arr[i][j] %= 1000;
			}
		}

		map.put(1L, arr);
		int[][] result = dnc(B);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	private static int[][] dnc(long power) {
		// 기존에 계산했던 제곱수의 경우 바로 반환
		if (map.containsKey(power)) {
			return map.get(power);
		}

		// 5 => (2 + 3) => (1 + 1) + (1 + 2) => (1 + 1) + (1 + (1 + 1)) 로 분할 가능
		int[][] half = dnc(power / 2);
		int[][] rem = dnc(power - (power / 2));

		// 행렬 구하는 식
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					result[i][j] += half[i][k] * rem[k][j];
					result[i][j] %= 1000;
				}
			}
		}

		map.put(power, result);
		return result;
	}

}
