import java.io.*;
import java.util.*;

public class BOJ_14621나만안되는연애 {

    private static int[] mf;
    private static int[] parents;
    private static StringBuilder sb = new StringBuilder();
    /**
     *  14621 나만 안되는 연애
     *  기본적인 mst + 성별 구분 하나만 추가된 문제
     *  유니온 시도를 할 때, 두 정점의 성별이 다른가? 이거 하나만 추가하면 됨 << 이 때 대표 기준으로 비교하지 않도록 주의
     *  mst 가 불가능한 경우는 while 문을 돌면서 유효한 간선의 수를 체크 -> N - 1 개로 종료된다면 mst 임
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        parents = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
        	parents[i] = i;
        }
        
        mf = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
        	// 각 대학교를 성별에 따라 다른 값으로 배열에 저장
        	mf[i] = st.nextToken().equals("M") ? 1 : 2;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
        	
        	queue.add(new Edge(from, to, weight));
        }
        
        // MST 시작
        int cnt = 0, answer = 0;
        while(!queue.isEmpty()) {
        	Edge minEdge = queue.poll();
        	
        	if(!union(minEdge.from, minEdge.to)) {
        		continue;
        	}
        	
        	answer += minEdge.weight;
        	// 이어진 간선의 개수가 n - 1 개라면 즉시 탈출
        	if(++cnt == n - 1) break;        	
        }

        // 간선의 개수가 n - 1 개가 아니라면 MST가 아님
        System.out.println(cnt == n - 1 ? answer : -1);
    }
    
    private static int find(int node) {
    	if(parents[node] == node) {
    		return node;
    	}
    	return parents[node] = find(parents[node]);
    }
    
    private static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if(aRoot == bRoot || mf[a] == mf[b]) {
    		return false;
    	}
    	
    	if(aRoot < bRoot) {
    		parents[bRoot] = aRoot;
    	} else {
    		parents[aRoot] = bRoot;    		
    	}
    	
    	return true;
    }


    private static class Edge implements Comparable<Edge> {
    	int from;
    	int to;
    	int weight;
    	
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
    }
}
