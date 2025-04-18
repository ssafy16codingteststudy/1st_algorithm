import java.io.*;
import java.util.*;



public class BOJ1107_리모컨 {
	static boolean[] buttons = new boolean[10];
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken());
    	
    	
    	if (N == 100) {
    		System.out.println(0);
    		return;
    	}

    	if (M == 0) {
    		System.out.println(Integer.toString(N).length() > Math.abs(N - 100) ? Math.abs(N - 100) : Integer.toString(N).length());
    		return;
    	}
    	
    	if (M == 10) {
    		System.out.println(Math.abs(N - 100));
    		return;
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	//고장났으면 true
    	for (int i=0;i<M;i++) {
    		int b = Integer.parseInt(st.nextToken());
    		buttons[b] = true;
    	}
    	
    	int min = N;
    	int minCnt = 0;
    	int minAnswer = Integer.MAX_VALUE;
    	while (min >= 0) {
    		String minN = Integer.toString(min);
    		boolean canMake = true;
    		for (int i=0;i< minN.length();i++) {
    			if (buttons[minN.charAt(i) - '0']) {
    				canMake = false;
    				break;
    			}
    		}
    		
    		if (canMake) {
    			minAnswer = minCnt + Integer.toString(min).length();
    			break;
    		}
    		minCnt++;
    		min--;
    	}
    	
    	int max = N;
    	int maxCnt = 0;
    	int maxAnswer = Integer.MAX_VALUE;
    	while (maxCnt < 1_000_000) {
    		String maxN = Integer.toString(max);
    		boolean canMake = true;
    		for (int i=0;i< maxN.length();i++) {
    			if (buttons[maxN.charAt(i) - '0']) {
    				canMake = false;
    				break;
    			}
    		}
    		
    		if (canMake) {
    			maxAnswer = maxCnt + Integer.toString(max).length();
    			break;
    		}
    		maxCnt++;
    		max++;
    	}
    	
    	int pmAnswer = Math.abs(N - 100);
    	int answer1 = minAnswer > maxAnswer ? maxAnswer : minAnswer;
    	System.out.println(answer1 > pmAnswer ? pmAnswer : answer1);
    	
    }
}