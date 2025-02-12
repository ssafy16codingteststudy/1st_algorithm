package 예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4485_녹색옷입은애가젤다지 {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int n;
    private static int[][] map;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int round = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            map = new int[n][n];
            dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            int answer = dijkstra();

            sb.append("Problem ")
                    .append(round)
                    .append(": ")
                    .append(answer)
                    .append("\n");

            round++;
        }

        System.out.println(sb);
    }

    private static int dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        dist[0][0] = map[0][0];
        pq.add(new int[]{map[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int cost = node[0];
            int x = node[1];
            int y = node[2];

            if (cost > dist[x][y]) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isInRange(nx, ny)) {
                    continue;
                }

                int newCost = cost + map[nx][ny];
                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;
                    pq.add(new int[]{newCost, nx, ny});
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
