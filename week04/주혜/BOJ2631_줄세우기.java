import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] student = new int[N];
        
        for (int i = 0; i < N; i++) {
            student[i] = Integer.parseInt(br.readLine());
        }

        int [] dp = new int[N];
        dp[0] = 1;
        int max;
        int answer = 1;

        for (int i = 1; i < N; i++) {
            max = 0;
            for (int j = 0; j < i; j++) {
                if (student[j] < student[i] && max < dp[j]) max = dp[j]; 
            }
            dp[i] = max + 1;
            if (dp[i] > answer) answer = dp[i];
        }

        System.out.println(N - answer);
    }
}