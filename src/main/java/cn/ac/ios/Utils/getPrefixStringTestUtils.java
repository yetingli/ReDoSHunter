package cn.ac.ios.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getPrefixStringTestUtils {
    public static void main(String[] args) {
        final String regex = "(?<prefix>(?!a{2,})(?!b))(?<infix>[a-z]+)";
        final String string = "a";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group("prefix"));
            System.out.println(matcher.group("infix"));
        }
    }
}
