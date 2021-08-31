package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Bean.Pair;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.Transition;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.TreeNode.Utils.printTree;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.BracketUtils.isSpecialCharacterBracket;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.GenMatchStringUtils.getExampleByDkBricsAutomaton2;
import static cn.ac.ios.Utils.GenMatchStringUtils.getTranslateRegexForAssertionsList;
import static cn.ac.ios.Utils.NegateUtils.deleteZeroWidthAssertion;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.getR0;
import static cn.ac.ios.Utils.SplitRegexUtils.getR4;

public class DkBricsAutomatonUtils {

    // 判断是否是需要加方括号的字符 适配dk.brics.automaton
    private static boolean isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(String string) {
        for (String sp : SPECIAL_STRING_NEED_ADD_SQUARE_BRACKETS_FOR_DK_BRICS_AUTOMATON) {
            if (string.equals(sp)) {
                return true;
            }
        }
        return false;
    }

    // 对单字母来说 需要加[]的 加[]
    public static String addSquareBracketsForSpecialStringForDkBricsAutomaton(String regex) throws InterruptedException {
//        if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(singleLetter)) {
//            singleLetter = "[" + singleLetter + "]";
//        }
//        return singleLetter;

        TreeNode treeNode = createReDoSTree(regex);
        List<TreeNode> allLeafNodes = treeNode.getLeafNodes();
        for (int i = 0; i < allLeafNodes.size(); i++) {
            String singleLetter = allLeafNodes.get(i).getData();
            if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(singleLetter)) {
//                allLeafNodes.get(i).setData("[" + singleLetter + "]");
                // 改为加斜杠 原因是如果本身在[]内 无脑加[]会有问题 例如[\w@] ->[\w[@]] 就错了 改成加斜杠后[\w@] -> [\w\@]
                allLeafNodes.get(i).setData("\\" + singleLetter);
            }
        }
        return treeNode.getLeafsData();
    }

    // 通过自动机去补 这里只支持单层的补结点
    // [^abc] -> [\u0000-`d-\uffff]
    // [^\d] -> [\u0000-/:-\uffff]
    // [^\D] -> [0-9]
    // [^\d\D] -> []
    // 方法是先把补集符号去掉得到A 对A进行改写得到a1|a2|...|an
    // 然后通过自动机求~(a1|a2|...|an)&.的automaton.getInitialState().getTransitions() 得到列表
    // 通过列表中的值拼接 得到一个集合[...]
    // 可先对自动机进行判空automaton.isEmpty(), 若结果为true, 则该集合不接受任何字符 可用＃来表示空语言 注意是＃, 不是#
    public static String transNegateNode(TreeNode negateNode) throws InterruptedException {
        // 改为只处理含有\D \S \W这三个 否则不处理了
        String data = negateNode.getData();
        if (!(data.contains("\\D") || data.contains("\\S") || data.contains("\\W"))) return data;

        StringBuilder stringBuilder = new StringBuilder();
        List<TreeNode> allLeafs = negateNode.getLeafNodes();
        for (int i = 2; i < allLeafs.size() - 1; i++) {
            // 如果是第一个孩子 一定是[ 在stringBuilder中添加(
            // 如果是最后一个孩子 一定是] 在stringBuild中添加)
            // 其他孩子 如果是\s\S\d\D\w\W 则按对应的值添加
            // 如果是 a-c这种 使用BracketUtils.isCollectionSymbol判断
            // 如果是SPECIAL_CHARACTERS_BRACKET中的字符 要先加斜杠
            // 中间用或|连接
            if (allLeafs.get(i).getData().equals("\\s")) {
                stringBuilder.append("[\f \n\r\t\u000b]");
                stringBuilder.append("|");
            } else if (allLeafs.get(i).getData().equals("\\S")) {
                stringBuilder.append("[^\f \n\r\t\u000b]");
                stringBuilder.append("|");
            } else if (allLeafs.get(i).getData().equals("\\d")) {
                stringBuilder.append("[0-9]");
                stringBuilder.append("|");
            } else if (allLeafs.get(i).getData().equals("\\D")) {
                stringBuilder.append("[^0-9]");
                stringBuilder.append("|");
            } else if (allLeafs.get(i).getData().equals("\\w")) {
                stringBuilder.append("[A-Za-z0-9_]");
                stringBuilder.append("|");
            } else if (allLeafs.get(i).getData().equals("\\W")) {
                stringBuilder.append("[^A-Za-z0-9_]");
                stringBuilder.append("|");
            } else {
                if (isGeneralizedCollectionSymbol(allLeafs.get(i).getParent())) {
                    stringBuilder.append("[" + allLeafs.get(i).getParent().getData()
                            .replace("\\n", "\n").replace("\\r", "\r")
                            .replace("\\f", "\f").replace("\\v", "\u000b")
                            .replace("\\u000b", "\u000b") + "]");
                    stringBuilder.append("|");

                    i += 2;
                } else {
                    if (isSpecialCharacterBracket(allLeafs.get(i).getData())) {
                        stringBuilder.append("\\" + allLeafs.get(i).getData());
                    } else if (allLeafs.get(i).getData().equals("\\t")) {
                        stringBuilder.append("\t");
                    } else if (allLeafs.get(i).getData().equals("\\f")) {
                        stringBuilder.append("\f");
                    } else if (allLeafs.get(i).getData().equals("\\n")) {
                        stringBuilder.append("\n");
                    } else if (allLeafs.get(i).getData().equals("\\v") || allLeafs.get(i).getData().equals("\\u000b")) {
                        stringBuilder.append("\u000b");
                    } else if (allLeafs.get(i).getData().equals("\\r")) {
                        stringBuilder.append("\r");
                    } else if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(allLeafs.get(i).getData())) {   // 修正
//                            stringBuilder.append("[" + allLeafs.get(i).getData() + "]");
                        stringBuilder.append("\\" + allLeafs.get(i).getData());
                    } else {
                        stringBuilder.append(allLeafs.get(i).getData());
                    }
                    stringBuilder.append("|");
                }
            }
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.insert(0, "～(");
        stringBuilder.append(")＆.");
        String regex = addSquareBracketsForSpecialStringForDkBricsAutomaton(stringBuilder.toString());
        regex = reductSpecialStringForDkBricsAutomaton(regex);
        RegExp regExp = new RegExp(regex);
        Automaton automaton = regExp.toAutomaton(false);
        if (automaton.isEmpty()) {
            return "＃";
        } else {
            StringBuilder newNodeData = new StringBuilder();
            newNodeData.append("[");

            Set<Transition> transitionSet = automaton.getInitialState().getTransitions();
            for (Transition transition : transitionSet) {
                String min = String.valueOf(transition.getMin()), max = String.valueOf(transition.getMax());
                if (min.equals("[")) min = "\\[";
                if (max.equals("]")) max = "\\]";
//                System.out.println("min = " + min + " max = " + max);

                if (min.equals(max)) {
                    newNodeData.append(min);
                } else {
                    newNodeData.append(min + "-" + max);
                }
            }

            newNodeData.append("]");
//            System.out.println("newNodeData = " + newNodeData);
            if (newNodeData.toString().equals("[]")) return "＃";
            else return newNodeData.toString();
        }
    }

    public static String transNegateNode(String regex) throws InterruptedException {
        return transNegateNode(createReDoSTree(regex));
    }


    // 重写正则以适配dk.brics.automaton中的自动机
    // 该自动机不支持\w \s \d \W \S \D 但支持.
    // 要注意[\w]等价于\w 以下方法不能直接使用
    // "\\s" -> "[\f \n\r\t\u000b]"
    // "\\S" -> "[^\f \n\r\t\u000b]"
    // "\\d" -> "[0-9]"
    // "\\D" -> "[^0-9]"
    // "\\w" -> "[A-Za-z0-9_]"
    // "\\W" -> "[^A-Za-z0-9_]"
    // 不能直接根据以上替换 要先判断是否在方括号内
    // 输入为TreeNode 输出为String 这里的输入就不带有[^ ]了
    // 注意 修正. dk.brics.automaton中是匹配\n和\r 这是不对的 参数repairDot=true表示修正.   repairDot=false表示不修正 即匹配任意字符包括\n\r
    public static String reWriteMetaEscape(TreeNode treeNode1, boolean repairDot) throws InterruptedException {
        TreeNode treeNode = SerializationUtils.clone(treeNode1);    // 深拷贝
        if (treeNode == null) return null;

        // 通过自动机去补，主要是[^\d\D]这种问题
        List<TreeNode> allNegateChildren = treeNode.getAllNegateChildren();
        for (TreeNode allNegateChild : allNegateChildren) {
            String data = allNegateChild.getData();
            String newData = transNegateNode(data);
            allNegateChild.updateTreeByModifyNode(newData);
        }

//        printTree(treeNode);

        StringBuilder stringBuilder = new StringBuilder();
        List<TreeNode> allLeafs = treeNode.getLeafNodes();
//        for (int i = 0; i < allLeafs.size(); i++) {
//            System.out.println(allLeafs.get(i).getData());
//        }
        for (int i = 0; i < allLeafs.size(); i++) {
            if (isInBrackets(allLeafs.get(i)) || isInNegateNode(allLeafs.get(i))) {
                if (isInBrackets(allLeafs.get(i))) {
                    // 如果是第一个孩子 一定是[ 在stringBuilder中添加(
                    // 如果是最后一个孩子 一定是] 在stringBuild中添加)
                    // 其他孩子 如果是\s\S\d\D\w\W 则按对应的值添加
                    // 如果是 a-c这种 使用BracketUtils.isCollectionSymbol判断
                    // 如果是SPECIAL_CHARACTERS_BRACKET中的字符 要先加斜杠
                    // 中间用或|连接
                    if (allLeafs.get(i).getData().equals("[") && allLeafs.get(i).isFirstChild()) {
                        stringBuilder.append("(");
                    } else if (allLeafs.get(i).getData().equals("]") && allLeafs.get(i).isLastChild()) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        stringBuilder.append(")");
                    } else if (allLeafs.get(i).getData().equals("\\s")) {
                        stringBuilder.append("[\f \n\r\t\u000b]");
                        stringBuilder.append("|");
                    } else if (allLeafs.get(i).getData().equals("\\S")) {
                        stringBuilder.append("[^\f \n\r\t\u000b]");
                        stringBuilder.append("|");
                    } else if (allLeafs.get(i).getData().equals("\\d")) {
                        stringBuilder.append("[0-9]");
                        stringBuilder.append("|");
                    } else if (allLeafs.get(i).getData().equals("\\D")) {
                        stringBuilder.append("[^0-9]");
                        stringBuilder.append("|");
                    } else if (allLeafs.get(i).getData().equals("\\w")) {
                        stringBuilder.append("[A-Za-z0-9_]");
                        stringBuilder.append("|");
                    } else if (allLeafs.get(i).getData().equals("\\W")) {
                        stringBuilder.append("[^A-Za-z0-9_]");
                        stringBuilder.append("|");
                    } else {
                        if (isGeneralizedCollectionSymbol(allLeafs.get(i).getParent())) {
                            stringBuilder.append("[" + allLeafs.get(i).getParent().getData()
                                    .replace("\\n", "\n").replace("\\r", "\r")
                                    .replace("\\f", "\f").replace("\\v", "\u000b")
                                    .replace("\\u000b", "\u000b") + "]");
                            stringBuilder.append("|");

                            i += 2;
                        } else {
                            if (isSpecialCharacterBracket(allLeafs.get(i).getData())) {
                                stringBuilder.append("\\" + allLeafs.get(i).getData());
                            } else if (allLeafs.get(i).getData().equals("\\t")) {
                                stringBuilder.append("\t");
                            } else if (allLeafs.get(i).getData().equals("\\f")) {
                                stringBuilder.append("\f");
                            } else if (allLeafs.get(i).getData().equals("\\n")) {
                                stringBuilder.append("\n");
                            } else if (allLeafs.get(i).getData().equals("\\v") || allLeafs.get(i).getData().equals("\\u000b")) {
                                stringBuilder.append("\u000b");
                            } else if (allLeafs.get(i).getData().equals("\\r")) {
                                stringBuilder.append("\r");
                            } else if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(allLeafs.get(i).getData())) {   // 修正
//                            stringBuilder.append("[" + allLeafs.get(i).getData() + "]");
                                stringBuilder.append("\\" + allLeafs.get(i).getData());
                            } else {
                                stringBuilder.append(allLeafs.get(i).getData());
                            }
                            stringBuilder.append("|");
                        }
                    }
                } else {    // 是在[^...]结点中 这里经过处理已经没有\D \S \W了
                    // 如果是第一个孩子 一定是[ 在stringBuilder中添加[
                    // 如果是第二个孩子 一定是^ 在stringBuilder中添加^
                    // 如果是最后一个孩子 一定是] 在stringBuild中添加]
                    // 其他孩子 如果是\s\d\w 则按对应的值添加
                    // 如果是 a-c这种 使用BracketUtils.isCollectionSymbol判断
                    // 如果是SPECIAL_CHARACTERS_BRACKET中的字符 要先加斜杠
                    if (allLeafs.get(i).getData().equals("[") && allLeafs.get(i).isFirstChild()) {
                        stringBuilder.append("[");
                    } else if (allLeafs.get(i).getData().equals("^") && allLeafs.get(i).isSecondChild()) {
                        stringBuilder.append("^");
                    } else if (allLeafs.get(i).getData().equals("]") && allLeafs.get(i).isLastChild()) {
                        stringBuilder.append("]");
                    } else if (allLeafs.get(i).getData().equals("\\s")) {
                        stringBuilder.append("\f \n\r\t\u000b");
                    } else if (allLeafs.get(i).getData().equals("\\d")) {
                        stringBuilder.append("0-9");
                    } else if (allLeafs.get(i).getData().equals("\\w")) {
                        stringBuilder.append("A-Za-z0-9_");
                    } else {
                        if (isGeneralizedCollectionSymbol(allLeafs.get(i).getParent())) {
                            stringBuilder.append(allLeafs.get(i).getParent().getData()
                                    .replace("\\n", "\n").replace("\\r", "\r")
                                    .replace("\\f", "\f").replace("\\v", "\u000b")
                                    .replace("\\u000b", "\u000b"));

                            i += 2;
                        } else {
                            if (isSpecialCharacterBracket(allLeafs.get(i).getData())) {
                                stringBuilder.append("\\" + allLeafs.get(i).getData());
                            } else if (allLeafs.get(i).getData().equals("\\t")) {
                                stringBuilder.append("\t");
                            } else if (allLeafs.get(i).getData().equals("\\f")) {
                                stringBuilder.append("\f");
                            } else if (allLeafs.get(i).getData().equals("\\n")) {
                                stringBuilder.append("\n");
                            } else if (allLeafs.get(i).getData().equals("\\v") || allLeafs.get(i).getData().equals("\\u000b")) {
                                stringBuilder.append("\u000b");
                            } else if (allLeafs.get(i).getData().equals("\\r")) {
                                stringBuilder.append("\r");
                            } else if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(allLeafs.get(i).getData())) {   // 修正
//                            stringBuilder.append("[" + allLeafs.get(i).getData() + "]");
                                stringBuilder.append("\\" + allLeafs.get(i).getData());
                            } else {
                                stringBuilder.append(allLeafs.get(i).getData());
                            }
                        }
                    }
                }
            } else {
                if (allLeafs.get(i).getData().equals("\\s")) {
                    stringBuilder.append("[\f \n\r\t\u000b]");
                } else if (allLeafs.get(i).getData().equals("\\S")) {
                    stringBuilder.append("[^\f \n\r\t\u000b]");
                } else if (allLeafs.get(i).getData().equals("\\d")) {
                    stringBuilder.append("[0-9]");
                } else if (allLeafs.get(i).getData().equals("\\D")) {
                    stringBuilder.append("[^0-9]");
                } else if (allLeafs.get(i).getData().equals("\\w")) {
                    stringBuilder.append("[A-Za-z0-9_]");
                } else if (allLeafs.get(i).getData().equals("\\W")) {
                    stringBuilder.append("[^A-Za-z0-9_]");
                } else if (allLeafs.get(i).getData().equals(".")) {
                    if (repairDot) {
//                    stringBuilder.append("[\u0000-\u0009\u000b-\u000c\u000e-\uffff]");

//                        // 这里做了个优化
//                        // 如果根节点获取getLetterSet为空 则说明是全集 则使用dot_MATCH_CHARACTER
//                        // 否则按getLetterSet来算
//                        // 如果输入.*这种只有.的 则默认添加个符号!
//                        Set<String> auxiliarySet = treeNode.getRoot().getLetterSet(false);
//                        // .删除\n和\r并强制加入!
//                        auxiliarySet.remove("\\n");
//                        auxiliarySet.remove("\\r");
//                        if (auxiliarySet.contains("\\t")) {
//                            auxiliarySet.remove("\\t");
//                            auxiliarySet.add("\t");
//                        }
//                        if (auxiliarySet.contains("\\f")) {
//                            auxiliarySet.remove("\\f");
//                            auxiliarySet.add("\f");
//                        }
//                        if (auxiliarySet.contains("\\v") || auxiliarySet.contains("\\u000b")) {
//                            auxiliarySet.remove("\\v");
//                            auxiliarySet.remove("\\u000b");
//                            auxiliarySet.add("\u000b");
//                        }
//                        auxiliarySet.add("!");
//
//                        stringBuilder.append("[");
//                        for (String s : auxiliarySet) {
//                            stringBuilder.append(s);
//                        }
//                        stringBuilder.append("]");

                        // 重新优化 考虑[\u00a1-\uffff]这种getLetterSet会超大 不如直接用[^\n\r]来表示.
                        stringBuilder.append("[^\n\r]");

                    } else {
                        stringBuilder.append(".");
                    }
                } else {
                    if (allLeafs.get(i).getData().equals("\\t")) {
                        stringBuilder.append("\t");
                    } else if (allLeafs.get(i).getData().equals("\\f")) {
                        stringBuilder.append("\f");
                    } else if (allLeafs.get(i).getData().equals("\\n")) {
                        stringBuilder.append("\n");
                    } else if (allLeafs.get(i).getData().equals("\\v") || allLeafs.get(i).getData().equals("\\u000b")) {
                        stringBuilder.append("\u000b");
                    } else if (allLeafs.get(i).getData().equals("\\r")) {
                        stringBuilder.append("\r");
                    } else if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(allLeafs.get(i).getData())) {   // 修正
//                        stringBuilder.append("[" + allLeafs.get(i).getData() + "]");
                        stringBuilder.append("\\" + allLeafs.get(i).getData());
                    } else {
                        stringBuilder.append(allLeafs.get(i).getData());
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    // 重写reWriteMetaEscape 输入改为String regex
    public static String reWriteMetaEscape(String regex, boolean repairDot) throws InterruptedException {
        if (regex == null) return null;
        return reWriteMetaEscape(createReDoSTree(regex), repairDot);
    }

    // 恢复特殊字符for dk.brics.automaton
    public static String reductSpecialStringForDkBricsAutomaton(String regex) {
        for (String key : SPECIAL_STRING_NEED_REDUCTION_FOR_DK_BRICS_AUTOMATON.keySet()) {
            regex = regex.replace(key, SPECIAL_STRING_NEED_REDUCTION_FOR_DK_BRICS_AUTOMATON.get(key));
        }
        return regex;
    }

    // 针对EOD生成infix特别的需要对某个结点进行跳过
    // child1和child2 是 treeNode的某两个孩子结点
    public static String reWriteMetaEscapeForEODInfix(TreeNode treeNode, TreeNode child1, TreeNode child2) {
        StringBuilder stringBuilder = new StringBuilder();
        List<TreeNode> allLeafs = treeNode.getLeafNodes();
        // 删除child的所有孩子
        for (int i = allLeafs.size() - 1; i >= 0; i--) {
            if (child1.isNowNodeChildOrGrandchild(allLeafs.get(i)) || child1 == allLeafs.get(i) ||
                    child2.isNowNodeChildOrGrandchild(allLeafs.get(i)) || child2 == allLeafs.get(i)) {
                allLeafs.remove(i);
            }
        }

        for (int i = 0; i < allLeafs.size(); i++) {
            if (isInBrackets(allLeafs.get(i)) || isInNegateNode(allLeafs.get(i))) {
                // 如果是第一个孩子 一定是[ 在stringBuilder中添加(
                // 如果是最后一个孩子 一定是] 在stringBuild中添加)
                // 其他孩子 如果是\s\S\d\D\w\W 则按对应的值添加
                // 如果是 a-c这种 使用BracketUtils.isCollectionSymbol判断
                // 如果是SPECIAL_CHARACTERS_BRACKET中的字符 要先加斜杠
                // 中间用或|连接
                if (allLeafs.get(i).getData().equals("[") && allLeafs.get(i).isFirstChild()) {
                    stringBuilder.append("(");
                } else if (allLeafs.get(i).getData().equals("]") && allLeafs.get(i).isLastChild()) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.append(")");
                } else if (allLeafs.get(i).getData().equals("\\s")) {
                    stringBuilder.append("[\f \n\r\t\u000b]");
                    stringBuilder.append("|");
                } else if (allLeafs.get(i).getData().equals("\\S")) {
                    stringBuilder.append("[^\f \n\r\t\u000b]");
                    stringBuilder.append("|");
                } else if (allLeafs.get(i).getData().equals("\\d")) {
                    stringBuilder.append("[0-9]");
                    stringBuilder.append("|");
                } else if (allLeafs.get(i).getData().equals("\\D")) {
                    stringBuilder.append("[^0-9]");
                    stringBuilder.append("|");
                } else if (allLeafs.get(i).getData().equals("\\w")) {
                    stringBuilder.append("[A-Za-z0-9_]");
                    stringBuilder.append("|");
                } else if (allLeafs.get(i).getData().equals("\\W")) {
                    stringBuilder.append("[^A-Za-z0-9_]");
                    stringBuilder.append("|");
                } else {
                    if (isGeneralizedCollectionSymbol(allLeafs.get(i).getParent())) {
                        stringBuilder.append("[" + allLeafs.get(i).getParent().getData()
                                .replace("\\n", "\n").replace("\\r", "\r")
                                .replace("\\f", "\f").replace("\\v", "\u000b")
                                .replace("\\u000b", "\u000b") + "]");
                        stringBuilder.append("|");

                        i += 2;
                    } else {
                        if (isSpecialCharacterBracket(allLeafs.get(i).getData())) {
                            stringBuilder.append("\\" + allLeafs.get(i).getData());
                        } else if (allLeafs.get(i).getData().equals("\\t")) {
                            stringBuilder.append("\t");
                        } else if (allLeafs.get(i).getData().equals("\\f")) {
                            stringBuilder.append("\f");
                        } else if (allLeafs.get(i).getData().equals("\\n")) {
                            stringBuilder.append("\n");
                        } else if (allLeafs.get(i).getData().equals("\\v") || allLeafs.get(i).getData().equals("\\u000b")) {
                            stringBuilder.append("\u000b");
                        } else if (allLeafs.get(i).getData().equals("\\r")) {
                            stringBuilder.append("\r");
                        } else if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(allLeafs.get(i).getData())) {   // 修正
//                            stringBuilder.append("[" + allLeafs.get(i).getData() + "]");
                            stringBuilder.append("\\" + allLeafs.get(i).getData());
                        } else {
                            stringBuilder.append(allLeafs.get(i).getData());
                        }
                        stringBuilder.append("|");
                    }
                }
            } else {
                if (allLeafs.get(i).getData().equals("\\s")) {
                    stringBuilder.append("[\f \n\r\t\u000b]");
                } else if (allLeafs.get(i).getData().equals("\\S")) {
                    stringBuilder.append("[^\f \n\r\t\u000b]");
                } else if (allLeafs.get(i).getData().equals("\\d")) {
                    stringBuilder.append("[0-9]");
                } else if (allLeafs.get(i).getData().equals("\\D")) {
                    stringBuilder.append("[^0-9]");
                } else if (allLeafs.get(i).getData().equals("\\w")) {
                    stringBuilder.append("[A-Za-z0-9_]");
                } else if (allLeafs.get(i).getData().equals("\\W")) {
                    stringBuilder.append("[^A-Za-z0-9_]");
                } else if (allLeafs.get(i).getData().equals(".")) {
//                    stringBuilder.append("[\u0000-\u0009\u000b-\u000c\u000e-\uffff]");

//                    // 这里做了个优化
//                    // 如果根节点获取getLetterSet为空 则说明是全集 则使用dot_MATCH_CHARACTER
//                    // 否则按getLetterSet来算
//                    // 如果输入.*这种只有.的 则默认添加个符号!
//                    Set<String> auxiliarySet = treeNode.getRoot().getLetterSet(false);
//                    // .删除\n和\r并强制加入!
//                    auxiliarySet.remove("\\n");
//                    auxiliarySet.remove("\\r");
//                    if (auxiliarySet.contains("\\t")) {
//                        auxiliarySet.remove("\\t");
//                        auxiliarySet.add("\t");
//                    }
//                    if (auxiliarySet.contains("\\f")) {
//                        auxiliarySet.remove("\\f");
//                        auxiliarySet.add("\f");
//                    }
//                    if (auxiliarySet.contains("\\v") || auxiliarySet.contains("\\u000b")) {
//                        auxiliarySet.remove("\\v");
//                        auxiliarySet.remove("\\u000b");
//                        auxiliarySet.add("\u000b");
//                    }
//                    auxiliarySet.add("!");
//
//                    stringBuilder.append("[");
//                    for (String s : auxiliarySet) {
//                        stringBuilder.append(s);
//                    }
//                    stringBuilder.append("]");

                    // 重新优化 考虑[\u00a1-\uffff]这种getLetterSet会超大 不如直接用[^\n\r]来表示.
                    stringBuilder.append("[^\n\r]");

                } else {
                    if (allLeafs.get(i).getData().equals("\\t")) {
                        stringBuilder.append("\t");
                    } else if (allLeafs.get(i).getData().equals("\\f")) {
                        stringBuilder.append("\f");
                    } else if (allLeafs.get(i).getData().equals("\\n")) {
                        stringBuilder.append("\n");
                    } else if (allLeafs.get(i).getData().equals("\\v") || allLeafs.get(i).getData().equals("\\u000b")) {
                        stringBuilder.append("\u000b");
                    } else if (allLeafs.get(i).getData().equals("\\r")) {
                        stringBuilder.append("\r");
                    } else if (isSpecialStringNeedAddSquareBracketsForDkBricsAutomaton(allLeafs.get(i).getData())) {   // 修正
//                        stringBuilder.append("[" + allLeafs.get(i).getData() + "]");
                        stringBuilder.append("\\" + allLeafs.get(i).getData());
                    } else {
                        stringBuilder.append(allLeafs.get(i).getData());
                    }
                }
            }
        }
        return stringBuilder.toString();
    }


    // 输入一个(一串)正则 返回求交集后生成串
    // 第一个参数regexList1 表示要求交的各个正则 这里的正则是全部要转义的 主要是.会变成[...]
    // 第二个参数regexList2 表示要求交的各个正则 这里的正则中的.是不转义的 就表示任意字符(包括\n\r) 用处在EOA/EOD里 可以firstCharacter.*
    // 第二个参数accepted 表示生成的串是匹配还是不匹配
    // 第三个参数minLength 表示所生成的串长度最小值  用-1表示无限制
    // 第四个参数maxLength 表示所生成的串长度最大值  用-1表示无限制
    // 返回null表示生成失败
    public static String getExampleByDkBricsAutomaton(List<String> regexList1, List<String> regexList2, boolean accepted, int minLength, int maxLength) throws InterruptedException {
        Automaton automaton = getIntersectionAutomaton(regexList1, regexList2, minLength, maxLength);
        return automaton.getShortestExample(accepted);
    }

    // 重写getExampleByDkBricsAutomaton
    public static String getExampleByDkBricsAutomaton(List<String> regexList1) throws InterruptedException {
        return getExampleByDkBricsAutomaton(regexList1, null, true, -1, -1);
    }

    public static String getExampleByDkBricsAutomaton(List<String> regexList1, List<String> regexList2) throws InterruptedException {
        return getExampleByDkBricsAutomaton(regexList1, regexList2, true, -1, -1);
    }

    public static String getExampleByDkBricsAutomaton(List<String> regexList1, List<String> regexList2, boolean accepted) throws InterruptedException {
        return getExampleByDkBricsAutomaton(regexList1, regexList2, accepted, -1, -1);
    }

    public static String getExampleByDkBricsAutomaton(List<String> regexList1, List<String> regexList2, boolean accepted, int minLength) throws InterruptedException {
        return getExampleByDkBricsAutomaton(regexList1, regexList2, accepted, minLength, -1);
    }

    public static String getExampleByDkBricsAutomaton(List<String> regexList1, List<String> regexList2, int minLength) throws InterruptedException {
        return getExampleByDkBricsAutomaton(regexList1, regexList2, true, minLength, -1);
    }

    // 返回交集自动机
    public static Automaton getIntersectionAutomaton(List<String> regexList1, List<String> regexList2, int minLength, int maxLength) throws InterruptedException {
        String regex = "";
        if (regexList1 != null) {
            for (int i = 0; i < regexList1.size(); i++) {
                regex = regex + "(" + regexList1.get(i) + ")";
                if (i != regexList1.size() - 1) {
                    regex = regex + "＆";    // 注意这里是＆不是& 因为&会被加方括号 无法表示交集了 待全拼接完后再统一还原回&
                }
            }
            regex = reWriteMetaEscape(regex, true);
        }

//        System.out.println("regex = " + regex);

        if (regexList2 != null) {
            for (int i = 0; i < regexList2.size(); i++) {
//                String str = "";
//                for (int j = 0; j < regexList2.get(i).length(); j++) {
//                    str = str + addSquareBracketsForSpecialStringForDkBricsAutomaton(String.valueOf(regexList2.get(i).charAt(j)));
//                }
                String str = addSquareBracketsForSpecialStringForDkBricsAutomaton(regexList2.get(i));
//                System.out.println("str = " + str);
                if (regex.equals("")) {
                    regex = str;
                } else {
                    regex = regex + "＆(" + str + ")";
                }
            }
            regex = reWriteMetaEscape(regex, false);
        }
//        System.out.println("regex = " + regex);

        if (regex.equals("")) {
            if (minLength == -1 && maxLength == -1) {   // 全部缺省 则按最一般生成 默认加入.+保证生成长度大于等于1
                regex = regex + ".+";
            } else if (minLength == -1 && maxLength != -1) { // 限定上限了
                regex = regex + ".{1," + maxLength + "}";
            } else if (minLength != -1 && maxLength == -1) { // 限定下限了
                regex = regex + ".{" + minLength + ",}";
            } else if (minLength != -1 && maxLength != -1) {    // 上下限全限定了
                regex = regex + ".{" + minLength + "," + maxLength + "}";
            }
        } else {
            if (minLength == -1 && maxLength == -1) {   // 全部缺省 则按最一般生成 默认加入.+保证生成长度大于等于1
                regex = regex + "＆(.+)";
            } else if (minLength == -1 && maxLength != -1) { // 限定上限了
                regex = regex + "＆(.{1," + maxLength + "})";
            } else if (minLength != -1 && maxLength == -1) { // 限定下限了
                regex = regex + "＆(.{" + minLength + ",})";
            } else if (minLength != -1 && maxLength != -1) {    // 上下限全限定了
                regex = regex + "＆(.{" + minLength + "," + maxLength + "})";
            }
        }

        regex = reductSpecialStringForDkBricsAutomaton(regex);

//        System.out.println("regex = " + regex);

        // 加一下处理狗屎正则的优化
        regex = optimizeCounting(regex);

//        System.out.println("regex = " + regex);

        RegExp regExp = new RegExp(regex);
        Automaton automaton = regExp.toAutomaton(false);    // 这里要加第二个参数minimize: false 这样就是nfa了 比dfa快
        return automaton;
    }

    // 返回自动机
    public static Automaton getAutomaton(String regex) throws InterruptedException {
        regex = reWriteMetaEscape(regex, true);

        regex = reductSpecialStringForDkBricsAutomaton(regex);

//        System.out.println("regex = " + regex);

        // 加一下处理狗屎正则的优化
        regex = optimizeCounting(regex);

//        System.out.println("regex = " + regex);

        RegExp regExp = new RegExp(regex);
        Automaton automaton = regExp.toAutomaton(false);    // 这里要加第二个参数minimize: false 这样就是nfa了 比dfa快
        return automaton;
    }


    public static Automaton getIntersectionAutomaton(List<String> regexList1) throws InterruptedException {
        return getIntersectionAutomaton(regexList1, null, -1, -1);
    }

    public static Automaton getIntersectionAutomaton(List<String> regexList1, List<String> regexList2) throws InterruptedException {
        return getIntersectionAutomaton(regexList1, regexList2, -1, -1);
    }

    public static Automaton getIntersectionAutomaton(List<String> regexList1, List<String> regexList2, int minLength) throws InterruptedException {
        return getIntersectionAutomaton(regexList1, regexList2, minLength, -1);
    }

    // 判断自动机是否接受空串
    public static boolean isAcceptEmptyString(Automaton automaton) throws InterruptedException {
        return automaton.run("");
    }

    // 处理臭狗屎正则必备 改counting 把{m,n}中的n＞1的counting改写为{m,}  {1,250} -> {1,}
    public static String optimizeCounting(String regex) throws InterruptedException {
        TreeNode tree = createReDoSTree(regex);
        List<TreeNode> allLeafs = tree.getLeafNodes();
        for (TreeNode allLeaf : allLeafs) {
            if (isQuantifierNode(allLeaf)) {
//                System.out.println(allLeafs.get(i).getData());
                String data = allLeaf.getData();
                int firstNum = getCountingFirstNum(data);
                int secondNum = getCountingSecondNum(data);
//                System.out.println(data + " " + firstNum + " " + secondNum);
                if (secondNum > 1 && data.contains(",")) {
                    allLeaf.setData("{" + firstNum + ",}");
                }
            }
        }
        return tree.getLeafsData();
    }

    // 还原(?= (?<= (?! (?<!
    public static String reductAssertPattern(TreeNode treeNode) throws InterruptedException {
        TreeNode treeNodeCopy = SerializationUtils.clone(treeNode);    // 深拷贝

        Stack<TreeNode> auxiliaryStack = new Stack<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        auxiliaryStack.push(treeNodeCopy);
        while (!auxiliaryStack.isEmpty()) {
            TreeNode tmp = auxiliaryStack.pop();
            if (getAssertType(tmp) != null && getAssertType(tmp) != AssertType.NO_ASSERT) {
                treeNodeStack.push(tmp);
            }
            for (int i = 0; i < tmp.getChildCount(); i++) {
                auxiliaryStack.push(tmp.getChild(i));
            }
        }
        // 后序遍历处理
        while (!treeNodeStack.isEmpty()) {
            TreeNode node = treeNodeStack.pop();
            AssertType assertType = getAssertType(node);
            if (assertType == AssertType.POSITIVE_AHEAD) {
                String newData = "(?=)";
                if (node.getChild(0).getChildCount() == 3) {
                    newData = "(?=" + node.getChild(0).getChild(1).getData() + ")";    // (?=
                }
                node.updateTreeByModifyNode(newData);
            } else if (assertType == AssertType.NEGATIVE_AHEAD) {
                String newData = "(?!)";
                if (node.getChild(0).getChildCount() == 3) {
                    newData = "(?!" + node.getChild(0).getChild(1).getData() + ")";    // (?!
                }
                node.updateTreeByModifyNode(newData);
            } else if (assertType == AssertType.POSITIVE_BEHIND) {
                String newData = "(?<=)";
                if (node.getChild(0).getChildCount() == 3) {
                    newData = "(?<=" + node.getChild(0).getChild(1).getData() + ")";    // (?<=
                }
                node.updateTreeByModifyNode(newData);
            } else if (assertType == AssertType.NEGATIVE_BEHIND) {
                String newData = "(?<!)";
                if (node.getChild(0).getChildCount() == 3) {
                    newData = "(?<!" + node.getChild(0).getChild(1).getData() + ")";    // (?<!
                }
                node.updateTreeByModifyNode(newData);
            }
        }
        return treeNodeCopy.getData();
    }

    // 获取前缀匹配串
    public static String getPrefixString(TreeNode root, TreeNode left, boolean considerZeroWidthAssertions) throws InterruptedException {
        // 获取前缀 + 中缀正则
        String r1 = getR0(root, left);  // 前缀正则
        // 中缀这里要强制改成{1,}
        String r2 = left.getData();
        if (isCanEmptyNode(left)) {
            r2 = "((" + r2 + ")＆[\\s\\S]{1,})";
        }


        TreeNode newTreeNode = createReDoSTree("(" + r1 + ")(" + r2 + ")");
        // 使用重写后的去首尾^$
        newTreeNode.deleteCaretAndDollarSymbols();
        // 新版重写空串
        newTreeNode = removeBlankStr(newTreeNode);
        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        newTreeNode.deleteNonCapturingGroupFlag();
        if (considerZeroWidthAssertions) {  // 如果考虑零宽断言
            List<String> translateRegexForLookAroundList = getTranslateRegexForAssertionsList(newTreeNode);
            return getExampleByDkBricsAutomaton2(translateRegexForLookAroundList, 0);
        } else {    // 如果不考虑零宽断言
            // 删除零宽断言
            deleteZeroWidthAssertion(newTreeNode);
            // 使用重写后的去首尾^$
            newTreeNode.deleteCaretAndDollarSymbols();
            // 新版重写空串
            newTreeNode = removeBlankStr(newTreeNode);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            newTreeNode.deleteNonCapturingGroupFlag();
            List<String> regexList1 = new ArrayList<>();
            regexList1.add(newTreeNode.getData());
            return getExampleByDkBricsAutomaton2(regexList1, 0);
        }
    }

    // 获取后缀不匹配串
    public static List<String> getSuffixString(TreeNode root, TreeNode left, String infixString) throws InterruptedException {
        // 生成后缀的方式
        // r2 -> 非空的r2
        // 如果r3没有 且 r2不为空   yz∉L(r2)且|z|≥1
        // 如果r3没有 且 r2为空     yz∉L(r2)且|z|≥1  这种情况不存在
        // 如果r3有   且 r3不为空   z∉L(r2.*)且z∉L(r3) (1)|z|≥1 (2)|z|≥0    注释掉
        //                       yz∉L(r2)且yz∉L(r2r3) (1)|yz|≥1+|y|
        // 如果r3有   且 r3为空     z∉L(r2.*)且z∉L(r3) (1)|z|≥1 (2)|z|≥0   注释掉

        String r2 = left.getData();
        if (isCanEmptyNode(left)) {
            r2 = "((" + r2 + ")＆[\\s\\S]{1,})";
        }

        String r3 = getR4(root, left);
        TreeNode r3Tree = createReDoSTree(r3);
        // 使用重写后的去首尾^$
        r3Tree.deleteCaretAndDollarSymbols();
        // 新版重写空串
        r3Tree = removeBlankStr(r3Tree);
        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        r3Tree.deleteNonCapturingGroupFlag();
        if (r3Tree.getData().equals("")) {
            TreeNode newR2Tree = createReDoSTree(r2);
            // 使用重写后的去首尾^$
            newR2Tree.deleteCaretAndDollarSymbols();
            // 新版重写空串
            newR2Tree = removeBlankStr(newR2Tree);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            newR2Tree.deleteNonCapturingGroupFlag();
            List<String> trans = getTranslateRegexForAssertionsList(newR2Tree);
            List<String> regexList1 = new ArrayList<>(trans);


            String transInfixString = "";
            for (int i = 0; i < infixString.length(); i++) {
                if (isSpecialCharacterBracket(infixString.charAt(i))) {
                    transInfixString += "\\" + infixString.charAt(i);
                } else {
                    transInfixString += infixString.charAt(i);
                }
            }

            for (int i = 0; i < regexList1.size(); i++) {
                String regex = regexList1.get(i);
                if (! transInfixString.equals("")) {
                    regexList1.set(i, transInfixString + "[\\s\\S]*" + "＆" + "～(" + regex + ")");
                } else {
                    regexList1.set(i, "～(" + regex + ")");
                }
            }
            String example = getExampleByDkBricsAutomaton2(regexList1, 1 + infixString.length());
            List<String> exampleList = new ArrayList<>();
            if (example == null) {
                exampleList.add("");
            } else {
                exampleList.add(example);
            }

            if (! transInfixString.equals("")) {
                for (int i = 0; i < exampleList.size(); i++) {
//                    String z = exampleList.get(i).replaceFirst("\\Q" + transInfixString + "\\E", "");
                    String z = exampleList.get(i);
                    if (z.startsWith(infixString)) {
                        z = z.substring(infixString.length());
                    }
                    exampleList.set(i, z);
                }
            }
            return exampleList;
        } else {
//            // z∉L(r2.*)且z∉L(r3) (1)|z|≥1 (2)|z|≥0
//            TreeNode r2DotStar = createnewlyttree(r2 + "[\\s\\S]*");
//            // 使用重写后的去首尾^$
//            r2DotStar.deleteCaretAndDollarSymbols();
//            // 新版重写空串
//            r2DotStar = removeBlankStr(r2DotStar);
//            // 重写反向引用后 删除NonCapturingGroupFlag ?:
//            r2DotStar.deleteNonCapturingGroupFlag();
//            List<String> transR2 = getTranslateRegexForAssertionsList(r2DotStar);
//            List<String> transR3 = getTranslateRegexForAssertionsList(r3Tree);
//            List<String> regexList1 = new ArrayList<>();
//            regexList1.addAll(transR2);
//            regexList1.addAll(transR3);
//            for (int i = 0; i < regexList1.size(); i++) {
//                String regex = regexList1.get(i);
//                regexList1.set(i, "～(" + regex + ")");
//            }   // 要注释掉这个for会超时
//
//            List<String> exampleList = new ArrayList<>();
//            String example0 = getExampleByDkBricsAutomaton2(regexList1, 0);
//            if (example0 == null) {
//                exampleList.add("");
//            } else {
//                exampleList.add(example0);
//            }
//            String example1 = getExampleByDkBricsAutomaton2(regexList1, 1);
//            if (example1 == null) {
//                exampleList.add("");
//            } else {
//                exampleList.add(example1);
//            }
            // ====================================


            // yz∉L(r2)且yz∉L(r2r3) (1)|yz|≥1+|y|
            String transInfixString = "";
            for (int i = 0; i < infixString.length(); i++) {
                if (isSpecialCharacterBracket(infixString.charAt(i))) {
                    transInfixString += "\\" + infixString.charAt(i);
                } else {
                    transInfixString += infixString.charAt(i);
                }
            }


            TreeNode r2Tree = createReDoSTree(r2);
            // 使用重写后的去首尾^$
            r2Tree.deleteCaretAndDollarSymbols();
            // 新版重写空串
            r2Tree = removeBlankStr(r2Tree);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            r2Tree.deleteNonCapturingGroupFlag();
            List <String> transR2 = getTranslateRegexForAssertionsList(r2Tree);
            TreeNode transInfixStringR3Tree = createReDoSTree("(" + r2 + ")(" + r3 + ")");
            // 使用重写后的去首尾^$
            transInfixStringR3Tree.deleteCaretAndDollarSymbols();
            // 新版重写空串
            transInfixStringR3Tree = removeBlankStr(transInfixStringR3Tree);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            transInfixStringR3Tree.deleteNonCapturingGroupFlag();
            List<String> transR3 = getTranslateRegexForAssertionsList(transInfixStringR3Tree);
            List<String> regexList1 = new ArrayList<>();
            regexList1.addAll(transR2);
            regexList1.addAll(transR3);

            for (int i = 0; i < regexList1.size(); i++) {
                String regex = regexList1.get(i);
                regexList1.set(i, transInfixString + "[\\s\\S]*" + "＆" + "～(" + regex + ")");
            }

            List<String> exampleList = new ArrayList<>();
            String example1 = getExampleByDkBricsAutomaton2(regexList1, 1 + infixString.length());
            if (example1 == null) {
                exampleList.add("");
            } else {
                exampleList.add(example1);
            }

            for (int i = 0; i < exampleList.size(); i++) {
//                String z = exampleList.get(i).replaceFirst("\\Q" + transInfixString + "\\E", "");
                String z = exampleList.get(i);
                if (z.startsWith(infixString)) {
                    z = z.substring(infixString.length());
                }
                exampleList.set(i, z);
            }

            return exampleList;
        }
    }


    // 获取前缀匹配串 考虑lookAround
    public static String getPrefixStringWithLookAround(TreeNode root, TreeNode left) throws InterruptedException {
        Pair<String, String> regexPair = getPrefixRegexWithLookAround(root, left);

        // 改为前缀正则 + 中缀正则
        String restoreAssertPatternRegex = regexPair.getKey() + regexPair.getValue();


//        System.out.println("restoreAssertPatternRegex = " + restoreAssertPatternRegex);

//        // root = (.{1,})?(\d+)+a   left = (\d+)+   前缀(.{1,})?  转换回lookaround是(?=.{1,})
//        // 直接求例子是得\u0000 这是错误的  应该是再连接上(\d+)+   变成(?=.{1,})(\d+)+
//        // 尝试
//        restoreAssertPatternRegex += left.getData();


//        System.out.println("restoreAssertPatternRegex = " + restoreAssertPatternRegex);
//        TreeNode newTreeNode = PatternNQUtils.getReDoSTree(restoreAssertPatternRegex, "PCRE");
        TreeNode newTreeNode = createReDoSTree(restoreAssertPatternRegex);
////        printTree(newTreeNode);
//        restoreAssertPatternRegex = translateRegexForLookAround(newTreeNode);
////        System.out.println("restoreAssertPatternRegex = " + restoreAssertPatternRegex);
////        System.out.println("restoreAssertPatternRegex.length() = " + restoreAssertPatternRegex.length());
//
//
//
////        String prefix = getExampleByDkBricsAutomaton(null,new ArrayList<>(Collections.singleton(restoreAssertPatternRegex)), 0);
//        String prefix = getExampleByDkBricsAutomaton(Collections.singletonList(restoreAssertPatternRegex));
////        System.out.println("prefix = " + prefix);
//        return prefix != null ? prefix : "";


        List<String> translateRegexForLookAroundList = getTranslateRegexForAssertionsList(newTreeNode);
        return getExampleByDkBricsAutomaton2(translateRegexForLookAroundList, 0);
    }

    // 获取前缀和中缀串 考虑lookAround
    public static Pair<String, String> getPrefixAndInfixStringWithLookAround(TreeNode root, TreeNode left) throws InterruptedException {
        Pair<String, String> restoreAssertPatternRegexPair = getPrefixRegexWithLookAround(root, left);
        String prefixRegex = restoreAssertPatternRegexPair.getKey();
        String infixRegex = restoreAssertPatternRegexPair.getValue();
//        System.out.println("prefixRegex = " + prefixRegex);
//        System.out.println("infixRegex = " + infixRegex);
        String regexWithGroupName = "(?<prefix>" + prefixRegex + ")(?<infix>" + infixRegex + ")";
//        System.out.println("regexWithGroupName = " + regexWithGroupName);
//        TreeNode newTreeNode = PatternNQUtils.getReDoSTree(prefixRegex + infixRegex,"PCRE");
        TreeNode newTreeNode = createReDoSTree(prefixRegex + infixRegex);
        String restoreAssertPatternRegex = translateRegexForLookAround(newTreeNode);
//        System.out.println("restoreAssertPatternRegex = " + restoreAssertPatternRegex);
        String prefixAndInfixString = getExampleByDkBricsAutomaton(Collections.singletonList(restoreAssertPatternRegex));
//        System.out.println("prefixAndInfixString = " + prefixAndInfixString);
        if (prefixAndInfixString == null) return null;
        Matcher matcher = Pattern.compile(regexWithGroupName).matcher(prefixAndInfixString);
        if (matcher.find()) {
            return new Pair<>(matcher.group("prefix"), matcher.group("infix"));
        }
        return null;
    }


    // 获取后缀不匹配串 通过 前缀中缀.* & ~(除去补操作的正则) 的到的字符串 replace掉第一个前缀中缀 即为后缀
    // 考虑lookaround
    // rootWithoutRemoveNegateSymbol 为没有去补操作的树
    public static String getSuffixStringWithLookAround(TreeNode rootWithoutRemoveNegateSymbol, String prefixLinkInfix) throws InterruptedException {
        String restoreAssertPatternRegex = translateRegexForLookAround(rootWithoutRemoveNegateSymbol); // 还原lookaround
//        System.out.println("restoreAssertPatternRegex = " + restoreAssertPatternRegex);
        List<String> regex1List = new LinkedList<>();
        String transferPrefixLinkInfix = "";
        for (int i = 0; i < prefixLinkInfix.length(); i++) {
            if (isSpecialCharacterBracket(prefixLinkInfix.charAt(i))) {
                transferPrefixLinkInfix += "\\" + prefixLinkInfix.charAt(i);
            } else {
                transferPrefixLinkInfix += prefixLinkInfix.charAt(i);
            }
        }
        regex1List.add(transferPrefixLinkInfix + "[\\s\\S]*");
        regex1List.add("～(" + restoreAssertPatternRegex + ")");    // ~要写成～
        String suffix = getExampleByDkBricsAutomaton(regex1List, null, 0);
//        System.out.println("suffix = " + suffix + " suffix.length() = " + suffix.length());
        if (suffix == null) {
            return null;
        } else {
//            suffix = suffix.replaceFirst("\\Q" + prefixLinkInfix + "\\E", "");
            if (suffix.startsWith(prefixLinkInfix)) {
                suffix = suffix.substring(prefixLinkInfix.length());
            }
            return suffix;
        }
    }

    // 获取后缀不匹配串 通过 前缀中缀.* & ~(除去补操作的正则) 的到的字符串 replace掉第一个前缀中缀 即为后缀
    // 考虑lookaround
    // root为默认的树
    public static String getSuffixStringWithoutLookAround(TreeNode root, String prefixLinkInfix) throws InterruptedException {
//        String regexWithoutRemoveNegateSymbol = root.getData();
//        if (root.getOriginalRegex() != null) regexWithoutRemoveNegateSymbol = root.getOriginalRegex();  // 去补之前的正则
//        TreeNode newlyttree = createnewlyttree(regexWithoutRemoveNegateSymbol); // 新建一棵树
//        newlyttree = removeBlankStr(newlyttree);    // 把重写空串和删除?:接上
//        newlyttree.deleteNonCapturingGroupFlag();   // 得到一个含补的标准正则
        List<String> regex1List = new LinkedList<>();
        String transferPrefixLinkInfix = "";
        for (int i = 0; i < prefixLinkInfix.length(); i++) {
            if (isSpecialCharacterBracket(prefixLinkInfix.charAt(i))) {
                transferPrefixLinkInfix += "\\" + prefixLinkInfix.charAt(i);
            } else {
                transferPrefixLinkInfix += prefixLinkInfix.charAt(i);
            }
        }
        regex1List.add(transferPrefixLinkInfix + "[\\s\\S]*");
        regex1List.add("～(" + root.getData() + ")");    // ~要写成～
//        String suffix = getExampleByDkBricsAutomaton(regex1List, null, 0);
        String suffix = getExampleByDkBricsAutomaton2(regex1List, 0);


//        System.out.println("suffix = " + suffix + " suffix.length() = " + suffix.length());
        if (suffix == null) {
            return null;
        } else {
            suffix = suffix.replaceFirst("\\Q" + prefixLinkInfix + "\\E", "");
            return suffix;
        }
    }


    public static String translateRegexForLookAround(TreeNode treeNode) throws InterruptedException {
        // 后序遍历树 对于每一个树中的结点 根据assertType修改对应的写法 写的时候.*可以写成[\u0000-\uFFFF]*  &要写成＆   ~要写成～
        // AssertType.POSITIVE_AHEAD    r1(?=r2)r3   r1 ( r2.* & r3 )
        // AssertType.NEGATIVE_AHEAD    r1(?!r2)r3   r1 ( ~(r2.*) & r3 )
        // AssertType.POSITIVE_BEHIND   r1(?<=r2)r3  ( r1 & .*r2 ) r3
        // AssertType.NEGATIVE_BEHIND   r1(?<!r2)r3  ( r1 & ~(.*r2) ) r3

        // lookaround的父节点不可能是或结点 否则无意义  ((?!a)|a).*

        List<String> translateResults = new LinkedList<>();

        TreeNode treeNodeCopy = SerializationUtils.clone(treeNode);    // 深拷贝

        Stack<TreeNode> auxiliaryStack = new Stack<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        auxiliaryStack.push(treeNodeCopy);
        while (!auxiliaryStack.isEmpty()) {
            TreeNode tmp = auxiliaryStack.pop();
            treeNodeStack.push(tmp);
            for (int i = 0; i < tmp.getChildCount(); i++) {
                auxiliaryStack.push(tmp.getChild(i));
            }
        }
        // 后序遍历处理
        while (!treeNodeStack.isEmpty()) {
            TreeNode node = treeNodeStack.pop();
            AssertType assertType = getAssertType(node);
            if (assertType != null) {
                String r2 = "";
                if (node.getChild(0).getChildCount() == 3) {
                    r2 = node.getChild(0).getChild(1).getData();
                } else {    // 若出现(?=) (?!) (?<=) (?<!)这种 相当于无lookaround 无需修改
                    continue;
                }
                String r1 = "", r3 = "";    // TODO: 这里只找了同层的，实际应该穿透
                TreeNode parent = node.getParent();
                if (parent != null) {   // treeNode = (?=a)
                    List<TreeNode> childList = parent.getChildList();
//                    int index = childList.indexOf(node);
                    // getIndexByChainIndex 用chainIndex确定index
                    int index = -1;
                    for (int i = 0; i < childList.size(); i++) {
                        if (node.getChainIndex().equals(childList.get(i).getChainIndex())) {
                            index = i;
                            break;
                        }
                    }

                    for (int i = 0; i < index; i++) {
                        r1 = r1 + childList.get(i).getData();
                    }
                    for (int i = index + 1; i < childList.size(); i++) {
                        r3 = r3 + childList.get(i).getData();
                    }
                }
//                else {
//                    r1 = "[\\s\\S]*";
//                    r3 = "[\\s\\S]*";
//                }

                if (assertType == AssertType.POSITIVE_AHEAD) {
                    String newData = "";    // r1 ( r2.* & r3 )
                    if (r3.equals("")) {
                        newData = r1 + "(" + r2 + "[\\s\\S]*)";
                    } else {
                        newData = r1 + "(" + r2 + "[\\s\\S]*" + "＆" + r3 + ")";
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getParent().getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                } else if (assertType == AssertType.NEGATIVE_AHEAD) {
                    String newData = "";    // r1 ( ~(r2.*) & r3 )
                    if (r3.equals("")) {
                        newData = r1 + "(" + "～(" + r2 + "[\\s\\S]*" + "))";
                    } else {
                        newData = r1 + "(" + "～(" + r2 + "[\\s\\S]*" + ")＆" + r3 + ")";
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getParent().getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                } else if (assertType == AssertType.POSITIVE_BEHIND) {
                    String newData = "";    // ( r1 & .*r2 ) r3
                    if (r1.equals("")) {
                        newData = "([\\s\\S]*" + r2 + ")" + r3;
                    } else {
                        newData = "(" + r1 + "＆[\\s\\S]*" + r2 + ")" + r3;
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                } else if (assertType == AssertType.NEGATIVE_BEHIND) {
                    String newData = "";    // ( r1 & ~(.*r2) ) r3
                    if (r1.equals("")) {
                        newData = "(～([\\s\\S]*" + r2 + "))" + r3;
                    } else {
                        newData = "(" + r1 + "＆～([\\s\\S]*" + r2 + "))" + r3;
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                }
            }
        }

        if (translateResults.isEmpty()) translateResults.add(treeNode.getData());

//        System.out.println("translateResults = " + translateResults);

//        return treeNodeCopy.getData();
        return String.join("＆", translateResults);
    }


    // 获得root中 left之前的正则的匹配串 考虑lookaround
    // 返回 前缀正则,中缀正则
    public static Pair<String, String> getPrefixRegexWithLookAround(TreeNode root, TreeNode left) throws InterruptedException {
        root = getGroupSubNode(root);
        int firstNum = getCountingFirstNum(left.getChild(1).getData());
        int secondNum = getCountingSecondNum(left.getChild(1).getData());
        String leftRegex = left.getChild(0).getData();
        if (firstNum == 0) {
            if (secondNum == -1)
                leftRegex = leftRegex + "{1,}";
            else
                leftRegex = leftRegex + "{1," + secondNum + "}";
        } else {
            leftRegex = left.getData();
        }
        StringBuilder midRegex = new StringBuilder();
        boolean start = false;
        if (left == root) return new Pair<>("", leftRegex);
        while (left.getParent() != root) {
            TreeNode parent = left.getParent();
            if (parent != null && !isGroupNode(parent)) {
                if (isOrNode(parent)) {

                } else {
                    start = false;
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        if (start) {
//                            midRegex.append(parent.getChild(i).getData());
                            TreeNode node = parent.getChild(i);
                            if (!node.isNowNodeChildOrGrandchild(left) && node != left &&
//                                    !parent.getChild(i).isNowNodeChildOrGrandchild(right) && parent.getChild(i) != right &&
                                    !isQuantifierNode(node)) {
//                                midRegex.append(parent.getChild(i).getData());
//                                midRegex.insert(0, parent.getChild(i).getData());
                                AssertType assertType = getAssertType(node);
                                if (assertType != null) {
                                    String r2 = "";
                                    if (node.getChild(0).getChildCount() == 3) {
                                        r2 = node.getChild(0).getChild(1).getData();
                                    } else {    // 若出现(?=) (?!) (?<=) (?<!)这种 相当于无lookaround 无需修改
                                        continue;
                                    }
                                    if (assertType == AssertType.POSITIVE_AHEAD) {  // (?=
                                        midRegex.append("(?=" + r2 + ")");
                                    } else if (assertType == AssertType.NEGATIVE_AHEAD) {   // (?!
                                        midRegex.append("(?!" + r2 + ")");
                                    } else if (assertType == AssertType.POSITIVE_BEHIND) {  // (?<=
                                        midRegex.append("(?<=" + r2 + ")");
                                    } else if (assertType == AssertType.NEGATIVE_BEHIND) {  // (?<!
                                        midRegex.append("(?<!" + r2 + ")");
                                    }
                                } else {
                                    midRegex.insert(0, node.getData());
                                }
                            }
                        }
                        if (parent.getChild(i) == left) {
                            start = true;
                        }
                    }
                }
            }
            left = parent;
        }

        if (!isOrNode(root)) {
            start = false;
            for (int i = root.getChildCount() - 1; i >= 0; i--) {
                TreeNode child = root.getChild(i);
//            if (child == left) {
//                break;
//            }
                if (start) {
                    if (!isQuantifierNode(child))
//                    midRegex.append(child.getData());
//                        midRegex.insert(0, child.getData());
                        if (getAssertType(child) != null && getAssertType(child) != AssertType.NO_ASSERT)
                            midRegex.insert(0, reductAssertPattern(child));
                        else
                            midRegex.insert(0, child.getData());
                }
                if (child == left) {
                    start = true;
                }
            }
        }

//        System.out.println("---");
//        System.out.println(leftRegex);
//        System.out.println(midRegex);
////        System.out.println(rightRegex);
//        System.out.println("---");


        return new Pair<>(midRegex.toString(), leftRegex);
    }


    // 未完成
//    // 获得root中 right之后的正则的匹配串 考虑lookaround
//    public static String getSuffixRegexWithLookAround(TreeNode root, TreeNode right) throws InterruptedException {
//        root = getGroupSubNode(root);
////        String leftRegex = left.getData();
//        StringBuilder midRegex = new StringBuilder();
//        StringBuilder rightRegex = new StringBuilder(right.getData());
//        boolean start = false;
//        if (right == root) return "";
//        while (right.getParent() != root && right.getParent() != null) {
//            TreeNode parent = right.getParent();
//            if (parent != null && !isGroupNode(parent)) {
//                if (isOrNode(parent)) {
//
//                } else {
//                    start = false;
//                    for (int i = 0; i < parent.getChildCount(); i++) {
//                        if (start) {
//                            if (
////                                !parent.getChild(i).isNowNodeChildOrGrandchild(left) && parent.getChild(i) != left &&
//                                    !parent.getChild(i).isNowNodeChildOrGrandchild(right) && parent.getChild(i) != right
//                                            && !isQuantifierNode(parent.getChild(i))) {
//                                AssertType assertType = parent.getChild(i).getAssertType();
//                                if (assertType != null) {
//                                    if (assertType == AssertType.POSITIVE_AHEAD) {  // (?=
//                                        midRegex.insert(0, "(?=");
//                                        midRegex.append(parent.getChild(i).getData());
//                                        midRegex.append(")");
//                                    } else if (assertType == AssertType.NEGATIVE_AHEAD) {   // (?!
//                                        midRegex.insert(0, "(?!");
//                                        midRegex.append(parent.getChild(i).getData());
//                                        midRegex.append(")");
//                                    } else if (assertType == AssertType.POSITIVE_BEHIND) {  // (?<=
//                                        midRegex.insert(0, "(?<=");
//                                        midRegex.append(parent.getChild(i).getData());
//                                        midRegex.append(")");
//                                    } else if (assertType == AssertType.NEGATIVE_BEHIND) {  // (?<!
//                                        midRegex.insert(0, "(?<!");
//                                        midRegex.append(parent.getChild(i).getData());
//                                        midRegex.append(")");
//                                    }
//                                } else {
//                                    midRegex.append(parent.getChild(i).getData());
//                                }
//                            }
//                        }
////                        if (parent.getChild(i) == right) {
//                        if (parent.getChild(i) == right) {
//                            start = true;
//                        }
//                    }
//                }
//            }
//            right = parent;
//        }
//
//        if (! isOrNode(root)) {
//            start = false;
//            for (int i = 0; i < root.getChildCount(); i++) {
//                TreeNode child = root.getChild(i);
////            if (child == right) {
////                break;
////            }
//                if (start) {
//                    if (!isQuantifierNode(child))
////                        midRegex.append(child.getData());
//                        if (child.getAssertType() != null && child.getAssertType() != AssertType.NO_LOOK_AROUND)
//                            midRegex.insert(0, reductAssertPattern(child));
//                        else
//                            midRegex.insert(0, child.getData());
//
//                }
//                if (child == right) {
//                    start = true;
//                }
//            }
//        }
////        return midRegex.toString();
//
//        System.out.println("---");
////        System.out.println(leftRegex);
//        System.out.println(midRegex);
//        System.out.println(rightRegex);
//        System.out.println("---");
//
//        return midRegex.toString();
//    }


    // 接受空串
    public static boolean acceptEmptyString(String regex) throws InterruptedException {
        Automaton automaton = getIntersectionAutomaton(new ArrayList<>(Collections.singletonList(regex)), null, 0);
        return automaton.run("");
    }


    public static void main(String[] args) throws InterruptedException {
//        String regex1 = "(.*(~([0-9][\u0000-\uFFFF]*)))";
//        RegExp regExp = new RegExp(regex1);
//        Automaton automaton = regExp.toAutomaton(false);    // 这里要加第二个参数minimize: false 这样就是nfa了 比dfa快
//        String example = automaton.getExample(true).replace("\u0000", "\\u0000");
//        System.out.println(example);
//        System.exit(0);

        String regex = "((&quot;).*|(&quot;).*|(&quot;).*|([a-z]).*|(&gt;).*|([a-z]).*|(&gt;).*|(&gt;).*)＆.*";
        regex = "((&quot;).*＆.*|(&quot;).*＆.*|(&quot;).*＆.*|([a-z]).*＆.*|(&gt;).*＆.*|([a-z]).*＆.*|(&gt;).*＆.*|(&gt;).*＆.*)";
        regex = "(((\\s?color=&quot;?.*?&quot;)|(\\s?color=&quot;?.*?\\s.*)|(\\s?color=&quot;?.*?&gt;.*))|(&gt;.*))＆.*";
        regex = "[^\\d]";
        TreeNode treeNode = createReDoSTree(regex);
        printTree(treeNode);
        System.out.println(reWriteMetaEscape(treeNode, true));
//        String regex1 = "(?!a)([a-z]+)+(?<!b)";
//        TreeNode newlyttree1 = PatternNQUtils.getReDoSTreeList(regex1).get(0);
//        String p = getR0(newlyttree1, newlyttree1.getChild(1));
//        System.out.println(p);

//        String regex2 = ".*[a-z]+.*";
//        TreeNode newlyttree2 = PatternNQUtils.getReDoSTreeList(regex2).get(0);
//        String regex11 = translateRegexForLookAround(newlyttree1);
//        String regex22 = translateRegexForLookAround(newlyttree2);
//        List<String> list = new LinkedList<>();
//        list.add(regex11);
//        list.add(regex22);
//        System.out.println(getExampleByDkBricsAutomaton(list));

    }
}
