package cn.ac.ios.PCRE;

/**
 * A small demo class that demonstrates how to use the
 * generated parser classes.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String regex = "&&";
        System.out.println(new PCREBuilder.Tree(regex).toStringASCII());
    }
}
