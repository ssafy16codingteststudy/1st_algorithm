package study0402;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16562 {
	static int[] parents;
	static int N, M, K;
	static int[] price;
	
	static void make() {
		parents = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		if(price[aRoot] < price[bRoot]) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		
		return true;
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		price = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < N + 1; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		make();
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (parents[i] == i) {
				answer += price[i];
			}
		}
		
		if (K < answer) {
			System.out.println("Oh no");
		}
		else System.out.println(answer);
		
	}

}
