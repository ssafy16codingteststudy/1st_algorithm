import java.io.*;
import java.util.*;


public class BOJ_1786_찾기 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String T = br.readLine();
    	String P = br.readLine();
    	
    	int[] lst = new int[P.length()];
    	int j = 0;

    	for (int i = 1; i < P.length(); i++) {
    	    while (j > 0 && P.charAt(i) != P.charAt(j)) {
    	        j = lst[j - 1]; // 이전 접두사 위치로 점프
    	    }
    	    if (P.charAt(i) == P.charAt(j)) {
    	        j++;
    	        lst[i] = j;
    	    }
    	}
    	
    	int cnt = 0;
    	List<Integer> answer = new ArrayList<>();
    	int ptr = 0;
    	for (int i=0;i<T.length();i++) {
    		while (ptr > 0 && T.charAt(i) != P.charAt(ptr)) {
    	        ptr = lst[ptr - 1];
    	    }
    		if (T.charAt(i) == P.charAt(ptr)) {
    			ptr++;
    			if (ptr >= P.length()) {
        			cnt++;
        			answer.add(i - ptr + 2);
        			ptr = lst[ptr - 1];
        		}
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	sb.append(cnt).append("\n");
    	for (int val: answer) {
    		sb.append(val).append(" ");
    	}
    	System.out.println(sb);
    }
}