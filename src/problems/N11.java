package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N11 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0; // 아이템 사용 갯수
        int copyN = N; // N 숫자 복사 변수

        /**
         * 아이템 사용의 최솟값을 구해야하므로 가장 큰 수로 먼저 나눈다.
         */
        count += N / arr[1]; // 가장 큰 수의 아이템을 사용한 갯수
        N %= arr[1]; // 아이템 사용 후 남은 N

        /**
         * 다음 아이템을 사용했을 때 N이 0이 되지 않는 경우
         * 처음 아이템의 사용 갯수를 하나씩 줄여간다
         * 최종적으로 N이 0이 되는 경우를 찾는다
         * 만약 count가 0이 될 때까지 경우의 수가 없다면 -1을 출력한다
         */
        if(N != 0 && N % arr[0] != 0){
            while(count != 0){ // count가 0이면 반복문 탈출
                count--; // count를 하나씩 줄여간다
                N = copyN - count * arr[1];
                if(N % arr[0] == 0) { // 두 번째 아이템을 썼을 때 N이 0일 때
                    count += N / arr[0];
                    N %= arr[0];
                    break;
                }
            }
        }else{
            count += N / arr[0];
            N %= arr[0];
        }

        if(N == 0)bw.write(String.valueOf(count)); // 최종 N이 0이면 count 출력
        else bw.write(String.valueOf(-1)); // 아니면 -1 출력
        bw.flush();
        bw.close();
        br.close();
    }
}
