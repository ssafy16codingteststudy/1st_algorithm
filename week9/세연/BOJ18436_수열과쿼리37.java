package week9.세연;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static SegTree evenSeg, oddSeg; // 짝수, 홀수 트리 따로 저장 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        // 배열 초기화
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        // 트리 초기화
        evenSeg = new SegTree(N*4, 1);
        oddSeg = new SegTree(N*4,0);
        evenSeg.init(0,N-1, 1);
        oddSeg.init(0,N-1, 1);
        
        // cmd 입력받고 수행
        M = Integer.parseInt(br.readLine());
        int cmd, left, right;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 1:
                    int idx = Integer.parseInt(st.nextToken())-1;
                    int bef = arr[idx], after = Integer.parseInt(st.nextToken()), delta = after-bef;
                    arr[idx] = after;
                    if (bef%2 != after%2) { // 짝수->짝수, 홀수->홀수 변경의 경우 배열만 수정하고 트리는 수정 안해도 됨 (짝수 홀수 개수니까)
                        if (bef%2 == 0) { // 짝수에서 홀수로 변경됨
                            evenSeg.update(0, N-1, 1, idx, -1); // 1이었던(짝수) 걸 0(홀수)으로 변경해줘야하므로 delta -1
                            oddSeg.update(0, N-1, 1, idx, 1); // 0이었던(홀수) 걸 1(짝수)로 변경해줘야하므로 delta +1
                        }
                        else { //홀수에서 짝수로 변경됨
                            evenSeg.update(0, N-1, 1, idx, 1); // 0이었던(홀수) 걸 1(짝수)으로 변경해줘야하므로 delta +1
                            oddSeg.update(0, N-1, 1, idx, -1); // 1이었던(홀수) 걸 0(짝수)으로 변경해줘야하므로 delta -1
                        }
                    }
                    break;
                case 2:
                    left = Integer.parseInt(st.nextToken())-1;
                    right = Integer.parseInt(st.nextToken())-1;
                    sb.append(evenSeg.intervalSum(0, N-1, 1, left, right)).append("\n");
                    break;
                case 3:
                    left = Integer.parseInt(st.nextToken())-1;
                    right = Integer.parseInt(st.nextToken())-1;
                    sb.append(oddSeg.intervalSum(0, N-1, 1, left, right)).append("\n");
                    break;
                default: break;
            }


        }

        System.out.println(sb);
    }

    static class SegTree {
        int [] tree;
        int type;

        SegTree(int size, int type) {
            this.tree = new int[size];
            this.type = type;
        }

        int init(int start, int end, int idx){
            if (start == end) tree[idx] = (arr[start] + type) % 2; // 짝수 세그먼트트리 : type이 1이면 짝수가 1, 홀수가 0이 됨
                                                                   // 홀수 세그먼트트리 : type이 0이면 홀수가 1, 짝수가 0이 됨
            else {
                int mid = (start + end) / 2;
                tree[idx] = init(start, mid, 2*idx) + this.init(mid+1, end, 2*idx+1); // 중간 노드에는 자식 노드의 값의 합 -> ex. 짝수만 1 인 세그먼트 트리의 intervalSum은 짝수 개수
            }
            return tree[idx];
        }

        int intervalSum(int start, int end, int idx, int left, int right) {
            if (end < left || right < start) return 0;
            else if (left <= start && end <= right) return tree[idx];
            int mid = (start + end) / 2;
            return intervalSum(start, mid, 2*idx, left, right) + intervalSum(mid+1, end, 2*idx+1, left, right);
        }

        void update(int start, int end, int idx, int target, int delta) {
            if (target < start || end < target) return;
            tree[idx] += delta;
            if (start == end) return;
            int mid = (start + end) / 2;
            update(start, mid, 2*idx, target, delta);
            update(mid+1, end, 2*idx+1, target, delta);
        }
    }

}