import java.io.*;
import java.util.*;

class Problem implements Comparable<Problem> {
	int id;
	int difficulty;

	public Problem(int id, int difficulty) {
		super();
		this.id = id;
		this.difficulty = difficulty;
	}

	@Override
	public int compareTo(Problem o) {
		if (this.difficulty == o.difficulty)
			return o.id - this.id;

		return o.difficulty - this.difficulty;
	}
}

// 문제를 읽어보면, 너무 익숙한 자료구조가 떠오른다. => TreeSet
// 사실 조건에 따라서 조금 복잡해질 수 있었는데, 다행히 recommend 함수에서 꼬이지 않았다.
// 매우매우 쉬운 B형 문제 느낌이다. ID를 관리하고 add와 remove 쿼리가 존재한다.
/*
현재 문제의 recommend 쿼리의 조건

x가 1인 경우 추천 문제 리스트에서 <가장 어려운 문제의 번호를 출력한다.
만약 가장 어려운 문제가 여러 개라면 문제 번호가 큰 것>으로 출력한다.

x가 -1인 경우 추천 문제 리스트에서 <가장 쉬운 문제의 번호를 출력한다.
만약 가장 쉬운 문제가 여러 개라면 문제 번호가 작은 것>으로 출력한다.

===

recommend 함수에서 꼬였을 때의 문제 => SWEA - [Pro] AI 로봇 문제 => 힙 두개를 구현해서 풀이한다.

① 높은 지능 우선 방식 : 센터에 있는 로봇들 중 <지능지수가 가장 높은 로봇부터 차례대로 투입한다.
만약, 지능지수가 같은 경우 고유 번호가 가장 작은 로봇>을 투입한다.

② 낮은 지능 우선 방식 : 센터에 있는 로봇들 중 <지능지수가 가장 낮은 로봇부터 차례대로 투입한다.
만약, 지능지수가 같은 경우 고유 번호가 가장 작은 로봇>을 투입한다.
*/
public class Main {

	private static int N, M;

	private static TreeSet<Problem> set = new TreeSet<>();
	private static Map<Integer, Problem> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			Problem problem = new Problem(P, L);
			set.add(problem);
			map.put(P, problem);
		}

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			String command = st.nextToken();
			if (command.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());

				if (x == 1) {
					sb.append(set.first().id).append('\n');
				} else if (x == -1) {
					sb.append(set.last().id).append('\n');
				}
			} else if (command.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());

				Problem problem = new Problem(P, L);
				set.add(problem);
				map.put(P, problem);
			} else if (command.equals("solved")) {
				int P = Integer.parseInt(st.nextToken());

				Problem problem = map.remove(P);
				set.remove(problem);
			}
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
