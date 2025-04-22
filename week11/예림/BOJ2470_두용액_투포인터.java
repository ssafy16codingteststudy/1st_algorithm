package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2470 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] answer = find();
        System.out.println(new StringBuilder().append(answer[0]).append(" ").append(answer[1]));
    }

    private static int[] find() {
        int left = 0, right = N - 1;
        int minDiff = Integer.MAX_VALUE;
        int[] selected = new int[2];

        while (left < right) {
            int sum = arr[left] + arr[right];
            int diff = Math.abs(sum);
            if (diff < minDiff) {
                minDiff = diff;
                selected[0] = arr[left];
                selected[1] = arr[right];
            }

            if (sum < 0) {
                left++;
            } else if (sum == 0) {
                return selected;
            } else {
                right--;
            }
        }
        return selected;
    }
}
