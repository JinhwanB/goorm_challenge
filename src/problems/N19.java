package problems;

import java.io.*;
import java.util.*;

public class N19 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수
        int S = Integer.parseInt(st.nextToken()); // 출발 도시
        int E = Integer.parseInt(st.nextToken()); // 도착 도시

        List<Integer>[] lists = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lists[s].add(e);
            lists[e].add(s);
        }

        for(int i = 1; i <= N; i++){
            int result = BFS(S, E, i, lists);

            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int BFS(int s, int e, int i, List<Integer>[] lists) {
        if(s == i || e == i){
            return -1;
        }

        int[] count = new int[lists.length]; // 노드의 수와 방문 확인 동시 수행
        Arrays.fill(count, Integer.MAX_VALUE);

        count[s] = 1;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, s));

        while(!queue.isEmpty()){
            Node poll = queue.poll();
            int city = poll.city;
            int cnt = poll.count;

            if(city == e){
                return count[city];
            }

            for(int num : lists[city]){
                /**
                 * num != i -> 다음 방문할 노드가 공사중인 곳이 아닐 때
                 * count[num] > cnt + 1 -> 다음 방문할 노드에는 count가 현재 방문했던 노드 cnt + 1이 되어 들어가야한다.
                 * 그런데 다음 방문할 노드에 cnt + 1의 값이 이미 들어가 있거나 더 작은 수가 있다면 그 전에 이미 방문했다는 의미가 된다.
                 */
                if(num != i && count[num] > cnt + 1){
                    count[num] = cnt + 1;
                    queue.offer(new Node(count[num], num));
                }
            }
        }

        return -1; // 도착 노드 e에 도달하지 못할 시
    }

    static class Node{
        private int count; // 현재 노드까지 오는데 방문한 노드 수
        private int city; // 현재 노드

        public Node(int count, int city) {
            this.count = count;
            this.city = city;
        }
    }
}
