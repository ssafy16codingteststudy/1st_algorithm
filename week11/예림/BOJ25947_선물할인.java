import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, a;
    private static long b;
    private static long[] prices, prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        n = Integer.parseInt(st.nextToken()); // 선물의 개수
        b = Long.parseLong(st.nextToken()); // 예산
        a = Integer.parseInt(st.nextToken()); // 반값 할인을 받을 수 있는 최대 선물의 수

        st = new StringTokenizer(br.readLine());
        prices = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prices[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(prices);
        makePrefixSum();

        // 최대로 살 수 있는 선물의 수
        long answer = binarySearch();
        System.out.println(answer);
    }

    private static void makePrefixSum() {
        prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + prices[i];
        }
    }

    private static int binarySearch() {
        int left = 0, right = n, answer = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isPossible(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private static boolean isPossible(int count) {
        int discountCount = Math.min(a, count);
        long discounted = (prefixSum[count] - prefixSum[count - discountCount]) / 2;
        long undiscounted = prefixSum[count - discountCount];

        return discounted <= b - undiscounted;
    }
}
