package problems;

import java.io.*;
import java.util.*;

public class N5 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 주어진 정수의 수
        int K = Integer.parseInt(st.nextToken()); // 구해야 할 K 번째 수

        st = new StringTokenizer(br.readLine());
        List<Number> list = new ArrayList<>(); // 객체를 담을 리스트 선언
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            list.add(new Number(num));
        }

        Collections.sort(list); // list를 해당 클래스에 정의해 놓은 정렬 기준으로 정렬된다.

        int result = list.get(K - 1).getNum(); // 정렬 후 K번 쨰 수 출력
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}

class Number implements Comparable<Number>{

    private final int num;
    private int count = 0; // 2진수로 비교 시 "1"의 갯수

    public Number(int num) { // 객체를 생성하면서 2진수일 때 1의 갯수를 같이 저장
        this.num = num;

        String binary = Integer.toBinaryString(num); // 2진수 변환(문자형)
        for(int i = 0; i < binary.length(); i++){
            char c = binary.charAt(i);
            if(c == '1') count++; // 1의 갯수 구하는 과정
        }
    }

    public int getNum() { // Getter
        return num;
    }

    /**
     * 정렬 기준
     * 1. 2진수 일 때 1의 갯수(count)를 기준으로 정렬
     * 2. 2진수 일 때 1의 갯수가 같으면 원래 숫자끼리 비교
     */
    @Override
    public int compareTo(Number o) {
        if(this.count > o.count) return -1;
        else if(this.count < o.count) return 1;
        else return o.num - this.num;
    }
}
