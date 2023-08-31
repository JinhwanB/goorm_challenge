package problems;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class N14 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int K = Integer.parseInt(st.nextToken()); // 시작 노드 번호

        boolean[] check = new boolean[N + 1]; // 방문 체크 배열
        List<Integer>[] list = new ArrayList[N + 1]; // 인접 리스트

        for(int i = 1; i < list.length; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            list[num1].add(num2);
            list[num2].add(num1);
        }

        int node = K; // 현재 방문한 노드
        int count = 1; // 총 노드 방문 횟수, 처음 시작한 노드부터 카운트
        boolean flag = false;

        while(!flag){
            Collections.sort(list[node]); // 번호가 작은 수로 방문하기 위해 정렬
            check[node] = true; // 방문 체크

            // 해당 노드와 연결된 노드가 없는 경우 반복문 탈출
            if(list[node].isEmpty()){
                flag = true;
            }

            // 방문한 적이 없는 노드를 방문한다
            for(int num : list[node]){
                if(!check[num]){
                    node = num;
                    count++;
                    check[num] = true;
                    flag = false;
                    break;
                }else {
                    flag = true;
                }
            }
        }

        bw.write(count + " " + node);
        bw.flush();
        bw.close();
        br.close();
    }
}
