import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int [4];
        
        Map<Integer, Integer> outDegree = new HashMap<>(); 
        Map<Integer, Integer> inDegree = new HashMap<>(); 
        
        Set <Integer> nodes = new HashSet<>();
      
        for (int[] edge : edges) {
        	outDegree.put(edge[0], outDegree.getOrDefault(edge[0], 0) + 1);
        	inDegree.put(edge[1], inDegree.getOrDefault(edge[1], 0) + 1);
        	
        	nodes.add(edge[0]);
        	nodes.add(edge[1]);
        }
        
        int root = -1;
        for (int node : outDegree.keySet()) {
        	if (!inDegree.containsKey(node) && outDegree.get(node) > 1) {
        		root = node;
        		break;
        	}
        }
        
        answer[0] = root;
        
        for (int node : nodes) {
        	if (node == root) continue;
        	if (outDegree.getOrDefault(node, 0) == 0) answer[2]++;
        	else if (outDegree.getOrDefault(node, 0) == 2 && inDegree.getOrDefault(node, 0) >= 2) answer[3]++;
        }
        
        answer[1] = outDegree.get(root) - answer[2] - answer[3];
        
        return answer;
    }
}

