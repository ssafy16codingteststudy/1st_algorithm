package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19637_IF문좀대신써줘 {

    /**
     * 19637 IF문 좀 대신 써줘
     * 전투력의 경계 값들을 arr 에 담고, 그 인덱스와 관련 칭호를 map 에 담음
     * 그리고 lowerBound 돌리면서 인덱스를 찾고, 그 인덱스를 키값으로 하는 칭호를 찾음
     * Arrays.binarySearch() 를 하면 중복 값에 대한 최소값 보장을 못하기 때문에 시간 초과가 난다.
     * 시간초과가 난 이전 코드는 뒤의 주석을 참고
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            map.put(i, title);
        }

        for (int i = 0; i < m; i++) {
            int score = Integer.parseInt(br.readLine());
            int index = lowerBound(arr, score);

            sb.append(map.get(index)).append("\n");
        }
        System.out.println(sb);

/*      // Arrays.binarySearch()으로 날먹하려다 시간초과 남

        for (int i = 0; i < m; i++) {
            int score = Integer.parseInt(br.readLine());
            int index = Arrays.binarySearch(arr, score);
            if (index < 0) {
                String title = map.get(index * (-1) - 1);
                sb.append(title);
            } else {
                // 왼쪽으로 이동해서 첫 번째 인덱스를 찾기
                while (index > 0 && arr[index - 1] == score) {
                    index--;
                }
                sb.append(map.get(index));
            }
            sb.append("\n");
        }
        System.out.println(sb);
*/
    }

    private static int lowerBound(int[] arr, int score) {
        int start = 0;
        int end = arr.length;
        int mid = (start + end) / 2;
        while (start < end) {
            if (arr[mid] < score) {
                start = mid + 1;
            } else {
                end = mid;
            }
            mid = (start + end) / 2;
        }
        return mid;
    }
}