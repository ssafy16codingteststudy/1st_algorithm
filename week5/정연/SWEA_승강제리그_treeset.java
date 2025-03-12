import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Player implements Comparable<Player> {
	int no;
	int ab;

	public Player(int no, int ab) {
		this.no = no;
		this.ab = ab;
	}

	@Override
	public String toString() {
		return "P " + no + "-" + ab + " |";
	}

	@Override
	public int compareTo(Player o) {
		if (o.ab != this.ab) {
			return o.ab - this.ab;
		}
		return this.no - o.no;
	}
}

class UserSolution {

	TreeSet<Player>[] arr;
	int N, L, LperN;
	Player[] midPlayers;

	void init(int n, int l, int mAbility[]) {
		N = n;
		L = l;
		LperN = (int) N / L;
		midPlayers = new Player[L];
		arr = new TreeSet[L];
		for (int i = 0; i < L; i++) {
			arr[i] = new TreeSet<>();
		}
		int idx = 0;
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < LperN; j++) {
				arr[i].add(new Player(idx, mAbility[idx]));
				idx++;
			}
		}

		for (int i = 0; i < L; i++) {
			int j = 0;
			for (Player p : arr[i]) {
				if (j == LperN / 2) {
					midPlayers[i] = p;
					break;
				}
				j++;
			}
		}
	}

	/**
	 * MOVE
	 * 
	 * @return
	 */
	int move() {
		int result = 0;
		List<Player> updatePlayer = new ArrayList<>();
		for (int i = 0; i < L - 1; i++) {

			Player strong = arr[i + 1].first();
			Player week = arr[i].last();
			result += strong.no + week.no;

			updatePlayer.add(strong);
			updatePlayer.add(week);
			arr[i].pollLast();
			if (i > 0) {
				arr[i].pollFirst();
			}
		}
		arr[L - 1].pollFirst();

		int updateIdx = 0;
		// 리그에 선수 추가 해주기
		for (int i = 0; i < L - 1; i++) {
			arr[i].add(updatePlayer.get(updateIdx++));
			arr[i + 1].add(updatePlayer.get(updateIdx++));
		}
		// 각 리그 중간급 선수 변동
		// 1. 0번 리그 : 신입이 중간급 보다 큰경우 -> 중간급 선수보다 좀더 잘하는 선수가 중간급이 된다.
		if (midPlayers[0].compareTo(updatePlayer.get(0)) > 0) {
			midPlayers[0] = arr[0].lower(midPlayers[0]);
		}
		// 2. 1~L-2번 리그의 경우
		updateIdx = 1;
		for (int i = 1; i < L - 1; i++) {
			// 신입 2명이 중간보다 작은경우 -> 중간급 선수보다 좀더 못하는 선수가 중간급이 된다.
			if (midPlayers[i].compareTo(updatePlayer.get(updateIdx)) < 0
					&& midPlayers[i].compareTo(updatePlayer.get(updateIdx + 1)) < 0) {
				midPlayers[i] = arr[i].higher(midPlayers[i]);
			}
			// 신입 2명이 중간보다 큰경우 -> 중간급 선수보다 좀더 잘하는 선수가 중간급이 된다.
			else if (midPlayers[i].compareTo(updatePlayer.get(updateIdx)) > 0
					&& midPlayers[i].compareTo(updatePlayer.get(updateIdx + 1)) > 0) {
				midPlayers[i] = arr[i].lower(midPlayers[i]);
			}
			updateIdx += 2;
		}
		// 3. L-1 번 리그 : 신입이 중간급 보다 작은 경우 -> 중간급 보다 좀더 못하는 선수가 중간급이 된다.
		if (midPlayers[L - 1].compareTo(updatePlayer.get(L)) < 0) {
			midPlayers[L - 1] = arr[L - 1].higher(midPlayers[L - 1]);
		}
		return result;
	}

	/**
	 * TRADE
	 * 
	 * @return
	 */
	int trade() {

		int result = 0;
		List<Player> updatePlayer = new ArrayList<>();
		for (int i = 0; i < L - 1; i++) {
			Player strong = arr[i + 1].first();
			Player mid = midPlayers[i];
			// 중간급 1칸 못하는 선수로 밀어두기
			midPlayers[i] = arr[i].higher(midPlayers[i]);
			result += strong.no + mid.no;
			updatePlayer.add(strong);
			updatePlayer.add(mid);
			arr[i].remove(mid);
			arr[i + 1].pollFirst();
		}

		int updateIdx = 0;

		// 리그에 선수 재배치 해주기
		for (int i = 0; i < L - 1; i++) {
			arr[i].add(updatePlayer.get(updateIdx++));
			arr[i + 1].add(updatePlayer.get(updateIdx++));
		}

		// 중간값 설정
		// 1. 0번 리그 : 신입이 임시 중간급 보다 잘하는 경우 조금더 잘하는 선수가 중간급이 된다.
		if (midPlayers[0].compareTo(updatePlayer.get(0)) > 0) {
			midPlayers[0] = arr[0].lower(midPlayers[0]);
		}
		// 2. 1~L-2번 리그
		updateIdx = 1;
		for (int i = 1; i < L - 1; i++) {
			// 신입 둘다 임시 중간급 보다 잘하는 경우 -> 좀더 잘하는 선수가 중간급이 된다.
			if (midPlayers[i].compareTo(updatePlayer.get(updateIdx)) > 0
					&& midPlayers[i].compareTo(updatePlayer.get(updateIdx + 1)) > 0) {
				midPlayers[i] = arr[i].lower(midPlayers[i]);
			}
			// 신입 둘다 임시 중간급 보다 못하는 경우 -> 좀더 못하는 선수가 중간급이 된다.
			else if (midPlayers[i].compareTo(updatePlayer.get(updateIdx)) < 0
					&& midPlayers[i].compareTo(updatePlayer.get(updateIdx + 1)) < 0) {
				midPlayers[i] = arr[i].higher(midPlayers[i]);
			}
			updateIdx += 2;
		}
		// 3. L-1 번 리그 : 신입이 기존 중간급 보다 못하는 경우 -> 좀더 못하는 선수가 중간급이 된다.
		if (midPlayers[L - 1].compareTo(updatePlayer.get(L)) < 0) {
			midPlayers[L - 1] = arr[L - 1].higher(midPlayers[L - 1]);
		}
		return result;
	}
}
