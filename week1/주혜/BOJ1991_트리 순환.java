import java.util.*;

class Main{
    static HashMap<String, ArrayList<String>> tree = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String a = sc.next();
            String b = sc.next();
            String c = sc.next();

            tree.put(a, new ArrayList<>(Arrays.asList(b, c)));
        }

        preorderTraversal("A");
        System.out.println();

        inorderTraversal("A");
        System.out.println();
        
        postorderTraversal("A");
    }

    static void preorderTraversal(String root) {
        System.out.print(root);
        if (tree.get(root).get(0).equals(".") != true)
            preorderTraversal(tree.get(root).get(0));
        if (tree.get(root).get(1).equals(".") != true)
            preorderTraversal(tree.get(root).get(1));
    }
    static void inorderTraversal(String root) {
        if (tree.get(root).get(0).equals(".") != true)
            inorderTraversal(tree.get(root).get(0));
        System.out.print(root);
        if (tree.get(root).get(1).equals(".") != true)
            inorderTraversal(tree.get(root).get(1));
    }
    static void postorderTraversal(String root) {
        if(tree.get(root).get(0).equals(".") != true)
            postorderTraversal(tree.get(root).get(0));
        if(tree.get(root).get(1).equals(".") != true)
            postorderTraversal(tree.get(root).get(1));
        System.out.print(root);
    }
}