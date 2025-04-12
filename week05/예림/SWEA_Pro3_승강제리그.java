package week5.예림;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class Player implements Comparable<Player> {
    final int id;
    final int ability;

    public Player(int id, int ability) {
        this.id = id;
        this.ability = ability;
    }

    @Override
    public int compareTo(Player other) {
        if (this.ability != other.ability) return Integer.compare(other.ability, this.ability);
        return Integer.compare(this.id, other.id);
    }
}

public class SWEA_Pro3_승강제리그 {

    private int N, L;
    private List<TreeSet<Player>> leagues;

    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        leagues = new ArrayList<>();

        for (int i = 0; i < L; i++) {
            leagues.add(new TreeSet<>());
        }

        int numPlayersPerLeague = N / L;

        for (int i = 0; i < N; i++) {
            leagues.get(i / numPlayersPerLeague).add(new Player(i, mAbility[i]));
        }
    }

    int move() {
        List<Player> moveUp = new ArrayList<>();
        List<Player> moveDown = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < L - 1; i++) {
            Player bestInLower = leagues.get(i + 1).first();
            Player worstInUpper = leagues.get(i).last();

            moveUp.add(bestInLower);
            moveDown.add(worstInUpper);
        }

        for (int i = 0; i < L - 1; i++) {
            leagues.get(i + 1).remove(moveUp.get(i));
            leagues.get(i).remove(moveDown.get(i));
            leagues.get(i).add(moveUp.get(i));
            leagues.get(i + 1).add(moveDown.get(i));
            sum += moveUp.get(i).id + moveDown.get(i).id;
        }

        return sum;
    }

    int trade() {
        List<Player> tradeUp = new ArrayList<>();
        List<Player> tradeMid = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < L - 1; i++) {
            Player bestInLower = leagues.get(i + 1).first();
            int midIndex = (leagues.get(i).size() + 1) / 2 - 1;

            Iterator<Player> iterator = leagues.get(i).iterator();
            for (int j = 0; j < midIndex; j++) {
                iterator.next();
            }
            Player midInUpper = iterator.next();

            tradeUp.add(bestInLower);
            tradeMid.add(midInUpper);
        }

        for (int i = 0; i < L - 1; i++) {
            leagues.get(i + 1).remove(tradeUp.get(i));
            leagues.get(i).remove(tradeMid.get(i));
            leagues.get(i).add(tradeUp.get(i));
            leagues.get(i + 1).add(tradeMid.get(i));
            sum += tradeUp.get(i).id + tradeMid.get(i).id;
        }

        return sum;
    }
}
