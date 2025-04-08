package week9.세연;

import java.util.*;
import java.io.*;

class Node {
    int index, color;
    List<Integer> next;
    Node(int index, int color) {
        this.index = index;
        this.color = color;
        this.next = new ArrayList<>();
    }
}

public class Main {
    static int N;
    static Node[] tree;
    static Queue<Integer> queue = new ArrayDeque<>();
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new Node[N+1];
        visited = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            tree[i] = new Node(i, Integer.parseInt(st.nextToken()));
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].next.add(b);
            tree[b].next.add(a);
        }

        queue.add(1);
        visited[1] = 1;
        int cnt = 0;
        int idx, parent =-1;
        while(!queue.isEmpty()) {
            idx = queue.poll();
            for (int next : tree[idx].next) {
                if (visited[next] == 1) parent = next;
                else {
                    queue.add(next);
                    visited[next] = 1;
                }
            }
            if (tree[idx].color == 0) continue;
            if (parent == -1 || tree[parent].color != tree[idx].color) {
                cnt++;
            }
            parent = -1;
        }
        System.out.println(cnt);
    }
}
