import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			List<List<Integer>> parentOf = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				parentOf.add(new ArrayList<>());
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				parentOf.get(B).add(A);
			}

			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			boolean[] visited = new boolean[N + 1];
			for (int i = node1;; i = parentOf.get(i).get(0)) {
				visited[i] = true;
				if (parentOf.get(i).size() == 0)
					break;
			}

			for (int i = node2;; i = parentOf.get(i).get(0)) {
				if (visited[i] == true) {
					bw.write(String.valueOf(i) + '\n');
					break;
				}
			}

		}

		bw.close();
	}
}
