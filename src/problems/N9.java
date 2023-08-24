package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N9 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[][] arr = new String[N + 1][N + 1];
        int[][] check = new int[N + 1][N + 1]; // 실제로 변화되는 수치를 저장할 배열

        // arr 배열에 @, #을 포함한 것 모두 저장
        for(int i = 1; i < arr.length; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < arr[i].length; j++){
                arr[i][j] = st.nextToken();
            }
        }

        int max = 0; // 최댓값 저장할 변수

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()); // 입력받은 y좌표
            int c = Integer.parseInt(st.nextToken()); // 입력받은 x좌표

            // 폭탄이 떨어진 칸 확인
            if(arr[r][c].equals("@")) check[r][c] += 2; // @는 2 증가
            if(arr[r][c].equals("0")) check[r][c]++; // 그냥 0일 때 1 증가

            if(check[r][c] > max) max = check[r][c]; // 최댓값 비교 후 저장

            // 폭탄이 떨어진 칸 주변(위, 아래, 양옆) 확인
            for(int j = 0; j < dx.length; j++){
                int currentX = c + dx[j];
                int currentY = r + dy[j];

                // 주변의 칸의 x, y좌표가 0보다 커야 하며 변의 길이보다는 작아야한다.
                if(currentY > 0 && currentY < arr.length && currentX > 0 && currentX < arr.length){
                    if(arr[currentY][currentX].equals("@")) check[currentY][currentX] += 2;
                    if(arr[currentY][currentX].equals("0")) check[currentY][currentX]++;

                    if(check[currentY][currentX] > max) max = check[currentY][currentX];
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
