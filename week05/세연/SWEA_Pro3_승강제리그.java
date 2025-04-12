package week5.세연;

class Team {
    public int topPlayer, bottomPlayer;
    Team() {
        topPlayer = -1;
        bottomPlayer = -1;
    }
}

class Player {
    public int id, ability, nextPlayer, befPlayer;
    Player(int nId, int nAbility) {
        id = nId;
        ability = nAbility;
        nextPlayer = -1;
        befPlayer = -1;
    }
}

class UserSolution {
    static int teamSize, leagueSize, playerCnt;
    static Player[] players;
    static Team[] league;

    void init(int N, int L, int mAbility[]) {
        playerCnt = N;
        leagueSize = L;
        teamSize = N / L;
        players = new Player[N];
        league = new Team[L];

        for (int i = 0; i < N; i++) {
            players[i] = new Player(i, mAbility[i]);

            if (i % teamSize == 0) {
                league[i / teamSize] = new Team();
                league[i / teamSize].topPlayer = league[i / teamSize].bottomPlayer = i;
            } else {
                insert(i / teamSize, i);
            }
        }
    }

    int move() {
        int result = 0;
        for (int i = 0; i < leagueSize - 1; i++) {
            int goDown = league[i].bottomPlayer;
            int goUp = league[i + 1].topPlayer;

            // 연결 해제
            if (players[goDown].befPlayer != -1)
                players[players[goDown].befPlayer].nextPlayer = -1;
            if (players[goUp].nextPlayer != -1)
                players[players[goUp].nextPlayer].befPlayer = -1;

            league[i].bottomPlayer = players[goDown].befPlayer;
            league[i + 1].topPlayer = players[goUp].nextPlayer;

            // 새로운 팀에 삽입
            insert(i, goUp);
            insert(i + 1, goDown);

            result += goUp + goDown;
        }
        System.out.println(result);
        return result;
    }

    int trade() {
        int result = 0;
        for (int i = 0; i < leagueSize - 1; i++) {
            int goDown = getMid(i);
            int goUp = getMid(i + 1);

            // 연결 해제
            if (players[goDown].befPlayer != -1)
                players[players[goDown].befPlayer].nextPlayer = players[goDown].nextPlayer;
            if (players[goDown].nextPlayer != -1)
                players[players[goDown].nextPlayer].befPlayer = players[goDown].befPlayer;

            if (players[goUp].befPlayer != -1)
                players[players[goUp].befPlayer].nextPlayer = players[goUp].nextPlayer;
            if (players[goUp].nextPlayer != -1)
                players[players[goUp].nextPlayer].befPlayer = players[goUp].befPlayer;

            // 새로운 팀에 삽입
            insert(i + 1, goDown);
            insert(i, goUp);

            result += goUp + goDown;
        }
        System.out.println(result);
        return result;
    }

    int getMid(int tIdx) {
        int result = league[tIdx].topPlayer;
        for (int i = 0; i < teamSize / 2; i++) {
            if (players[result].nextPlayer == -1) break;
            result = players[result].nextPlayer;
        }
        return result;
    }

    void insert(int tIdx, int pIdx) {
        int start = league[tIdx].topPlayer;
        int prev = -1;

        // 능력치 높은 순으로 정렬하며 적절한 위치 찾기
        while (start != -1 && (players[start].ability > players[pIdx].ability ||
                (players[start].ability == players[pIdx].ability && start < pIdx))) {
            prev = start;
            start = players[start].nextPlayer;
        }

        // 삽입 위치 찾았음 -> 연결 갱신
        players[pIdx].nextPlayer = start;
        players[pIdx].befPlayer = prev;

        if (prev != -1) players[prev].nextPlayer = pIdx;
        if (start != -1) players[start].befPlayer = pIdx;

        // 팀의 최상단 및 최하단 갱신
        if (prev == -1) league[tIdx].topPlayer = pIdx;
        if (start == -1) league[tIdx].bottomPlayer = pIdx;
    }
}