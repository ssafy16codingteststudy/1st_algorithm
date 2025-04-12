package 건휘;

import java.util.*;
import java.io.*;

public class BOJ25634_전구상태뒤집기 {

	static int n, result = 0, maxGain, current;
	static int[] light;
	static int[] status;
	static int[] diff;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		light = new int[n];
		status = new int[n];
		diff = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			light[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			status[i] = Integer.parseInt(st.nextToken());
			if(status[i] == 1) result += light[i];
		}
		
		for(int i = 0; i < n; i++) {
			diff[i] = (status[i] == 0) ? light[i] : -light[i];
		}
		
		maxGain = diff[0];
		current = diff[0];
		
		for(int i = 1; i < n; i++) {
			current = Math.max(diff[i], current + diff[i]);
			maxGain = Math.max(maxGain, current);
		}
		
		result += maxGain;
		
		System.out.println(result);
	}

}
