import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

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
        int minDiff = Integer.MAX_VALUE;
        int[] selected = new int[2];

        for (int i = 0; i < N; i++) {
            // -arr[i]와 가까운 값을 arr[i + 1] ~ arr[N - 1]에서 탐색
            int index = binarySearch(i + 1, N - 1, -arr[i]);

            if (index < N) {
                int diff = Math.abs(arr[i] + arr[index]);
                if (diff < minDiff) {
                    minDiff = diff;
                    selected[0] = arr[i];
                    selected[1] = arr[index];
                }
            }
            if (index - 1 > i) {
                int diff = Math.abs(arr[i] + arr[index - 1]);
                if (diff < minDiff) {
                    minDiff = diff;
                    selected[0] = arr[i];
                    selected[1] = arr[index - 1];
                }
            }
        }
        return selected;
    }

    private static int binarySearch(int left, int right, int target) {
        int result = right + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
