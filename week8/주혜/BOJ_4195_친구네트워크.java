package study0402;

import java.io.*;
import java.util.*;

public class BJ4195 {
	static int F;
	static Map<String, String> network;
	static Map<String, Integer> parents;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testcase; t++) {
			network = new HashMap<>();
			parents = new HashMap<>();

			F = Integer.parseInt(br.readLine());
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				String f1 = st.nextToken();
				String f2 = st.nextToken();

				if (!network.containsKey(f1)) {
					network.put(f1, f1);
					parents.put(f1, 1);
				}
				if (!network.containsKey(f2)) {
					network.put(f2, f2);
					parents.put(f2, 1);
				}

				union(f1, f2);
				
				int answer =(parents.get(network.get(f1)) > parents.get(network.get(f2))) ? parents.get(network.get(f1)) : parents.get(network.get(f2));
				
				System.out.println(answer);
			}
		}
	}

	static boolean union(String f1, String f2) {
	    String p1 = find(f1);
	    String p2 = find(f2);

	    if (p1.equals(p2)) return false;

	    if (parents.get(p1) < parents.get(p2)) {
	        network.put(p1, p2);
	        parents.put(p2, parents.get(p2) + parents.get(p1));
	    } else {
	        network.put(p2, p1);
	        parents.put(p1, parents.get(p1) + parents.get(p2));
	    }
	    return true;
	}


	static String find(String f) {
	    if (!network.get(f).equals(f)) {
	        network.put(f, find(network.get(f))); // 경로 압축 적용
	    }
	    return network.get(f);
	}


}
