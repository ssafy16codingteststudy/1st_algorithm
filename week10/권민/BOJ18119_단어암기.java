import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int curAlphabet;
	private static int[] strBit;
	private static String[] str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		curAlphabet = (1 << 26) - 1;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		strBit = new int[N];
		str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();

			for (int j = 0; j < str[i].length(); j++) {
				strBit[i] |= (1 << str[i].charAt(j) - 'a');
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int query = Integer.parseInt(st.nextToken());
			int alphabet = (1 << (st.nextToken().charAt(0) - 'a'));
			if (query == 1) {
				curAlphabet -= alphabet;
			} else if (query == 2) {
				curAlphabet += alphabet;
			}

			// 이전에는 이 위치에서 문자열의 비트를 하나하나 비교했다면,
			// 지금은 문자열을 입력 받을 때 비트를 미리 기록해두고, 전체 비트에 대해서 and 연산을 한다.
			int answer = 0;
			for (int j = 0; j < N; j++) {
				if ((strBit[j] & curAlphabet) == strBit[j]) {
					answer++;
				}
			}

			sb.append(answer).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}


/*
제출하기 전에 시간복잡도 계산해보니, 시간초과 날 것 같아서 코드 수정했음

import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int curAlphabet;
	private static String[] str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 26; i++) {
			curAlphabet <<= 1;
			curAlphabet |= 1;
		}

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int query = Integer.parseInt(st.nextToken());
			int alphabet = (1 << st.nextToken().charAt(0) - 'a');
			if (query == 1) {
				curAlphabet -= alphabet;
			} else if (query == 2) {
				curAlphabet += alphabet;
			}

			int answer = 0;
			for (String next : str) {
				int count = 0;
				for (int j = 0; j < next.length(); j++) {
					int compareBit = (1 << next.charAt(j) - 'a');

					if ((curAlphabet | compareBit) == compareBit) {
						count++;
					}
				}

				if (count == next.length()) {
					answer++;
				}
			}

			sb.append(answer).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}
}
 */
