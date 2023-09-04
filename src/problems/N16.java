package problems;

import java.io.*;
import java.util.*;

public class N16 {

    static boolean union = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬의 개수
        int M = Integer.parseInt(st.nextToken()); // 다리의 개수

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

        int count = 0;
        boolean[] check = new boolean[N + 1];
        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= N; i++){
            if(!check[i]) {
                DFS(i, check, set, list);
            }

            if(union){
                count++;
            }

            union = false;
        }

        int result = N - set.toArray().length + count;

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void DFS(int i, boolean[] check, Set<Integer> set, List<Integer>[] list) {
        for(int num : list[i]){
            if(!check[num]) {
                for(int value : list[num]){
                    if(value == i){
                        check[num] = true;
                        check[value] = true;
                        union = true;
                        set.add(num);
                        set.add(value);
                        DFS(num, check, set, list);
                    }
                }
            }
        }
    }
}
