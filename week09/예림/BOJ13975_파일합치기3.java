import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int K = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            Queue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long total = 0;
            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();

                long sum = a + b;
                total += sum;
                pq.offer(sum);
            }

            sb.append(total).append("\n");
        }

        System.out.println(sb);
    }
}
