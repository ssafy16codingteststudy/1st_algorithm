import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, List<Integer>> nodes = new HashMap<>();
        int[] answer = new int[4];
        for (int i=0;i<edges.length;i++) {
            nodes.putIfAbsent(edges[i][0], new ArrayList<>());
            nodes.putIfAbsent(edges[i][1], new ArrayList<>());
            nodes.get(edges[i][0]).add(edges[i][1]);
        }
        
        int node = 0;
        int N = nodes.keySet().size();
        // boolean[] checkCenter = new boolean[N + 1];
        Map<Integer,Boolean> checkCenter = new HashMap<>();
        
        for (int k: nodes.keySet()) {
            checkCenter.putIfAbsent(k, false);
            if (nodes.get(k).size() == 0)
                continue;
            for (int n: nodes.get(k)) {
                checkCenter.put(n, true);
            }
        }
        for (int i: checkCenter.keySet()) {
            if (!checkCenter.get(i) && nodes.get(i).size() > 1) { // my gosh
                node = i;
                break;
            }
        }
        
        answer[0] = node;
        // boolean[] visited = new boolean[N + 1];
        Map<Integer,Boolean> visited = new HashMap<>();
        for (int k: nodes.keySet()) {
            visited.putIfAbsent(k, false);
        }
        
        for (int k: nodes.get(node)) {
            visited.put(k, true);
            Deque<Integer> q = new ArrayDeque<>();
            if (nodes.get(k).size() == 0) {
                answer[2]++; // 1
                continue;
            }
            
            for (int next: nodes.get(k))
                q.addLast(next);
            while (!q.isEmpty()) {
                int now = q.pollFirst();
                if (visited.get(now)) { //서클이거나 8자
                    if (nodes.get(now).size() > 1) // 8
                        answer[3]++;
                    else
                        answer[1]++; // 0
                    break;
                } else {
                    if (nodes.get(now).size() > 1) {
                        answer[3]++;
                        break;
                    }
                    if (nodes.get(now).size() == 0) {
                        answer[2]++; // 1
                        break;
                    }
                    for (int next: nodes.get(now))
                        q.addLast(next);
                }
                visited.put(now, true);
            }
        }
        
        return answer;
    }
}