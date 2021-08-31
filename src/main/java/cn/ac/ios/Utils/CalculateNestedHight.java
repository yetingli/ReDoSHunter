package cn.ac.ios.Utils;


import cn.ac.ios.TreeNode.TreeNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.Utils.Utils.readFile;
import static cn.ac.ios.Utils.Utils.writeFile;

public class CalculateNestedHight {
    public static void calculateMaxNestedHight(TreeNode newlyttree, int starHeight, List<Integer> maxStarHeight) {
        List<TreeNode> chirdren = newlyttree.getChildList();
//        System.out.println(starHeight);
        if (starHeight > maxStarHeight.get(0)) {
            maxStarHeight.set(0, starHeight);
        }
        if (chirdren.size() > 1) {
            for (int i = 1; i < chirdren.size(); i++) {
                TreeNode pre = chirdren.get(i - 1);
                TreeNode now = chirdren.get(i);

                if ((now.getData().startsWith("{") && now.getData().endsWith("}") && now.getData().contains(","))) {
//                    System.out.println(pre.data);
                    calculateMaxNestedHight(pre, starHeight + 1, maxStarHeight);
                } else {
                    calculateMaxNestedHight(pre, starHeight, maxStarHeight);
                }
            }
            calculateMaxNestedHight(chirdren.get(chirdren.size() - 1), starHeight, maxStarHeight);
        }
    }

    public static int calculateNestedHeight(String regex) throws InterruptedException {
        regex = regex.replace("{,", "{0,");
        Pattern pattern = Pattern.compile("\\{\\d+,\\d+\\}");
        Matcher matcher = pattern.matcher(regex);

        while (matcher.find()) {
            regex = regex.replace(matcher.group(0), "@");
        }

        regex = regex.replace("a", "").replace(",", "");
        regex = regex.replace("@", "{2,3}");

//        System.out.println(regex);
        TreeNode newlyttree = createReDoSTree(regex);


        // 计算最大嵌套深度
        List<Integer> maxStarHeight2 = new LinkedList<>();
        maxStarHeight2.add(0);
        calculateMaxNestedHight(newlyttree, 0, maxStarHeight2);
//        System.out.println(maxStarHeight2.get(0));
        return maxStarHeight2.get(0);
    }

    public static int calculateAlphabetSize(String regex) {
        regex = regex.replace("{,", "{0,");
        Pattern pattern = Pattern.compile("\\{\\d+,\\d+\\}");
        Matcher matcher = pattern.matcher(regex);

        while (matcher.find()) {
            regex = regex.replace(matcher.group(0), "@");
        }

        Set<Integer> set = new LinkedHashSet<>();

        pattern = Pattern.compile("\\d+");
        matcher = pattern.matcher(regex);
        while (matcher.find()) {
            set.add(Integer.parseInt(matcher.group(0)));
        }

        return set.size();
    }

    public static class NestedHight {
        public String regex;
        public String serilizedRegex;
        public int id;
        public int nestedHeight;
        public int alphabetSize;
        public NestedHight(int _id, int _nestedHeight, int _alphabetSize) {
            this.id = _id;
            this.nestedHeight = _nestedHeight;
            this.alphabetSize = _alphabetSize;
        }
        public NestedHight(){}
    }


    public static void main(String[] args) {
//        String regex = "(a0|a1|a2|a3|a4|a5|a6|a7|a8|a9|a10|a11|a12|a13|a14|a15|a16|a17|a18|a19|a20|a21|a22|a23|a24|a25|a26|a27|a28|a29|a30|a31|a32|a33|a34|a35|a36|a37|a38|a39|a40|a41|a42|a43|a44|a45|a46|a47|a48|a49|a50|a51|a52|a53|a54|a55|a56|a57|a58|a59|a60|a61|a62|a63|a64|a65|a66|a67|a68|a69|a70|a71|a72|a73){1,64},(a74,(a0|a1|a2|a3|a4|a5|a6|a7|a8|a9|a10|a11|a12|a13|a14|a15|a16|a17|a18|a19|a20|a21|a22|a23|a24|a25|a26|a27|a28|a29|a30|a31|a32|a33|a34|a35|a36|a37|a38|a39|a40|a41|a42|a43|a44|a45|a46|a47|a48|a49|a50|a51|a52|a53|a54|a55|a56|a57|a58|a59|a60|a61|a62|a63|a64|a65|a66|a67|a68|a69|a70|a71|a72|a73){1,64}){0,4}";
//        System.out.println(calculateNestedHeight(regex));
//        System.out.println(calculateAlphabetSize(regex));

        String fileName = "计数_results";
        List<String> REList = readFile("data/large/计数.txt");
        assert REList != null;
        List<String> regexList = readFile("src/main/java/cn/ac/ios/util/" + fileName);
        List<NestedHight> nestedHightList = new LinkedList<>();
//        List<String> regexList = new ArrayList<>();
//        regexList.add("(a0|a1|a2|a3|a4|a5|a6|a7|a8|a9|a10|a11|a12|a13|a14|a15|a16|a17|a18|a19|a20|a21|a22|a23|a24|a25|a26|a27|a28|a29|a30|a31|a32|a33|a34|a35|a36|a37|a38|a39|a40|a41|a42|a43|a44|a45|a46|a47|a48|a49|a50|a51|a52|a53|a54|a55|a56|a57|a58|a59|a60|a61|a62|a63|a64|a65|a66|a67|a68|a69|a70|a71|a72|a73){1,64},(a74,(a0|a1|a2|a3|a4|a5|a6|a7|a8|a9|a10|a11|a12|a13|a14|a15|a16|a17|a18|a19|a20|a21|a22|a23|a24|a25|a26|a27|a28|a29|a30|a31|a32|a33|a34|a35|a36|a37|a38|a39|a40|a41|a42|a43|a44|a45|a46|a47|a48|a49|a50|a51|a52|a53|a54|a55|a56|a57|a58|a59|a60|a61|a62|a63|a64|a65|a66|a67|a68|a69|a70|a71|a72|a73){1,64}){0,4}");

        assert regexList != null;
        for (int i = 0; i < regexList.size(); i++) {
            System.out.println(i + " / " + regexList.size());
            String regex = regexList.get(i);
            regex = regex.replace("{1}", "{1,1}")
                    .replace("{2}", "{2,2}")
                    .replace("{3}", "{3,3}")
                    .replace("{4}", "{4,4}")
                    .replace("{5}", "{5,5}")
                    .replace("{6}", "{6,6}")
                    .replace("{7}", "{7,7}")
                    .replace("{8}", "{8,8}")
                    .replace("{9}", "{9,9}")
                    .replace("{10}", "{10,10}")
                    .replace("{11}", "{11,11}");
            NestedHight nestedHight = new NestedHight();
            try {
//                NestedHight nestedHight = new NestedHight(i + 1, calculateNestedHeight(regex), calculateAlphabetSize(regex));
                nestedHight.id = i + 1;
                nestedHight.nestedHeight = calculateNestedHeight(regex);
                nestedHight.alphabetSize = calculateAlphabetSize(regex);
                nestedHight.serilizedRegex = regex;
                nestedHight.regex = REList.get(i);

            } catch (Exception e) {
//                NestedHight nestedHight = new NestedHight(i + 1, -1, -1);
                nestedHight.id = i + 1;
                nestedHight.nestedHeight = -1;
                nestedHight.alphabetSize = -1;
                nestedHight.serilizedRegex = regex;
                nestedHight.regex = REList.get(i);
            }
            nestedHightList.add(nestedHight);
        }

//        nestedHightList.sort(new Comparator<NestedHight>() {
//            @Override
//            public int compare(NestedHight o1, NestedHight o2) {
//                if (o1.nestedHeight == o2.nestedHeight) {
//                    return Integer.compare(o2.alphabetSize, o1.alphabetSize);
//                }
//                return Integer.compare(o1.nestedHeight, o1.nestedHeight);
//            }
//        });

        List<String> contentList = new ArrayList<>();
        for (NestedHight n: nestedHightList) {
//            System.out.println(n.id + " " + n.nestedHeight + " " + n.alphabetSize);
            contentList.add(n.id + "\t" + n.nestedHeight + "\t" + n.alphabetSize + "\t" + n.serilizedRegex + "\t" + n.regex);
        }

        writeFile("src/main/java/cn/ac/ios/util/" + fileName + "_2", contentList);
    }
}
