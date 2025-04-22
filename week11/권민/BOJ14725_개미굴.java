import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Trie {
	TreeMap<String, Trie> children = new TreeMap<>();

	void add(List<String> inputs, int index) {
		if (index == inputs.size()) {
			return;
		}

		String input = inputs.get(index);
		if (!children.containsKey(input)) {
			Trie newChild = new Trie();
			children.put(input, newChild);
		}

		children.get(input).add(inputs, index + 1);
	}

	void find(StringBuilder sb, int index) {
		for (String key : children.keySet()) {
			Trie child = children.get(key);

			StringBuilder dashSb = new StringBuilder();
			for (int i = 0; i < index; i++) {
				dashSb.append("--");
			}

			sb.append(dashSb.toString()).append(key).append('\n');
			child.find(sb, index + 1);
		}
	}
}

public class Main {

	private static int N;
	private static Trie head;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		head = new Trie();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());

			List<String> inputs = new ArrayList<>();
			for (int j = 0; j < K; j++) {
				inputs.add(st.nextToken());
			}

			head.add(inputs, 0);
		}

		head.find(sb, 0);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
