import java.io.*;
import java.util.*;

public class Main {

    private static int[] parents;
    private static int[] sizes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        int k = Integer.parseInt(st.nextToken()); // 가지고 있는 돈
        
        st = new StringTokenizer(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(c -> c[1]));
        for (int i = 1; i <= N; i++) {
            pq.add(new int[]{i, Integer.parseInt(st.nextToken())});
        }

        parents = new int[N + 1];
        sizes = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        long total = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (find(0) == find(now[0])) {
                continue;
            }
            union(0, now[0]);
            total += now[1];

            if (sizes[0] == N + 1) {
                break;
            }
            if (total > k) {
                break;
            }
        }

        // 최소 비용(불가능 할 경우 "Oh no")
        System.out.println((total > k) ? "Oh no" : total);
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootB] = rootA;
            sizes[rootA] += sizes[rootB];
        }
    }
}
