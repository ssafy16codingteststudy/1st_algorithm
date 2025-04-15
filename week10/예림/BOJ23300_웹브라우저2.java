import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 웹페이지 종류 수
        int Q = Integer.parseInt(st.nextToken()); // 작업 수

        Deque<Integer> backStack = new ArrayDeque<>();
        Deque<Integer> frontStack = new ArrayDeque<>();
        int current = -1;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("B")) {
                if (backStack.isEmpty()) {
                    continue;
                }
                if (current != -1) {
                    frontStack.push(current);
                }
                current = backStack.pop();
            } else if (cmd.equals("F")) {
                if (frontStack.isEmpty()) {
                    continue;
                }
                if (current != -1) {
                    backStack.push(current);
                }
                current = frontStack.pop();
            } else if (cmd.equals("A")) {
                frontStack.clear();
                if (current != -1) {
                    backStack.push(current);
                }
                current = Integer.parseInt(st.nextToken());

            } else if (cmd.equals("C")) {
                Deque<Integer> temp = new ArrayDeque<>();
                int prev = -1;

                while (!backStack.isEmpty()) {
                    int cur = backStack.pop();
                    if (cur != prev) {
                        temp.push(cur);
                        prev = cur;
                    }
                }

                while (!temp.isEmpty()) {
                    backStack.push(temp.pop());
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(current).append("\n");

        if (backStack.isEmpty()) {
            sb.append("-1\n");
        } else {
            for (int b : backStack) {
                sb.append(b).append(" ");
            }
            sb.append("\n");
        }

        if (frontStack.isEmpty()) {
            sb.append("-1\n");
        } else {
            for (int f : frontStack) {
                sb.append(f).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
