package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N4 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // N개의 재료

        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0; // 최댓값
        int idx = 0; // 최댓값 인덱스
        int sum = 0; // 총 합
        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num; // 배열에 저장 과정

            if(num > max){ // 최댓값과 그 인덱스 구하는 과정
                max = num;
                idx = i;
            }

            sum += num; // 총 합 구하는 과정
        }

        boolean flag = false; // 0을 출력할지 말지 정할 변수, true면 0을 출력
        /**
         * 문제의 조건 : 맛의 정도가 가장 높은 것을 기준으로 위 아래로는 감소하거나 같아야 한다.
         * 즉, 배열에서 최댓값을 기준으로 왼쪽 또는 오른쪽으로 갈수록 값이 같거나 감소해야 한다.
         */
        for(int i = 1; i < idx - 1; i++){ // 최댓값보다 왼쪽에 있는 값들 비교
            int result = arr[i] - arr[i + 1]; // 이 값은 항상 0보다 작거나 같아야 한다
            if(result > 0) {
                flag = true;
                break;
            }
        }

        for(int i = idx + 1; i < arr.length - 1; i++){ // 최댓값보다 오른쪽에 있는 값들 비교
            if(flag) break; // 이미 flag가 true 상태이면 반복문 실행 x

            int result = arr[i] - arr[i + 1]; // 이 값은 항상 0 보다 크거나 같아야 한다
            if(result < 0) {
                flag = true;
                break;
            }
        }

        if(flag) bw.write(String.valueOf(0));
        else bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
        br.close();
    }
}
