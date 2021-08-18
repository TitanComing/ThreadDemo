package AssciTest;

import java.util.List;
import java.util.Objects;

/**
 * Create by peng on 2021/8/9.
 */
public class AsciiForSohTest {
    private static String sohString = "9010=000000144079\u00019008=28\u00019006=D890792580\u00019016=11225";
    private static String spaceString = "9010=000000144079 9008=14 9006=D990792580 9016=11225";

    public static void main(String[] args) {
        String term = "";
        String account = "";
        System.out.println("Test Thread.SOH，Ascii表示：标题开始，编码十六进制01");
        String[] sohStringList = sohString.split("\u0001");
        for (String s : sohStringList) {
            if (s.startsWith("9008=")) {
                term = s.split("=")[1];
            }
            if (s.startsWith("9006")) {
                account = s.split("=")[1];
            }
        }
        System.out.println(term + "----" + account);

        System.out.println("Test 空格");
        String[] spaceStringList = spaceString.split("\\s+");
        for (String s : spaceStringList) {
            if (s.startsWith("9008=")) {
                term = s.split("=")[1];
            }
            if (s.startsWith("9006")) {
                account = s.split("=")[1];
            }
        }
        System.out.println(term + "----" + account);

        if(sohString.matches(".*\u0001.*")){
            System.out.println("包含标题开始字符");
        }
        if(spaceString.matches(".*\\s+.*")){
            System.out.println("包含空格");
        }
    }
}
