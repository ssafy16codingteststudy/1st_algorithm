import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1) 불필요 간선 표시용
        boolean[][] redundant = new boolean[N][N];

        // 2) Floyd‐Warshall 스타일로 검사
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (i == k) continue;
                for (int j = 0; j < N; j++) {
                    if (j == k || i == j) continue;
                    // 불가능한 경우
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                    // 같으면 (i,j) 간선은 불필요
                    // 왜냐하면 (i,k)와 (k,j)로 대체가 가능하기 때문
                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        redundant[i][j] = true;
                    }
                }
            }
        }

        // 3) 필요한 간선만 합산 (i<j)
        long answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (!redundant[i][j]) {
                    answer += dist[i][j];
                }
            }
        }

        System.out.println(answer);
    }
}
