import java.util.*;

class UserSolution {
	int playerNum;
	int leagueNum;
	ArrayList <Map <Integer, Integer>> playerList;

    void init(int N, int L, int mAbility[]) {
    	playerNum = N;
    	leagueNum = L;
    	playerList = new ArrayList<>();
    	
    	// 리그 별로 선수들 정보 받기
    	for (int i = 0; i < L; i++) {
    		Map<Integer, Integer> league = new HashMap<>();
    		for (int j = 0; j < N/L; j++) {
    			league.put(i * N/L + j, mAbility[i * N/L + j]);
    		}
    		playerList.add(league);
    	}
    	
    }

    int move() {
    	
    	int answer = 0;
    	// 정렬
    	for (int i = 0; i < leagueNum; i++) {
    		playerList.set(i, sort(playerList.get(i)));
    	}
    	
    	// 교환할 선수 ID 찾기
		int lastID;
		int firstID;
		int index = playerList.get(0).size();
    	
    	for (int i = 0; i < leagueNum - 1; i++) {
    	
    		lastID = 0;
    		firstID = 0;
    		// 0 아닐 때 -1 하는 이유 : 처음 빼고는 앞에 것들이 삭제된 후에 뒤쪽에 하나 추가되기 때문에 하나 더 빼줬다.
    		int size = (i == 0) ? index : index - 1;
    		
    		// Map으로 해서 key 인덱싱 불가 -> 처음부터 끝까지 확인해야됨
    		for (Integer ID : playerList.get(i).keySet()) {
    			lastID = ID;
    			size--;
    			if (size == 0) break;
    		}
    		
    		// 다음 리그 첫번째꺼 구하기
    		for (Integer ID : playerList.get(i + 1).keySet()) {
    			firstID = ID;
    			break;
    		}
    		
    		// 교환
    		swap(i, lastID, firstID);
        	
        	answer += (lastID + firstID);
 
    	}

        return answer;
    }

    int trade() {
    	
    	int answer = 0;
    	
    	for (int i = 0; i < leagueNum; i++) {
    		playerList.set(i, sort(playerList.get(i)));
    	}
    	
		int middleID;
		int firstID;
		int index = playerList.get(0).size()/2 + 1;
    	
    	for (int i = 0; i < leagueNum - 1; i++) {
    		middleID = 0;
    		firstID = 0;
    		int size = (i == 0) ? index : index - 1;
    		
    		for (Integer ID : playerList.get(i).keySet()) {
    			middleID = ID;
    			size--;
    			if (size == 0) break;
    		}
    		for (Integer ID : playerList.get(i + 1).keySet()) {
    			firstID = ID;
    			break;
    		}
    		
    		swap(i, middleID, firstID);
        	
        	answer += (middleID + firstID);
 
    	}
        return answer;
   	}

    // 교환 함수
    void swap(int league, int player1, int player2) {
    	int player1value = playerList.get(league).get(player1);
    	int player2value = playerList.get(league + 1).get(player2);
    	playerList.get(league).remove(player1);
    	playerList.get(league + 1).remove(player2);
    	playerList.get(league).put(player2, player2value);
    	playerList.get(league + 1).put(player1, player1value);
    }
    
    // 정렬하는 함수 (gpt도움)
    public Map<Integer, Integer> sort(Map<Integer, Integer> map) {
        return map.entrySet()
                  .stream()
                  .sorted(Comparator
                          .<Map.Entry<Integer, Integer>, Integer>comparing(Map.Entry::getValue, Comparator.reverseOrder()) // value 내림차순
                          .thenComparing(Map.Entry::getKey)) // key 오름차순
                  .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }
}
