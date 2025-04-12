package week2.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19638_센티와마법의뿅망치 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 거인 수
        int H = Integer.parseInt(st.nextToken()); // 센티의 키
        int T = Integer.parseInt(st.nextToken()); // 마법의 뿅망치 횟수 제한
        int count = 0;

        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while (H <= pq.element() && count < T && pq.element() != 1) {
            pq.offer(pq.poll() / 2);
            count++;
        }

        if (H > pq.element()) {
            sb.append("YES\n").append(count);
        } else {
            sb.append("NO\n").append(pq.element());
        }

        System.out.println(sb);
    }
}
