package week10.일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ12763지각하면안돼 {

    // 도로 정보를 저장하는 클래스
    static class Edge {
        int to, time, cost;
        public Edge(int to, int time, int cost) {
            this.to = to;
            this.time = time;
            this.cost = cost;
        }
    }

    // 현재 상태를 저장하는 클래스 (건물 번호, 누적 택시비, 누적 이동시간)
    // 우선순위 큐에서는 비용(cost)이 작은 상태를 우선 처리합니다.
    static class State implements Comparable<State> {
        int node, cost, time;
        public State(int node, int cost, int time) {
            this.node = node;
            this.cost = cost;
            this.time = time;
        }
        @Override
        public int compareTo(State other) {
            return this.cost - other.cost;  // 비용 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());  // 건물의 개수

        // 2번째 줄: T(남은 시간, 분)와 M(가진 돈)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 도로(길) 개수
        int L = Integer.parseInt(br.readLine().trim());

        // 그래프 구성: 건물은 1번부터 N번까지, 각 건물의 인접 리스트 작성
        List<Edge>[] graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향 도로이므로 두 방향 모두 추가
            graph[u].add(new Edge(v, time, cost));
            graph[v].add(new Edge(u, time, cost));
        }

        // 각 노드별로 도달할 수 있는 가장 최소 시간
        // dp[node][spent] : 건물 node에, 지금까지 spent 만큼 돈을 사용해서 도달했을 때 걸린 최소 시간
        int INF = 1000000000;
        int[][] dp = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1][0] = 0; // 시작점: 1번 건물, 비용 0, 시간 0

        // 우선순위 큐 초기화: 상태(State)를 비용 기준으로 오름차순 처리
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(1, 0, 0));

        // 답: 최소 지출액 (만약 도착 못하면 -1)
        int answer = -1;

        while (!pq.isEmpty()) {
            State s = pq.poll();

            // 이미 더 좋은 시간으로 도달한 상태라면 무시
            if (s.time > dp[s.node][s.cost]) continue;

            // 목적지인 N번 건물에 도달했고, 시간 조건을 만족한다면
            if (s.node == N && s.time <= T) {
                answer = s.cost;
                break;  // 비용이 낮은 순으로 처리하므로, 처음 나온 것이 최소 비용임
            }

            // 현재 건물에서 갈 수 있는 모든 인접 도로를 확인하며 상태 전이
            for (Edge edge : graph[s.node]) {
                int nextNode = edge.to;
                int newCost = s.cost + edge.cost; //현재 까지의 소요한 비용 + 다음 길을 이용하는 비용
                int newTime = s.time + edge.time; //현재 까지의 소요한 시간 + 다음 길 소요시간

                // 새로운 비용이 가진 돈 M 이하이고,
                // 새로 도달하는 시간이 이전보다 짧다면 dp를 갱신하고, 우선순위 큐에 상태 추가
                if (newCost <= M && newTime < dp[nextNode][newCost]) {
                    dp[nextNode][newCost] = newTime;
                    pq.offer(new State(nextNode, newCost, newTime));
                }
            }
        }

        System.out.println(answer);
    }
}

