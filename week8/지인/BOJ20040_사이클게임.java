
import java.io.*;
import java.util.*;

public class Main {
	static int find(int[] p, int now) {
		if (p[now] != now)
			p[now] = find(p, p[now]);
		return p[now];
	}
	
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] p = new int[N];
		for (int i=0;i<N;i++) {
			p[i] = i;
		}
		
		int result = 0;
		
		for (int i=0;i<M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			int pa = find(p, a);
			int pb = find(p, b);
			
			if (pa == pb) {
				result = i+1;
				break;
			} else {
				if (a > b) {
					int temp = a;
					a = b;
					b = temp;
				}
				p[find(p, b)] = find(p, a);
			}
		}
		
		System.out.println(result);
		
    }
}