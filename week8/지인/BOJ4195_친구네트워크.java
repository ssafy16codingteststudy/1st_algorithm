
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
		int T = sc.nextInt();
		
		for (int test = 0; test<T; test++) {
			int F = sc.nextInt();
			sc.nextLine();
			Set<String> friends = new TreeSet<>();
			Map<String, Integer> friendsId = new HashMap<>();
			
			String[][] input = new String[F][];
			for (int i=0;i<F;i++) {
				String temp = sc.nextLine();
				input[i] = temp.split(" ");
				friends.add(input[i][0]);
				friends.add(input[i][1]);
			}
			
			int id = 0;
			for (String f: friends)
				friendsId.put(f, id++);
			
			int[] p = new int[friends.size()];
			int[] size = new int[friends.size()];
			for (int i=0;i<friends.size();i++) {
				p[i] = i;
				size[i] = 1;
			}
			
			for (String[] line: input) {
				int a = friendsId.get(line[0]);
				int b = friendsId.get(line[1]);
				
				if (a > b) {
					int temp = a;
					a = b; b = temp;
				}
				int pa = find(p, a);
				int pb = find(p, b);
				if (pa != pb) {
					p[pb] = pa;
					size[pa] = size[pa] + size[pb];
				}
				
				System.out.println(size[pa]);
			}
			
			
		}
		
    }
}