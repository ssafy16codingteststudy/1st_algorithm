import java.util.*;
import java.io.*;

public class BOJ_16975_수열과쿼리21 {

    static int N;
    static long[] A;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 1) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                for (int s = i - 1; s < j; s++) {
                    A[s] += k;
                }
            } else {
                int x = Integer.parseInt(st.nextToken());
                sb.append(A[x - 1]).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}
