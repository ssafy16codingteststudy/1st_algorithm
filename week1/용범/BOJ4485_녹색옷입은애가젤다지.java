package 용범;

import java.io.*;
import java.util.*;

public class BOJ4485_녹색옷입은애가젤다지 {

    static class Node implements Comparable<Node> {
        int cost, y, x;

        Node(int cost, int y, int x) {
            this.cost = cost;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // cost 기준으로 오름차순 정렬
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, tc = 1, ans, nxtY, nxtX, newDist, INF = Integer.MAX_VALUE;
    static int[][] map, dist;
    static int[] dys = {-1, 1, 0, 0}, dxs = {0, 0, -1, 1};
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                br.close();
                break;
            }

            input(N);
            solve();

            tc++;
        }

        System.out.println(sb.toString());
    }

    private static void solve() {

        ans = dijkstra();
        sb.append(String.format("Problem %d: %d\n", tc, ans));
    }

    private static int dijkstra() {

        dist = new int[N][N];
        // 거리 배열 초기화
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                dist[i][j] = INF;

        dist[0][0] = map[0][0]; // 시작점 초기화
        pq = new PriorityQueue<Node>();
        pq.offer(new Node(map[0][0], 0, 0)); // 시작 노드 셋팅

        // 다익스트라 핵심 로직 구현
        while (!pq.isEmpty()) {

            Node curr = pq.poll();

            // 이미 갱신된 노드 -> continue
            if (curr.cost != dist[curr.y][curr.x])
                continue;

            for (int i = 0; i < 4; i++) {
                nxtY = curr.y + dys[i];
                nxtX = curr.x + dxs[i];
                if (!inRange(nxtY, nxtX))
                    continue;

                newDist = curr.cost + map[nxtY][nxtX];
                // 방문이 가능하고, 거리 갱신이 가능한 경우 -> pq 삽입
                if (newDist < dist[nxtY][nxtX]) {
                    dist[nxtY][nxtX] = newDist;
                    pq.offer(new Node(newDist, nxtY, nxtX));
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    private static void input(int N) throws IOException {

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

}
