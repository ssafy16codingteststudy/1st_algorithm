import java.io.*;
import java.util.*;

class Node{
    char val;
    Node left;
    Node right;

    Node(char val, Node left, Node right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 받기
        int n = Integer.parseInt(br.readLine());
        Node root = new Node('A', null, null);
        for (int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char leftChild = st.nextToken().charAt(0);
            char rightChild = st.nextToken().charAt(0);

            insertNode(root, parent, leftChild, rightChild);
        }

        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
    }

    public static void insertNode(Node node, char parent, char leftChild, char rightChild){
        if (node.val == parent){
            node.left = (leftChild == '.') ? null :  new Node(leftChild, null, null);
            node.right = (rightChild == '.') ? null : new Node(rightChild, null, null);
        }
        else {
            if (node.left != null) insertNode(node.left, parent, leftChild, rightChild);
            if (node.right != null) insertNode(node.right, parent, leftChild, rightChild);
        }
    }

    public static void preorder(Node node){
        if (node == null) return;
        System.out.print(node.val);
        preorder(node.left);
        preorder(node.right);
    }
    public static void inorder(Node node){
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val);
        inorder(node.right);
    }
    public static void postorder(Node node){
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val);
    }
}
