import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr, minTree, maxTree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        minTree = new int[4 * N];
        maxTree = new int[4 * N];

        Arrays.fill(minTree, -1);
        Arrays.fill(maxTree, -1);

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(min(a, b, 1, 1, N) + " " + max(a, b, 1, 1, N));
        }
    }

    static int min(int startIndex, int endIndex, int current, int start, int end) {

        int min = Integer.MAX_VALUE;

        if (startIndex <= start && endIndex >= end) {
            min = (min < minTree[current]) ? min : minTree[current];
            return min;
        }

        else if (end < startIndex || start > endIndex) {
            return min;
        }

        else {

            int a = min(startIndex, endIndex, current * 2, start, (end + start) / 2);
            int b = min(startIndex, endIndex, current * 2 + 1, (start + end) / 2 + 1, end);

            min = (min < a) ? min : a;
            min = (min < b) ? min : b;
        }

        return min;
    }

    static int max(int startIndex, int endIndex, int current, int start, int end) {

        int max = Integer.MIN_VALUE;

        if (startIndex <= start && endIndex >= end) {
            max = (max > maxTree[current]) ? max : maxTree[current];
            return max;
        }

        else if (end < startIndex || start > endIndex) {
            return max;
        }

        else {

            int a = max(startIndex, endIndex, current * 2, start, (end + start) / 2);
            int b = max(startIndex, endIndex, current * 2 + 1, (start + end) / 2 + 1, end);

            max = (max > a) ? max : a;
            max = (max > b) ? max : b;
        }

        return max;
    }

    static void init(int index, int start, int end) {
        if (start == end) {
            minTree[index] = arr[start];
            maxTree[index] = arr[start];
        }
        else {
            init(index * 2, start, (start+end)/2);
            init(index * 2 + 1, (start+end)/2+1, end);
            if (minTree[index*2] != -1 && minTree[index*2+1] != -1) minTree[index] = (minTree[index*2] > minTree[index*2+1]) ? minTree[index*2 + 1] : minTree[index*2];
            if (maxTree[index*2] != -1 && maxTree[index*2+1] != -1) maxTree[index] = (maxTree[index*2] < maxTree[index*2+1]) ? maxTree[index*2 + 1] : maxTree[index*2];
        }
    }

}
