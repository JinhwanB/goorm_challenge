package problems;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N12 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        boolean[][] check = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < arr.length; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < arr[i].length; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0; // 발전기 수 저장 변수

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == 1 && !check[i][j]){ // 방문 한 곳이 집이고 연결된 발전기가 없을 때
                    findGenerator(arr, check, queue, i, j);

                    count++;
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 방문한 곳 큐에 넣는다
     * 상, 하, 좌, 우를 방문하여 집이면서 발전기 연결이 안 되어있는 좌표를 큐에 넣는다
     * 큐에 넣은 모든 좌표를 방문 처리한다
     * 큐에서 하나씩 빼면서 해당 좌표의 상, 하, 좌, 우를 확인하고 해당 좌표가 집이며 발전기가 연결 안되어 있을 시 방문 처리 후 큐에 넣는다
     * 큐가 비어있을 때까지 반복한다
     * 큐가 비었다면 count + 1
     */
    private static void findGenerator(int[][] arr, boolean[][] check, Queue<int[]> queue, int i, int j) {
        queue.offer(new int[] {i, j});
        check[i][j] = true; // 방문 처리

        // 상, 하, 좌, 우 확인
        for(int k = 0; k < dx.length; k++){
            int currentX = i + dx[k];
            int currentY = j + dy[k];

            // 발전기 연결 X
            if(currentX >= 0 && currentX < arr.length && currentY >= 0 && currentY < arr.length){
                if(arr[currentX][currentY] == 1 && !check[currentX][currentY]){
                    check[currentX][currentY] = true; // 방문처리
                    queue.offer(new int[] {currentX, currentY}); // 큐에 넣기
                }
            }
        }

        // 큐에서 하나씩 빼면서 상, 하, 좌, 우 확인 -> 인접해 있는 집 찾기
        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            for(int k = 0; k < dx.length; k++){
                int X = poll[0] + dx[k];
                int Y = poll[1] + dy[k];

                if(X >= 0 && X < arr.length && Y >= 0 && Y < arr.length){
                    if(arr[X][Y] == 1 && !check[X][Y]){
                        check[X][Y] = true;
                        queue.offer(new int[] {X, Y});
                    }
                }
            }
        }
    }
}
