package week7.세연;

import java.util.*;
import java.io.*;

class Main {
    static int[] xCut, yCut;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        int lst, tmp;
        int N = Integer.parseInt(br.readLine());
        xCut = new int[N+1];
        st = new StringTokenizer(br.readLine());
        lst = 0;
        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            xCut[i] = tmp - lst;
            lst = tmp;
        }
        xCut[N] = H - lst;
        int M = Integer.parseInt(br.readLine());
        yCut = new int[M+1];
        st = new StringTokenizer(br.readLine());
        lst = 0;
        for (int i = 0; i < M; i++) {
            tmp = Integer.parseInt(st.nextToken());
            yCut[i] = tmp - lst;
            lst = tmp;
        }
        yCut[M] = W - lst;

        Arrays.sort(xCut);
        Arrays.sort(yCut);

        long cnt = 0;
        long val;
        for (int x : xCut){
            if (x > K) break;
            val = K / x;
            cnt += binarySearch(0, M+1, val);
        }
        System.out.println(cnt);
    }

    static int binarySearch(int start, int end, long val) {
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (val >= yCut[mid]) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}