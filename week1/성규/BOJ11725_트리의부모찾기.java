import java.util.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        parent = new int[N+1];

        for (int i=0 ; i<=N ; i++){
            tree.add(new ArrayList<>());
        }
        
        for(int i=0; i<N-1 ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        dfs(1, 0);

        for(int i=2; i<=N ; i++){
            System.out.println(parent[i]);
        }
    }
    static void dfs(int cur, int par){
        parent[cur] = par;
        for (int node : tree.get(cur)){
            if (node != par){
                dfs(node, cur);
            }
        }
    }
}