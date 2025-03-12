import java.util.Arrays;

class Player implements Comparable<Player> {
	int no;
	int ab;

	public Player(int no, int ab) {
		this.no = no;
		this.ab = ab;
	}

	@Override
	public String toString() {
		return "P [no:" + no + ", ab:" + ab + "]";
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

	Player[][] arr;
	int N, L, LperN;

	void init(int n, int l, int mAbility[]) {
		N = n;
		L = l;
		LperN = (int) N / L;
		arr = new Player[L][LperN];
		int idx = 0;
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < LperN; j++) {
				arr[i][j] = new Player(idx++, mAbility[i * LperN + j]);
			}
		}
		for (Player[] p : arr) {
			Arrays.sort(p);
		}
	}

	int move() {
		int result = 0;
		for (int i = 0; i < L - 1; i++) {
			result += arr[i][LperN - 1].no + arr[i + 1][0].no;
			swap(i, LperN - 1, i + 1, 0);
		}
		for (Player[] p : arr) {
			Arrays.sort(p);
		}
		return result;
	}

	int trade() {
		int result = 0;
		for (int i = 0; i < L - 1; i++) {

			result += arr[i][LperN / 2].no + arr[i + 1][0].no;
			swap(i, LperN / 2, i + 1, 0);
		}
		for (Player[] p : arr) {
			Arrays.sort(p);
		}
		return result;
	}

	void swap(int al, int aidx, int bl, int bidx) {
		Player temp = arr[al][aidx];
		arr[al][aidx] = arr[bl][bidx];
		arr[bl][bidx] = temp;
	}
}