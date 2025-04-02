package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195_친구네트워크 {

    static int T; // 테스트 케이스
    static int F; // 친구 관계의 수. 100,000
    static Map<String, Integer> f = new HashMap<>();
    static int[] p; // 닉네임을 입력된 순서대로 숫자를 부여해주고 숫자로만 생각함
    static int n; // 사람수 저장.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = 0;
            F = Integer.parseInt(br.readLine());
            p = new int[2 * F];
            Arrays.fill(p, -1);
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                int x, y = 0;
                if (f.containsKey(f1)) {
                    x = f.get(f1);
                } else {
                    x = n;
                    f.put(f1, n++);
                }
                if (f.containsKey(f2)) {
                    y = f.get(f2);
                } else {
                    y = n;
                    f.put(f2, n++);
                }

                union(x, y);
                if (find(x) < find(y)) {
                    int ans = -1 * p[find(x)];
                    sb.append(ans).append("\n");
                } else {
                    int ans = -1 * p[find(y)];
                    sb.append(ans).append("\n");
                }
            }
        }
        System.out.print(sb);
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

        p[u] += p[v]; // 음수를 친구 네트워크 수로 저장.
        p[v] = u;
    }
}
