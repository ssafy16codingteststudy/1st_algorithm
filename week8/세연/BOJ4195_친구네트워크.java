package week8.세연;

import java.util.*;
import java.io.*;

class Friend {
    String root;
    int netSize;

    Friend(String name){
        this.root = name;
        this.netSize = 1;
    }
    Friend(String root, int netSize) {
        this.root = root;
        this.netSize = netSize;
    }
    String setRoot(String name){
        this.root = name;
        return this.root;
    }
    void setNetSize(int size){
        this.netSize = size;
    }
}

public class BOJ4195_친구네트워크 {
    static Map<String, Friend> network;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int F = Integer.parseInt(br.readLine());
            network = new HashMap<>();
            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                if (!network.containsKey(f1)) network.put(f1, new Friend(f1));
                if (!network.containsKey(f2)) network.put(f2, new Friend(f2));

                union(f1, f2);
            }
        }

        System.out.println(sb);
    }
    static String find(String f) {
        if (network.get(f).root.equals(f)) return f;
        return network.get(f).setRoot(find(network.get(f).root));
    }
    static void union(String f1, String f2) {
        String x = find(f1);
        String y = find(f2);
        if (x.equals(y)) {
            sb.append(network.get(x).netSize).append("\n");
            return;
        }

        if (network.get(x).netSize >= network.get(y).netSize) {
            network.get(y).setRoot(x);
            network.get(x).setNetSize(network.get(x).netSize + network.get(y).netSize);
            network.get(y).netSize = 1;
            sb.append(network.get(x).netSize).append("\n");
        }
        else{
            network.get(x).setRoot(y);
            network.get(y).netSize += network.get(x).netSize;
            network.get(x).netSize = 1;
            sb.append(network.get(y).netSize).append("\n");
        }
    }
}