import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Node{
    public int num;
    public long cost;

    public Node(int num, long cost){
        this.num = num;
        this.cost = cost;
    }
}

class Main {
    static int N;
    static int M;
    static int A;
    static int B;
    static ArrayList<ArrayList<Node>>graph = new ArrayList<>();
    static Long[] distance;
    static boolean[] visited;
    static boolean[] visited_dijk;
    static Set<Integer> result = new HashSet<>();


    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        for (int i=0; i<=N ; i++){
            graph.add(new ArrayList<>());
        }

        for (int i=0 ; i<M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }
        distance = new Long[N+1];
        Arrays.fill(distance,Long.MAX_VALUE);
        visited = new boolean[N+1];
        visited_dijk = new boolean[N+1];
        
        dijkstra();
        bfs();
        result.add(B);
        
        List<Integer> tempSet = new ArrayList<>(result);
        Collections.sort(tempSet);
        bw.write(tempSet.size() + "\n");
        for (int number : tempSet) {
            bw.write(number + " ");
        }
        bw.flush();
        bw.close();
        
    }

    public static void dijkstra(){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(o->o.cost));
        distance[A] = 0L;
        queue.offer(new Node(A,0L));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            
            if(visited_dijk[curNode.num])continue;
            visited_dijk[curNode.num] = true; 

            if (curNode.num == B)break;
            
            for (Node nextNode : graph.get(curNode.num)) {
                if (distance[nextNode.num] > distance[curNode.num] + nextNode.cost) {
                    distance[nextNode.num] = distance[curNode.num] + nextNode.cost;
                    queue.offer(new Node(nextNode.num, distance[nextNode.num]));
                }
            }
        }
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(B);
        visited[B] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node next : graph.get(cur)) {
                if (!visited[next.num]) {
                    if (distance[next.num] + next.cost == distance[cur]) {
                        visited[next.num] = true;
                        result.add(next.num);
                        queue.add(next.num);
                    }
                }
            }
        }
    }
}