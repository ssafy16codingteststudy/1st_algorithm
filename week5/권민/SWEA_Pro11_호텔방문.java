import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int to;
	int weight;

	public Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}

}

class UserSolution {

	private static int hotelCount, brandCount;
	private static int hotels[], dist[];
	private static boolean[] isAvailable;

	private static List<Node>[] brands;

	void init(int N, int mBrands[]) {
		hotelCount = N;
		brandCount = 0;

		boolean[] isBrand = new boolean[51];
		isAvailable = new boolean[51];

		hotels = new int[hotelCount];
		brands = new ArrayList[hotelCount];
		for (int i = 0; i < hotelCount; i++) {
			brands[i] = new ArrayList<>();

			hotels[i] = mBrands[i];
			isAvailable[mBrands[i]] = true;

			if (isBrand[hotels[i]])
				continue;

			isBrand[hotels[i]] = true;
			brandCount++;
		}
	}

	void connect(int mHotelA, int mHotelB, int mDistance) {
		brands[mHotelA].add(new Node(mHotelB, mDistance));
		brands[mHotelB].add(new Node(mHotelA, mDistance));
	}

	int merge(int mHotelA, int mHotelB) {
		int res = 0;

		int ba = hotels[mHotelA], bb = hotels[mHotelB];
		for (int i = 0; i < hotelCount; i++) {
			if (hotels[i] == bb) {
				hotels[i] = ba;
			}

			if (hotels[i] == ba) {
				res++;
			}
		}

		return res;
	}

	int move(int mStart, int mBrandA, int mBrandB) {
		int v[] = dijk(mStart, mBrandA, mBrandB);
		return v[0] + v[1];
	}

	private static int[] dijk(int start, int mBrandA, int mBrandB) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist = new int[hotelCount];
		for (int i = 0; i < hotelCount; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		pq.offer(new Node(start, 0));
		dist[start] = 0;

		int[] res = new int[2];
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (res[0] == 0 && cur.to != start && hotels[cur.to] == mBrandA) {
				res[0] = dist[cur.to];
			} else if (res[1] == 0 && cur.to != start && hotels[cur.to] == mBrandB) {
				res[1] = dist[cur.to];
			}

			if (res[0] != 0 && res[1] != 0)
				break;

			for (Node next : brands[cur.to]) {
				int nTo = next.to;
				int nWeight = next.weight;

				if (dist[nTo] > dist[cur.to] + nWeight) {
					dist[nTo] = dist[cur.to] + nWeight;
					pq.offer(new Node(nTo, dist[nTo]));
				}
			}
		}

		return res;
	}
}
