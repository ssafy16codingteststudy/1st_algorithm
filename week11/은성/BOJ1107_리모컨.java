import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1107_리모컨 {

    private static int my = 100, cnt;
    private static String target;
    private static boolean[] button = new boolean[10];
    /**
     * 1107 리모컨
     * 복잡하게 생각해서 애먹었었는데, 알고리즘 분류 -> 브루트포스, 완전탐색으로 접근
     * 중복순열처럼 나올 수 있는 모든 경우의 수와 비교 + 한 자릿값이 결정될 때 마다 비교
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();

        int m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                button[num] = true;
            }
        }

        // 모든 버튼 고장이라면 +- 로만 이동
        if(m == 10) {
            System.out.println(Math.abs(my - Integer.parseInt(target)));
            return;
        }

        // 고장 안났다면 문자열 길이만큼 << +- 로만 이동하는게 최솟값일 수 있음
//        if(m == 0) {
//            System.out.println(target.length());
//            return;
//        }

        // 순정 +- 이동
        cnt = Math.abs(my - Integer.parseInt(target));
        dfs(0, "");

        System.out.println(cnt);
    }

    private static void dfs(int index, String num) {
        if (!num.isEmpty()) {
            check(index, num);
        }

        // 최댓값이 목표 채널의 자릿수보다 높을 수 있다는 사실을 고려
        if(index == target.length() + 1) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            if(button[i]) continue;

            dfs(index + 1, i + num);
        }
    }

    private static void check(int index, String num) {
        int targetValue = Integer.parseInt(target);
        int value = Integer.parseInt(num);

        int nowCnt = Math.abs(value - targetValue) + index;
        cnt = Math.min(cnt, nowCnt);
    }
}