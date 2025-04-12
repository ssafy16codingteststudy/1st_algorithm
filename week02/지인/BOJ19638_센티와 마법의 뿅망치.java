

package main;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int H = sc.nextInt();
		int T = sc.nextInt();

		PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b.compareTo(a));
		
		for (int i=0;i<N;i++) {
			q.add(sc.nextInt());
		}
		
		int used = 0;
		while (used < T) {
			int now = q.poll();
			if (now < H) {
				System.out.println("YES");
				System.out.println(used);
				break;
			} else {
				if (now > 1)
					now = now / 2;
				q.add(now);
				used++;
			}
		}
		
		if (used == T) {
			int now = q.poll();
			if (now < H) {
				System.out.println("YES");
				System.out.println(used);
			} else {
				System.out.println("NO");
				System.out.println(now);
			}
		}
		
	}	
}