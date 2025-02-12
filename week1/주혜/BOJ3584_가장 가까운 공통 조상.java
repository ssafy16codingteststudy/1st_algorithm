import java.util.*;

class Main {

    static ArrayList<ArrayList<Integer>> tree;
    static int first;
    static int second;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testcase = 0; testcase < T; testcase++) {
            tree = new ArrayList<>();
            N = sc.nextInt();
            for (int i = 0; i <= N; i++) {
                tree.add(new ArrayList<>());
            }
            for (int i = 0; i < N-1; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                tree.get(b).add(a);
            }
            first = sc.nextInt();
            second = sc.nextInt();

            System.out.println(find());
        }
    }

    static int find() {
        Set<Integer> ancestors = new HashSet<>();
    
        int node = first;
        while (node != 0 && tree.get(node).size() != 0) {
            ancestors.add(node);
            node = tree.get(node).get(0); 
        }

    
        node = second;
        while (!ancestors.contains(node) && tree.get(node).size() != 0) {
            node = tree.get(node).get(0);
        }

        return node;
    }

}