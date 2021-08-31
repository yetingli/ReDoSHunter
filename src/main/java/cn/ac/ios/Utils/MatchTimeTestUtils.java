package cn.ac.ios.Utils;

import java.util.regex.Pattern;

public class MatchTimeTestUtils {
    public static void main(String[] args) throws InterruptedException {
//        String regex = "[A-z]";
//        for (int i = 0; i < 65536; i++) {
//            char c = (char) i;
//            boolean isMatch = Pattern.compile(regex).matcher(String.valueOf(c)).find();
//            if (isMatch)
////                System.out.println(Integer.toHexString(i) + " " + isMatch);
//                System.out.println(c);
//        }

//        String regex1 = "[[A-Za-z0-9\\.\\_\\-]]";
//        String regex2 = "[\\--\\.0-9A-Z_a-z]";
//        for (int i = 0; i < 65536; i++) {
//            char c = (char) i;
//            boolean isMatch1 = Pattern.compile(regex1).matcher(String.valueOf(c)).find();
//            boolean isMatch2 = Pattern.compile(regex1).matcher(String.valueOf(c)).find();
//            if (!((isMatch1 && isMatch2) || (!isMatch1 && !isMatch2)))
//                System.out.println(Integer.toHexString(i) + " " + isMatch1 + " " + isMatch2);
////                System.out.println(c);
//        }
//        System.exit(0);

        String regex = "\\s*$";
        regex = "^\\s+|\\s*$";
        regex = "(?m)(?s)^.{1,32}\\|.{1,32}\\|.{1,64}@.{1,64}\\|.{1,32}\\|.{1,5}\\|";
        for (int i = 0; i < 1100; i++) {
            StringBuilder attackString = new StringBuilder();
            // 前缀
            attackString.append("");
            // 歧义点
            for (int j = 0; j < i * 1000; j++) {
                attackString.append(" |\r");
            }//"a@a.a"+"0"*2+"@1 \n_"
            // 后缀
            attackString.append("@1 \n_! _1!");
//            System.out.println(attackString);
            long time1 = System.nanoTime();
//            boolean isMatch = Pattern.matches(regex, attackString);
            boolean isMatch = Pattern.compile(regex).matcher(attackString).find();
            long time2 = System.nanoTime();
            System.out.println(i * 1000 + " " + isMatch);
            System.out.println((time2 - time1)/1e9);

        }
    }
}
