import java.util.*;
import java.io.*;

public class BOJ_23300 {

    static int currentPage = -1;
    static Deque<Integer> back = new LinkedList<>();
    static Deque<Integer> forward = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < Q; i++) {
            String[] cmd = br.readLine().split(" ");

            if (cmd[0].equals("A")) {
                int page = Integer.parseInt(cmd[1]);
                if (currentPage != -1) {
                    back.addFirst(currentPage);
                }
                currentPage = page;
                forward.clear();
            } else if (cmd[0].equals("B")) {
                if (!back.isEmpty()) {
                    forward.addFirst(currentPage);
                    currentPage = back.pollFirst();
                }
            } else if (cmd[0].equals("F")) {
                if (!forward.isEmpty()) {
                    back.addFirst(currentPage);
                    currentPage = forward.pollFirst();
                }
            } else if (cmd[0].equals("C")) {
                compressBack();
            }
        }

        System.out.println(currentPage);
        printStack(back);
        printStack(forward);
    }

    static void compressBack() {
        Deque<Integer> temp = new LinkedList<>();
        Integer prev = null;
        for (Integer page : back) {
            if (!page.equals(prev)) {
                temp.addLast(page);
                prev = page;
            }
        }
        back = new LinkedList<>(temp);
    }

    static void printStack(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            System.out.println(-1);
        } else {
            Iterator<Integer> it = stack.iterator();
            StringBuilder sb = new StringBuilder();
            while (it.hasNext()) {
                sb.append(it.next()).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}

