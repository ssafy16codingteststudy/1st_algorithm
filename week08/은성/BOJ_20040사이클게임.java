import java.io.*;
import java.util.*;


public class BOJ_20040사이클게임 {

	/**
	 * 20040  사이클 게임
	 * union 메서드의 반환값을 boolean 으로 두어, 사이클 발생 즉시(false 값 발생 즉시) 탈출
	 * 그 때의 i 값을 저장해 출력하면 됨
	 */
	private static int[] parents;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        parents = new int[n];
        for (int i = 0; i < n; i++) {
        	parents[i] = i;
        }
        
        
        int result = 0;
        for(int i = 1; i <= m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	if(!union(a, b)) {
        		result = i;
        		break;
        	}
        }
        System.out.println(result);
    }
    
    private static int find(int node) {
    	if(parents[node] == node) {
    		return node;
    	}
    	return parents[node] = find(parents[node]);
    }
    
    private static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if(aRoot == bRoot) {
    		return false;
    	}
    	
    	if(aRoot < bRoot)
    		parents[bRoot] = aRoot;
    	else
    		parents[aRoot] = bRoot;
    	return true;
    }
}
