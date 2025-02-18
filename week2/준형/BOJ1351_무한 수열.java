import java.util.*;
import java.io.*;

public class Main {

	static long P;
	static long Q;

	static HashMap<Long, Long> dp = new HashMap<>();

	static long dfs(long n) {

		if(dp.containsKey(n)) {
			return dp.get(n);
		}

		long ret = dfs(n / P) + dfs(n / Q);
		dp.put(n, ret);

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());


		dp.put(0L, 1L);
		System.out.println(dfs(N));

	}
}