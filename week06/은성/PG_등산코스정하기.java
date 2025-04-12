package codingTestStudy;

import java.util.*;

class PG_등산코스정하기 {

    int n;
    List<Integer[]>[] lists;
    Set<Integer> gateSet;
    Set<Integer> summitSet;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        gateSet = new HashSet<>();
        summitSet = new HashSet<>();

        lists = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int cost = path[2];


            lists[a].add(new Integer[] {b, cost});
            lists[b].add(new Integer[] {a, cost});
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        return dijkstra();
    }

    private int[] dijkstra() {
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(new CostComparator());
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 모든 출발점을 한꺼번에 큐에 넣어주고 배열 초기화
        for (int gate : gateSet) {
            queue.add(new Integer[] {gate, 0});
            dist[gate] = 0;
        }


        while(!queue.isEmpty()) {
            Integer[] poll = queue.poll();
            int now = poll[0];
            // cost : 해당 node 를 지나오기까지 건너온 간선 중 최댓 값
            int cost = poll[1];

            // 만약 현재 정점이 산봉우리라면 더 이상 확장하지 않음.
            if (summitSet.contains(now)) continue;

            if(dist[now] < cost) {
                continue;
            }

            for (Integer[] node : lists[now]) {
                int next = node[0];
                // next 의 간선 값과, 현재 간선 최댓값 비교
                int newCost = Math.max(cost, node[1]);

                // 기록된 간선 최댓값보다 현재 간선 최댓값이 더 작은 경우에만 탐색 진행
                if(dist[next] > newCost) {
                    dist[next] = newCost;
                    queue.add(new Integer[] {next, newCost});
                }
            }
        }

        // 산봉우리에 기록된 최대 간선 값중 최솟값을 담아 반환
        int[] route = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int summit : summitSet) {
            int[] temp = {summit, dist[summit]};

            if(temp[1] == route[1]) {
                route = temp[0] > route[0] ? route : temp;
            } else if(temp[1] < route[1]) {
                route = temp;
            }
        }

        return route;
    }

    private class CostComparator implements Comparator<Integer[]> {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return o1[1] - o2[1];
        }
    }
}