import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            arr = new long[n + 1];
            try {
                arr[1] = 1;
                arr[2] = 2;
                arr[3] = 4;
                for (int i = 4; i <= n; i++) {
                    // 나머지 연산은 분배 법칙이 성립한다
                    // (10+36+7) % 10 = result % 10
                    // (53)%10 = 3
                    // 10%10 + 36%10 + 7%10 = result % 10
                    // (0 + 6 + 7)%10 = 13%10 = 3
                    arr[i] = (arr[i - 1] + arr[i - 2] + arr[i - 3]) % 1_000_000_009;
                }
                System.out.println(arr[n]);
            } catch (Exception e) {
                System.out.println(arr[n]);
            }
        }
    }
}
