package problems;

import java.io.*;
import java.util.*;

public class N6 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        List<String> P = new ArrayList<>(); // 3등분 문자 넣을 리스트

        if (N == 3) {
            String str1 = String.valueOf(S.charAt(0));
            String str2 = String.valueOf(S.charAt(1));
            String str3 = String.valueOf(S.charAt(2));

            P.add(str1);
            P.add(str2);
            P.add(str3);
        } else {
            for (int i = 1; i < S.length(); i++) {
                for (int j = i + 1; j < S.length(); j++) {
                    String str1 = S.substring(0, i);
                    String str2 = S.substring(i, j);
                    String str3 = S.substring(j);

                    P.add(str1);
                    P.add(str2);
                    P.add(str3);
                }
            }
        }

        List<String> copy = new ArrayList<>(P); // 3등분 한 문자 넣은 리스트 복사
        List<String> list = new ArrayList<>(); // 중복 제거를 담을 리스트
        Set<String> uniqueValues = new HashSet<>(); // Set은 중복값이 들어갈 수 없다
        for (String s : P) {
            if (uniqueValues.add(s)) { // Set에 s가 들어간다면
                list.add(s); // list에 s를 추가한다. 즉 list는 중복이 없는 값이 들어있게 된다.
            }
        }

        P = list;
        Collections.sort(P);

        int count = 0;
        int sum = 0;
        int max = 0;
        for (String s : copy) { // 3개의 연속된 부분문자가 들어있는 리스트 순환
            for (int j = 0; j < P.size(); j++) { // 중복값이 제거되고 정렬이 된 리스트 순환 -> 인덱스 값을 통해 몇 번째 위치했는지 알아낸다.
                if (P.get(j).equals(s)) {
                    sum += (j + 1); // 인덱스는 0부터 시작
                    count += 1;
                }
                if (count == 3) { // 문자를 3개의 연속된 부분문자로 나눴기 때문에 3번마다 한번씩 max값을 비교해주어야 한다.
                    max = Math.max(sum, max);
                    count = 0;
                    sum = 0;
                    break;
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
