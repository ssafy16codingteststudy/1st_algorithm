import java.io.*;
import java.util.*;

public class BOJ_25319 {
    static int n, m;
    static char[][] map;
    static String id;
    static Map<Character, List<int[]>> charPositions = new HashMap<>(); // 알파벳 별 존재하는 위치를 저장
    static Map<Character, Integer> idCount = new HashMap<>(); // id 알파벳 별로 필요한 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char c = map[i][j];
                charPositions.computeIfAbsent(c, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        id = br.readLine();

        for (char c : id.toCharArray()) {
            idCount.put(c, idCount.getOrDefault(c, 0) + 1);
        }

        int k = Integer.MAX_VALUE;
        for (char c : idCount.keySet()) {
            List<int[]> positions = charPositions.get(c);
            if (positions == null || positions.size() < idCount.get(c)) { // 해당 문자가 존재하지 않거나, 꼭 필요한 최소 갯수보다 적다면
                k = 0;
                break;
            }
            k = Math.min(k, positions.size() / idCount.get(c)); // 여러 문자들에 대해 문자열 k 생성 가능한 k의 최솟값이 문자열 만드는 최대횟수
        }

        if (k == Integer.MAX_VALUE) k = 0; // id 문자열이 공백인 경우 처리

        List<int[]> allTargets = new ArrayList<>(); // id 문자열을 최대로 만드는데 사용되는 모든 좌표 위치 리스트
        for (int i = 0; i < k; i++) {
            Map<Character, Integer> index = new HashMap<>(); // 각 문자가 몇번째 인덱스에 사용될 것인가 저장
            for (char c : idCount.keySet()) {
                index.put(c, i * idCount.get(c));
            }
            for (char c : id.toCharArray()) {
                int ptr = index.get(c);
                allTargets.add(charPositions.get(c).get(ptr));
                index.put(c, ptr + 1);
            }
        }

        List<Character> commands = new ArrayList<>();
        int x = 0, y = 0;

        for (int[] target : allTargets) {
            int tx = target[0], ty = target[1];
            appendMoves(commands, x, y, tx, ty);
            commands.add('P');
            x = tx;
            y = ty;
        }

        appendMoves(commands, x, y, n - 1, m - 1);

        System.out.println(k + " " + commands.size());
        for (char c : commands) System.out.print(c);
        System.out.println();
    }

    static void appendMoves(List<Character> commands, int x1, int y1, int x2, int y2) { // 이동 저장
        int dy = y2 - y1;
        if (dy > 0) {
            for (int i = 0; i < dy; i++) commands.add('R');
        } else if (dy < 0) {
            for (int i = 0; i < -dy; i++) commands.add('L');
        }

        int dx = x2 - x1;
        if (dx > 0) {
            for (int i = 0; i < dx; i++) commands.add('D');
        } else if (dx < 0) {
            for (int i = 0; i < -dx; i++) commands.add('U');
        }
    }
}