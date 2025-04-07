
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2357_최솟값과최댓값 {

    private static int N;
    private static int size = 2;
    private static int[][] seg;
    private static StringBuilder sb = new StringBuilder();

    /**
     * BOJ2357 최솟값과 최댓값
     * 반복문으로 구현한 세그먼트 트리
     * 반복문으로 하면 뭐가 좋나? -> 재귀에 비해 이해가 좀 더 쉬움
     * 하지만 반복문은 기본적인 세그에만 적용하기 쉽고 세그먼트 응용 문제는 재귀로 푸는것이 더 편하다고 함(GPT 피셜)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        while (size < N) {
            size = size << 1;
        }
        seg = new int[size << 1][2];

        // 세그먼트 트리 크기 : 2^k * 2
        // 원본 데이터 : 2^k ~
        // 해당 작업은 리프 노드에 차례대로 원래 값을 넣는 중임
        for (int i = 0; i < N; i++) {
            seg[size + i][0] = seg[size + i][1] = Integer.parseInt(br.readLine());
        }

        // 루트 노드까지 최댓값, 최솟값 구하면서 올라감
        for (int i = size - 1; i > 0; i--) {
            seg[i][0] = Math.min(seg[i * 2][0], seg[i * 2 + 1][0]);
            seg[i][1] = Math.max(seg[i * 2][1], seg[i * 2 + 1][1]);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            getValue(a, b);
        }

        System.out.println(sb);
    }

    private static void getValue(int start, int end) {
        // 리프 노드 index 로 이동 : index + (2^k - 1)
        start += size - 1;
        end += size - 1;

        int minValue = 1_000_000_001;
        int maxValue = -1;
        // 반복문 종료 조건 :
        while (start <= end) {

            // 선택 조건 : 독립 노드 일 때
            if (start % 2 == 1) {
                minValue = Math.min(minValue, seg[start][0]);
                maxValue = Math.max(maxValue, seg[start][1]);
            }

            if (end % 2 == 0) {
                minValue = Math.min(minValue, seg[end][0]);
                maxValue = Math.max(maxValue, seg[end][1]);
            }

            // 선택 했으면 옆 부모, 아니면 자신의 부모로 이동
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        sb.append(minValue).append(" ").append(maxValue).append("\n");
    }

/*
    // 값 변경 질의에는 다음과 같은 메서드를 사용
    private static void change(int index, long newNum) {
        // 리프 노드로 이동 후, 원본 데이터 갱신
        index += size - 1;
        seg[index] = newNum;

        // 부모 노드로 올라가면서, 구간 합 갱신
        while (index >= 1) {
            index /= 2;
            seg[index][0] = Math.min(seg[index * 2][0], seg[index * 2 + 1][0]);
        }
    }
*/
}