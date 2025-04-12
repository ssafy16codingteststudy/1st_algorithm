
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	
	public static boolean canGo(Map<Integer, List<int[]>> arr, int limit, int start, int end) {
		boolean[] visited = new boolean[N + 1];
		Deque<Integer> q = new ArrayDeque<>();
		
		q.addLast(start);
		while (!q.isEmpty()) {
			int now = q.pollFirst();
			if (now == end)
				return true;
			for (int[] next: arr.get(now)) {
				if (next[1] >= limit && !visited[next[0]]) {
					q.addLast(next[0]);
					visited[next[0]] = true;
				}
			}
		}
		
		return false;
	}
	
	
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        int M = sc.nextInt();
        Map<Integer, List<int[]>> arr = new HashMap<>();
        
        int min = 1_000_000_001;
        int max = 0;
        
        for (int i=0;i<M;i++) {
        	int a = sc.nextInt();
        	int b = sc.nextInt();
        	int cost = sc.nextInt();
        	
        	arr.putIfAbsent(a, new ArrayList<>());
        	arr.putIfAbsent(b, new ArrayList<>());
        	arr.get(a).add(new int[] {b, cost});
        	arr.get(b).add(new int[] {a, cost});
        	min = Math.min(min, cost);
        	max = Math.max(max, cost);
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        
        
        while(min <= max) {
        	int mid = (min + max) / 2;
        	
        	if (canGo(arr, mid, start, end)) {
        		min = mid + 1;
        	} else {
        		max = mid - 1;
        	}
        	
        }
        
        System.out.println(min - 1);
        //리미트 설정해서 그 값으로 지나갈 수 있는지 보기
    }
}
