package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2512_예산 {

    /**
     * 2512 예산
     * 모든 경우의수의 상한액을 대입해보는 대신, 이분 탐색으로 대입 횟수를 줄인다.
     * 1. while 조건에 등호가 들어가는 이유
     *    상한액이 될 수 있는 값은 [start, end] 폐구간 안에 있다. 따라서 등호를 붙여줌
     * 2. start, end 의 값을 갱신할 때, mid +-1 해주는 이유
     *    탐색 범위가 폐구간이기 때문에, 조건에 따라 mid 값을 완전히 배제(즉, mid 를 포함하지 않음)하기 위해
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 1;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int budget = Integer.parseInt(br.readLine());

        int start = 0;
        int end = max;
        int mid = (start + end) / 2;
        while (start <= end) {
            if (getBudget(arr, mid, budget)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }
        System.out.println(mid);
    }

    private static boolean getBudget(int[] arr, int mid, int budget) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (mid >= arr[i]) {
                sum += arr[i];
            } else {
                sum += mid;
            }
        }

        return sum <= budget;
    }
}