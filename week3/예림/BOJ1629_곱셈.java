package week3.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629_곱셈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        System.out.println(solution(a, b, c));
    }

    private static long solution(long a, long b, long c) {
        if (b == 0) {
            return 1;
        }

        long half = solution(a, b / 2, c);
        long square = (half * half) % c;

        if (b % 2 == 0) {
            return square;
        } else {
            return (square * (a % c)) % c;
        }
    }
}
