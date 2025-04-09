package week9.세연;

import java.util.*;
import java.io.*;

class Node {
    int index, color;
    List<Integer> next;
    Node(int index, int color) {
        this.index = index;
        this.color = color;
        this.next = new ArrayList<>(); // 현재 노드와 연결된 노드들의 index
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
            for (int next : tree[idx].next) { // 자식노드는 queue에 모두 넣되 현재 노드 색칠 여부 확인을 위해 부모 노드가 뭔지도 구하기
                if (visited[next] == 1) parent = next; // 연결된 노드 중 이미 방문했던 노드는 부모 노드임
                else { // 그 외는 자식 노드 -> 다음에 방문할 노드 처리
                    queue.add(next); 
                    visited[next] = 1;
                }
            }
            if (tree[idx].color == 0) continue; // 만일 흰색이면 건드리지 않고 넘어가기
            if (parent == -1 || tree[parent].color != tree[idx].color) { // 현재 노드가 루트 노드이거나 부모 노드의 색상과 다르면 현재 노드 새로 색칠해야함
                cnt++; // 색칠 횟수 증가
            }
            parent = -1; // 부모 노드 초기화
        }
        System.out.println(cnt);
    }
}
