import java.util.*;

class Node {
    int idx;
    int w;

    public Node(int i, int w) {
        this.idx = i;
        this.w = w;
    }

}

class Solution {
    static int slen, V;
    static List<Node>[] graph;
    static int[] dist;
    static int[] answer = { 50_001, 10_000_001 };
    static Set<Integer> gateList, summitList;
    final static int MAX_VALUE = 10_000_001;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        V = n;
        gateList = new HashSet<>();
        summitList = new HashSet<>();
        for (int i : gates) {
            gateList.add(i);
        }
        for (int i : summits) {
            summitList.add(i);
        }

        slen = MAX_VALUE;

        graph = new List[n + 1];
        dist = new int[V + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i <= V; i++) {
            dist[i] = MAX_VALUE;
        }
        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            graph[i].add(new Node(j, w));
            graph[j].add(new Node(i, w));
        }

        djik();
        return answer;
    }

    static void djik() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for (int i : gateList) {
            queue.offer(new Node(i, 0));
            dist[i] = 0;
        }
        while (!queue.isEmpty()) {
            Node curN = queue.poll();
            int cur = curN.idx;
            if (curN.w > answer[1])
                continue;

            for (Node nd : graph[cur]) {
                if (gateList.contains(nd.idx))
                    continue;

                // 1. 현재 노드(이때까지 지나온 길중 가장 힘든길), 목표 노드로 가는 간선의 비용 중 큰 것 선택
                int l = Math.max(dist[cur], nd.w);

                if (l > answer[1])
                    continue;

                // 2. 왔던 길로 갔을 때 예상 비용(위에서 구한 l), 이미 노드까지 계산된 비용 중 작은 것 선택
                // 이미 노드까지 계산된 비용보다 방금까지 왔던 길이 더 비용이 적다면 그 길 선택
                // System.out.printf("l %d , dist[nd.idx] %d ",l,dist[nd.idx]);
                if (l < dist[nd.idx]) {

                    dist[nd.idx] = l;

                    if (summitList.contains(nd.idx)) {
                        if (answer[1] > l || (answer[1] == l && answer[0] > nd.idx)) {
                            answer = new int[] { nd.idx, l };
                        }
                    } else {
                        queue.offer(new Node(nd.idx, l));
                    }
                }
            }
        }
    }

}