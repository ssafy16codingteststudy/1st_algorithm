import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String [] s = br.readLine().split(" ");
        int [] arr = new int[N];
        int [] dp = new int[N];

        dp[0] = 1;
        int max;
        int answer = 1;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        for (int i = 1; i < N; i++) {
            max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i] && max < dp[j]) max = dp[j]; 
            }
            dp[i] = max + 1;
            if (dp[i] > answer) answer = dp[i];
        }

        System.out.println(answer);
    }
}