package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.getNodeByRemoveLocalFlag;
import static cn.ac.ios.Utils.RegexUtils.removeBlankStr;

public class ParseTestUtils {
    private static TreeNode getReDoSTree(String regex, String language) throws InterruptedException {
        language = language.toLowerCase();

        // 最开头的预处理
        regex = rewriteRegex(regex);
        regex = reduceLocalFlags(regex);
        regex = removeAnnotationByFlagX(regex);
        regex = processLocalFlag(regex);
        regex = replaceLocalFlagGM(regex);

        // 建树
        TreeNode ReDoSTree = createReDoSTree(regex, language);

        // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
        ReDoSTree.rewriteIllegalBarSymbol();

        // 处理java中奇奇怪怪的character_class 及 交集问题
        if (language.equals("java")) {
            ReDoSTree.dealWithCharacterClassInJava();
        }

        // 去group name
        ReDoSTree.deleteGroupName();

        // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
        ReDoSTree.addBackslashBeforeSomeCharacters();

        // 将方括号中的\0~\777重写为\u0000~\u0777
        ReDoSTree.rewriteUnicodeNumberInBracketNode();

        // 将方括号中的\b删除 因为方括号中的\b表示退格符
        ReDoSTree.reWriteBackspace();

        // 优化方括号结点, 将内部重复的字符删掉
        // 这里假设结点内部不会嵌套方括号结点/补结点
        ReDoSTree.optimizeBracketNode();

        // 处理\x{....} \xff
        ReDoSTree.escapeHexadecimal();

        // 处理特殊斜杠字符 根据不同的语言
        ReDoSTree.rewriteSpecialBackslashCharacterForDifferentLanguage(language);

        // 删除Flags
        ReDoSTree = getNodeByRemoveRegExpFlag(ReDoSTree);

        ReDoSTree = getNodeByRemoveLocalFlag(ReDoSTree);

        // 重写反向引用
        ReDoSTree.rewriteBackReferences();

        // 新版重写空串
        ReDoSTree = removeBlankStr(ReDoSTree);

        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        ReDoSTree.deleteNonCapturingGroupFlag();

        return ReDoSTree;
    }


    private static String getParseResult(String regex, String language) {
        StringBuilder builder1 = new StringBuilder();
        try {
            TreeNode ReDoSTree = getReDoSTree(regex, language);
            walk1(ReDoSTree, builder1);
        } catch (Exception e) {
            return null;
        }
        return builder1.toString();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        String fileName = "data/large/corpus.txt";
        List<String> regexes = FileUtils.readLines(new File(fileName), "utf-8");
        for (int i = 0; i < regexes.size(); i++) {
            String regex = regexes.get(i);
            String PCREParseResult = getParseResult(regex, "PCRE");
            String JavaParseResult = getParseResult(regex, "java");
            if (PCREParseResult == null && JavaParseResult == null) {
//                System.out.println((i + 1) + " PCRE & java parse error");
            } else if (PCREParseResult == null) {
//                System.out.println((i + 1) + " PCRE parse error");
            } else if (JavaParseResult == null) {
//                System.out.println((i + 1) + " java parse error");
            } else {
                if (PCREParseResult.equals(JavaParseResult)) {
//                    System.out.println((i + 1) + "equal");
                } else {
                    System.out.println((i + 1) + " not equal");
                }
            }
        }
    }
}
