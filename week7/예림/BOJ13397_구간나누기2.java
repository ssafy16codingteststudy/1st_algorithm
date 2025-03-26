package week7.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13397_구간나누기2 {

    private static int[] arr;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 구간의 점수의 최댓값의 최솟값
        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int right = max - min;

        int left = 0, answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossible(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static boolean isPossible(int maxSection) {
        int count = 1;
        int min = arr[0], max = arr[0];

        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > maxSection) {
                count++;
                min = arr[i];
                max = arr[i];
            }
        }

        return count <= M;
    }
}
