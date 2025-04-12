import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7575_바이러스 {

    private static int n, k;
    private static int[][] arr;
    /**
     * 7575 바이러스
     * kmp 를 브루투포스로 돌려서 해결
     * 첫 문장을 기준으로 k 길이의 순서대로, 역순으로 배열을 투 포인터로 구함
     * 그 구한 배열을 기준으로 실패 함수를 구함
     * 종합한 정보를 가지고 n - 1 개의 문장에 대해 검색 여부 확인
     * 모두 통과하면 YES, 아니면 NO
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][];

        for (int i = 0; i < n; i++) {
            int len = Integer.parseInt(br.readLine());
            arr[i] = new int[len];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 코드에 대한 투 포인터로 k 길이의 연속된 배열을 구함
        int start = 0, end = k;
        while (end <= arr[0].length) {
            boolean flag = false;
            // 정상 순서의 배열과 그 실패 함수
            int[] pattern = getPattern(start, end, arr[0], 1);
            int[] pi = getPi(pattern);

            // 역순 배열과 그 실패 함수
            int[] reversePattern = getPattern(start, end, arr[0], -1);
            int[] reversePi = getPi(reversePattern);

            // 나머지 n - 1 개의 코드에 대해서 kmp 실행
            for (int t = 1; t < n; t++) {
                if (!check(pattern, pi, reversePattern, reversePi, t)) {
                    // 한 코드라도 발견되지 않으면 바로 패스
                    flag = true;
                    break;
                }
            }

            // 전부 검색 성공하면 yes
            if (!flag) {
                System.out.println("YES");
                return;
            }

            start++; end++;
        }

        System.out.println("NO");
    }

    private static int[] getPattern(int start, int end, int[] arr, int flag) {
        int[] pattern = new int[k];
        int index = 0;

        if (flag == 1) {
            for (int i = start; i < end; i++) {
                pattern[index++] = arr[i];
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                pattern[index++] = arr[i];
            }
        }

        return pattern;
    }

    private static int[] getPi(int[] pattern) {
        int[] pi = new int[k];
        for (int i = 1, j = 0; i < k; i++) {

            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    private static boolean check(int[] pattern, int[] pi, int[] reversePattern, int[] reversePi, int t) {
        int[] target = arr[t];
        int len = target.length;

        // j : pi 비교, k : reversePi 비교
        for (int i = 0, j = 0, l = 0; i < len; i++) {

            while (j > 0 && target[i] != pattern[j]) {
                j = pi[j - 1];
            }

            // pi 와의 일치 여부 비교
            if (target[i] == pattern[j]) {
                if (j == k - 1) {
                    // 문자열 찾음
                    return true;
                }
                j++;
            }

            // reversePi 와의 일치 여부 비교
            while (l > 0 && target[i] != reversePattern[l]) {
                l = reversePi[l - 1];
            }

            if (target[i] == reversePattern[l]) {
                if (l == k - 1) {
                    // 문자열 찾음
                    return true;
                }
                l++;
            }
        }
        return false;
    }
}