package test;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, start, end;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList <Map <Integer, Integer>> map = new ArrayList<>();
		
		int max = 0;
		
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			max = (max < C) ? C : max;
		
			graph.get(A).add(new int[]{B, C});
            graph.get(B).add(new int[]{A, C});
		
		}
		
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

		int left = 1;
		int right = max;
		int result = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (canCross(mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
		}
		System.out.println(result);
	}

    static boolean canCross(int weight) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == end) return true;

            for (int[] next : graph.get(now)) {
                int nextNode = next[0];
                int nextWeight = next[1];

                if (!visited[nextNode] && nextWeight >= weight) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }
            }
        }

        return false;
    }

}
