
import java.io.*;
import java.util.*;


public class BOJ1039_교환 {
	static int K;
	
	static int convert(int a, int b, String N) {
		int A = (int) (((N.charAt(a)) - '0') * Math.pow(10, N.length() - a - 1));
		int B = (int) (((N.charAt(b)) - '0') * Math.pow(10, N.length() - b - 1));
//		System.out.println("A: " + A +"B: " + B);
		int answer = Integer.parseInt(N) - A - B;
		
		answer += ((N.charAt(b)) - '0') * Math.pow(10, N.length() - a - 1);
		answer += ((N.charAt(a)) - '0') * Math.pow(10, N.length() - b - 1);
//		System.out.println(a + " " + b + " " +  "BEFORE: " + N + " AFTER: " + answer);
		return answer;
	}
	// 0 1 2 3 4 5
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	String N = st.nextToken();
    	K = Integer.parseInt(st.nextToken());
    	
    	Deque<int[]> q = new ArrayDeque<>();
    	
    	int max = -1;
    	q.addLast(new int[] {Integer.parseInt(N), 0});
    	Map<Integer, Integer> map = new HashMap<>();
    	while (!q.isEmpty()) {
    		int[] now = q.pollFirst();
    		
    		if (now[1] == K) {
    			max = Math.max(max, now[0]);
    			continue;
    		} else if (now[1] > K)
    			continue;
    		
    		for (int i=0;i<N.length();i++) {
    			for (int j=i+1;j<N.length();j++) {
    				if (Integer.toString(now[0]).charAt(j) == '0' && i==0)
    					continue;
    				int next = convert(i, j, Integer.toString(now[0]));
    				if (!map.containsKey(next)) {
    					q.addLast(new int[] {next, now[1] + 1});
    					map.put(next, now[1] + 1);
    				} else {
    					if ((K - map.get(next)) % 2 != 0)
    						map.put(next, now[1] + 1);
    				}
    			}
    		}
    	}
    	
    	for (int key: map.keySet()) {
    		int num = map.get(key);
    		if ((K - num) % 2 == 0)
    			max = Math.max(max, key);
    	}
    	System.out.println(max);
    }
}