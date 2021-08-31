package cn.ac.ios.Utils;


public class genInfixStringTestUtils {

    /**
     * 字符串转unicode
     *
     * @param str
     * @return
     */
    public static String stringToUnicode(String str) {
        StringBuilder sb = new StringBuilder();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
//            sb.append("\\u" + Integer.toHexString(c[i]));

            // 修改为 不满足四位的 前面补0
            String hexString = Integer.toHexString(c[i]);
            sb.append("\\u");
            for (int j = 4 - hexString.length(); j > 0 ; j--) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /**
     * unicode转字符串
     *
     * @param unicode
     * @return
     */
    public static String unicodeToString(String unicode) {
        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i], 16);
            sb.append((char) index);
        }
        return sb.toString();
    }



    public static void main(String[] args) throws InterruptedException {
        System.out.println(stringToUnicode("abc"));
    }
}
