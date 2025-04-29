package priorityQueue;

import java.util.*;
import java.io.*;

public class BOJ_19598 {
	
	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			return Integer.compare(this.start, o.start);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end);
		}
		Arrays.sort(meetings);	// 시작 시간 기준 정렬
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(meetings[0].end);	// 첫 회의 삽입
		
		for(int i = 1; i < n; i++) {
			if(meetings[i].start >= pq.peek()) {
				pq.poll();
			}
			pq.add(meetings[i].end);
		}
		
		System.out.println(pq.size());
	}

}