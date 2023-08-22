package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            int C = Integer.parseInt(br.readLine());

            for(int j = 0; j < C; j++){
                M++;
                if(M == 60){
                    M = 0;
                    T += 1;
                }
                if(T == 24) T = 0;
            }
        }

        bw.write(T + " " + M);
        bw.flush();
        bw.close();
        br.close();
    }
}
