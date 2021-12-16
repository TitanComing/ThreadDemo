package RandomUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Create by peng on 2021/12/16.
 */
public class RandomUtils {

    public static void main(String[] args){
        //随机数总数量
        int count = 300;
        //每个随机数的长度
        int randomNumSize = 20;
        //每个随机数中“0”的个数
        int allZeroCount = 8;

        String[] randomsHolder = new String[count];
        for(int i = 0; i < count; i++){
            //生成随机字符串数据
            String[] random = new String[randomNumSize];
            Arrays.fill(random, "1");
            int curZeroCount = 0;
            do {
                int location = ThreadLocalRandom.current().nextInt(0, randomNumSize);
                if (random[location].equals("1")) {
                    random[location] = "0";
                    curZeroCount++;
                }
            } while (curZeroCount != allZeroCount);
            //拼装格式
            StringBuilder sb = new StringBuilder();
            for (String s : random) {
                sb.append(s);
            }
            randomsHolder[i] = sb.toString();
        }
        for(String random : randomsHolder){
            System.out.println(random);
        }
    }
}
