import java.util.*;
import java.io.*;

class Title {
	String t;
	int v;

	Title(String t, int v) {
		this.t = t;
		this.v = v;
	}
}

public class Main {

	private static int N, M;
	private static Title[] titles;
	private static Map<Integer, String> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		titles = new Title[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String t = st.nextToken();
			int v = Integer.parseInt(st.nextToken());

			if (map.containsKey(v))
				t = map.get(v);
			map.put(v, t);

			titles[i] = new Title(t, v);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());

			sb.append(titles[bs(q)].t).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static int bs(int q) {
		int start = 0, end = N - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (q < titles[mid].v) {
				end = mid - 1;
			} else if (q > titles[mid].v) {
				start = mid + 1;
			} else {
				return mid;
			}
		}

		return start;
	}
}
