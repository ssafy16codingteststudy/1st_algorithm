package april_week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ18119_단어암기 {

	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] words = new int[N];
		int[] changeWords = new int[N];
		for(int i = 0; i < N; i++) {
			String s = bf.readLine();
			int word = 0;
			for(int j = 0; j < s.length(); j++) {
				int alpabet = 1 << (s.charAt(j)-'a');
				if((word & alpabet) == 0) {
					word += (alpabet);
				}
				
			}
			words[i] = word;
			changeWords[i] = word;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			char b = st.nextToken().charAt(0);
			int count = 0;
			int alpa = b - 'a';
			if(a == 1) {
				for(int j = 0; j < N; j++) {
					if((changeWords[j] & (1 << alpa)) != 0) {
						changeWords[j] -= (1 << alpa);
					}
					if((words[j] & changeWords[j]) == words[j]) {
						count++;
					}
				}
			}else {
				for(int j = 0; j < N; j++) {
					changeWords[j] = changeWords[j] | (1 << alpa);
					if((words[j] & changeWords[j]) == words[j]) {
						count++;
					}
				}
			}
			
			sb.append(count).append("\n");
			
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		bf.close();
	
	}
}
