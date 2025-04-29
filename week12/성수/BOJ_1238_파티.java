package week12.성수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {
    static int N, M, X; //마을의 수(1~1000), 단방향 도로 수(1~10,000), 모일 마을
    static int[] se; //갈 때 시간
    static int[] es; //올 때 시간.
    static List<int[]>[] goWay; //x에서 가는 길
    static List<int[]>[] comeWay; //x까지 오는 길
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        se = new int[N+1]; Arrays.fill(se, INF);
        es = new int[N+1]; Arrays.fill(es, INF);
        goWay = new ArrayList[N+1];
        comeWay = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            goWay[i] = new ArrayList<>();
            comeWay[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            goWay[u].add(new int[] {v, w}); //원래 길, x에서 각 집까지 가는 길에 이용
            comeWay[v].add(new int[] {u, w}); //전치 한 길, x 까지 온 길을 반대로 저장해서 출발지에서 x 까지 가는 길을 반대로 타고 갈 수 있게 함
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> {
            return Integer.compare(n1[1], n2[1]);
        });
        pq.add(new int[] {X, 0});
        se[X] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(se[cur[0]] < cur[1]) continue;

            for(int[] nxt : comeWay[cur[0]]){
                if(se[nxt[0]] <= se[cur[0]] + nxt[1]) continue;
                se[nxt[0]] = se[cur[0]] + nxt[1];
                pq.add(new int[] {nxt[0], se[nxt[0]]});
            }
        }
        pq.add(new int[] {X, 0});
        es[X] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(es[cur[0]] < cur[1]) continue;

            for(int[] nxt : goWay[cur[0]]){
                if(es[nxt[0]] <= es[cur[0]] + nxt[1]) continue;
                es[nxt[0]] = es[cur[0]] + nxt[1];
                pq.add(new int[] {nxt[0], es[nxt[0]]});
            }
        }
        int max = 0;
        for(int i=1; i<=N; i++){
            max = Math.max(max, se[i] + es[i]);
        }
        System.out.print(max);
        br.close();
    }
}
