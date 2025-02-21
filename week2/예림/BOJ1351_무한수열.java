package week2.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1351_무한수열 {

    private static long P, Q;
    private static Map<Long, Long> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        map = new HashMap<>();
        map.put(0L, 1L);

        calculate(N);

        System.out.println(map.get(N));
    }

    private static long calculate(long i) {
        if (map.containsKey(i)) {
            return map.get(i);
        }
        long result = calculate(i / P) + calculate(i / Q);
        map.put(i, result);
        return result;
    }
}
