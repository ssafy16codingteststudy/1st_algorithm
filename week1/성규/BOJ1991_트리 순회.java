import java.util.*;
import java.lang.*;
import java.io.*;

class Node {
    char name;
    Node left;
    Node right;
    
    public Node (char name) {
        this.name = name;
        this.left = null;
        this.right = null;
    }

    public void addLeft (Node left) {
        this.left = left;
    }

    public void addRight (Node right) {
        this.right = right;
    }
}

class Main {
    static Node [] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int node_size = sc.nextInt();
        tree = new Node[node_size];
        sc.nextLine();
        for(int i=0 ; i<node_size ; i++) {
            String node_info = sc.nextLine();
            char a = node_info.charAt(0);
            char b = node_info.charAt(2);
            char c = node_info.charAt(4);

            if (tree[a-'A']==null) {
                tree[a-'A'] = new Node(a);
            }
            if (!(b=='.')) {
                if (tree[b-'A']==null) tree[b-'A'] = new Node(b);
                tree[a-'A'].addLeft(tree[b-'A']);
            }
            if (!(c=='.')) {
                if (tree[c-'A']==null) tree[c-'A'] = new Node(c);
                tree[a-'A'].addRight(tree[c-'A']);
            }
        }
        preorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        postorder(tree[0]);
    }

    public static void preorder(Node start) {
        System.out.print(start.name);
        if (start.left!=null)preorder(start.left);
        if (start.right!=null)preorder(start.right);
    }

    public static void inorder(Node start) {
        if (start.left!=null)inorder(start.left);
        System.out.print(start.name);
        if (start.right!=null)inorder(start.right);
    }

    public static void postorder(Node start) {
        if (start.left!=null)postorder(start.left);
        if (start.right!=null)postorder(start.right);
        System.out.print(start.name);
    }
}