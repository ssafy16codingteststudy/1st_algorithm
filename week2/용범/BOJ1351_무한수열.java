package 용범;

import java.io.*;
import java.util.*;

public class BOJ1351_무한수열 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long N;
    static int P, Q;
    static Map<Long, Long> cache;

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        long ans = calculate(N);
        System.out.println(ans);
    }

    private static long calculate(long N) {

        if (N == 0) return 1;

        // 캐시에 값이 있다면 바로 해당 결과값 return
        if (cache.containsKey(N)) return cache.get(N);

        long res = calculate(N / P) + calculate(N / Q);
        cache.put(N, res); // 캐시 저장
        return res;
    }

    private static void init() throws IOException {

        // 입력 부분
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        cache = new HashMap<>();
        br.close();
    }
}
