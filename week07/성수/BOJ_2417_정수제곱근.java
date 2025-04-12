import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BigInteger n; // 제곱근을 찾을 숫자. 0~2^63
    static BigInteger start = BigInteger.ZERO; // 이분탐색 시작점.
    static BigInteger end; // 마지막점.
    static BigInteger sq; // 제곱값 저장.
    static BigInteger mid; // 중간점.

    public static void main(String[] args) throws IOException {
        String num = br.readLine();
        n = new BigInteger(num);
        end = n;

        System.out.print(solve(start, end));
    }

    public static BigInteger solve(BigInteger s, BigInteger e) {
        if (s.compareTo(e) == 0)
            return s;

        mid = s.add(e).divide(BigInteger.TWO);
        sq = mid.multiply(mid);

        int cmp = sq.compareTo(n);
        if (cmp == 0) {
            return mid;
        } else if (cmp < 0) {
            return solve(mid.add(BigInteger.ONE), e);
        } else {
            return solve(s, mid);
        }
    }
}
