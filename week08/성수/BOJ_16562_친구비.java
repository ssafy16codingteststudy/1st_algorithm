import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16562_친구비 {

    static int N; // 학생 수, 1~10,000
    static int M; // 친구 관계 수, 0~10,000
    static int k; // 가지고 있는 돈, 1~10,000,000
    static int[] fp; // i번째 사람과 친구가 되려면 내야할 돈.
    static int[] p; // 연결된 친구의 집합 정보
    static boolean[] checked; // 방문 체크.
    static int sum; // 내야할 총 친구비.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        fp = new int[N + 1];
        p = new int[N + 1];
        Arrays.fill(p, -1);
        checked = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            fp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }
        for (int i = 1; i <= N; i++) {
            if (checked[i])
                continue;

            int min = 10_000_001;
            if (p[i] < 0) {
                checked[i] = true;
                min = fp[i];
                for (int j = 1; j <= N; j++) {
                    if (checked[j])
                        continue;

                    if (find(j) != i)
                        continue;

                    min = Math.min(min, fp[j]);
                }
            } else {
                continue;
            }
            sum += min;
        }
        if (sum > k) {
            System.out.print("Oh no");
        } else {
            System.out.print(sum);
        }

        // for(int i=1; i<=N; i++) {
        // System.out.print(p[i] + " ");
        // }
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
            p[u]--;

        p[v] = u;
    }
}
