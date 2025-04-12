class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int idx = -1;
        
        for (int i=0;i<n;i++) {
            if (deliveries[i] != 0 || pickups[i] != 0) {
                idx = i;
            }
        }
        
        
        while (idx >= 0) {
            int capD = cap;
            int capP = cap;
            
            int idxD = idx;
            int idxP = idx;
            answer += (idx + 1) * 2;
            while (capD > 0 && idxD >= 0) {
                if (deliveries[idxD] > 0) {
                    if (deliveries[idxD] > capD) {
                        deliveries[idxD] -= capD;
                        capD = 0;
                    } else {
                        capD -= deliveries[idxD];
                        deliveries[idxD] = 0;
                        idxD--;
                    }
                } else
                    idxD--;
            }
            
            while (capP > 0 && idxP >= 0) {  
                if (pickups[idxP] > 0) {
                    if (pickups[idxP] > capP) {
                        pickups[idxP] -= capP;
                        capP = 0;
                    } else {
                        capP -= pickups[idxP];
                        pickups[idxP] = 0;
                        idxP--;
                    }
                    
                } else
                    idxP--;
            }
            while(idxD > 0 && deliveries[idxD] == 0)
                idxD--;
            while(idxP >= 0 && pickups[idxP] == 0)
                idxP--;
            idx = Math.max(idxP, idxD);
        }
        
        
        
        return answer;
    }
}