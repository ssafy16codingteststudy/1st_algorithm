import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static int T;
	private static int F;
	private static Map<String, String> map;
	private static Map<String, Integer> size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			F = Integer.parseInt(st.nextToken());

			map = new HashMap<>();
			size = new HashMap<>();
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());

				String t1 = st.nextToken();
				String t2 = st.nextToken();

				if (!map.containsKey(t1)) {
					map.put(t1, t1);
					size.put(t1, 1);
				}
				if (!map.containsKey(t2)) {
					map.put(t2, t2);
					size.put(t2, 1);
				}

				union(t1, t2);

				String root = find(t1);
				sb.append(size.get(root)).append('\n');
			}
		}
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static boolean union(String a, String b) {
		String rootA = find(a);
		String rootB = find(b);

		if (!rootA.equals(rootB)) {
			map.put(rootA, rootB);
			size.put(rootB, size.get(rootA) + size.get(rootB));
			return true;
		}

		return false;
	}

	private static String find(String a) {
		if (a.equals(map.get(a)))
			return a;

		map.put(a, find(map.get(a)));
		return map.get(a);
	}

}
