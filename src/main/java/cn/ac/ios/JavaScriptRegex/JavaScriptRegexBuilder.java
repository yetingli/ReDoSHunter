package cn.ac.ios.JavaScriptRegex;

import cn.ac.ios.TreeNode.TreeNode;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

import static cn.ac.ios.TreeNode.Utils.*;


public final class JavaScriptRegexBuilder {

    private static final DescriptiveBailErrorListener ERROR_LISTENER = new DescriptiveBailErrorListener();

    // No need to instantiate this class.
    private JavaScriptRegexBuilder() {
    }

    public static final class Lexer {

        private JavaScriptRegexLexer lexer;

        public Lexer(String input) {
            this(new ANTLRInputStream(input));
        }

        public Lexer(ANTLRInputStream input) {
            this.lexer = new JavaScriptRegexLexer(input);
            this.lexer.removeErrorListeners();
            this.lexer.addErrorListener(ERROR_LISTENER);
        }

        public Lexer withErrorListener(ANTLRErrorListener listener) {
            this.lexer.removeErrorListeners();
            this.lexer.addErrorListener(listener);
            return this;
        }

        public JavaScriptRegexLexer build() {
            return this.lexer;
        }
    }

    public static final class Parser {

        private JavaScriptRegexParser parser;

        public Parser(String input) {
            this(new ANTLRInputStream(input));
        }

        public Parser(ANTLRInputStream input) {
            JavaScriptRegexLexer lexer = new JavaScriptRegexLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ERROR_LISTENER);
            this.parser = new JavaScriptRegexParser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        public Parser(JavaScriptRegexLexer lexer) {
            this.parser = new JavaScriptRegexParser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        public Parser withErrorListener(ANTLRErrorListener listener) {
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(listener);
            return this;
        }

        public JavaScriptRegexParser build() {
            return this.parser;
        }
    }

    public static final class Tree {

        private final String input;

        public Tree(String input) {
            this.input = input;
        }

        public String toStringASCII() {

            JavaScriptRegexParser parser = new Parser(input).build();
            ParseTree tree = parser.parse();

            StringBuilder builder = new StringBuilder();

            walk(tree, builder);

            return builder.toString();
        }


        @SuppressWarnings("unchecked")
        private void walk(ParseTree tree, StringBuilder builder) {

            List<ParseTree> firstStack = new ArrayList<ParseTree>();
            firstStack.add(tree);

            List<List<ParseTree>> childListStack = new ArrayList<List<ParseTree>>();
            childListStack.add(firstStack);

            while (!childListStack.isEmpty()) {

                List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

                if (childStack.isEmpty()) {
                    childListStack.remove(childListStack.size() - 1);
                } else {
                    tree = childStack.remove(0);

                    String node = tree.getClass().getSimpleName().replace("Context", "");
                    node = Character.toLowerCase(node.charAt(0)) + node.substring(1);

                    String indent = "";

                    for (int i = 0; i < childListStack.size() - 1; i++) {
                        indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                    }

                    builder.append(indent)
                            .append(childStack.isEmpty() ? "'- " : "|- ")
                            .append(node.startsWith("terminal") ? tree.getText() : node)
                            .append("\n");

                    if (tree.getChildCount() > 0) {
                        List<ParseTree> children = new ArrayList<ParseTree>();
                        for (int i = 0; i < tree.getChildCount(); i++) {
                            children.add(tree.getChild(i));
                        }
                        childListStack.add(children);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        String regex = "((.)\\1+ (?<YEAR>(?:19|20)\\d{2})) [^]-x]";

//        regex = "[^[^\\d]&&\\w&&[0-2]]";
//        regex = "[^ [a-z]]";
        regex = "(<<=|>>=|&&=|(\\|\\|=)|<<|>>(\\+=)|-=|(\\*=)|(\\/=)|%=|&=|(\\^=)|(\\|=)|<=|>=|==|!=|&&|(\\|\\|)|(\\+\\+)|--|>|<|\\^|&|(\\|)|\\*|\\/|%|\\+|-|~|=)";
        regex = "&&";
        regex = "[A-Z&&[^FIOQUY]&&A-C]";
//        regex = "[[[A-Z]&&[ABC]]&&[^FIOQUY]]";
//        System.out.println(new Builder.Tree(regex).toStringASCII());

        JavaScriptRegexParser parser = new Parser(regex).build();
        ParseTree tree = parser.parse();

//        StringBuilder builder = new StringBuilder();
        TreeNode ReDoSTree = new TreeNode(regex);
        getReDoSTreeHelper1(tree, ReDoSTree);
        StringBuilder builder1 = new StringBuilder();
        walk1(ReDoSTree, builder1);
        System.out.println(builder1);
        TreeNode newReDoSTree = new TreeNode(regex);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        StringBuilder builder2 = new StringBuilder();
        walk1(newReDoSTree, builder2);
        System.out.println(builder2);

    }
}