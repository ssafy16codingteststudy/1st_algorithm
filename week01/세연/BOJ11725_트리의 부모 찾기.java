import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // graph 테이블 초기화
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        // visited 배열 초기화
        int [] visited = new int[n+1];
        // graph 값 input 받기
        for (int i = 1; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        // Queue 생성
        Queue<Integer> queue = new LinkedList<>();
        // 부모 노드 탐색
        visited[1] = 1;
        queue.add(1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int node : graph[cur]) {
                if (visited[node] == 0) {
                    visited[node] = cur;
                    queue.add(node);
                }
            }
        }
        // 결과 출력
        for (int i = 2; i<n+1; i++)
            System.out.println(visited[i]);
    }
}
