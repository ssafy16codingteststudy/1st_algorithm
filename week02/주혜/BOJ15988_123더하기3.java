import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int maxN = 0;
        long mod = 1000000009;
        int[] n = new int[T];

        for (int t = 0; t < T; t++) {
            n[t] = sc.nextInt();
            if(maxN < n[t]) maxN = n[t];
        }

        long[] answer = new long[maxN + 1];
        answer[1] = 1;
        answer[2] = 2;
        answer[3] = 4;

        if (maxN > 4) { 
            for (int i = 4; i <= maxN; i++) {
                answer[i] = (answer[i - 3] + answer[i - 2] + answer[i - 1]) % mod;
            }
        }

        for (int t = 0; t < T; t++) {
            System.out.println(answer[n[t]]);
        }

        sc.close();
    }
}