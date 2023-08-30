package problems;

import java.io.*;
import java.util.*;

public class N13 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 크기
        int K = Integer.parseInt(st.nextToken()); // 단지의 기준
        int[][] arr = new int[N][N]; // 마을
        boolean[][] check = new boolean[N][N]; // 방문 여부 체크
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < arr[i].length; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(set.add(arr[i][j])) list.add(arr[i][j]); // 중복 제거
            }
        }

        int num = 0; // 단지 수가 많은 유형
        int max = 0; // 가장 많은 단지 수
        int houseCount = 0; // 집의 수
        int finalCount = 0; // 단지 수
        Queue<int[]> queue = new LinkedList<>();

        num = getResult(K, arr, check, list, num, max, houseCount, finalCount, queue);

        bw.write(String.valueOf(num));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getResult(int K, int[][] arr, boolean[][] check, List<Integer> list, int num, int max, int houseCount, int finalCount, Queue<int[]> queue) {
        while(!list.isEmpty()){
            int M = list.get(0); // 집 유형

            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr[i].length; j++){
                    if(arr[i][j] == M && !check[i][j]) { // 해당 유형에 맞는 집일 때 탐색 시작
                        check[i][j] = true;
                        queue.offer(new int[]{i, j});

                        for (int k = 0; k < dx.length; k++) { // 상 하 좌 우 확인
                            int X = i + dx[k];
                            int Y = j + dy[k];

                            if (X >= 0 && X < arr.length && Y >= 0 && Y < arr.length) {
                                if (arr[X][Y] == M && !check[X][Y]) {
                                    check[X][Y] = true;
                                    queue.offer(new int[]{X, Y});
                                }
                            }
                        }

                        while(!queue.isEmpty()){
                            int[] XY = queue.poll();
                            houseCount++;

                            for (int k = 0; k < dx.length; k++) { // 상 하 좌 우 확인
                                int X = XY[0] + dx[k];
                                int Y = XY[1] + dy[k];

                                if (X >= 0 && X < arr.length && Y >= 0 && Y < arr.length) {
                                    if (arr[X][Y] == M && !check[X][Y]) {
                                        check[X][Y] = true;
                                        queue.offer(new int[]{X, Y});
                                    }
                                }
                            }
                        }

                        if (houseCount >= K) { // 단지가 될 조건에 맞으면
                            finalCount++; // 단지의 수 + 1
                        }

                        houseCount = 0; // 집의 수 초기화
                    }
                }
            }

            if(finalCount > max){ // 단지 수가 전의 단지 수 보다 많을 때
                num = M;
                max = finalCount;
            }else if(finalCount == max && M > num){ // 현재 탐색한 단지 수가 가장 많은 단지 수와 같은 때
                num = M;
            }

            finalCount = 0;
            list.remove(0);
        }
        return num;
    }
}
