import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * # TRY 1 - ArrayList로 선수들 관리해서 index로 넣고 빼기 (22/25, TLE)
 *         - move와 trade에서 각 리그의 맨 뒤에 선수를 삽입한 뒤 메서드가 끝날 때마다 정렬
 *         - 아마도 정렬에서 시간이 오래 걸렸을 것 같음
 *         
 * # TRY 2 - 그렇다면 정렬에서 시간을 줄이는 방법을 선택? or 또 다른 방법 찾기?
 * 
 *         - (기각)새로운 방법? 리그 별로 min, max heap을 가지고 있기? <- heap 끼리 같은 값 관리가 안됨
 *            - 대신에 이렇게 하면 trade를 어떻게 하지?
 *            - min, max heap을 각 2개씩 관리?
 *               - 최대 최소를 구하는 heap 2개
 *               - 중간 값을 구하는 heap 2개
 *               
 *         - (포기)삽입만 잘 해주면 정렬하는 데 시간이 걸리지 않지 않을까? <- ..^^ 시간은 더 걸리고 답은 안 나오고~
 *            - 정렬 n log(n), 특정 위치 삽입 n
 *            - 대신에 이렇게 하면 ArrayList를 못 쓰기 때문에 LinkedList를 써야겠네
 *            
 * # TRY 3 - ArrayList로 관리하는 방법은 유지, 대신에 정렬을 계속 하지 않고 선수를 넣고 빼는 위치는 이분탐색 사용
 *         - 유의할 점, ArrayList는 index 기반으로 탐색이 빠른 반면, index 기반이기 때문에 앞의 값이 사라지면 전부 당겨진다.
 */

class UserSolution {
	private static class Player implements Comparable<Player> {
		int id;
		int ability;

		public Player(int id, int ability) {
			this.id = id;
			this.ability = ability;
		}

		@Override
		public int compareTo(Player o) {
			if (this.ability == o.ability)
				return this.id - o.id;
			return o.ability - this.ability;
		}
	}

	private static int playerCount;
	private static int leagueCount;
	private static List<Player>[] leagues;

	// 어떤 리그에서 어떤 선수를 뽑았는지 저장
	private static Player[][] toInput;

	void init(int N, int L, int mAbility[]) {
		playerCount = N;
		leagueCount = L;

		leagues = new ArrayList[leagueCount];
		for (int i = 0; i < leagueCount; i++) {
			leagues[i] = new ArrayList<>();
		}

		int tmp = 0, playersPerLeague = playerCount / leagueCount;
		for (int i = 0; i < leagueCount; i++) {
			for (int j = tmp; j < tmp + playersPerLeague; j++) {
				leagues[i].add(new Player(j, mAbility[j]));
			}
			tmp += playersPerLeague;

			Collections.sort(leagues[i]);
		}
	}

	int move() {
		int res = 0;

		// 열의 값이 2인 이유?
		// => 한 리그에서 선수를 최대 두 번밖에 뽑지 못하기 때문
		toInput = new Player[leagueCount][2];
		for (int i = 0; i < leagueCount - 1; i++) {
			List<Player> upperLeague = leagues[i];
			List<Player> lowerLeague = leagues[i + 1];

			// move 메서드의 경우, 맨 앞과 맨 뒤에서 뽑기 때문에 index를 사용하는 데 어려움이 없음
			Player upperWorst = upperLeague.remove(upperLeague.size() - 1);
			Player lowerBest = lowerLeague.remove(0);

			res += (upperWorst.id + lowerBest.id);

			toInput[i][0] = lowerBest;
			toInput[i + 1][1] = upperWorst;
		}

		insertPlayer();

		return res;
	}

	int trade() {
		int res = 0;
		int playersPerLeague = playerCount / leagueCount;
		int midIndex = ((playersPerLeague) + 1) / 2 - 1;

		toInput = new Player[leagueCount][2];
		for (int i = 0; i < leagueCount - 1; i++) {
			List<Player> upperLeague = leagues[i];
			List<Player> lowerLeague = leagues[i + 1];

			// trade의 경우 중간의 선수를 뽑아야하기 함
			// 첫 번째 리그를 제외하고는 본인이 하위 리그일 때 가장 잘 하는 선수가 뽑혀있는 상태임
			// 이를 상쇄하기 위해서 리그의 가장 앞에 더미 데이터를 삽입
			if (i != 0)
				upperLeague.add(0, new Player(0, 0));

			Player upperMid = upperLeague.remove(midIndex);
			Player lowerBest = lowerLeague.remove(0);

			res += (upperMid.id + lowerBest.id);

			toInput[i][0] = lowerBest;
			toInput[i + 1][1] = upperMid;

			// 리그에 대한 비교가 끝난 뒤에는 더미 데이터 제거
			if (i != 0)
				upperLeague.remove(0);
		}

		insertPlayer();

		return res;
	}

	private static void insertPlayer() {
		for (int i = 0; i < leagueCount; i++) {
			for (int j = 0; j < 2; j++) {
				if (toInput[i][j] == null)
					continue;

				int pos = Collections.binarySearch(leagues[i], toInput[i][j]);
				if (pos < 0)
					pos = -pos - 1;

				leagues[i].add(pos, toInput[i][j]);
			}
		}
	}
}

/*
 * 1. 리그의 ID 값(0 ~ L-1)이 작을수록 더 우수한 리그임
 * 
 * 2. N 명의 선수들이 존재함
 * 
 * 3. 선수의 능력은 능력 값과 ID값으로 평가됨 -> 능력값이 높을수록 좋은 선수 -> 능력값이 같으면 ID가 작을수록 좋은 선수
 * 
 * 4. init -> 앞 번호 리그부터 앞 선수를 N/L 명씩 차례대로 배치 -> 이때, N은 L의 배수이고, N/L은 항상 홀수임
 * 
 * 5. move -> 각 리그의 하위, 상위 교체
 * 
 * 6. trade -> 현재 리그의 가장 높은 선수와 바로 위 리그의 중간 선수((M+1)/2)와 트레이드
 * 
 */
