package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16562_PRIM {
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N, M, K;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        price = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        // 0번: 가상 노드, 1~N번: 실제 학생 노드
        ArrayList<Edge>[] edges = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        // 가상 노드 0와 학생 i 간의 간선 추가 (비용: 학생의 친구비)
        for (int i = 1; i <= N; i++) {
            edges[0].add(new Edge(i, price[i]));
            edges[i].add(new Edge(0, price[i]));
        }

        // 친구 관계 입력: 친구 관계 간선은 비용 0으로 추가
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, 0));
            edges[b].add(new Edge(a, 0));
        }

        // Prim 알고리즘을 이용하여 MST 구성
        boolean[] visited = new boolean[N + 1]; // 0 ~ N번 노드
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 가상 노드 0부터 시작
        visited[0] = true;
        for (Edge e : edges[0]) {
            pq.offer(e);
        }

        long totalCost = 0;
        int count = 1; // 가상 노드 0을 방문한 상태

        while (!pq.isEmpty() && count < (N + 1)) {
            Edge cur = pq.poll();
            if (visited[cur.to]) continue;

            visited[cur.to] = true;
            totalCost += cur.weight;
            count++;

            for (Edge next : edges[cur.to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        // 모든 노드를 MST에 포함시켰는지 확인 후, 가진 돈과 비교
        if (count == N + 1 && totalCost <= K) {
            System.out.println(totalCost);
        } else {
            System.out.println("Oh no");
        }
    }
}
