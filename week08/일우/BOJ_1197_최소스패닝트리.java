package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ1197 {
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

    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        // 정점은 1 ~ V 인접리스트
        ArrayList<Edge>[] edges = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            edges[i] = new ArrayList<Edge>();

        // 간선정보 양방향으로 추가
        for (int i = 0; i < E; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);

            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        //방문배열
        boolean[] visited = new boolean[V + 1];

        PriorityQueue<Edge> q = new PriorityQueue();

        // 1번부터 순차적으로 방문 최소 가중치로 우선순위큐에서 방문처리하기
        visited[1] = true;
        for (Edge e : edges[1]) {
            q.offer(e); // 1과 연결될 간선 전부 우선순위큐에 넣기
        }

        // 현재까지 방문한 정점
        long minWeight = 0;
        int count = 1;

        // 우선순위큐에서 가중치가 낮은 대로 간선들을 정점들을 탐색
        // count가 정점갯수랑 같아지면 MST를 완성한 거니 그만
        while (!q.isEmpty() && count < V) {
            Edge e = q.poll();
            if (visited[e.to]) continue;

            // 아직 체크 안한 간선이라면
            minWeight += e.weight; //가중치합
            visited[e.to] = true; //방문처리
            count++;

            // 큐에 다음 연결된 간선 넣기
            for (Edge nextE : edges[e.to]) {
                if (!visited[nextE.to]) q.offer(nextE);
            }
        }
        System.out.println(minWeight);
    }
}
