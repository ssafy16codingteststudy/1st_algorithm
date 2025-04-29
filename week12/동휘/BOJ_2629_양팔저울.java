package April_week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2629_양팔저울 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		List<Integer> items = new ArrayList<>();
		int weight = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			items.add(num);
			weight += num;
		}
		Collections.sort(items, Collections.reverseOrder());
		boolean[][] can = new boolean[N+1][weight+1];
		can[0][0] = true;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= weight; j++) {
				if(!can[i-1][j]) {
					continue;
				}
				can[i][j] = true;
				if(j + items.get(i-1) <= weight) {
					can[i][j+items.get(i-1)] = true;
				}
				can[i][Math.abs(j-items.get(i-1))] = true;
			}
		}

		/*for(int i = 0; i <= weight; i++) {
			System.out.print(can[N][i] + " ");
		}
		System.out.println();*/
		
		int M = Integer.parseInt(bf.readLine());
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num > weight) {
				sb.append("N ");
				continue;
			}
			if(can[N][num]) {
				sb.append("Y ");
			}else {
				sb.append("N ");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bf.close();
	}
}
