package 용범;

import java.util.*;

class UserSolution {

  static class Player implements Comparable<Player> {

    int id, ability; // idx: 선수 ID, ability: 선수 능력

    Player(int id, int ability) {
      this.id = id;
      this.ability = ability;
    }

    @Override
    public int compareTo(Player o) {
      if (this.ability != o.ability) {
        return o.ability - this.ability; // 선수 능력 기준으로 내림차순 정렬
      }
      return this.id - o.id; // 선수 ID 기준으로 오름차순 정렬
    }
  }

  static int N, L, SIZE;
  static int[] mAbility;
  static List<TreeSet<Player>> league;
  static TreeSet<Player> player;
  static ArrayDeque<Player> upgrade;
  static ArrayDeque<Player> downgrade;

  void init(int N, int L, int[] mAbility) {
    // 초기화 부분
    UserSolution.N = N; // N: 선수 숫자
    UserSolution.L = L; // L: 리그의 숫자
    UserSolution.mAbility = new int[N];

    SIZE = N / L; // SIZE: 한 리그의 크기
    player = new TreeSet<>(); // 선수 정보 리스트
    league = new ArrayList<>();  // 리그 정보 리스트

    int cnt = 0;
    for (int i = 0; i < N; i++) {
      mAbility[i] = mAbility[i];
      player.add(new Player(i, mAbility[i]));
      if (++cnt == SIZE) {
        league.add(player);
        cnt = 0;
        player = new TreeSet<>();
      }
    }
  }

  int move() {

    upgrade = new ArrayDeque<>();
    downgrade = new ArrayDeque<>();

    int total = 0;
    for (int i = league.size() - 1; i >= 1; i--) {
      Player winner = league.get(i).pollFirst();
      Player loser = league.get(i - 1).pollLast();

      upgrade.offer(winner);
      downgrade.offer(loser);
      total += (winner.id + loser.id);
    }

    playerMove();

    return total;
  }

  int trade() {

    upgrade = new ArrayDeque<>();
    downgrade = new ArrayDeque<>();

    int total = 0;
    for (int i = league.size() - 1; i >= 1; i--) {
      Player winner = league.get(i).pollFirst();
      Iterator<Player> iterator = league.get(i - 1).iterator();
      for (int j = 0; j < SIZE / 2; j++) {
        iterator.next();
      }
      Player loser = iterator.next();
      league.get(i - 1).remove(loser);

      upgrade.offer(winner);
      downgrade.offer(loser);

      total += (winner.id + loser.id);
    }

    playerMove();

    return total;
  }

  private static void playerMove() {
    for (int i = league.size() - 1; i >= 1; i--) {
      Player winner = upgrade.poll();
      Player loser = downgrade.poll();

      league.get(i - 1).add(winner);
      league.get(i).add(loser);
    }
  }

