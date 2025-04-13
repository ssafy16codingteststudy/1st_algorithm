import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ23300_웹브라우저2
{
    // 23300 웹 브라우저 2
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Deque<Integer> back = new ArrayDeque<>();
        Deque<Integer> front = new ArrayDeque<>();
        int now = -1;

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "B" : {
                    if (!back.isEmpty() && now != -1) {
                        front.push(now);
                        now = back.pop();
                    }
                    break;
                }

                case "F" : {
                    if (!front.isEmpty() && now != -1) {
                        back.push(now);
                        now = front.pop();
                    }
                    break;
                }

                case "A" : {
                    front.clear();
                    if (now != -1) {
                        back.push(now);
                    }
                    now = Integer.parseInt(st.nextToken());;
                    break;
                }

                case "C" : {
                    back = compress(back);
                    break;
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(now).append("\n");
        if (back.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            while (!back.isEmpty()) {
                sb.append(back.pop()).append(" ");
            }
            sb.append("\n");
        }
        if (front.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            while (!front.isEmpty()) {
                sb.append(front.pop()).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static Deque<Integer> compress(Deque<Integer> back) {
        Deque<Integer> newBack = new ArrayDeque<>();
        while (!back.isEmpty()) {
            int now = back.pollLast();

            if (!newBack.isEmpty() && newBack.peek() == now) {
                continue;
            }

            newBack.push(now);
        }
        return newBack;
    }
}