import java.io.*;
import java.util.*;

public class BOJ28081_직사각형피자 {

  static int W, H, N, M;
  static long K;
  static int[] y, x;
  static long[] diffY, diffX;
  static Map<Long, Long> cache;


  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    W = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());
    K = Long.parseLong(st.nextToken());

    N = Integer.parseInt(br.readLine());
    y = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      y[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    x = new int[M];
    for (int i = 0; i < M; i++) {
      x[i] = Integer.parseInt(st.nextToken());
    }

    cache = new HashMap<>();
    br.close();
  }

  private static void solve() {

    diffY = new long[N + 1];
    diffX = new long[M + 1];
    diffY[0] = y[0];
    diffX[0] = x[0];

    for (int i = 1; i < N; i++) {
      diffY[i] = Math.abs(y[i] - y[i - 1]);
    }
    diffY[N] = Math.abs(H - y[N - 1]);

    for (int i = 1; i < M; i++) {
      diffX[i] = Math.abs(x[i] - x[i - 1]);
    }
    diffX[M] = Math.abs(W - x[M - 1]);

    Arrays.sort(diffY);
    Arrays.sort(diffX);

    long cnt = 0;
    for (long width : diffX) {
      if (cache.containsKey(width)) {
        cnt += cache.get(width);
      } else {
        long res = bs(width);
        cnt += res;
        cache.put(width, res);
      }
    }

    System.out.println(cnt);
  }

  private static long bs(long width) {

    int left = 0;
    int right = N;
    long idx = -1;

    while (left <= right) {
      int mid = (left + right) / 2;
      long area = width * diffY[mid];
      // 피자의 면적이 K보다 큰 경우 -> 가로가 고정되어 있으니, 높이를 줄여야 한다.
      if (area > K) {
        right = mid - 1;
      }
      // 피자의 면적이 K보다 작거나 같은 경우 -> 최대 개수를 알아내기 위해 높이를 늘린다.
      else {
        left = mid + 1;
        idx = mid;
      }
    }

    return idx + 1;
  }


}
