package 용범;

import java.io.*;
import java.util.*;

public class BOJ1967_트리의지름 {

    static class Node implements Comparable<Node> {
        int v, dist;

        Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist; // 거리 순으로 오름차순 정렬
        }
    }

    static int n, v1, v2, cost, newDist, idx;
    static ArrayList<ArrayList<int[]>> vertex;
    static int[] dist, info;
    static PriorityQueue<Node> pq;


    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        info = dijkstra(1);
        idx = getLongDistIdx(info);
        info = dijkstra(idx);
        idx = getLongDistIdx(info);

        System.out.println(info[idx]);
    }

    private static int[] dijkstra(int s) {


        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 초기화
        dist[s] = 0; // 시작 위치 초기화

        pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (dist[cur.v] != cur.dist)
                continue;

            for (int[] v : vertex.get(cur.v)) {
                newDist = dist[cur.v] + v[1];
                // 거리 갱신이 가능하다면 -> 정보 갱신
                if (newDist < dist[v[0]]) {
                    dist[v[0]] = newDist;
                    pq.offer(new Node(v[0], newDist));
                }
            }
        }

        return dist;
    }

    private static int getLongDistIdx(int[] info) {
        int MAX = 0;
        int pos = 0;
        for (int i = 1; i < info.length; i++) {
            if (MAX < info[i] && info[i] != Integer.MAX_VALUE) {
                MAX = info[i];
                pos = i;
            }
        }
        return pos;
    }

    private static void init() throws IOException {

        // 입력 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        vertex = new ArrayList<>();
        for (int i = 0; i < n + 1; i++)
            vertex.add(new ArrayList<int[]>());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            // 양방향 저장
            vertex.get(v1).add(new int[]{v2, cost});
            vertex.get(v2).add(new int[]{v1, cost});
        }

        br.close();
    }
}
