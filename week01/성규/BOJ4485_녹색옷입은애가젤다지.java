import java.util.*;
import java.lang.*;
import java.io.*;


class Node {
    int curY;
    int curX;
    int weight;

    public Node ( int curY, int curX, int weight){
        this.curY = curY;
        this.curX = curX;
        this.weight = weight;
    }
}

class Main {

    static int [][] board;
    static int [][] weights ;
    static int N=-1;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count=1;
        int result=0;
        while (true)
        {            
            N = sc.nextInt();
            if (N==0) break;
            
            board = new int[N][N];
            weights = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(weights[i], Integer.MAX_VALUE);
            }

            for (int i=0 ; i<N ; i++) {
                for (int j=0 ; j<N ; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            dijkstra();

            
            System.out.printf("Problem "+(count++)+": "+ weights[N-1][N-1]+"\n");
            
        }// while
    }

    public static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o->o.weight));
        queue.add(new Node(0,0,board[0][0]));
        weights[0][0] = board[0][0];
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            
            for (int i=0 ; i<4 ; i++ ){
                int curY = node.curY + dy[i];
                int curX = node.curX + dx[i];
                int curW = node.weight;
                 
                if(curY>=0 && curY<N && curX>=0 && curX<N) {
                    if (weights[curY][curX] > curW + board[curY][curX]){
                        weights[curY][curX] = curW + board[curY][curX];
                        queue.add(new Node(curY,curX,weights[curY][curX]));
                    }
                }
            }
        }         
    }
}


