package week10.세연;

import java.util.*;
import java.io.*;

class Node {
    int idx;
    List<int[]> conn ;
    Node(int idx) {
        this.idx = idx;
        this.conn = new ArrayList<>();
    }
}

public class BOJ12763_지각하면안돼 {
    static int T, N, M, L, price = -1;
    static Node[] building;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(br.readLine());

        building = new Node[N+1];
        for (int i = 1; i < N+1; i++) {
            building[i] = new Node(i);
        }

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int buildingA = Integer.parseInt(st.nextToken());
            int buildingB = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            building[buildingA].conn.add(new int[] {buildingB, time, amount});
            building[buildingB].conn.add(new int[] {buildingA, time, amount});
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->(a[0]-b[0]));
        visited = new int[T+1][N+1];
        for (int i = 0 ; i < T+1 ; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        visited[1][0] = 0;
        queue.add(new int [] {0, 0, 1}); // 금액, 시간, 현재 건물
        int[] curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[0] > M) break; // 금액 초과
            if (curr[2] == N) { // 도착
                price = curr[0];
                break;
            }
            for (int [] next : building[curr[2]].conn){
                if (T < curr[1] + next[1]) continue; // 제한 시간 초과
                if (visited[curr[1] + next[1]][next[0]] <= curr[0] + next[2]) continue;
                visited[curr[1] + next[1]][next[0]] = curr[0] + next[2];
                queue.add(new int[] {curr[0] + next[2], curr[1] + next[1], next[0]});
            }
        }
        System.out.println(price);
    }
}
