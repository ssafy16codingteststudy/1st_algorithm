import java.util.*;

public class UserSolution {

    private int N;
    private int L;
    private int leagueSize;                       // N / L 값을 저장
    private List<TreeSet<Integer>> leagueList;    // 각 리그를 TreeSet으로 저장, 전체 리그는 List에서 관리

    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        leagueSize = N / L;
        leagueList = new ArrayList<>();

        for (int i = 0; i < L; i++) {
            // 오름차순 정렬을 하는 TreeSet 생성
            leagueList.add(new TreeSet<>(Comparator.reverseOrder()));
            TreeSet<Integer> league = leagueList.get(i);

            for (int j = 0; j < leagueSize; j++) {
                int id = (leagueSize * i) + j;
                int ability = mAbility[id];
                // 좌표 압축
                // 이렇게 한 이유? Player class 를 생성하는 것 보다 시간 절약이 될 것이라 생각했음
                // 하지만 실제로 테스트 해보니, 드라마틱한 시간 절약이 되진 않았다.
                // (약 1.6초 -> 1.5초)
                league.add(ability * N + (N - (id + 1)));
            }
        }
    }

    int move() {
        Deque<Integer> deque = new ArrayDeque<>();
        int sumId = 0;

        // 못하는 선수 뽑기
        for (int i = 0; i < L - 1; i++) {
            TreeSet<Integer> league = leagueList.get(i);
            Integer player = league.pollLast();
            deque.add(player);

            // 좌표 반환하여 sum 에 저장
            sumId += (N - (player % N)) - 1;
        }

        // 잘하는 선수 뽑아 넘기기
        for (int i = 1; i < L; i++) {
            TreeSet<Integer> nextLeague = leagueList.get(i - 1);
            TreeSet<Integer> league = leagueList.get(i);

            // 1. 뽑고 넘기기
            Integer player = league.pollFirst();
            nextLeague.add(player);

            // 2. 못하는 선수 넣기
            league.add(deque.pollFirst());

            // 3. 좌표 반환하여 sum 에 저장
            sumId += (N - (player % N)) - 1;
        }

        return sumId;
    }

    int trade() {
        Deque<Integer> deque = new ArrayDeque<>();
        int sumId = 0;

        // 못하는 선수 뽑기
        for (int i = 0; i < L - 1; i++) {
            TreeSet<Integer> league = leagueList.get(i);

            // iterator 접근을 하면, treeSet도 순차 접근이 가능하다. (leagueSize / 2) 번만에 중간값 찾기 가능
            Iterator<Integer> iterator = league.iterator();
            Integer player = 0;
            for (int j = 0; j < (leagueSize + 1) / 2; j++) {
                player = iterator.next();
            }

            league.remove(player);
            deque.add(player);

            // 좌표 반환하여 sum 에 저장
            sumId += (N - (player % N)) - 1;
        }

        // 잘하는 선수 뽑아 넘기기
        for (int i = 1; i < L; i++) {
            TreeSet<Integer> nextLeague = leagueList.get(i - 1);
            TreeSet<Integer> league = leagueList.get(i);

            // 1. 뽑고 넘기기
            Integer player = league.pollFirst();
            nextLeague.add(player);

            // 못하는 선수 넣기
            league.add(deque.pollFirst());

            // 좌표 반환하여 sum 에 저장
            sumId += (N - (player % N)) - 1;
        }

        return sumId;
    }

}
