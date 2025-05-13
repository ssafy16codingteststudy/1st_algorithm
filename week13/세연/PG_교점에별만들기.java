package week13.세연;

import java.util.*;

class PG_교점에별만들기 {
    public String[] solution(int[][] line) {
        long maxX = Long.MIN_VALUE, minX = Long.MAX_VALUE, maxY = Long.MIN_VALUE, minY = Long.MAX_VALUE;
        List<long[]> dots = new ArrayList<>();
        
        for (int i = 0; i < line.length; i++) {
            for (int j = i+1; j < line.length; j++) {
                long a = line[i][0], b = line[i][1], e = line[i][2];
                long c = line[j][0], d = line[j][1], f = line[j][2];
                long x = b*f-e*d, y = e*c-a*f, r=a*d-b*c;
                if (r == 0) continue;
                if ((x != 0 && Math.abs(x) < Math.abs(r)) || x % r != 0 || (y!= 0 && Math.abs(y) < Math.abs(r)) || y % r != 0) continue;
                x /= r; y /= r;
                maxX = Math.max(maxX, x); minX = Math.min(minX, x); 
                maxY = Math.max(maxY, y); minY = Math.min(minY, y); 
                dots.add(new long[] {x,y});
            }
        }
        
        int lenX = (int)(maxX-minX+1), lenY = (int)(maxY-minY+1);
        String[] answer = new String[lenY];
        StringBuilder [] tmp = new StringBuilder[lenY];
        for (int i = 0; i<lenY; i++) tmp[i] = new StringBuilder(".".repeat(lenX));
        for (long [] dot : dots) {
            int x = (int)(dot[0]-minX), y=(int)(dot[1] -minY);
            tmp[y].setCharAt(x, '*');
        }
        for (int i = 0; i<lenY; i++) answer[lenY-1-i] = tmp[i].toString();
        
        return answer;
    }
}