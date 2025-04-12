package 용범;

import java.io.*;
import java.util.*;

public class BOJ5639_이진검색트리 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String line;
    static int N, size;
    static int[] tree = new int[10_001];
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        postOrder(0, size);

        System.out.println(sb.toString());
    }


    private static void postOrder(int s, int e) {
        // 종료 조건
        if (s >= e)
            return;

        int mid = s + 1;

        while (mid < e) {
            if (tree[s] < tree[mid])
                break;
            mid++;
        }

        postOrder(s + 1, mid);
        postOrder(mid, e);
        sb.append(tree[s]).append("\n");
    }

    private static void init() throws IOException {

        // 입력 부분
        line = null;

        size = 0; // 입력받은 숫자의 개수
        ans = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            N = Integer.parseInt(line);
            tree[size++] = N;
        }

        br.close();
    }
}
