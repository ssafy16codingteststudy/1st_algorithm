

package main;

import java.util.Scanner;
import java.util.*;

class Main {
    private static Scanner sc;
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_MOVE = 200;
    private final static int CMD_TRADE = 300;

    private static boolean run() throws Exception {

        int query_num = sc.nextInt();
		int ans;
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            int query = sc.nextInt();

            if (query == CMD_INIT) {
                int N = sc.nextInt();
                int L = sc.nextInt();
                int mAbility[] = new int[N];
                for (int i = 0; i < N; i++){
                    mAbility[i] = sc.nextInt();
                }
                usersolution.init(N, L, mAbility);
                ok = true;
            } else if (query == CMD_MOVE) {
                int ret = usersolution.move();
                ans = sc.nextInt();
                if (ans != ret) {
                    ok = false;
                }
            } else if (query == CMD_TRADE) {
                int ret = usersolution.trade();
                ans = sc.nextInt();
                if (ans != ret) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        // System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        sc = new Scanner(System.in);
        T = sc.nextInt();
        MARK = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }
        sc.close();
    }
}


class UserSolution {
	static int N;
	static Map<Integer, Integer> players = new HashMap<>();
	static int L;
	static List<TreeSet<Integer>> leagues = new ArrayList<>();
	
    void init(int N, int L, int mAbility[]) {
    	this.N = N;
    	this.L = L;
    	players.clear();
    	leagues.clear();
    	
    	for (int i = 0; i < L; i++) {
            leagues.add(new TreeSet<>((a, b) -> {  // 정렬 순서
                int cmp = Integer.compare(players.get(b), players.get(a));
                return (cmp != 0) ? cmp : Integer.compare(a, b);
            }));
        }
		
    	for (int i=0;i<N;i++) {
    		players.put(i, mAbility[i]);
    		leagues.get((int) i / (N/L)).add(i); //각 리그에 넣어주기
    	}
    	
    	
    }
    
    int findPlayer(int idx, int leaguenum) { // 0번째가 제일 잘하는 친구
//    	TreeSet<Integer> league = leagues.get(leaguenum);
    	
    	Iterator<Integer> it = leagues.get(leaguenum).iterator();
        while (idx-- > 0) it.next();
        return it.next(); 
    	
//    	return  league.stream().skip(idx).findFirst().orElse(-1);    //league.get(idx) ; //해당 플레이어의 id를 반환
    }

    int move() {
    	int answer = 0;
    	int[] lowest = new int[L - 1];
    	int[] highest = new int[L - 1];
    	for (int i=0;i<L - 1;i++) {
    		lowest[i] = findPlayer(N / L - 1 , i);
    		highest[i] = findPlayer(0, i + 1);
    		answer += lowest[i] + highest[i];
    	}
    	for (int i=0;i<L - 1;i++) {
    		leagues.get(i).remove(lowest[i]);
    		leagues.get(i).add(highest[i]);
    		leagues.get(i + 1).remove(highest[i]);
    		leagues.get(i + 1).add(lowest[i]);
    	}
    	
        return answer;
    }

    int trade() {
    	int answer = 0;
    	int[] middle = new int[L - 1];
    	int[] highest = new int[L - 1];
    	for (int i=0;i<L - 1;i++) {
    		middle[i] = findPlayer((int) (N / L) / 2 , i);
    		highest[i] = findPlayer(0, i + 1);
    		answer += middle[i] + highest[i];
    	}
    	
    	for (int i=0;i<L - 1;i++) {
    		leagues.get(i).remove(middle[i]);
    		leagues.get(i).add(highest[i]);
    		leagues.get(i + 1).remove(highest[i]);
    		leagues.get(i + 1).add(middle[i]);
    	}
    	
    	
        return answer;
    }

}