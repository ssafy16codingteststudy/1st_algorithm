import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Long> CD = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                CD.add(Long.parseLong(st.nextToken()));
            }

            long answer = 0;

            while (CD.size() > 1) {
                long a = CD.poll() + CD.poll();
                CD.add(a);
                answer+=a;
            }

            System.out.println(answer);
        }
    }
}
