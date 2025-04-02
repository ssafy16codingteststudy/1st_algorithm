import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1717_집합의표현 {
	static int n, m;
	static int [] parents;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		make();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (cal == 0) {
				union(a, b);
			} else {
				if (find(a) == find(b)) System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}

	static void make() {
		parents = new int[n+1];
		for (int i = 0 ; i < n + 1; i++) {
			parents[i] = i;
		}
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		
		return true;
	}

	static int find(int a) {
		if (parents[a] == a) return a;
		return find(parents[a]);
	}
}
