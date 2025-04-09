package week9.세연;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static SegTree evenSeg, oddSeg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        evenSeg = new SegTree(N*4, 1);
        oddSeg = new SegTree(N*4,0);
        evenSeg.init(0,N-1, 1);
        oddSeg.init(0,N-1, 1);

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
                    if (bef%2 != after%2) {
                        if (bef%2 == 0) { // 짝수에서 홀수로 변경됨
                            evenSeg.update(0, N-1, 1, idx, -1);
                            oddSeg.update(0, N-1, 1, idx, 1);
                        }
                        else { //홀수에서 짝수로 변경됨
                            evenSeg.update(0, N-1, 1, idx, 1);
                            oddSeg.update(0, N-1, 1, idx, -1);
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
            if (start == end) tree[idx] = (arr[start] + type) % 2;
            else {
                int mid = (start + end) / 2;
                tree[idx] = init(start, mid, 2*idx) + this.init(mid+1, end, 2*idx+1);
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