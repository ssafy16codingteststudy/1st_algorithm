package week7.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// sum <= total인 가장 큰 mid
public class BOJ2512_예산 {

    private static int total;
    private static int[] budgets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 지방 수

        budgets = new int[n]; // 각 지방의 예산 요청
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
        }

        total = Integer.parseInt(br.readLine());

        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int left = 0;
        int right = Arrays.stream(budgets).max().getAsInt();
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int sum = 0;
            for (int budget : budgets) {
                sum += Math.min(budget, mid);
            }

            if (sum <= total) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
}
