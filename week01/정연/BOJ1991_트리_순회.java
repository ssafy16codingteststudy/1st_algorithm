import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    char v;
    Node l;
    Node r;

    public Node(char v) {
        this.v = v;
        l = null;
        r = null;
    }
}

public class Main {

    static void PreOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.v);
        PreOrder(node.l);
        PreOrder(node.r);
    }

    static void InOrder(Node node) {
        if (node == null)
            return;
        InOrder(node.l);
        System.out.print(node.v);
        InOrder(node.r);
    }

    static void PostOrder(Node node) {
        if (node == null)
            return;
        PostOrder(node.l);
        PostOrder(node.r);
        System.out.print(node.v);
    }

    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new Node[n + 1];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            char p = input[0];
            char l = input[2];
            char r = input[4];

            if (tree[p - 'A'] == null) {
                tree[p - 'A'] = new Node(p);
            }
            if (l != '.') {
                tree[l - 'A'] = new Node(l);
                tree[p - 'A'].l = tree[l - 'A'];
            }
            if (r != '.') {
                tree[r - 'A'] = new Node(r);
                tree[p - 'A'].r = tree[r - 'A'];
            }

        }
        PreOrder(tree[0]);
        System.out.println();
        InOrder(tree[0]);
        System.out.println();
        PostOrder(tree[0]);
    }
}
