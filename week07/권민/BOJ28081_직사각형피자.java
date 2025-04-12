import java.util.*;
import java.io.*;

public class Main {

    private static int W, H, N, M;
    private static long K;
    private static long[] sero, garo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        
        // 가로 방향 커팅 (y 좌표, 즉 세로 분할)
        N = Integer.parseInt(br.readLine());
        sero = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        int before = 0;
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            sero[i] = v - before;
            before = v;
        }
        sero[N] = H - before;
        Arrays.sort(sero);
        
        // 세로 방향 커팅 (x 좌표, 즉 가로 분할)
        M = Integer.parseInt(br.readLine());
        garo = new long[M + 1];
        st = new StringTokenizer(br.readLine());
        before = 0;
        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(st.nextToken());
            garo[i] = v - before;
            before = v;
        }
        garo[M] = W - before;
        Arrays.sort(garo);
        
        // 조각의 개수가 매우 클 수 있으므로 answer는 long 사용
        long answer = 0;
        // 각 가로 조각 길이(garo) v에 대해, 세로 조각 길이 u가 u <= K/v를 만족하면
        // v*u <= K임을 이용하여 sero 배열에서 u의 개수를 이진 탐색으로 찾는다.
        for (int i = 0; i <= M; i++) {
            long v = garo[i];
            long threshold = K / v;
            int count = upperBound(sero, threshold);
            answer += count;
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
    
    // 배열 arr에서 key 이하인 원소의 개수를 반환 (upper bound)
    private static int upperBound(long[] arr, long key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= key)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
}
