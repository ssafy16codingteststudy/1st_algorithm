package week12.은성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1238_파티 {
    public static final int INF = 1_000_000_000;
    public static int N, M, X;
    /**
     * 1238 파티
     * 파티 장소에서 모든 집으로의 최단거리 구한 뒤,
     * 각 학생들의 파티 장소 최단거리를 더해 최솟값 비교
     * 그냥 N + 1 번 다익스트라 돌리는거임 ㅇㅇ
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        X = Integer.parseInt(split[2]);

        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF); // 최단 경로 초기화

        List<Integer[]>[] lists = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lists[a].add(new Integer[]{b, cost});
        }

        // X 에서 각 학생들의 최단거리 구하기
        dijkstra(lists, distance, X, false);

        int answer = -1;
        for (int i = 1; i < N + 1; i++) {
            if (i == X) {
                continue;
            }
            int[] studentDist = new int[N + 1];
            Arrays.fill(studentDist, INF);
            // 각 학생들의 최단거리
            dijkstra(lists, studentDist, i, true);
            // 왕복 거리 중 최댓값 저장
            answer = Math.max(answer, distance[i] + studentDist[X]);
        }
        System.out.println(answer);
    }

    private static void dijkstra (List<Integer[]>[] lists, int[] distance, int start, boolean flag) {
        // 경로 비용이 적은 순 대로 정렬되는 우선순위 큐 생성
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        distance[start] = 0;

        while (!queue.isEmpty()) {
            // 현재 정점에서 가장 적은 비용 경로 선택
            Node poll = queue.poll();
            int now = poll.vertex;
            int dist = poll.cost;

            // 시간 단축을 위한 flag 설정 : 학생 기준이라면 X 만날때 즉시 탈출
            if (flag && now == X) {
                return;
            }

            // 이미 현재 정점이 최적화 되어 있으면 통과
            if (distance[now] < dist) {
                continue;
            }

            // 현재 정점을 거쳐 다음 정점으로의 경로 최신화
            for (Integer[] next : lists[now]) {
                int cost = dist + next[1];
                if (cost < distance[next[0]]) {
                    distance[next[0]] = cost;
                    queue.add(new Node(next[0], cost));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        // 우선순위 큐에 담길 수 있도록 비교자 설정
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}