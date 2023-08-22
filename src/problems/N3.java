package problems;

import java.io.*;
import java.util.StringTokenizer;

public class N3 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        int result = 0;
        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            String symbol = st.nextToken();
            int num2 = Integer.parseInt(st.nextToken());

            switch (symbol) {
                case "+":
                    result += num1 + num2;
                    break;
                case "-":
                    result += num1 - num2;
                    break;
                case "/":
                    result += num1 / num2;
                    break;
                default:
                    result += num1 * num2;
                    break;
            }
        }

        bw.write(String.valueOf(result));
        bw.close();
        br.close();
    }
}
