package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13397_구간나누기2 {

    /**
     * 13397 구간 나누기 2
     * 이분탐색 방식과 구간 결정 방식이 잘 떠오르지 않아 헤멨던 문제
     * 결국 구하려는 값을 두고 가능한지/불가능 한지를 따져가며 이분탐색
     * 배열의 첫 번째 부터 구간을 탐색하며, (최대 - 최소) 값이 mid 를 넘어서는 순간 다음 구간으로 변경
     * 그렇게 구한 구간의 갯수가 m 을 넘어서지 않으면 유효한 값임
     */
    private static int n, m;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        int min = 10_001, max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        int start = 0;
        int end = (max - min);
        while (start < end) {
            int mid = (start + end) / 2;
            if (isPossible(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    private static boolean isPossible(int mid) {
        int min = 10_001, max = 0;
        int count = 1;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > mid) {
                count++;
                min = max = arr[i];
            }
        }

        if (count > m) {
            return false;
        } else {
            return true;
        }
    }
}