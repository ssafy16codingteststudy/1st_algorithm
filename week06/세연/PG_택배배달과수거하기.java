package week6.세연;

class PG_택배배달과수거하기 {
    static int [] deliver, pickup;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        deliver = deliveries.clone(); pickup = pickups.clone();
        long answer = 0;
        int maxD = -1, maxP = -1;
        // 가장 멀리까지 가야하는 경우 구해주기
        // 가장 먼 곳의 업무부터 수행해주면 되니까! 
        for (int i = n-1; i > -1; i--){ 
            if (maxD == -1 && deliveries[i] > 0) maxD = i; 
            if (maxP == -1 && pickups[i] > 0) maxP = i;
            if (maxP != -1 && maxD != -1) break;
        }
        while (maxD > -1 || maxP > -1){ // 배달과 수거 모두 완료할 때 까지
            int dst = Math.max(maxD, maxP); // 이동해야하는 거리 = task남아있는 집 중 가장 먼 곳
            answer += (dst+1) * 2; // 왔다 갔다
            if (maxD > -1) // 만일 배달 업무 남아있으면
                maxD = update(cap, maxD, 1);
            if (maxP > -1) // 만일 수거 업무 남아있으면
                maxP = update(cap, maxP, 0);
        }
        
        return answer;
    }
    
    static int update(int cap, int maxIdx, int type){
        if (type == 1){ // 배달업무
            while (maxIdx > -1) { // 남은 집이 없으면 종료
                if (deliver[maxIdx] > cap) { // 옮겨야하는 물건 개수가 수용량보다 큰 경우
                    deliver[maxIdx] -= cap; // 남은 물건 개수 업데이트
                    cap = 0; // 수용량 모두 찼음을 업데이트
                    break; // 더이상 물건을 수용할 수 없으므로 종료
                }
                else if (deliver[maxIdx] <= 0) { // 남은 물건 개수가 없는 경우 (배달 요청이 없었던 집인 경우)
                    maxIdx--; // 한 칸 가까운 집이 배달 업무가 남아있는 가장 먼 집이 됨
                }
                else { // 옮겨야하는 물건 개수가 수용량 이하 경우
                    cap -= deliver[maxIdx]; // 수용량 업데이트
                    deliver[maxIdx] = 0; // 남은 물건 없음을 업데이트
                    maxIdx--; // 한 칸 가까운 집이 배달 업무가 남아있는 가장 먼 집이 됨
                }
            }
        }
        else { // 수거 업무
            while (maxIdx >-1) { // 배달과 로직이 동일
                if (pickup[maxIdx] > cap) {
                    pickup[maxIdx] -= cap;
                    cap = 0;
                    break;
                }
                else if (pickup[maxIdx] <= 0) {
                    maxIdx--;
                }
                else {
                    cap -= pickup[maxIdx];
                    pickup[maxIdx] = 0;
                    maxIdx--;
                }
            }
        }
        return maxIdx;
    }
}
