package month4week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13975_파일합치기3 {

	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine().trim());
		for(int test = 0; test < T; test++) {
			sb = new StringBuilder();
			
			int N = Integer.parseInt(bf.readLine().trim());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			long answer = 0;
			while(pq.size() != 1) {
				Long a = pq.poll();
				Long b = pq.poll();
				Long sum = a + b;
				answer += sum;
				pq.add(sum);
			}
			sb.append(answer).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
		bf.close();

	}

}
