package week9.세연;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static SegTree segTree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        segTree = new SegTree(N*4);
        segTree.init(0, N-1, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken())-1;
            // sb.append(segTree.intervalSearch(0,N-1,1,left,right));
            sb.append(segTree.intervalMin(0, N-1, 1, left, right)).append(" ").append(segTree.intervalMax(0, N-1, 1, left, right)).append("\n");
        }

        System.out.println(sb);
    }

    static class Node {
        int min, max;
        Node(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return min + " " + max + "\n";
        }
    }
    static class SegTree {
        Node [] tree;

        SegTree(int size) {
            this.tree = new Node[size];
        }

        Node init(int start, int end, int idx){
            if (start == end) tree[idx] = new Node(arr[start], arr[start]);
            else {
                int mid = (start + end) / 2;
                Node left = init(start, mid, 2*idx);
                Node right = init(mid+1, end, 2*idx+1);
                tree[idx] = new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
            }
            return tree[idx];
        }

        int intervalMax(int start, int end, int idx, int left, int right) {
            if (end < left || right < start) return Integer.MIN_VALUE;
            else if (left <= start && end <= right) return tree[idx].max;
            int mid = (start + end) / 2;
            return Math.max(intervalMax(start, mid, 2*idx, left, right), intervalMax(mid+1, end, 2*idx+1, left, right));
        }

        int intervalMin(int start, int end, int idx, int left, int right) {
            if (end < left || right < start) return Integer.MAX_VALUE;
            else if (left <= start && end <= right) return tree[idx].min;
            int mid = (start + end) / 2;
            return Math.min(intervalMin(start, mid, 2*idx, left, right), intervalMin(mid+1, end, 2*idx+1, left, right));
        }

        Node intervalSearch(int start, int end, int idx, int left, int right) {
            if (end < left || right < start) return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
            else if (left <= start && end <= right) return tree[idx];
            int mid = (start + end) / 2;
            Node f = intervalSearch(start, mid, 2*idx, left, right);
            Node s = intervalSearch(mid+1, end, 2*idx+1, left, right);
            return new Node(Math.min(f.min,s.min), Math.max(f.max,s.max));
        }
    }
}