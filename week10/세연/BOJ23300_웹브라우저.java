package week10.세연;

import java.util.*;
import java.io.*;

public class BOJ23300_웹브라우저 {
    static int N, Q;
    static char cmd;
    static Deque<Integer> backward = new ArrayDeque<>(), forward = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int curr = 0, next = 0;
        for (int q = 0; q < Q; q++) {
            st  = new StringTokenizer(br.readLine());
            cmd = st.nextToken().charAt(0);
            switch (cmd){
                case 'F':
                    curr = cmdForward(curr);
                    break;
                case 'B':
                    curr = cmdBackward(curr);
                    break;
                case 'A':
                    next = Integer.parseInt(st.nextToken());
                    curr = cmdAccess(curr, next);
                    break;
                case 'C':
                    cmdCompress();
                    break;
                default: break;
            }
        }
        sb.append(curr).append("\n");
        if (backward.isEmpty()) sb.append(-1);
        while(! backward.isEmpty()) {
            sb.append(backward.poll()).append(" ");
        }
        sb.append("\n");
        if (forward.isEmpty()) sb.append(-1);
        while(! forward.isEmpty()) {
            sb.append(forward.poll()).append(" ");
        }
        System.out.println(sb);
    }
    static int cmdForward(int curr){
        if (forward.isEmpty()) return curr;
        if (curr != 0) backward.addFirst(curr);
        curr = forward.poll();
        return curr;
    }
    static int cmdBackward(int curr){
        if (backward.isEmpty()) return curr;
        if (curr != 0) forward.addFirst(curr);
        curr = backward.poll();
        return curr;
    }
    static int cmdAccess(int curr, int next){
        if (curr !=0) backward.addFirst(curr);
        forward.clear();
        return next;
    }
    static void cmdCompress() {
        Deque<Integer> backup = new ArrayDeque<>();
        int before = -1, curr;
        while(!backward.isEmpty()) {
            curr = backward.poll();
            if (before == curr) continue;
            backup.add(curr);
            before = curr;
        }
        backward = backup;
    }
}
