package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N7 {

    /**
     * 8방향 체크할 배열
     */
    static int[] x = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] y = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];

        for(int i = 0; i < arr.length; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < arr[i].length; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = findFlag(N, K, arr);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findFlag(int N, int K, int[][] arr) {
        int result = 0;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] != 0) continue; // 해당 칸이 깃발이 아닌 경우에만 체크 진행

                int count = 0; // 현재 칸에 인접한 깃발의 수

                // 8방향 체크 순환
                for(int k = 0; k < x.length; k++){
                    if(i + x[k] >= 0 && i + x[k] < N && j + y[k] >= 0 && j + y[k] < N){
                        if(arr[i + x[k]][j + y[k]] == 1) count++;
                    }
                }

                if(count == K) result++;
            }
        }
        return result;
    }
}
