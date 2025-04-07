import java.io.*;
import java.util.*;

public class BOJ18436수열과쿼리37 {

    private static int N;
    private static int[] oddSeg, evenSeg;
    /**
     * 18436. 수열과 쿼리 37
     * 짝수와 홀수의 개수를 어떻게 처리하지? 했는데 짝수 개수만 저장하는 세그, 홀수 개수만 저장하는 세그를 관리하면 됨
     * 세그에 저장할 때, 그 값 그대로 저장하지 말고 모두 1 or 0으로 바꿔 저장한 뒤, 그것의 합을 구하면 곧 개수임
     * 처음에 헤멨었는데, 값을 변경할 때, 짝수 세그에 1이 들어가야 한다면, 동일한 위치의 홀수 세그에는 0으로 갱신해줘야 한다는 것을 놓침
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        oddSeg = new int[N * 4];
        evenSeg = new int[N * 4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            // init() 를 만들지 않고, 그냥 change() 를 더 실행해주는것으로 했음
            change(1, N, 1, i, Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 1) {

                change(1, N, 1, a, b);
            } else if(cmd == 2) {
                sb.append(sum(1, N, 1, a, b, evenSeg)).append("\n");
            } else if(cmd == 3){
                sb.append(sum(1, N, 1, a, b, oddSeg)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void change(int start, int end, int node, int index, int value) {
        if(index < start || end < index) {
            return;
        }

        if(start == end) {
            // 리프 노드의 값을 바꿀 때, 짝수 세그와 홀수 세그 둘 다 수정해줘야 함
            if(value % 2 == 1) {
                oddSeg[node] = 1;
                evenSeg[node] = 0;
            } else {
                oddSeg[node] = 0;
                evenSeg[node] = 1;
            }
            return;
        }

        int mid = (start + end) / 2;
        change(start, mid, node * 2, index, value);
        change(mid + 1, end, node * 2 + 1, index, value);
        // 두 개의 세그 모두 값이 바꼈으니까, 둘다 중간노드도 업뎃해줘야 함
        oddSeg[node] = oddSeg[node * 2] + oddSeg[node * 2 + 1];
        evenSeg[node] = evenSeg[node * 2] + evenSeg[node * 2 + 1];
    }

    private static int sum(int start, int end, int node, int left, int right, int[] seg) {
        if(end < left || right < start) {
            return 0;
        }

        if(left <= start && end <= right) {
            return seg[node];
        }

        int mid = (start + end) / 2;

        return sum(start, mid, node * 2, left, right, seg) + sum(mid + 1, end, node * 2 + 1, left, right, seg);
    }
}