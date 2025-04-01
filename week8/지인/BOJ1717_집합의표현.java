
import java.io.*;
import java.util.*;

public class Main {
	static int find(int[] p, int a) {
		if (p[a] != a)
			p[a] = find(p, p[a]);
		
		return p[a];
	}
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] p = new int[N + 1];
		for (int i=1;i<=N;i++) {
			p[i] = i;
		}
		
		for (int i=0;i<M;i++) {
			int com = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (a > b) {
				int t = a;
				a = b; b = t;
			}
			
			if (com == 0) {
				p[find(p, b)] = find(p, a);
			} else {
				if (find(p,b) == find(p, a))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
		
    }
}