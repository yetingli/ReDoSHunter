package cn.ac.ios.Utils;


/**
 * @author pqc
 */
public class TrimUtils {


    public static String rtrim(String str, String trimStr) {
        if (trimStr.length() == 0) {
            return str;
        }
        while (true) {
            if (str.length() == 0) {
                return "";
            }
            if (str.endsWith(trimStr)) {
                str = str.substring(0, str.length() - trimStr.length());
            } else {
                break;
            }
        }
        return str;
    }
}