import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Node[] nodes;
    static int T;
    static int n;
    static int x, y;
    static ArrayList<Integer> xParents, yParents;

    static class Node{
        int val;
        int parent;

        Node(int val, int parent){
            this.val = val;
            this.parent = parent;
        }
    }
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            nodes = new Node[n+1];
            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken()), child = Integer.parseInt(st.nextToken());
                nodes[child] = new Node(child, parent);
            }
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            xParents = new ArrayList<>();
            yParents = new ArrayList<>();
            xParents.add(x);
            yParents.add(y);
            while (nodes[xParents.get(xParents.size()-1)] != null) xParents.add(nodes[xParents.get(xParents.size()-1)].parent);
            while (nodes[yParents.get(yParents.size()-1)] != null) yParents.add(nodes[yParents.get(yParents.size()-1)].parent);
            for (int i : xParents) {
                if (yParents.contains(i)) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}