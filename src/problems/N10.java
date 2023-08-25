package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N10 {

    static int goormCount = 0; static int playerCount = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 구름이 시작 위치
        int goormX = Integer.parseInt(st.nextToken());
        int goormY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 플레이어 시작 위치
        int playerX = Integer.parseInt(st.nextToken());
        int playerY = Integer.parseInt(st.nextToken());

        // 칸마다 이동 명령 입력
        String[][] board = new String[N + 1][N + 1];
        for(int i = 1; i < board.length; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < board[i].length; j++){
                board[i][j] = st.nextToken();
            }
        }

        // 각각 구름이와 플레이어의 방문 여부 체크 배열
        boolean[][] checkGoorm = new boolean[N + 1][N + 1];
        boolean[][] checkPlayer = new boolean[N + 1][N + 1];

        goormCount = getCount(board, checkGoorm, goormX, goormY);
        playerCount = getCount(board, checkPlayer, playerX, playerY);


        // 최댓값 비교 후 출력
        if(Math.max(goormCount, playerCount) == goormCount) bw.write("goorm " + goormCount);
        else bw.write("player " + playerCount);

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCount(String[][] board, boolean[][] check, int currentX, int currentY) {
        int count = 0;

        // 방문했던 곳을 방문할 경우 끝낸다
        while(!check[currentX][currentY]) {
            String s = board[currentX][currentY];

            String direction = s.replaceAll("[0-9]", ""); // 이동 방향(L, R, U, P)
            int move = Integer.parseInt(s.replaceAll("[^0-9]", "")); // 이동 칸 수

            /**
             * 이동할 칸 수 만큼 건너띄는 것이 아닌 하나씩 방문하면서 함께 체크 배열에 방문 여부 체크
             * 이동했을 때 게임판의 크기를 넘어갈 시 반대편 끝 쪽으로 이동
             */
            switch (direction) {
                case "L":
                    for (int i = 0; i < move; i++) {
                        if (!check[currentX][currentY]) {
                            check[currentX][currentY] = true;
                            count++;
                        } else {
                            break;
                        }
                        currentY -= 1;
                        if (currentY <= 0) currentY = board.length - 1;
                    }
                    break;
                case "R":
                    for (int i = 0; i < move; i++) {
                        if (!check[currentX][currentY]) {
                            check[currentX][currentY] = true;
                            count++;
                        } else {
                            break;
                        }
                        currentY += 1;
                        if (currentY >= board.length) currentY = 1;
                    }
                    break;
                case "U":
                    for (int i = 0; i < move; i++) {
                        if (!check[currentX][currentY]) {
                            check[currentX][currentY] = true;
                            count++;
                        } else {
                            break;
                        }
                        currentX -= 1;
                        if (currentX <= 0) currentX = board.length - 1;
                    }
                    break;
                default:
                    for (int i = 0; i < move; i++) {
                        if (!check[currentX][currentY]) {
                            check[currentX][currentY] = true;
                            count++;
                        } else {
                            break;
                        }
                        currentX += 1;
                        if (currentX >= board.length) currentX = 1;
                    }
                    break;
            }
        }
        return count;
    }
}
