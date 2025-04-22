import java.io.*;
import java.util.*;

public class BOJ1949_우수마을 {

    private static int N;
    private static int[] people;
    private static List<Integer>[] lists;
    private static int[][] dp;
    /**
     * 1949 우수 마을
     * 트리에서의 dfs + dp
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        lists = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lists[a].add(b);
            lists[b].add(a);
        }

        // 각 노드 N 개에 대해 (0 - 안 선정 / 1 - 선정) 일 때에 대한 최댓값 dp
        dp = new int[N + 1][2];
        dfs(1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int now, int last) {
        // 초기값 : 안 선정  - 0, 선정 - 그 마을 인구수
        dp[now][0] = 0;
        dp[now][1] = people[now];

        for (Integer next : lists[now]) {
            // 트리이므로 되돌아가는 것만 막아줌
            if(next == last) {
                continue;
            }

            dfs(next, now);
            // 현재 마을 안 선정이라면 다음 마을은 상관없음
            dp[now][0] += Math.max(dp[next][0], dp[next][1]);
            // 현재 마을 선정이라면 다음 마을 무조건 안 선정
            dp[now][1] += dp[next][0];
        }
    }
}