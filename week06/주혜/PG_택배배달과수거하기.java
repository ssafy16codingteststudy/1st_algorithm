class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        long d = 0l;
        long p = 0l;
        int index =  n - 1;
        
        for (int i = 0; i < n; i++) {
        	d += deliveries[i];
        	p += pickups[i];
        }
        
        while(d > 0 || p > 0) {
        	
        	int objd = cap;
        	int objp = cap;
        	
        	while (index >= 0 && deliveries[index] == 0 && pickups[index] == 0) {
        		index--;
        		if (index < 0) return answer;
        	}
        	
        	for (int i = index; i >= 0; i--) {
                if (objd != 0 && d != 0 && deliveries[i] != 0){
                	if (objd > deliveries[i]) {
        				objd -= deliveries[i];
        				d -= deliveries[i];
        				deliveries[i] = 0;
        			} else if (objd == deliveries[i]) {
        				d -= objd;
        				objd = 0;
    					deliveries[i] = 0;
        			} else {
        				deliveries[i] -= objd;
        				d -= objd;
        				objd = 0;
        			}
                }
        		
        		if (objp != 0 && p != 0 && pickups[i] != 0){
        			if (objp > pickups[i]&& pickups[i] != 0) {
        				objp -= pickups[i];
        				p -= pickups[i];
        				pickups[i] = 0;
        			} else if (objp == pickups[i]) {
        				p -= objp;
        				objp = 0;
        				pickups[i] = 0;
        			} else {
        				pickups[i] -= objp;
        				p -= objp;
        				objp = 0;
        			}
                }
                
                if (objd == 0 && objp == 0) break;
                if (d == 0 && p == 0) break;
        		
        	}
    		answer += (index + 1) * 2;
        }
        return answer;
    }
}



class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        long deliverPending = 0;
        long pickupPending = 0;
        
        // 가장 먼 집부터 0번 집까지 누적하면서 처리
        for (int i = n - 1; i >= 0; i--) {
            deliverPending += deliveries[i];
            pickupPending += pickups[i];
            
            // 현재 집까지 처리해야 할 상자가 없다면 넘어감
            if (deliverPending == 0 && pickupPending == 0) continue;
            
            // 필요한 회수를 계산 (올림 나눗셈)
            long trips = Math.max((deliverPending + cap - 1) / cap, (pickupPending + cap - 1) / cap);
            
            // 현재 집의 위치를 기준으로 왕복 거리 추가
            answer += (i + 1) * 2 * trips;
            
            // 한 번의 왕복에 처리 가능한 양만큼 차감
            deliverPending -= trips * cap;
            pickupPending -= trips * cap;
        }
        return answer;
    }
}
