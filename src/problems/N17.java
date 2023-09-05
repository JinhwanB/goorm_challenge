package problems;

import java.io.*;
import java.util.*;

public class N17 {

    static List<Integer> result = new ArrayList<>();
    static double max = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
        int M = Integer.parseInt(st.nextToken()); // 통신 회선의 개수

        List<Integer>[] list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }

        boolean[] check = new boolean[N + 1]; // 연결된 여부 확인

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                BFS(i, check, list);
            }
        }

        for (int num : result) {
            bw.write(num + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS ( int i, boolean[] check, List<Integer >[]list){
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> computer = new ArrayList<>();
        int count = 0;

        queue.offer(i);
        computer.add(i);
        check[i] = true;

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            count += list[poll].size();

            for (int num : list[poll]) {
                if (!check[num]) {
                    check[num] = true;
                    computer.add(num);
                    queue.offer(num);
                }
            }
        }

        count /= 2; // 회선 갯수 = 양방향이기에 2로 나눠준다.(2번씩 탐색을 하므로)

        // 밀도 구하기
        double density = (double) count / computer.size();

        //조건에 맞게 컴포넌트 비교
        if (density > max) { // 밀도 비교
            max = density;
            result.clear();

            Collections.sort(computer);

            result.addAll(computer);
        } else if (density == max) { // 밀도 같을 때, 컴퓨터 갯수 비교
            if (result.size() > computer.size()) {
                result.clear();

                Collections.sort(computer);

                result.addAll(computer);
            } else if (result.size() == computer.size()) { // 컴퓨터 갯수 같을 때 가장 작은 수 인덱스 비교
                Collections.sort(computer);
                Collections.sort(result);

                if (result.get(0) > computer.get(0)) {
                    result.clear();
                    result.addAll(computer);
                }
            }
        }
    }
}
