class PG_혼자서하는틱택토 {

    private int[][] arr = new int[3][3];

    public int solution(String[] board) {
        int first = 0;
        int second = 0;

        for (int i = 0; i < 3; i++) {
            char[] c = board[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                if(c[j] == 'O') {
                    arr[i][j] = 1;
                    first++;
                } else if (c[j] == 'X') {
                    arr[i][j] = 2;
                    second++;
                }
            }
        }


        if(Math.abs(first - second) >= 2 || (first == 0 && second == 1)) {
            return 0;
        }

        if((isTicTacTo(1) && (first - second) != 1) || (isTicTacTo(2) && (second - first) != 0)) {
            return 0;
        }

        return 1;
    }

    private boolean isTicTacTo(int num) {
        for (int i = 0; i < 3; i++) {
            if(arr[i][0] == num && arr[i][1] == num && arr[i][2] == num) {
                return true;
            }

            if(arr[0][i] == num && arr[1][i] == num && arr[2][i] == num) {
                return true;
            }
        }

        if(arr[0][0] == num && arr[1][1] == num && arr[2][2] == num) {
            return true;
        }

        if(arr[0][2] == num && arr[1][1] == num && arr[2][0] == num) {
            return true;
        }

        return false;
    }
}