package problems;

import java.io.*;

public class N8 {

    static int[] item = {14, 7, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int count = 0;

        for (int i : item) {
            if(N == 0) break;

            if(N / i > 0){
                count += N / i;
                N %= i;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
