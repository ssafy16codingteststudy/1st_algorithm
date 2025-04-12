import java.util.*;

class Path implements Comparable<Path> {
    int to;
    int dist;
    
    Path(int to, int dist){
        this.to = to;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Path o){
        if(this.dist == o.dist)
            return this.to - o.to;
        return this.dist - o.dist;
    }
    
}

class Solution {
    
    private List<Path>[] nodes;
    // normal = 0, gates = 1, summits = 2
    private int typeOfPath[];
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        nodes = new ArrayList[n+1];
        typeOfPath = new int[n+1];
        
        for(int i=1; i<=n; i++){
            nodes[i] = new ArrayList<>();
        }

        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        for(int i=0; i<paths.length; i++){
            int a = paths[i][0];
            int b = paths[i][1];
            int dist = paths[i][2];
            
            nodes[a].add(new Path(b, dist));
            nodes[b].add(new Path(a, dist));
        }
        
        for(int i=0; i<gates.length; i++){
            typeOfPath[gates[i]] = 1;
        }
        
        for(int i=0; i<summits.length; i++){
            typeOfPath[summits[i]] = 2;
        }
        
        for(int i=0; i<gates.length; i++){
            // System.out.println("start");
            int res[] = dijk(n, gates[i]);
            
            if(answer[1] > res[1]){
                answer[0] = res[0];
                answer[1] = res[1];
            } else if(answer[1] == res[1] && answer[0] > res[0]){
                answer[0] = res[0];
            }
        }
        
        return answer;
    }
    
    private int[] dijk(int n, int start){        
        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.offer(new Path(start, 0));
        
        int dists[] = new int[n+1];
        for(int i=1; i<=n; i++){
            dists[i] = Integer.MAX_VALUE;
        }
        dists[start] = 0;
        
        // 0: 봉우리 번호, 1: 최장 거리
        int ret[] = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        int globalMd = Integer.MAX_VALUE;
        while(!pq.isEmpty()){
            Path cur = pq.poll();
            
            if(typeOfPath[cur.to] == 2){
                ret[0] = cur.to;
                ret[1] = cur.dist;
                break;
            }
            
            for(Path p : nodes[cur.to]){
                int next = p.to;
                if(typeOfPath[next] == 1) continue;
                
                int nextCost = dists[cur.to] > p.dist ? dists[cur.to] : p.dist;
                if(dists[next] > nextCost || typeOfPath[next] == 2){
                    int md = cur.dist < p.dist ? p.dist : cur.dist;
                    
                    dists[next] = nextCost;
                    pq.offer(new Path(next, nextCost));
                }
            }
        }
        
        return ret;
    }
}

================================================================================================================================================

  import java.util.*;

class Path implements Comparable<Path> {
    int to, dist;
    
    Path(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Path o) {
        return this.dist - o.dist;
    }
}

class Solution {
    // 각 지점의 인접 정보를 저장합니다.
    private List<Path>[] nodes;
    // 0: 일반 쉼터, 1: 게이트, 2: 산봉우리
    private int[] typeOfPath;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        nodes = new ArrayList[n + 1];
        typeOfPath = new int[n + 1];
        
        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            int a = path[0], b = path[1], w = path[2];
            nodes[a].add(new Path(b, w));
            nodes[b].add(new Path(a, w));
        }
        
        // 게이트와 산봉우리 표시
        for (int gate : gates) {
            typeOfPath[gate] = 1;
        }
        for (int summit : summits) {
            typeOfPath[summit] = 2;
        }
        
        // 다중 출발점 다익스트라: 각 게이트에서 출발하여 intensity(경로에서의 최대 간선 비용)를 구함
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        PriorityQueue<Path> pq = new PriorityQueue<>();
        
        // 모든 게이트를 시작점으로 설정
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Path(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            int curNode = cur.to;
            int curCost = cur.dist;
            
            // 이미 더 좋은 경로가 있다면 스킵
            if (curCost > intensity[curNode])
                continue;
            
            // 산봉우리에서는 더 이상 경로 확장을 하지 않습니다.
            if (typeOfPath[curNode] == 2)
                continue;
            
            // 현재 정점에서 인접한 정점들을 relax
            for (Path next : nodes[curNode]) {
                int nextNode = next.to;
                // 중간에 게이트를 방문하면 안 되므로
                if (typeOfPath[nextNode] == 1)
                    continue;
                
                // intensity는 경로상의 간선 중 최대값이므로, 현재까지의 값과 다음 간선의 가중치 중 큰 값
                int newCost = Math.max(curCost, next.dist);
                if (newCost < intensity[nextNode]) {
                    intensity[nextNode] = newCost;
                    pq.offer(new Path(nextNode, newCost));
                }
            }
        }
        
        // 산봉우리 중 최소 intensity를 갖는 것을 찾되, intensity가 같다면 번호가 낮은 산봉우리를 선택
        int answerSummit = 0;
        int answerIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensity[summit] < answerIntensity) {
                answerIntensity = intensity[summit];
                answerSummit = summit;
            }
        }
        return new int[]{answerSummit, answerIntensity};
    }
}
