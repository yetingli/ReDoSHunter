package cn.ac.ios.PythonRegex;

/**
 * A small demo class that demonstrates how to use the
 * generated parser classes.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String regex = "a{,1}";
        System.out.println(new PythonRegexBuilder.Tree(regex).toStringASCII());
    }
}
