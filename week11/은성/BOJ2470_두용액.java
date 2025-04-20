import java.io.*;
import java.util.*;

public class BOJ2470_두용액 {

    private static int N;
    private static int[] arr;
    /**
     * 3470 두 용액
     * 정렬 후 시작 인덱스, 끝 인덱스에서부터 투 포인터로 이동해가며 0에 근접하는 값 비교
     * 이 때 대소비교시 음수값을 제거하기 위해 절댓값으로 변환
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0, end = N - 1;
        int[] last = new int[2];
        int minValue = Integer.MAX_VALUE;
        while (start < end) {
            int temp = arr[start] + arr[end];

            if(Math.abs(minValue) > Math.abs(temp)) {
                minValue = temp;
                last = new int[] {arr[start], arr[end]};
            }

            if(temp == 0) {
                break;
            }

            if (temp >= 0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(last[0] + " " + last[1]);
    }
}