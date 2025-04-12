package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20040_사이클게임 {

    static int n; // 점의 개수, 3~500,000
    static int m; // 연결한 횟수, 3~1,000,000
    static int[] p; // 루트노드 저장, 음수면 루트노드이고 랭크를 나타냄
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n];
        Arrays.fill(p, -1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find(a) == find(b)) {
                ans = i + 1;
                break;
            }
            union(a, b);
        }
        System.out.print(ans);
        br.close();
    }

    public static int find(int x) {
        if (p[x] < 0)
            return x;

        return p[x] = find(p[x]);
    }

    public static void union(int x, int y) {
        int u = find(x);
        int v = find(y);

        if (u == v)
            return;

        if (p[u] > p[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        if (p[u] == p[v])
            p[u] += -1;

        p[v] = u;
    }
}
