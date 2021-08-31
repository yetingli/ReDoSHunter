package cn.ac.ios.Utils;

public class BaseConversionUtils {
    public static void main(String[] args) {
        for (int i = 0; i <= 777; i++) {
            int num2 = i;
            String newNum = eightTurnSixteen(turn8(num2));
            int len = newNum.length();
            for (int j = 4 - len; j > 0; j--) {
                newNum = "0" + newNum;
            }
            newNum = "\\u" + newNum;
            System.out.println("OCTAL_TO_HEX.put(\"\\\\" + num2 + "\",\"" + newNum + "\");");
        }

//        System.out.println("十进制数:" + num2);
//        System.out.println("十进制转换二进制:" + turn2(num2));
//        System.out.println("十进制转换八进制:" + turn8(num2));
//        System.out.println("十进制转换十六进制:" + turn16(num2));
//        //2 -> 8
//        System.out.println("二进制转换八进制:" + twoTurnEight(turn2(num2)));
//        System.out.println("八进制转换二进制:" + eightTurnTwo(turn8(num2)));
//        System.out.println("八进制转换十六进制:" + eightTurnSixteen(turn8(num2)));
//        //2 -> 16
//        System.out.println("二进制转换十六进制:" + twoTurnSixteen(turn2(num2)));
    }

    /**
     * 10进制转二进制
     */
    public static String turn2(int num){
        String shortTimeNum = "";
        int remainder = 0;
        /**
        * 模拟短除法
        */
        while(num >= 1){
            remainder = num % 2;
            num = num / 2;
            shortTimeNum = remainder + shortTimeNum;
        }
        return shortTimeNum;
    }

    /**
     * 10进制转8进制
     */
    public static String turn8 (int num){
        String shortTimeNum = "";
        int remainder = 0;
        /**
         * 模拟短除法
         */
        while(num >= 1){
            remainder = num % 8;
            num = num / 8;
            shortTimeNum = remainder + shortTimeNum;
        }
        return shortTimeNum;
    }

    /**
     * 10进制转16进制
     * @return
     */
    public static String turn16 (int num){
        String shortTimeNum = "";
        int remainder = 0;
        String tempStr = "";
        /**
         * 模拟短除法
         */
        while(num >= 1){
            remainder = num % 16;
            tempStr = getOtherNum(remainder);
            num = num / 16;
            shortTimeNum = tempStr + shortTimeNum;
        }
        return shortTimeNum;
    }

    /**
     * 2进制转换8进制
     * 概念说明:这里转换的是整数,从右向左三位一组分别乘以2的零次方，2的一次方，2的2次方
     * 然后把每组中的数相加，再把各组从左向右拼接到一起
     * @param strNum2
     */
    public static String twoTurnEight(String strNum2){
        String resultNum = "";
        int remainder = strNum2.length() % 3;
        //补位 三位一组,最后一组位数不够用0补充
        switch(remainder){
            case 1:
                strNum2 = "00"+strNum2;
                break;
            case 2:
                strNum2 = "0"+strNum2;
                break;
        }
        int index = strNum2.length()-1;
        int tempNum1 = 0;
        int tempNum2 = 0;
        int tempNum3 = 0;
        while(index>=1) {
            index -- ;
            if(index % 3 == 0){
            //每个数为一组
                tempNum1 = Integer.parseInt(strNum2.substring(index, index+1));
                tempNum2 = Integer.parseInt(strNum2.substring(index+1, index+2));
                tempNum3 = Integer.parseInt(strNum2.substring(index+2, index+3));
                resultNum = tempNum3*1+tempNum2*2+tempNum1*4+resultNum;
            }
        }
        return resultNum;
    }

    /**
     * 八进制转换二进制
     * @param eightNum
     * @return
     */
    public static String eightTurnTwo(String eightNum){
        String twoNum = "";
        int index = eightNum.length()-1;
        String tempNum = "";
        while (index >= 0) {
            tempNum = turn2(Integer.parseInt(eightNum.substring(index, index+1))) ;
            //补位,在转换8进制时是每三为二进制数为一组,转换回二进制时位数也需要是三位,不够用零补
            switch (tempNum.length()) {
                case 1:
                    tempNum = "00" + tempNum;
                    break;
                case 2:
                    tempNum = "0" + tempNum;
                    break;
            }
            twoNum = tempNum + twoNum;
            index--;
        }
        return twoNum;
    }

    /**
     * 2进制转换16进制
     * 概念说明:这里转换的是整数,从右向左四位一组分别乘以2的零次方，2的一次方，2的2次方，2的3次方,
     * 然后相加把每组最终的得数一次从左向右拼到一起,若其中一组的和大于9,按照对应关系转换后再把每组的结果拼接到一起
     * @param strNum2
     */
    public static String twoTurnSixteen(String strNum2){
        String resultNum = "";
        int remainder = strNum2.length() % 4;
        //补位,四位一组,最后一组位数不够用0补充
        switch (remainder) {
            case 1:
                strNum2 = "000"+strNum2;
                break;
            case 2:
                strNum2 = "00"+strNum2;
                break;
            case 3:
                strNum2 = "0"+strNum2;
                break;
        }
        int index = strNum2.length()-1;
        int tempNum1 = 0;
        int tempNum2 = 0;
        int tempNum3 = 0;
        int tempNum4 = 0;
        int tempNum5 = 0;
        while(index>=1){
            index -- ;
            if(index % 4 == 0){
            //每个数为一组
                tempNum1 = Integer.parseInt(strNum2.substring(index, index+1));
                tempNum2 = Integer.parseInt(strNum2.substring(index+1, index+2));
                tempNum3 = Integer.parseInt(strNum2.substring(index+2, index+3));
                tempNum4 = Integer.parseInt(strNum2.substring(index+3, index+4));
                tempNum5 = tempNum4*1 + tempNum3*2+tempNum2*4+tempNum1*8;
                resultNum = getOtherNum(tempNum5) +resultNum;
            }
        }
        return resultNum;

    }

    /**
     * 8进制转换16进制
     * @param strNum2
     */
    public static String eightTurnSixteen(String strNum2){
        return twoTurnSixteen(eightTurnTwo(strNum2));
    }

    /**
     * 16进制转换中的特殊处理,需要把大于9的数字转换成字母
     * @param tempNum
     * @return
     */
    public static String getOtherNum(int tempNum) {
        String tempStr = "";
        if(tempNum > 9){
            switch(tempNum){
                case 10:
                    tempStr = "A";
                    break;
                case 11:
                    tempStr = "B";
                    break;
                case 12:
                    tempStr = "C";
                    break;
                case 13:
                    tempStr = "D";
                    break;
                case 14:
                    tempStr = "E";
                    break;
                case 15:
                    tempStr = "F";
                    break;
            }
        } else {
            tempStr = String.valueOf(tempNum);
        }
        return tempStr;
    }
}
