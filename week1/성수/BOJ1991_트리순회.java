import java.io.*;
import java.util.*;

public class BOJ_1991{
    static class Node{
        char data;
        Node left;
        Node right;

        public Node(char data){
            this.data = data;
        }
    }
    static Map<Character, Node> tree = new HashMap<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree.putIfAbsent(parent, new Node(parent));
            tree.get(parent).data = parent;

            if(left != '.'){
                tree.putIfAbsent(left, new Node(left));
                tree.get(parent).left = tree.get(left);
            }
            if(right != '.'){
                tree.putIfAbsent(right, new Node(right));
                tree.get(parent).right = tree.get(right);
            }

        }

        preorder(tree.get('A'));
        sb.append("\n");
        inorder(tree.get('A'));
        sb.append("\n");
        postorder(tree.get('A'));

        System.out.print(sb.toString());
    }

    static void preorder(Node node){
        if(node == null) return;

        sb.append(node.data);
        preorder(node.left);
        preorder(node.right);
    }
    static void inorder(Node node){
        if(node == null) return;

        inorder(node.left);
        sb.append(node.data);
        inorder(node.right);
    }

    static void postorder(Node node){
        if(node == null) return;

        postorder(node.left);
        postorder(node.right);
        sb.append(node.data);
        
    }
}