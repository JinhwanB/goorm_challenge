package problems;

import java.io.*;
import java.util.*;

public class N20 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 배열의 크기
        int K = Integer.parseInt(st.nextToken()); // 연결 요소를 지울 기준
        int Q = Integer.parseInt(st.nextToken()); // 구름이가 문자를 적을 횟수

        char[][] arr = new char[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            String[] word = br.readLine().split("");
            for(int j = 1; j <= N; j++){
                arr[i][j] = word[j - 1].charAt(0);
            }
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            char change = st.nextToken().charAt(0);

            boolean[][] visit = new boolean[N + 1][N + 1];

            if(arr[r][c] == '.'){
                arr[r][c] = change;

                BFS(r, c, arr, visit, K); // BFS탐색 시작
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                bw.write(arr[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void BFS(int r, int c, char[][] arr, boolean[][] visit, int K) {
        List<int[]> checkList = new ArrayList<>(); // 연결 요소 개수 확인
        Queue<int[]> queue = new LinkedList<>();

        visit[r][c] = true; // 방문처리
        queue.add(new int[] {r, c});
        checkList.add(new int[] {r, c});

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int pollY = poll[0];
            int pollX = poll[1];

            for(int i = 0; i < dx.length; i++){
                int currentY = pollY + dy[i];
                int currentX = pollX + dx[i];

                // arr배열 범위 내에 있어야 함
                if(currentY > 0 && currentY < arr.length && currentX > 0 && currentX < arr.length){
                    // 방문한 곳이 아니어야하고, 문자가 같은 곳이어야 함
                    if(arr[r][c] == arr[currentY][currentX] && !visit[currentY][currentX]){
                        visit[currentY][currentX] = true; // 방문처리
                        queue.add(new int[] {currentY, currentX});
                        checkList.add(new int[] {currentY, currentX});
                    }
                }
            }
        }

        if(checkList.size() >= K){ // 연결 요소 개수가 K개 이상일 때
            for (int[] ints : checkList) {
                arr[ints[0]][ints[1]] = '.'; // 모두 지운다
            }
        }
    }
}
