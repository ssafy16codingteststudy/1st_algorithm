package 용범;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ31230_모비스터디 {

    static class Node implements Comparable<Node> {

        int v;
        long dist;

        Node(int v, long dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist); // 거리 기준으로 오름차순 정렬
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, A, B, a, b, c;
    static boolean[] visited;
    static ArrayList<Map<Integer, Integer>> vertex;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        dijkstra(); // 최단 경로 추적

        // 출력 부분
        sb.append(set.size()).append("\n");
        ArrayList<Integer> lst = new ArrayList<>(set);
        Collections.sort(lst);

        for (Integer num : lst)
            sb.append(num).append(" ");

        System.out.println(sb.toString());
    }

    private static void dijkstra() {

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[A] = 0; // 시작점 초기화

        Map<Integer, ArrayList<Integer>> prev = new HashMap<>();
        for (int i = 0; i <= N; i++)
            prev.put(i, new ArrayList<>());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(A, 0));

        while (!pq.isEmpty()) {

            Node cur = pq.poll();
            if (dist[cur.v] != cur.dist)
                continue;

            for (Map.Entry<Integer, Integer> nxt : vertex.get(cur.v).entrySet()) {
                int nxtV = nxt.getKey();
                long newDist = dist[cur.v] + nxt.getValue();

                if (newDist < dist[nxtV]) {
                    dist[nxtV] = newDist;
                    prev.get(nxtV).clear(); // 기존 경로 삭제
                    prev.get(nxtV).add(cur.v); // 단축된 경로 업데이트
                    pq.offer(new Node(nxtV, newDist));
                } else if (newDist == dist[nxtV]) {
                    prev.get(nxtV).add(cur.v); // 기존 경로 유지
                }
            }
        }

        set.add(B);
        visited[B] = true; // 방문 처리
        dfs(B, prev); // B 정점을 시작으로 경로 역추적
    }

    private static void dfs(int curr, Map<Integer, ArrayList<Integer>> prev) {

        for (Integer nxtV : prev.get(curr)) {
            if (!visited[nxtV]) {
                visited[nxtV] = true; // 방문 처리
                set.add(nxtV);
                dfs(nxtV, prev);
            }
        }

    }

    private static void init() throws IOException {

        // 입력 부분
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        vertex = new ArrayList<>();
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++)
            vertex.add(new HashMap<Integer, Integer>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            vertex.get(a).put(b, c);
            vertex.get(b).put(a, c);
        }

        br.close();
    }
}
