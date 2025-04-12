import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Long, Long> map = new HashMap<>();
    static int p, q;
    static long n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Long.parseLong(input[0]);
        p = Integer.parseInt(input[1]);
        q = Integer.parseInt(input[2]);

        /**
         * 1
         * 1 2 1
         * 1 3 3 1
         * 1 4 6 4 1
         */

        /**
         * A[N] = A[N/P] + A[N/Q] =A[N/P^2]+A[N/Q^2]+2*A[N/P*Q]
         */
        System.out.println(dp(n));

    }

    static long dp(long num) {
        if (num == 0)
            return 1;
        if (map.containsKey(num))
            return map.get(num);
        map.put(num, dp(num / p) + dp(num / q));
        return map.get(num);
    }
}
