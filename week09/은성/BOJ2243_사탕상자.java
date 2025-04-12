
import java.io.*;
import java.util.*;

public class BOJ2243_사탕상자 {

    private static int N = 1_000_000;
    private static int[] arr = new int[N + 1];
    private static int[] seg = new int[4 * N];
    /**
     * 2243. 사탕상자
     * 맛 점수가 1 ~ 1_000_000 인 사탕 순위 -> 배열의 인덱스로 생각하면,
     * 1~N 까지의 누적합 = 맛있는 순위
     * 그 아이디어까진 괜찮은데, 순위에 해당하는 인덱스 값을 찾는데 상당히 애를 먹음
     * 기존 세그먼트 트리를 반복적으로 호출하거나(nlogn), 일반 누적합 방식(n)으로 처리하면 시간초과 발생
     * 결국 이진트리 방식(logn)으로 처리해야 함 >> getIndex()
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                int rank = Integer.parseInt(st.nextToken());
                int index = getIndex(1, N, rank, 1);

                sb.append(index).append("\n");
                update(1, N, 1, index, -1);
            } else if(cmd == 2) {
                int flavor = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());

                update(1, N, 1, flavor, num);
            }
        }
        System.out.println(sb);
    }

    private static int getIndex(int start, int end, int rank, int index) {
        // 리프 노드로 내려갔을때를 확인하기 위한 start == end 비교 조건
        if(start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        // 왼쪽 자식의 합이 rank 이상이면 왼쪽으로
        if (seg[index * 2] >= rank) {
            return getIndex(start, mid, rank, index * 2);
        } else {
            // 그렇지 않으면 rank 에서 왼쪽 자식의 합을 빼고 오른쪽으로 내려가기
            rank -= seg[index * 2];
            return getIndex(mid + 1, end, rank, index * 2 + 1);
        }
    }

    private static void update (int start, int end, int node, int index, int num) {
        if(index < start || end < index) {
            return;
        }

        if(start == end) {
            arr[start] += num;
            seg[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, num);
        update(mid + 1, end, node * 2 + 1, index, num);
        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }

    private static int sum(int start, int end, int node, int left, int right) {
        if(end < left || right < start) {
            return 0;
        }

        if(left <= start && end <= right) {
            return seg[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}