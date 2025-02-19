import java.util.*;

class Main {
    static int N;
    static int K;
    static int[][] item;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        item = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            item[i][0] = sc.nextInt();
            item[i][1] = sc.nextInt();
        }

        int [][] answer = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j < item[i][0]) {
                    answer[i][j] = answer[i-1][j];
                } else {
                    answer[i][j] = Math.max(item[i][1] + answer[i-1][j-item[i][0]], answer[i-1][j]);
                }
            }
        }

        System.out.println(answer[N][K]);

        sc.close();
    }
}
