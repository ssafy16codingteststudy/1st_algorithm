import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, HC, T;
    static PriorityQueue<Integer> queue;
    static int[] g;
    static int t;

    public static void main(String[] args) throws IOException {
        input();
        t = 0;
        while (T-- > 0) {
            int gh = queue.poll();
            if (gh < HC) {
                queue.offer(gh);
                break;
            }
            t++;
            gh = (int) (gh / 2);
            if (gh < 1) {
                gh = 1;
            }
            queue.offer(gh);
        }
        int gh = queue.poll();

        if (gh < HC) {
            System.out.printf("YES\n%d", t);
        } else {
            System.out.printf("NO\n%d", gh);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        HC = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        g = new int[N];
        // queue = new PriorityQueue<>((a, b) -> (Integer.compare(b, a)));
        queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }
        br.close();
    }
}
