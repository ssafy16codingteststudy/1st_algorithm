import java.util.*;

class Main {
    static Map <Long, Long> A = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        if (N == 0) {
            System.out.println("1");
            sc.close();
            return;
        }
        long P = sc.nextLong();
        long Q = sc.nextLong();
        
        Long answer = dp(N, P, Q);

        System.out.println(answer);

        sc.close();
    }
    private static Long dp(long n, long p, long q) {

        if (n == 0) return 1L;
        else if(A.get(n) != null) return A.get(n);
        else A.put(n, dp(n/p, p, q) + dp(n/q, p, q));
        
        return A.get(n);
    }
}
