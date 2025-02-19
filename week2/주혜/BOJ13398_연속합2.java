import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        int max = -1001;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] > max) max = arr[i];
        }

        if (max < 0 && n == 1) {
            System.out.println(max);
            sc.close();
            return;
        }

        int [] answer = new int[n];
        int [] answer1 = new int[n];
        answer[0] = arr[0];
        answer1[0] = arr[0];

        int maxValue = answer[0];
        for (int i = 1; i < n; i++) {
            answer[i] = Math.max(answer[i - 1] + arr[i], arr[i]);
            answer1[i] = Math.max(answer1[i - 1] + arr[i], answer[i - 1]);

            maxValue = Math.max(maxValue, Math.max(answer[i], answer1[i]));
        
        }

        System.out.println(maxValue);
        sc.close();
    }

}
