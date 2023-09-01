package problems;

import java.io.*;
import java.util.*;

class Fruit implements Comparable<Fruit>{
    private long price;
    private long fullnessOfOne;

    public Fruit(long price, long fullness) {
        this.price = price;
        this.fullnessOfOne = fullness / price; // 1조각의 포만감
    }

    public long getPrice() {
        return price;
    }

    public long getFullnessOfOne() {
        return fullnessOfOne;
    }

    /**
     * 1조각의 포만감 순으로 정렬
     * 만약 포만감이 같을 시 가격 순으로 정렬
     */
    @Override
    public int compareTo(Fruit o) {
        if(o.fullnessOfOne != this.fullnessOfOne){
            return (int) (o.fullnessOfOne - this.fullnessOfOne);
        }
        else{
            return (int) (o.price - this.price);
        }
    }
}

public class N15 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 과일의 개수
        long K = Long.parseLong(st.nextToken()); // 플레이어가 가진 돈

        List<Fruit> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            long P = Long.parseLong(st.nextToken()); // 각 과일의 가격
            long C = Long.parseLong(st.nextToken()); // 각 과일의 포만감

            list.add(new Fruit(P, C));
        }

        Collections.sort(list);

        long max = 0;

        // 리스트 순회
        for (Fruit fruit : list) {
            long price = fruit.getPrice();
            long fullness = fruit.getFullnessOfOne();

            if(K - price >= 0){ // 해당 과일 구매시 가진 돈을 다 쓰거나 남는 경우
                K -= price;
                max += fullness * price;
            }
            else{ // 가진 돈을 초과하여 쓰는 경우
                long priceOfFruit = 1; // 1 조각씩 구입한다 (조각이면서 가격이 된다.)

                /*
                  반복의 조건
                  조각이 지금 과일의 가격이 되면 안된다(이미 위에서 조건문을 처리했기 때문)
                  현재 가진 돈보다 초과되면 안된다.
                 */
                while(priceOfFruit != price && K - priceOfFruit > 0){
                    priceOfFruit++; // 조각 갯수를 늘린다.
                }

                K -= priceOfFruit; // 구매한 만큼 가진 돈에서 뺀다.
                max += fullness * priceOfFruit; // 구매한 만큼 포만감을 더해준다
            }

            if(K == 0) break;
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}