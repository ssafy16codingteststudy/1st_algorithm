import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	private static long N;
	private static int P;
	private static int Q;

	// 주의해야 할 점은 N이 10^12이기 때문에 Long으로 해야 한다.
	private static Map<Long, Long> dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		dp = new HashMap<>();

		dp.put(0L, 1L);

		sb.append(dpFunction(N));
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	// 탑다운 방식으로 해결
	private static long dpFunction(long cur) {
		if (dp.containsKey(cur))
			return dp.get(cur);

		dp.put(cur, dpFunction(cur / P) + dpFunction(cur / Q));
		return dp.get(cur);
	}

}