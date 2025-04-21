import java.io.*;
import java.util.*;

class Trie {
	Trie[] children = new Trie[10];
	boolean isComplete = false;

	boolean add(String input, int index) {
		if (index == input.length()) {
			isComplete = true;
			return false;
		}

		int cur = input.charAt(index) - '0';
		if (children[cur] == null) {
			children[cur] = new Trie();
		} else if (children[cur].isComplete) {
			return true;
		}
		return children[cur].add(input, index + 1);

	}

}

public class Main {

	private static int T;
	private static Trie head;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			head = new Trie();

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			List<String> inputs = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				inputs.add(br.readLine());
			}
			Collections.sort(inputs);

			boolean check = false;
			for (int i = 0; i < inputs.size() && !check; i++) {

				check |= head.add(inputs.get(i), 0);
			}

			String answer = "YES";
			if (check)
				answer = "NO";

			sb.append(answer).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
