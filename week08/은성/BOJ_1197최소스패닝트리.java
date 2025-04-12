import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1197최소스패닝트리 {
    // 1197
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화.        
        int[] parent = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {
            String[] split1 = br.readLine().split(" ");
            int a = Integer.parseInt(split1[0]);
            int b = Integer.parseInt(split1[1]);
            int cost = Integer.parseInt(split1[2]);

            queue.add(new Edge(a, b, cost));
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (find(parent, poll.node1) != find(parent, poll.node2)) {
                union(parent, poll.node1, poll.node2);
                answer += poll.cost;
            }
        }

        System.out.println(answer);
    }

    // 특정 원소가 속한 집합을 찾기
    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    // 두 원소가 속한 집합을 합치기
    private static void union(int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int node1;
        int node2;
        int cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}