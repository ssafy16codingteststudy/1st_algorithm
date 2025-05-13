import java.util.*;

// s -> a -> b
// s -> b -> a
// s -> a, s -> b
class Solution {
    
    private static final int INF = 20_000_000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] distance = new int[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = INF;
                }
            }
        }
        
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            distance[u][v] = cost;
            distance[v][u] = cost;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            int total = distance[s][k] + distance[k][a] + distance[k][b];
            answer = Math.min(answer, total);
        }
        
        return answer;
    }
}
