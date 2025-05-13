import java.util.*;

class Solution {
    public String[] solution(int [][] line) {

        long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE, minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;

            List<long[]> point = new ArrayList<>();

            for (int i = 0; i < line.length - 1; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    if (line[i][0] == line[j][0] && line[i][1] == line[j][1]) continue;
                    double x = ( (double) line[i][1] * line[j][2] - line[i][2] * line[j][1]) / ( (double) line[i][0] * line[j][1] - line[i][1] * line[j][0]);
                    double y = ( (double) line[i][2] * line[j][0] - line[i][0] * line[j][2]) / ( (double) line[i][0] * line[j][1] - line[i][1] * line[j][0]);

                    if (y % 1 == 0.0 && x % 1 == 0.0) {

                        long[] cur_point = {(long)y, (long)x};

                        point.add(cur_point);

                        minX = Math.min(minX, (long) x);
                        minY = Math.min(minY, (long) y);
                        maxX = Math.max(maxX, (long) x);
                        maxY = Math.max(maxY, (long) y);

                    }
                }
            }

            int num = 0;

            String[] answer = new String[Math.toIntExact(maxY - minY + 1)];

            for (long i = maxY; i >= minY; i--) {
                StringBuilder sb = new StringBuilder();
                for (long j = minX; j <= maxX; j++) {
                    boolean isOk = false;
                    for (int k = 0; k < point.size(); k++) {
                        if (point.get(k)[0] == i && point.get(k)[1] == j) {
                            isOk = true;
                        }
                    }
                    if (isOk == true) sb.append("*");
                    else sb.append(".");
                }
                answer[num++] = sb.toString();
            }

            return answer;
    }
}
