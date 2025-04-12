import java.util.*;

// The main method must be in a class named "Main".
class Main {
    public static int root;
    public static int [] parent;
    public static List<Integer> [] node;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        node = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            node[a].add(b);
            node[b].add(a);
        }

        parent = new int[N];
        parent[0] = -1;
        root = 1;

        find(root);

        for (int i = 1; i < N; i++) {
            System.out.println(parent[i]);
        }
    }

    static void find(int root) {
        for (int n : node[root]) {
            if(parent[n - 1] == 0) {
                parent[n - 1] = root;
                find(n);
            }
        }
    }
}