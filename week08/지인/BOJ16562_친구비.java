
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
		int k = sc.nextInt();
		int[] friendMoney = new int[N + 1];
		int[] p = new int[N + 1];
		for (int i=1;i<=N;i++) {
			p[i] = i;
			friendMoney[i] = sc.nextInt();
		}
			
		for (int i=0;i<M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if (a > b) {
				int temp = a;
				a = b;
				b = temp;
			}
			
			p[find(p,b)] = find(p, a);
		}

		for (int i = N;i > 0;i--) {
			p[i] = find(p,i);
		}
		
		int result = 0;
		for (int i=1;i<=N;i++) { //이거 존재하는 부모들로만 하게 최적화도 가능
			int min = 10001;
			for (int j=1;j<=N;j++) {
				if (p[j] == i) {
					min = Math.min(min, friendMoney[j]);
				}
			}
			if (min != 10001)
				result += min;
		}
		
		if (result <= k)
			System.out.println(result);
		else
			System.out.println("Oh no");
    }
}