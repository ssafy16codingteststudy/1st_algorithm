package May1;

import java.util.*;

public class PG_합승택시요금 {
	

	class Solution {
	    public int solution(int n, int s, int a, int b, int[][] fares) {
	        int size = fares.length;
	        int[][] map = new int[n+1][n+1];
	        for(int i =0; i <= n; i++){
	            for(int j = 0; j <= n; j++){
	                map[i][j] = 123456789;
	            }
	        }
	        for(int i = 0; i < size; i++){
	            int start = fares[i][0];
	            int end = fares[i][1];
	            int cost = fares[i][2];
	            map[start][end] = cost;
	            map[end][start] = cost;
	        }
	        for(int k = 1; k <= n; k++){
	            for(int i = 1; i <= n; i++){
	                for(int j = 1; j <= n; j++){
	                    if(k == i || k == j){
	                         continue;
	                    }
	                    if(map[i][j] > map[i][k] + map[k][j]){
	                        map[i][j] = map[i][k] + map[k][j];
	                    }
	                }
	            }
	        }

	        int answer = 123456789;
	        answer = Math.min(answer, map[s][a] + map[s][b]);
	        answer = Math.min(answer, map[s][a] + map[a][b]);
	        answer = Math.min(answer, map[s][b] + map[b][a]);
	        for(int i = 1; i <= n; i++){
	            if(i == a || i == b){
	                continue;
	            }
	            if(answer > map[s][i] + map[i][a] + map[i][b]){
	                answer = map[s][i] + map[i][a] + map[i][b];
	            }
	        }
	        return answer;
	    }
	}
}
