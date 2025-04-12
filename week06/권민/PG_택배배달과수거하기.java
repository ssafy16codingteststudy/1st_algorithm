class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliverIdx = n - 1;
        int pickupIdx = n - 1;
        
        while (deliverIdx >= 0 || pickupIdx >= 0) {
            // 배송할 물량이 남아있는 가장 먼 집의 인덱스 찾기
            while (deliverIdx >= 0 && deliveries[deliverIdx] == 0) {
                deliverIdx--;
            }
            // 수거할 물량이 남아있는 가장 먼 집의 인덱스 찾기
            while (pickupIdx >= 0 && pickups[pickupIdx] == 0) {
                pickupIdx--;
            }
            
            // 배송과 수거할 곳이 모두 없다면 종료
            if (deliverIdx < 0 && pickupIdx < 0) break;
            
            // 현재 이동할 최대 거리는 두 인덱스 중 큰 값에 +1한 거리
            int currentDistance = Math.max(deliverIdx, pickupIdx) + 1;
            answer += currentDistance * 2L;
            
            // 현재 택배 트럭의 용량만큼 배송 작업 수행
            int capRemaining = cap;
            for (int i = deliverIdx; i >= 0 && capRemaining > 0; i--) {
                if (deliveries[i] <= capRemaining) {
                    capRemaining -= deliveries[i];
                    deliveries[i] = 0;
                } else {
                    deliveries[i] -= capRemaining;
                    capRemaining = 0;
                }
            }
            
            // 수거 작업도 동일하게 처리
            capRemaining = cap;
            for (int i = pickupIdx; i >= 0 && capRemaining > 0; i--) {
                if (pickups[i] <= capRemaining) {
                    capRemaining -= pickups[i];
                    pickups[i] = 0;
                } else {
                    pickups[i] -= capRemaining;
                    capRemaining = 0;
                }
            }
        }
      
        return answer;
    }
}

// ==============================

import java.util.*;

class C implements Comparable<C> {
    int count;
    int dist;
    
    C (int count, int dist) {
        this.count = count;
        this.dist = dist;
    }
    
    @Override
    public int compareTo (C o) {
        return o.dist - this.dist;
    }
}

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        PriorityQueue<C> dq = new PriorityQueue<>();
        PriorityQueue<C> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) dq.offer(new C(deliveries[i], i));
            if (pickups[i] != 0) pq.offer(new C(pickups[i], i));
        }
        
        while (!dq.isEmpty() || !pq.isEmpty()) {
            int curCap = cap;
            int temp = 0;
            
            while (!dq.isEmpty() && curCap > 0) {
                C cur = dq.poll();
                temp = temp < cur.dist ? cur.dist : temp;
                
                if (cur.count >= curCap) {
                    cur.count -= curCap;
                    curCap = 0;
                    
                    if(cur.count != 0) dq.offer(cur);
                    deliveries[cur.dist] = cur.count;
                } else {
                    curCap -= cur.count;
                    deliveries[cur.dist] = 0;
                }
            }
            
            curCap = curCap > 0 ? cap - curCap : cap;
            while (!pq.isEmpty() && curCap > 0) {
                C cur = pq.poll();
                temp = temp < cur.dist ? cur.dist : temp;
                                
                if (cur.count >= curCap) {
                    cur.count -= curCap;
                    curCap = 0;
                    
                    if(cur.count != 0) pq.offer(cur);
                    pickups[cur.dist] = cur.count;
                } else {
                    curCap -= cur.count;
                    pickups[cur.dist] = 0;
                }
            }
            
            answer += (temp+1) * 2;
        }
        
        
        return answer;
    }
}
