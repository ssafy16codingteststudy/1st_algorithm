import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] scores = new int[N];
        int [] steps = new int[N];
        for (int i = 0; i < N; i++) scores[i] = Integer.parseInt(br.readLine());
        if (N <= 2){
            steps[0] = scores[0];
            for (int i = 1; i < N; i++){ steps[i] = steps[i-1] + scores[i]; }
        }
        else {
            steps[0] = scores[0];
            steps[1] = scores[1] + steps[0];
            steps[2] = scores[2] + Math.max(scores[1], steps[0]);
            for (int i = 3; i < N; i++) {
                steps[i] = scores[i] + Math.max(scores[i - 1] + steps[i - 3], steps[i - 2]);
            }
        }
        System.out.println(steps[N - 1]);
    }
}
