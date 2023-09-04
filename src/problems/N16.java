package problems;

import java.io.*;
import java.util.*;

public class N16 {

    static boolean union = false; // 연합 결성 여부

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬의 개수
        int M = Integer.parseInt(st.nextToken()); // 다리의 개수

        // 인접 리스트
        List<Integer>[] list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
        }

        int count = 0; // 연합의 갯수
        boolean[] check = new boolean[N + 1]; // 연합이 결성된 노드 체크
        Set<Integer> set = new HashSet<>(); // 연합 노드의 수

        for(int i = 1; i <= N; i++){
            if(!check[i]) {
                DFS(i, check, set, list); // 연합이 되있는 노드가 아닐 시 탐색 시작
            }

            if(union){
                count++; // 연합일 시 연합 수(count) 증가
            }

            union = false; // 연합 boolean 초기화
        }

        // 연합 수 = 전체 노드 - 연합 노드 수 + 연합 수
        int result = N - set.toArray().length + count;

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 재귀 함수 이용
     * 두 개의 노드가 연합 즉 서로 양방향 연결이라면 해당 연합 노드와 연결된 노드를 모두 찾아 true 처리한다.
     */
    private static void DFS(int i, boolean[] check, Set<Integer> set, List<Integer>[] list) {
        for(int num : list[i]){
            if(!check[num]) { // 해당 노드가 이미 연합 노드인지 확인
                for(int value : list[num]){
                    if(value == i){ // 서로 양방향 연결인지 확인
                        check[num] = true;
                        check[value] = true;
                        union = true; // 연합 결성
                        set.add(num);
                        set.add(value);
                        DFS(num, check, set, list); // 연합 결성된 노드와 연결된 노드 찾기
                    }
                }
            }
        }
    }
}
