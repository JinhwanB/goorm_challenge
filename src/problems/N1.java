package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf((int)Math.floor(W * (1 + R / 30.0))));
        bw.flush();
        bw.close();
        br.close();
    }
}
