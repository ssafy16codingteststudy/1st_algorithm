import java.io.*;
import java.util.*;

class V implements Comparable<V> {
	int origValue;
	int unsignedValue;

	public V(int origValue, int unsignedValue) {
		this.origValue = origValue;
		this.unsignedValue = unsignedValue;
	}

	@Override
	public int compareTo(V o) {
		return this.unsignedValue - o.unsignedValue;
	}

}

public class Main {
	private static int N;
	private static V[] liquids;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		liquids = new V[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int origValue = Integer.parseInt(st.nextToken());
			liquids[i] = new V(origValue, Math.abs(origValue));
		}
		Arrays.sort(liquids);

		int answer = Integer.MAX_VALUE;
		int[] pair = new int[2];

		for (int i = 0; i < N - 1; i++) {
			int l1 = liquids[i].origValue;
			int l2 = liquids[i + 1].origValue;

			if (answer > Math.abs(l1 + l2)) {
				answer = Math.abs(l1 + l2);

				pair[0] = liquids[i].origValue;
				pair[1] = liquids[i + 1].origValue;
			}

			if (answer == 0)
				break;
		}

		Arrays.sort(pair);
		sb.append(pair[0]).append(' ').append(pair[1]);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}
}
