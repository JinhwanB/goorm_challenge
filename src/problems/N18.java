package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N18 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정사각형 크기
        int M = Integer.parseInt(st.nextToken()); // 반직선의 개수

        int[][] upDown = new int[N + 1][N + 1]; // 세로 배열
        int[][] rightLeft = new int[N + 1][N + 1]; // 가로 배열

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);

            if (direction == 'R') {
                for(int j = x; j <= N; j++){
                    rightLeft[y][j] += 1;
                }
            } else if (direction == 'L') {
                for(int j = x; j > 0; j--){
                    rightLeft[y][j] += 1;
                }
            } else if (direction == 'U') {
                for(int j = y; j > 0; j--){
                    upDown[j][x] += 1;
                }
            } else {
                for(int j = y; j <= N; j++){
                    upDown[j][x] += 1;
                }
            }
        }

        long count = 0; // 출력

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int row = rightLeft[i][j];
                int column = upDown[i][j];

                if(row >= 1 && column >= 1){
                    count += (long) row * column;
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
