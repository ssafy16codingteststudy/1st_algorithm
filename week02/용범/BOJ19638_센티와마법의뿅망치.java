package 용범;

import java.io.*;
import java.util.*;

public class BOJ19638_센티와마법의뿅망치 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, H, T;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        Integer height;
        int cnt = 0;
        while (cnt < T) {
            height = pq.poll();
            if (height < H || height == 1) {
                pq.offer(height);
                break;
            }
            cnt++;
            pq.offer(height / 2);
        }

        // 출력 부분
        height = pq.poll();
        sb.append(height < H ? "YES" : "NO").append("\n");
        sb.append(height < H ? cnt : height);

        System.out.println(sb.toString());
    }


    private static void init() throws IOException {

        // 입력 부분
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N: 나라의 인구수
        H = Integer.parseInt(st.nextToken()); // H: 센티의 키
        T = Integer.parseInt(st.nextToken()); // T: 뿅망치의 횟수 제한

        pq = new PriorityQueue<>(Comparator.reverseOrder()); // 키를 기준으로 내림차순 정렬
        for (int i = 0; i < N; i++)
            pq.offer(Integer.parseInt(br.readLine()));

        br.close();
    }
}
