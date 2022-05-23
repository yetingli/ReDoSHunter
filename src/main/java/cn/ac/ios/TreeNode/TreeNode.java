package cn.ac.ios.TreeNode;

import cn.ac.ios.Bean.GroupContent;
import cn.ac.ios.PCRERegex.PCREBuilder;
import cn.ac.ios.PCRERegex.PCREParser;
import cn.ac.ios.Utils.BracketUtils;
import cn.ac.ios.Utils.Constant;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.SerializationUtils;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.GenMatchStringUtils.*;
import static cn.ac.ios.Utils.NegateUtils.deleteZeroWidthAssertion;
import static cn.ac.ios.Utils.NegateUtils.removeNegateSymbol;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.UnicodeBackslashU.unicodeToCn;


public class TreeNode implements Iterable<TreeNode>, Serializable {
    private String data;
    private TreeNode parent;
    private List<TreeNode> children;
    private String initialChainIndex;   // 初始链式索引
    private String chainIndex;    // 链式索引
    private Set<String> flags;    // 当前节点的模式信息
    private boolean isChildReferencesNode;   // 是否为\1 \2 反向引用结点
    private String parentReferenceNodeChainIndex; // \1\2所引用的结点的当前链式索引值
    private String parentReferenceNodeInitialChainIndex;    // \1\2所引用的结点的初始链式索引值
    private AssertType assertType;
    private int nullable;               // nullable属性 -1表示无效值 0表示false 1表示true
    private Set<String> first;        // first属性
    private Set<String> last;         // last属性
    private Set<String> followLast;   //followLast属性
    private double dFlexible;   // Flexible属性 递推公式见doi:10.1016/j.ic.2006.12.003中定义4.1
    private boolean bFlexible;  // Flexible属性 算法伪代码见10.1016/j.is.2010.10.001中图2
    private String originalRegex;   // 原始正则 用于记录去补操作之前

    public TreeNode() {}

    public TreeNode(String data) {
        this.data = data;
        this.children = new LinkedList<>();
        this.chainIndex = "0";
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public TreeNode getRoot() {
        TreeNode root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    public String getInitialChainIndex() {
//        return initialChainIndex;
        if (! isChildReferencesNode) return initialChainIndex;
        return parentReferenceNodeInitialChainIndex;
    }

    public void setInitialChainIndex(String initialChainIndex) {
        this.initialChainIndex = initialChainIndex;
    }

    public String getChainIndex() {
        return chainIndex;
    }

    public void setChainIndex(String chainIndex) {
        this.chainIndex = chainIndex;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public List<String> getChildDataList() {
        List<String> childDataList = new ArrayList<>();
        List<TreeNode> childList = this.getChildList();
        for (TreeNode child : childList) {
            childDataList.add(child.getData());
        }
        return childDataList;
    }

    public List<TreeNode> getChildList() {
        return children;
    }


    // 判断当前结点是否为其父节点的第一个孩子
    public boolean isFirstChild() {
        if (this.getParent() == null) {
            return false;
        }
        return this == this.getParent().getChild(0);
    }

    // 判断当前结点是否为其父节点的第二个孩子
    public boolean isSecondChild() {
        if (this.getParent() == null) {
            return false;
        }
        if (this.getParent().getChildCount() < 2) {
            return false;
        }
        return this == this.getParent().getChild(1);
    }

    // 判断当前结点是否为其父亲结点的第三个孩子
    public boolean isThirdChild() {
        if (this.getParent() == null) {
            return false;
        }
        if (this.getParent().getChildCount() < 3) {
            return false;
        }
        return this == this.getParent().getChild(2);
    }

    // 判断当前结点是否为其父亲结点的第四个孩子
    public boolean isForthChild() {
        if (this.getParent() == null) {
            return false;
        }
        if (this.getParent().getChildCount() < 4) {
            return false;
        }
        return this == this.getParent().getChild(3);
    }

    // 判断当前结点是否为其父节点的最后一个孩子
    public boolean isLastChild() {
        if (this.getParent() == null) {
            return false;
        }
        return this == this.getParent().getChild(this.getParent().getChildCount() - 1);
    }

    public TreeNode getFirstChild() {
        if (children == null || children.size() == 0) {
            return null;
        }
        return children.get(0);
    }

    public TreeNode getSecondChild() {
        if (children == null || children.size() < 2) {
            return null;
        }
        return children.get(1);
    }

    public TreeNode getLastChild() {
        if (children == null || children.size() == 0) {
            return null;
        }
        return children.get(children.size() - 1);
    }

    public TreeNode getChild(int i) {
        return children.get(i);
    }

    public int getChildCount() {
        return children.size();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Set<String> getFlags() {
        return flags;
    }

    public void setFlags(Set<String> flags) {
        this.flags = flags;
    }

    public boolean isChildReferencesNode() {
        return isChildReferencesNode;
    }

    public String getParentReferenceNodeChainIndex() {
        return parentReferenceNodeChainIndex;
    }

    public void setParentReferenceNodeChainIndex(String parentReferenceNodeChainIndex) {
        this.parentReferenceNodeChainIndex = parentReferenceNodeChainIndex;
    }

    //    public AssertType getAssertType() {
//        return assertType;
//    }

    public void setAssertType(AssertType assertType) {
        this.assertType = assertType;
    }

    public void setNullable(int nullable) {
        this.nullable = nullable;
    }

    public int getNullable() {
        return nullable;
    }

    public void setFirst(Set<String> first) {
        this.first = first;
    }

    public Set<String> getFirst() {
        return first;
    }

    public void setFollowLast(Set<String> followLast) {
        this.followLast = followLast;
    }

    public Set<String> getFollowLast() {
        return followLast;
    }

    public void setLast(Set<String> last) {
        this.last = last;
    }

    public Set<String> getLast() {
        return last;
    }

    public void setdFlexible(double dFlexible) {
        this.dFlexible = dFlexible;
    }

    public double getdFlexible() {
        return dFlexible;
    }

    public void setbFlexible(boolean bFlexible) {
        this.bFlexible = bFlexible;
    }

    public boolean isbFlexible() {
        return bFlexible;
    }

    /**
     * 去掉RegExp 中的flags信息 i s
     */
    public void getNodeByRemoveRegExpFlag() throws InterruptedException {
        if (this.getFlags().isEmpty()) {
            return;
        }
        for (String str : this.getFlags()) {
            switch (str) {
                case "i":
                    removeI(this);
                    break;
                case "s":
                    removeS(this);
                    break;
                default:
                    break;
            }
        }
//        return createReDoSTree(node.getData());
        updateChainIndex(this.getRoot(), "0");
    }

    /**
     * 去掉flag i
     *
     * @param treeNode
     */
    private void removeI(TreeNode treeNode) throws InterruptedException {
        String data = treeNode.getData();
        if (isLocalFlagsX(data) || isLocalFlagsI(data) || isLocalFlagsS(data)) {
            return;
        }
        if (data.length() == 1) {
            letterReplace(treeNode);
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();

            // [a-z](a|b)[a-z] 特殊处理
            //  [] 中括号内部处理，不考虑中括号内部嵌套
            if (isBracketsNode(treeNode) || isNegateNode(treeNode)) {
                boolean isNegateNodeFlag = false;
                if (isNegateNode(treeNode)) isNegateNodeFlag = true;
                LinkedList<TreeNode> afterProcess = new LinkedList<>();
                Set<Character> letterSet = new LinkedHashSet<>();
                for (TreeNode child : children) {
                    // 如果是[^...]结点 则不将^加入到afterProcess中
                    if (isNegateNodeFlag && "^".equals(child.getData()) && child.isSecondChild()) continue;

                    // 单字母加入集合
                    if (child.getData().length() == 1 && Character.isLetter(child.getData().charAt(0))) {
                        letterSet.add(child.getData().charAt(0));
                    } else if ("-".equals(child.getData())) {
                        afterProcess.add(createReDoSTree("\\-"));
                    }
                    // 字母集合加入集合 如 a-z
                    else if (isCollectionLetter(child.getData())) {
                        letterSet.addAll(BracketUtils.getLetterSet(child.getData()));
                    }
                    // 否则不作处理
                    else {
                        afterProcess.add(child);
                    }
                }
                // 添加新的字母节点
                if (letterSet.size() > 0) {
                    // 删除原有节点
                    for (int i = children.size() - 1; i >= 0; i--) {
                        treeNode.deleteChild(i);
                    }
                    treeNode.addChild(afterProcess.getFirst());
                    // 如果是[^...]结点 将^添加到treeNode中
                    if (isNegateNodeFlag) treeNode.addChild("^");

                    // 添加新的字母节点
                    ArrayList<String> letters = getLettersSimplyInsensitive(letterSet);
                    for (String str : letters) {
                        treeNode.addChild(str);
                    }
                    for (int i = 1; i < afterProcess.size() - 1; i++) {
                        treeNode.addChild(afterProcess.get(i));
                    }
                    // 添加以前的节点非字母节点
                    treeNode.addChild(afterProcess.getLast());
                }
            } else {
                for (TreeNode node : children) {
                    removeI(node);
                }
            }
        }
    }

    /**
     * 去掉flag s
     *
     * @param treeNode
     */
    private void removeS(TreeNode treeNode) throws InterruptedException {
        if (FLAGS_REGEXP_S_REPLACE_PATTERN.equals(treeNode.getData())) {
            return;
        }
        if (".".equals(treeNode.getData())) {
            if (!isInBrackets(treeNode) && !isInNegateNode(treeNode)) {
                treeNode.updateTreeByModifyNode(FLAGS_REGEXP_S_REPLACE_PATTERN);
                // 将所有的孩子结点的initialChainIndex统一设成相同的值
                List<TreeNode> list = treeNode.getAllChildren();
                for (TreeNode node: list) {
                    node.initialChainIndex = treeNode.initialChainIndex;
                }
            }
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            for (TreeNode node : children) {
                removeS(node);
            }
        }
    }

    /**
     * 将单字母忽略大小写
     *
     * @param treeNode
     */
    private TreeNode letterReplace(TreeNode treeNode) throws InterruptedException {
        String data = treeNode.getData();
        char c = data.charAt(0);
        if (Character.isLetter(c)) {
            if (Character.isLowerCase(c)) {
//                data = "(?:" + data + "|" + data.toUpperCase() + ")";
                data = "[" + data + data.toUpperCase() + "]";   // 改为[]的形式
            } else {
//                data = "(?:" + data + "|" + data.toLowerCase() + ")";
                data = "[" + data.toLowerCase() + data + "]";   // 改为[]的形式
            }
            treeNode.updateTreeByModifyNode(data);
        }
        return treeNode;
    }

    /**
     * 去除正则中的局部模式信息 i,s,x
     */
    public void getNodeByRemoveLocalFlag() throws InterruptedException {
        if (this.data.contains(FLAGS_X_START) || this.data.contains(FLAGS_X_END)) {
            getNodeByRemoveLocalFlagX(this);
        }
        if (this.data.contains(FLAGS_I_START) || this.data.contains(FLAGS_I_END)) {
            getNodeByRemoveLocalFlagI(this);
        }
        if (this.data.contains(FLAGS_S_START) || this.data.contains(FLAGS_S_END)) {
            getNodeByRemoveLocalFlagS(this);
        }
        updateChainIndex(this.getRoot(),"0");
    }

    /**
     * 删除 LocalFlagX   Flag X包含的空格和 Flag X标志
     *
     * @param treeNode
     * @return
     */
    private TreeNode getNodeByRemoveLocalFlagX(TreeNode treeNode) {
        if (isLocalFlagsX(treeNode.getData())) {
            return null;
        }
        List<TreeNode> children = treeNode.getChildList();
        if (children.isEmpty()) {
            return treeNode;
        }
        List<TreeNode> afterList = new ArrayList<>();
        boolean flag = false;
        for (TreeNode child : children) {
            if (flag) {
                if (child.getData().equals(FLAGS_X_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_X_END)) {
                    flag = false;
                } else {
                    afterList.add(removeLocalX(child));
                }
            } else {
                if (child.getData().equals(FLAGS_X_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_X_END)) {
                    flag = false;
                } else {
                    afterList.add(getNodeByRemoveLocalFlagX(child));
                }
            }
        }
        // 删除原有节点
        return treeNode.refactorChildren(afterList);
    }

    /**
     * 去除 <?i:>模式信息
     *
     * @param treeNode
     * @return
     */
    private TreeNode getNodeByRemoveLocalFlagI(TreeNode treeNode) throws InterruptedException {
        if (isLocalFlagsI(treeNode.getData())) {
            return null;
        }
        List<TreeNode> children = treeNode.getChildList();
        if (children.isEmpty()) {
            return treeNode;
        }
        LinkedList<TreeNode> afterList = new LinkedList<>();
        boolean flag = false;
        for (TreeNode child : children) {
            if (flag) {
                if (child.getData().equals(FLAGS_I_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_I_END)) {
                    flag = false;
                } else {
                    afterList.add(removeLocalI(child));
                }
            } else {
                if (child.getData().equals(FLAGS_I_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_I_END)) {
                    flag = false;
                } else {
                    afterList.add(getNodeByRemoveLocalFlagI(child));
                }
            }
        }
        return treeNode.refactorChildren(afterList);
    }

    /**
     * 去掉局部模式 s  "."支持换行
     *
     * @return
     */
    private TreeNode getNodeByRemoveLocalFlagS(TreeNode treeNode) throws InterruptedException {
        if (isLocalFlagsS(treeNode.getData())) {
            return null;
        }
        List<TreeNode> children = treeNode.getChildList();
        if (children.isEmpty()) {
            return treeNode;
        }
        List<TreeNode> afterList = new ArrayList<>();
        boolean flag = false;
        for (TreeNode child : children) {
            if (flag) {
                if (child.getData().equals(FLAGS_S_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_S_END)) {
                    flag = false;
                } else {
                    afterList.add(removeLocalS(child));
                }
            } else {
                if (child.getData().equals(FLAGS_S_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_S_END)) {
                    flag = false;
                } else {
                    afterList.add(getNodeByRemoveLocalFlagS(child));
                }
            }
        }
        // 删除原有节点
        return treeNode.refactorChildren(afterList);
    }


    /**
     * 去掉局部flag i
     *
     * @param treeNode
     */
    private TreeNode removeLocalI(TreeNode treeNode) throws InterruptedException {
        String data = treeNode.getData();
        if (isLocalFlagsI(data)) {
            return null;
        }
        if (isLocalFlagsX(data)) {
            return treeNode;
        }
        if (isLocalFlagsS(data)) {
            return treeNode;
        }
        // 单字母处理 直接替换
        else if (data.length() == 1) {
            return letterReplace(treeNode);
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            if (children.isEmpty()) {
                return treeNode;
            }
            //  [] 中括号内部处理，不考虑中括号内部嵌套
            if (isBracketsNode(treeNode)) {
                LinkedList<TreeNode> afterList = new LinkedList<>();
                Set<Character> letterSet = new LinkedHashSet<>();
                for (TreeNode child : children) {
                    // 单字母加入集合
                    if (child.getData().length() == 1 && Character.isLetter(child.getData().charAt(0))) {
                        letterSet.add(child.getData().charAt(0));
                    } else if ("-".equals(child.getData())) {
                        afterList.add(createReDoSTree("\\-"));
                    }
                    // 字母集合加入集合
                    else if (isCollectionLetter(child.getData())) {
                        letterSet.addAll(BracketUtils.getLetterSet(child.getData()));
                    } else {
                        if (!isLocalFlagsI(child.getData())) {
                            afterList.add(removeLocalI(child));
                        }
                    }
                }
                // 添加新的字母节点
                if (letterSet.size() > 0) {
                    // 删除原有节点
                    for (int i = children.size() - 1; i >= 0; i--) {
                        treeNode.deleteChild(i);
                    }
                    treeNode.addChild(afterList.getFirst());
                    // 添加新的字母节点
                    ArrayList<String> letters = getLettersSimplyInsensitive(letterSet);
                    for (String str : letters) {
                        treeNode.addChild(str);
                    }
                    // 添加以前的节点非字母节点
                    for (int i = 1; i < afterList.size() - 1; i++) {
                        treeNode.addChild(afterList.get(i));
                    }
                    treeNode.addChild(afterList.getLast());
                }
            } else {
                LinkedList<TreeNode> afterList = new LinkedList<>();
                for (TreeNode node : children) {
                    afterList.add(removeLocalI(node));
                }
                treeNode.refactorChildren(afterList);
            }
        }
        return treeNode;
    }


    /**
     * 移除当前节点的空格
     *
     * @return
     */
    private TreeNode removeLocalX(TreeNode treeNode) {
        if (" ".equals(treeNode.getData())) {
            return null;
        } else if (isLocalFlagsX(treeNode.getData())) {
            return null;
        } else if (treeNode.isLeaf() || isBracketsNode(treeNode)) {
            return treeNode;
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            LinkedList<TreeNode> afterList = new LinkedList<>();
            for (TreeNode node : children) {
                afterList.add(removeLocalX(node));
            }
            // 删除原有节点
            treeNode.refactorChildren(afterList);
        }
        return treeNode;
    }

    /**
     * 移除局部flag s
     *
     * @return
     */
    private TreeNode removeLocalS(TreeNode treeNode) throws InterruptedException {
        if (".".equals(treeNode.getData())) {
            if (!isInBrackets(treeNode) && !isInNegateNode(treeNode)) {
                treeNode.updateTreeByModifyNode(FLAGS_REGEXP_S_REPLACE_PATTERN);
            }
            return treeNode;
        } else if (isLocalFlagsX(treeNode.getData())) {
            return null;
        } else if (treeNode.isLeaf()) {
            return treeNode;
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            LinkedList<TreeNode> afterList = new LinkedList<>();
            for (TreeNode node : children) {
                afterList.add(removeLocalS(node));
            }
            // 删除原有节点
            treeNode.refactorChildren(afterList);
        }
        return treeNode;
    }

    // 删除注释
    public void deleteAnnotation() {
        if (isAnnotationNode(this)) {
            this.data = "";
            this.children.clear();
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isAnnotationNode(node)) {
                node.getParent().deleteChild(node);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    // 将possessive和lazy匹配转换为对应的greedy匹配
    // (1) ++ -> +, (2) *+ -> *, (3) ?+ -> ?, (4) {m,n}+ -> {m,n}, (5) {m}+ -> {m}, (6) {m,}+ -> {m,}
    // (7) +? -> +, (8) *? -> *, (9) ?? -> ?, (10) {m,n}? -> {m,n}, (11) {m}? -> {m}, (12) {m,}? -> {m,}
    public void transNonGreedyQuantifier() {
        if (! pattern_nonGreedyMatching.matcher(this.data).find()) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            String data = node.getData();
            if ((data.startsWith("*") || data.startsWith("+") || data.startsWith("?") || data.startsWith("{")) && (data.endsWith("+") || data.endsWith("?"))) {
                if (pattern_nonGreedyMatching.matcher(data).matches()) {
                    String newQuantifier = data.substring(0, data.length() - 1);
                    node.updateQuantifier(newQuantifier);
                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
    }

    public void removeBlankStr() throws InterruptedException {
        Stack<TreeNode> stack = new Stack<>();
        // 删除空括号()
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOnlyEmptyNode(node)) {
                TreeNode par = node.getParent();
                if (par == null) {
                    TreeNode newNode = createReDoSTree("");
                    newNode.initialChainIndex = node.initialChainIndex;
                    node.updateTreeByModifyNode(newNode);
                } else {
                    par.deleteChild(node);
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                StringBuilder stringBuilder = new StringBuilder();
                Queue<TreeNode> queue = new LinkedList<>();
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNode child = node.getChild(i);
                    if (!"|".equals(child.getData()) && !"".equals(child.getData())) {
                        stringBuilder.append(child.getData()).append("|");
                        queue.add(child);
                    }
                }
//                if (stringBuilder.length() == 0) {
//                    node.updateTreeByModifyNode("");
//                } else {
//                    String data = stringBuilder.substring(0, stringBuilder.length() - 1);
//                    if (!data.equals(node.getData())) {
//                        node.updateTreeByModifyNode("(?:" + data + ")?");
//                    }
//                }
                if (queue.isEmpty()) {
                    TreeNode newNode = createReDoSTree("");
                    newNode.initialChainIndex = node.initialChainIndex;
                    node.updateTreeByModifyNode(newNode);
                } else {
                    String data = stringBuilder.substring(0, stringBuilder.length() - 1);
                    if (!data.equals(node.getData())) {
                        TreeNode newNode = new TreeNode("(?:" + data + ")?");
                        newNode.addChild("(?:" + data + ")");
                        newNode.addChild("?");
                        TreeNode child0 = newNode.getChild(0);
                        TreeNode child1 = newNode.getChild(1);

//                        node.data = "(?:" + data + ")?";
//                        node.children.clear();
//                        node.addChild("(?:" + data + ")");
//                        node.addChild("?");
//                        TreeNode child0 = node.getChild(0);
//                        TreeNode child1 = node.getChild(1);
                        child0.initialChainIndex = node.initialChainIndex;
                        child1.initialChainIndex = node.initialChainIndex;
                        child0.addChild("(");
                        child0.addChild("?");
                        child0.addChild(":");
                        while (!queue.isEmpty()) {
                            child0.addChild(queue.poll());
                            if (!queue.isEmpty()) child0.addChild("|");
                        }
                        child0.addChild(")");

                        newNode.initialChainIndex = node.initialChainIndex;
                        node.updateTreeByModifyNode(newNode);
                    }
                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        // 删除空括号()
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOnlyEmptyNode(node)) {
                TreeNode par = node.getParent();
                if (par == null) {
                    TreeNode newNode = createReDoSTree("");
                    newNode.initialChainIndex = node.initialChainIndex;
                    node.updateTreeByModifyNode(newNode);
                } else {
                    par.deleteChild(node);
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    /**
     * 判断节点是否是只能是空节点，删除空括号()
     *
     * @param node
     * @return
     */
    private boolean isOnlyEmptyNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isOnlyEmptyNode(getGroupSubNode(node));
        }
        if (isGeneralizedCountingNode(node)) {
            node = getGroupSubNode(node);
            return isOnlyEmptyNode(node.getChild(0));
        }
        if ("".equals(node.getData())) {
            return true;
        } else if (node.getChildCount() >= 2) {
            boolean flag = true;
            for (int i = 0; i < node.getChildCount(); i++) {
                flag = isOnlyEmptyNode(node.getChild(i));
                if (!flag) {
                    return false;
                }
            }
            return flag;
        }
        return false;
    }





    /**
     * 更新当前节点的所有孩子节点
     *
     * @param afterList
     * @return
     */
    public TreeNode refactorChildren(List<TreeNode> afterList) {
        List<TreeNode> children = getChildList();
        for (int i = children.size() - 1; i >= 0; i--) {
            deleteChild(i);
        }
        for (TreeNode node : afterList) {
            if (node != null) {
                addChild(node);
            }
        }
        return this;
    }

    /**
     * waring:别用
     *
     * @param child
     */
    // 添加孩子结点
    public void addChild(String child) {
        TreeNode childNode = new TreeNode(child);
        childNode.parent = this;
        childNode.chainIndex = this.chainIndex + "." + this.getChildCount();
        this.children.add(childNode);
        this.updateTree();
    }

    // 添加建树时所用的addChild函数
    public void addChildForCreateReDoSTree(String child) {
        TreeNode childNode = new TreeNode(child);
        childNode.parent = this;
        childNode.chainIndex = this.chainIndex + "." + this.getChildCount();
        this.children.add(childNode);
    }

    /**
     * waring:别用
     *
     * @param child
     */
    // 添加孩子结点
    public void addChild(TreeNode child) {
        child.parent = this;
        child.chainIndex = this.chainIndex + "." + this.getChildCount();
        this.children.add(child);
        this.updateTree();
    }

    /***
     * 在指定位置插入节点
     * @param child
     * @param index
     */
    public void insertChild(String child, int index) throws InterruptedException {
        PCREParser parser = new PCREBuilder.Parser(child).build();
        ParseTree tree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(child);
        getReDoSTreeHelper1(tree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(child);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        this.children.add(index, newReDoSTree);
        this.updateTree();
    }

    /**
     * 在最后面添加孩子节点
     *
     * @param child
     */
    public void insertChild(String child) throws InterruptedException {
        PCREParser parser = new PCREBuilder.Parser(child).build();
        ParseTree parseTree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(child);
        ReDoSTree = getReDoSTreeHelper1(parseTree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(child);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        this.children.add(newReDoSTree);
        this.updateTree();
    }

    // 更新孩子结点的值
    public void updateChild(String child, int index) throws InterruptedException {
        PCREParser parser = new PCREBuilder.Parser(child).build();
        ParseTree parseTree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(child);
        ReDoSTree = getReDoSTreeHelper1(parseTree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(child);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        this.children.set(index, newReDoSTree);
        this.updateTree();
    }

    public String getOriginalRegex() {
        return originalRegex;
    }

    public void setOriginalRegex(String originalRegex) {
        this.originalRegex = originalRegex;
    }

    // 删除孩子结点
    public void deleteChild(int i) {
        this.children.remove(i);
        this.updateTree();
    }

    // 重载删除孩子结点
    public void deleteChild(TreeNode child) {
        this.children.remove(child);
        this.updateTree();
    }

    // 更新树 在添加 插入 删除 后 需要执行
    public void updateTree() {
        TreeNode root = this;
        while (!root.isRoot()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (TreeNode i : root.children) {
                stringBuilder.append(i.data);
            }
            root.data = stringBuilder.toString();
            root = root.parent;
        }
        if (root.isRoot()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (TreeNode i : root.children) {
                stringBuilder.append(i.data);
            }
            root.data = stringBuilder.toString();
        }
        root = this.getRoot();
        updateChainIndex(root, "0");
    }

    // 修改结点值后，更新树
    public void updateTreeByModifyNode(TreeNode node) {
        TreeNode root = this;
        root.children = node.children;
        root.flags = node.flags;
        root.data = node.data;
        for (int i = 0; i < root.children.size(); i++) {
            root.getChild(i).parent = root;
        }
        while (!root.isRoot()) {
            root = root.parent;
            StringBuilder stringBuilder = new StringBuilder();
            for (TreeNode i : root.children) {
                stringBuilder.append(i.data);
            }
            root.data = stringBuilder.toString();
        }
        root = this.getRoot();
        updateChainIndex(root, "0");
    }

    // 修改节点值后，更新树
    public void updateTreeByModifyNode(String data) throws InterruptedException {
        updateTreeByModifyNode(data, false);
    }

    // 修改节点值后，更新树
    public void updateTreeByModifyNode(String data, boolean flag) throws InterruptedException {
        TreeNode node = createReDoSTree(data);
//        refactorAssertPattern(node);
        TreeNode root = this;
        root.children = node.children;
        root.flags = node.flags;
        if (flag) {
            if (root.originalRegex == null) {
                root.originalRegex = root.data; // 存储原值
            }
        }
        root.data = node.data;
        for (int i = 0; i < root.children.size(); i++) {
            root.getChild(i).parent = root;
        }
        while (!root.isRoot()) {
            root = root.parent;
            StringBuilder stringBuilder = new StringBuilder();
            for (TreeNode i : root.children) {
                stringBuilder.append(i.data);
            }
            if (flag) {
                if (root.originalRegex == null) {
                    root.originalRegex = root.data; // 存储原值
                }
            }
            root.data = stringBuilder.toString();
        }
        root = this.getRoot();
        updateChainIndex(root, "0");
    }

    // 是否修正\\t -> \t, \\v -> \u000b, \\f -> \f, \\n -> \n, \\r -> \r
    public Set<String> getLetterSet(boolean addDefaultw, boolean repairBackSlashElements) {
        Set<String> letterSet = getLetterSet(addDefaultw);
        Set<String> newLetterSet = new HashSet<>();
        if (repairBackSlashElements) {
            Iterator<String> it = letterSet.iterator();
            while (it.hasNext()) {
                String str = it.next();
                if (str.equals("\\t")) {
                    newLetterSet.add("\t");
                } else if (str.equals("\\f")) {
                    newLetterSet.add("\f");
                } else if (str.equals("\\n")) {
                    newLetterSet.add("\n");
                } else if (str.equals("\\v") || str.equals("\\u000b")) {
                    newLetterSet.add("\u000b");
                } else if (str.equals("\\r")) {
                    newLetterSet.add("\r");
                } else if (str.length() == 2) {
                    newLetterSet.add(str.substring(1, 2));
                }
                else {
                    newLetterSet.add(str);
                }
            }

//            if (letterSet.contains("\\t")) {
//                letterSet.remove("\\t");
//                letterSet.add("\t");
//            }
//            if (letterSet.contains("\\f")) {
//                letterSet.remove("\\f");
//                letterSet.add("\f");
//            }
//            if (letterSet.contains("\\n")) {
//                letterSet.remove("\\n");
//                letterSet.add("\n");
//            }
//            if (letterSet.contains("\\v") || letterSet.contains("\\u000b")) {
//                letterSet.remove("\\v");
//                letterSet.remove("\\u000b");
//                letterSet.add("\u000b");
//            }
//            if (letterSet.contains("\\r")) {
//                letterSet.remove("\\r");
//                letterSet.add("\r");
//            }

        }
//        return letterSet;
        return newLetterSet;
    }

    /**
     * 获取当前节点的字母表
     * 注意 . 返回空
     *
     * @param addDefaultw 是否添加默认的w
     * @return
     */
    public Set<String> getLetterSet(boolean addDefaultw) {
        Set<String> set = new LinkedHashSet<>();
        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(this);
        // 获取叶子节点集合
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            node = getGroupSubNode(node);
            if (isNonCapturingGroupNode(node)) {
                node = node.getChild(3);
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                set.add(node.getData());
            } else {
                if (isBracketsNode(node) || isNegateNode(node)) {
                    int i = node.getData().startsWith("[^") ? 2 : 1;
                    for (; i < node.getChildCount() - 1; i++) {
                        String childData = node.getChild(i).getData();
                        if (childData.length() == 1 && isSpecialCharacterBracket(childData.charAt(0))) {
                            // 中括号类的特殊字符，需要加上\才能在外部被识别
                            set.add("\\" + childData);
                        } else {
                            //todo  childData 必须是叶子节点
                            if (childData.contains("-") && childData.length() > 3 && childData.length() <= 5) {
                                childData = childData.replace("\\\\", "special_slash_ios_ac_cn");
                                childData = childData.replace("\\", "");
                                childData = childData.replace("special_slash_ios_ac_cn", "\\");
                                set.add(childData);
                            } else if (isCollectionSymbol(childData)) {
                                set.add(childData);
                            } else {
                                if (node.getChild(i).isLeaf()) {
                                    set.add(childData);
                                } else {
                                    LinkedList<String> list = node.getChild(i).getLeafs();
                                    set.addAll(list);
                                }
                            }
                        }
                    }
                } else {
                    for (TreeNode child : node.getChildList()) {
                        if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                            queue.add(child);
                        }
                    }
                }
            }
        }
        // 将叶子节点转换为单个字母集合
        ArrayList<String> sortSet = new ArrayList<>();
        for (String str : set) {
            if ("\\w".equals(str)) {
                sortSet.addAll(Arrays.asList(w_MATCH_CHARACTER_LIST));
            } else if ("\\d".equals(str)) {
                sortSet.addAll(Arrays.asList(d_MATCH_CHARACTER_LIST));
            } else if (".".equals(str)) {
                // . 不作处理，表示当前字母表集合
                // sortSet.addAll(Arrays.asList(dot_MATCHE_CHARACTER));
            } else if ("\\s".equals(str)) {
                sortSet.addAll(Arrays.asList(s_MATCH_CHARACTER_LIST));
            } else if (isCollectionSymbol(str)) {
                long start = str.charAt(0);
                long end = str.charAt(2);
                for (; start <= end; start++) {
                    char c = (char) start;
                    sortSet.add(String.valueOf(c));
                }
            } else if (COMPLEMENTARY_SET_SYMBOL.containsKey(str)) {
                sortSet.addAll(COMPLEMENTARY_SET_SYMBOL.get(str));
            } else {
                sortSet.add(str);
            }
        }
        if (addDefaultw) {
            sortSet.addAll(Arrays.asList(w_MATCH_CHARACTER_LIST));
        }
        sortSet.sort(Comparator.naturalOrder());
        ArrayList<String> newSet = new ArrayList<>();
        for (String string : sortSet) {
            if (string.length() == 1 && isSpecialCharacterBracket(string.charAt(0))) {
                newSet.add("\\" + string);
            } else {
                newSet.add(string);
            }
        }
        return new LinkedHashSet<>(newSet);
    }

    /**
     * 获取当前节点的必须包含的字母表
     *
     * @return
     */
    public Set<String> getLetterSetMustHas() {
        Set<String> set = new LinkedHashSet<>();
        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(this);
        // 获取叶子节点集合
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isZeroCountingNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    node = node.getChild(0);
                }
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                set.add(node.getData());
            } else {
                if (isBracketsNode(node)) {
                    int i = node.getData().startsWith("[^") ? 2 : 1;
                    for (; i < node.getChildCount() - 1; i++) {
                        String childData = node.getChild(i).getData();
                        if (childData.length() == 1 && isSpecialCharacterBracket(childData.charAt(0))) {
                            // 中括号类的特殊字符，需要加上\才能在外部被识别
                            set.add("\\" + childData);
                        } else {
                            //todo  childData 必须是叶子节点
                            if (childData.contains("-") && childData.length() > 3 && childData.length() <= 5) {
                                childData = childData.replace("\\\\", "special_slash_ios_ac_cn");
                                childData = childData.replace("\\", "");
                                childData = childData.replace("special_slash_ios_ac_cn", "\\");
                                set.add(childData);
                            } else if (isCollectionSymbol(childData)) {
                                set.add(childData);
                            } else {
                                if (node.getChild(i).isLeaf()) {
                                    set.add(childData);
                                } else {
                                    LinkedList<String> list = node.getChild(i).getLeafs();
                                    set.addAll(list);
                                }
                            }
                        }
                    }
                } else {
                    for (TreeNode child : node.getChildList()) {
                        if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                            queue.add(child);
                        }
                    }
                }
            }
        }
        // 将叶子节点转换为单个字母集合
        ArrayList<String> sortSet = new ArrayList<>();
        for (String str : set) {
            if ("\\w".equals(str)) {
                sortSet.add("1");
            } else if ("\\d".equals(str)) {
                sortSet.add("1");
            } else if ("\\s".equals(str)) {
                sortSet.add(" ");
            } else if (".".equals(str)) {
                sortSet.add("1");
            } else if ("\\W".equals(str)) {
                sortSet.add("!");
            } else if ("\\S".equals(str)) {
                sortSet.add("1");
            } else if ("\\D".equals(str)) {
                sortSet.add("a");
            } else if (isCollectionSymbol(str)) {
                long start = str.charAt(0);
                long end = str.charAt(2);
                for (; start <= end; start++) {
                    char c = (char) start;
                    sortSet.add(String.valueOf(c));
                }
            } else {
                sortSet.add(str);
            }
        }
        sortSet.sort(Comparator.naturalOrder());
        ArrayList<String> newSet = new ArrayList<>();
        for (String string : sortSet) {
            if (string.length() == 1 && isSpecialCharacterBracket(string.charAt(0))) {
                newSet.add("\\" + string);
            } else {
                newSet.add(string);
            }
        }
        return new LinkedHashSet<>(newSet);
    }

    // 判断node结点是否为当前结点的儿子或孙子结点
    public boolean isNowNodeChildOrGrandchild(TreeNode node) {
        while (node.getParent() != null) {
            if (node.getParent().equals(this)) {
                return true;
            } else {
                node = node.getParent();
            }
        }
        return false;
    }

    // 获取当前结点的前一个结点
    // e.g.  (a|b|c)+ 对于a 前一个结点就是null 对于b 前一个结点就是|
    public TreeNode getPreviousNode() {
        TreeNode parent = this.getParent();
        if (parent == null) return null;
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChild(i) == this) {
                if (i == 0) return null;
                else return parent.getChild(i - 1);
            }
        }
        return null;
    }

    // 获取当前结点的后一个结点
    // e.g.  (a|b|c)+ 对于a 后一个结点就是| 对于c 后一个结点就是null
    public TreeNode getNextNode() {
        TreeNode parent = this.getParent();
        if (parent == null) return null;
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChild(i) == this) {
                if (i == parent.getChildCount() - 1) return null;
                else return parent.getChild(i + 1);
            }
        }
        return null;
    }


    // 获取 从某一个结点sNode到最后的 非匹配串
    public String getNonMatchStr(TreeNode sNode) {
        StringBuilder s = new StringBuilder();

        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.equals(sNode)) {
                break;
            }
            if (node.isNowNodeChildOrGrandchild(sNode)) {
                if (node.equals(sNode)) {
                    break;
                }
                if (node.isNowNodeChildOrGrandchild(sNode)) {
                    if (node.equals(sNode)) {
                        continue;
                    }
                    if (isOrNode(node)) {
                        node = getGroupSubNode(node);
                        if (isEmptyOrNode(node)) {
                            continue;
                        } else {
                            for (int i = 0; i < node.getChildCount(); i++) {
                                if (!(node.getChild(i).equals(sNode) || node.getChild(i).isNowNodeChildOrGrandchild(sNode))) {
                                    stack.push(node.getChild(i));
                                    break;
                                }
                            }
                            continue;
                        }
                    }
                    if (isGeneralizedCountingNode(node)) {
                        node = getGroupSubNode(node);
                        if (isCountingNode(node)) { // 是{\d*,?\d*}这种counting
                            node = getGroupSubNode(node);
                            int num = getCountingFirstNum(node.getChild(1).getData());
                            String subStr = node.getChild(0).getMatchStr();
                            for (int i = 0; i < num; i++) {
                                s.append(subStr);
                            }
                            stack.push(node.getChild(0));
                        } else {    // 是*?+
                            stack.push(node.getChild(0));
                        }
                        continue;
                    }
                    if (node.isLeaf() || isBracketsNode(node) || isNegateNode(node)) {
                        s.append(node.getNonMatchStr());
                    } else {
                        int i;
                        for (i = 0; i < node.getChildCount(); i++) {
                            if (node.getChild(i).isNowNodeChildOrGrandchild(sNode) || node.getChild(i).equals(sNode)) {
                                break;
                            }
                        }
                        for (int j = node.getChildCount() - 1; j > i; j--) {
                            TreeNode child = node.getChild(j);
                            if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                                stack.push(child);
                            }
                        }
                    }
                }
            } else {
                s.append(node.getNonMatchStr());
            }
        }

        return s.toString();
    }

    // 获取 到某一个结点sNode 为止的 匹配串
    public String getMatchStr(TreeNode sNode) {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.equals(sNode)) {
                break;
            }
            if (node.isNowNodeChildOrGrandchild(sNode)) {
                if (node.equals(sNode)) {
                    break;
                }
                if (isOrNode(node)) {
                    node = getGroupSubNode(node);
                    if (isEmptyOrNode(node)) {
                        continue;
                    } else {
                        for (int i = 0; i < node.getChildCount(); i++) {
                            if (node.getChild(i).equals(sNode) || node.getChild(i).isNowNodeChildOrGrandchild(sNode)) {
                                stack.push(node.getChild(i));
                                break;
                            }
                        }
                    }
                    continue;
                }
                if (isGeneralizedCountingNode(node)) {
                    if (isCountingNode(node)) { // 是{\d*,?\d*}这种counting
                        node = getGroupSubNode(node);
                        int num = getCountingFirstNum(node.getChild(1).getData());
                        String subStr = node.getChild(0).getMatchStr();
                        for (int i = 0; i < num; i++) {
                            s.append(subStr);
                        }
                        stack.push(node.getChild(0));
                    } else {    // 是*?+
                        stack.push(node.getChild(0));
                    }
                    continue;
                }
                if (node.isLeaf() || isBracketsNode(node)) {
                    s.append(node.getMatchStr());
                } else {
                    int i;
                    for (i = 0; i < node.getChildCount(); i++) {
                        if (node.getChild(i).isNowNodeChildOrGrandchild(sNode) || node.getChild(i).equals(sNode)) {
                            break;
                        }
                    }
                    for (int j = i; j >= 0; j--) {
                        TreeNode child = node.getChild(j);
                        if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                            stack.push(child);
                        }
                    }
                }
            } else {
                s.append(node.getMatchStr());
            }
        }
        return s.toString();
    }


    /**
     * 获取匹配串
     * waring:会跳过可为0的计数节点，或节点取第一个，
     * 对应 getMatchStrWithCounting
     *
     * @return
     */
    public String getMatchStr() {
        StringBuilder s = new StringBuilder();

        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isZeroCountingNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    node = node.getChild(0);
                }
            }
            if (isCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
                String subStr = node.getChild(0).getMatchStr();
                for (int i = 0; i < num; i++) {
                    s.append(subStr);
                }
                continue;
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                s.append(getMatchLeafNode(node));
            } else if (isBracketsNode(node) || isNegateNode(node)) {    // 修改
                boolean flag = false;
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                for (int i = 0; i < 65536; i++) {
                    char c = (char) i;
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        break;
                    }
                }
            }
//            else if (isBracketsNode(node)) {
//                s.append(getMatchBracketNode(node));
//            }
            else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return s.toString();
    }


    // 判断当前结点是否含有lookaround结点
    public boolean containsLookAroundChild() {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);

        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isLookAroundNode(node)) {
                return true;
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return false;
    }

    // 判断当前结点是否包含零宽断言结点
    public boolean containsZeroWidthAssertionChild() {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);

        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isZeroWidthAssertionNode(node)) {
                return true;
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return false;
    }

    // 获得包含零宽断言的个数
    public int getNumberOfZeroWidthAssertions () {
        int count = 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);

        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isZeroWidthAssertionNode(node)) {
                count++;
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return count;
    }

    // 考虑零宽断言的生成匹配串, 可能会错
    // 或结点的处理方式为: 优先选择包含零宽断言最少的孩子结点
    // 别用
    // 这是专门为下面这个正则写的函数 生成中缀
    // &lt;(span|font).*?(?:(?:(\s?style=&quot;?).*?((?:\s?font-size:.+?\s*(?:;|,|(?=&quot;))+)|(?:\s?color:.+?\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\s?font-size:.+?\s*(?:;|,|(?=&quot;))+)|(?:\s?color:.+?\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\s?size=&quot;?.*?(?:(?=\s)|&quot;|(?=&gt;)))|(\s?color=&quot;?.*?(?:(?=\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}&gt;
    public String getMatchStrWithZeroWidthAssertions() throws InterruptedException {
        TreeNode treeNodeCopy = SerializationUtils.clone(this);    // 深拷贝
        // 去补
        removeNegateSymbol(treeNodeCopy, Constant.SimplyLevel.HIGH);

        Pattern pattern = Pattern.compile(this.getData());

        StringBuilder s = new StringBuilder();
        TreeNode zeroWidthAssertionNode = null;
        Stack<TreeNode> stack = new Stack<>();
//        stack.add(this);
        stack.add(treeNodeCopy);
        // 获取叶子结点集合
        while (! stack.isEmpty()) {
            TreeNode node = stack.pop();
            node = getGroupSubNode(node);
            if (isOrNode(node)) {
                int constraintCount = Integer.MAX_VALUE;
                TreeNode child = node.getChild(0);
                for (int i = 0; i < node.getChildCount(); i += 2) {
                    int num = node.getChild(i).getNumberOfZeroWidthAssertions();
                    if (num < constraintCount) {
                        constraintCount = num;
                        child = node.getChild(i);
                        if (num == 0) break;
                    }
                }
                node = child;
            }
            if (isZeroWidthAssertionNode(node)) {
                AssertType assertType = getAssertType(node);
                if (assertType == AssertType.POSITIVE_AHEAD) {  // （?=
                    if (node.getChildCount() == 5) {
                        zeroWidthAssertionNode = node;
                    }
                } else if (assertType == AssertType.POSITIVE_BEHIND) {  // (?<=
                    if (node.getChildCount() == 6) {
                        zeroWidthAssertionNode = node;
                    }
                } else if (assertType == AssertType.NEGATIVE_AHEAD) {   // (?!
                    if (node.getChildCount() == 5) {
                        zeroWidthAssertionNode = node;
                    }
                } else if (assertType == AssertType.NEGATIVE_BEHIND) {  // (?<!
                    if (node.getChildCount() == 6) {
                        zeroWidthAssertionNode = node;
                    }
                } else if (assertType == AssertType.WORD_BOUNDARY_ASSERT) { // \b
                    zeroWidthAssertionNode = node;
                } else if (assertType == AssertType.NON_WORD_BOUNDARY_ASSERT) { // \B
                    zeroWidthAssertionNode = node;
                }
                continue;
            }
            if (isGeneralizedCountingNode(node)) {
                node = getGroupSubNode(node);
                if (zeroWidthAssertionNode != null) {
                    AssertType assertType = getAssertType(zeroWidthAssertionNode);
                    if (assertType == AssertType.POSITIVE_AHEAD || assertType == AssertType.NEGATIVE_AHEAD) {  // (?= 和 (?!
                        TreeNode treeNode = createReDoSTree(zeroWidthAssertionNode.getData() + node.getData());
                        // 使用重写后的去首尾^$
                        treeNode.deleteCaretAndDollarSymbols();
                        // 新版重写空串
//                        treeNode = removeBlankStr(treeNode);
                        treeNode.removeBlankStr();
                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
                        treeNode.deleteNonCapturingGroupFlag();
                        List<String> regexList1 = getTranslateRegexForAssertionsList(treeNode);
                        String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                        if (example == null) example = "";
                        s.append(example);
                        zeroWidthAssertionNode = null;
                    } else if (assertType == AssertType.POSITIVE_BEHIND || assertType == AssertType.NEGATIVE_BEHIND){   // (?<= 和 (?<!
//                        TreeNode treeNode = createReDoSTree(node.getData() + zeroWidthAssertionNode.getData());
//                        // 使用重写后的去首尾^$
//                        treeNode.deleteCaretAndDollarSymbols();
//                        // 新版重写空串
//                        treeNode = removeBlankStr(treeNode);
//                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
//                        treeNode.deleteNonCapturingGroupFlag();
//                        List<String> regexList1 = getTranslateRegexForAssertionsList(treeNode);
//                        String example = getExampleByDkBricsAutomaton2(regexList1, 0);
//                        if (example == null) example = "";
//                        s.append(example);
                        zeroWidthAssertionNode = null;
                        int num = getCountingFirstNum(node.getChild(1).getData());
                        num = num == 0 ? 1 : num;
                        String subStr = node.getChild(0).getMatchStrWithZeroWidthAssertions();
                        for (int i = 0; i < num; i++) {
                            s.append(subStr);
                        }
                    } else if (assertType == AssertType.WORD_BOUNDARY_ASSERT) { // \b
                        if (s.length() == 0) {
                            List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                            for (int i = 0; i < regexList1.size(); i++) {
                                String regex = regexList1.get(i);
                                regexList1.set(i, "(\\w[\u0000-\uFFFF]*)" + "＆" + "(" + regex + ")");
                            }
                            String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                            if (example == null) example = "";
                            s.append(example);
                            zeroWidthAssertionNode = null;
                        } else {
                            char c = s.charAt(s.length() - 1);
                            Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                            if (w.matches()) {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\W[\u0000-\uFFFF]*)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = "";
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            } else {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\w[\u0000-\uFFFF]*)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = "";
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            }
                        }
                    } else if (assertType == AssertType.NON_WORD_BOUNDARY_ASSERT) { // \B
                        if (s.length() == 0) {
                            List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                            for (int i = 0; i < regexList1.size(); i++) {
                                String regex = regexList1.get(i);
                                regexList1.set(i, "(\\W[\u0000-\uFFFF]*)" + "＆" + "(" + regex + ")");
                            }
                            String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                            if (example == null) example = "";
                            s.append(example);
                            zeroWidthAssertionNode = null;
                        } else {
                            char c = s.charAt(s.length() - 1);
                            Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                            if (w.matches()) {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\w[\u0000-\uFFFF]*)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = "";
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            } else {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\W[\u0000-\uFFFF]*)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = "";
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            }
                        }
                    }
                } else {
                    int num = getCountingFirstNum(node.getChild(1).getData());
                    num = num == 0 ? 1 : num;
                    String subStr = node.getChild(0).getMatchStrWithZeroWidthAssertions();
                    for (int i = 0; i < num; i++) {
                        s.append(subStr);
                    }
                }
                continue;
            }
            if (node.isLeaf()) {
                if (isQuantifierNode(node) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                } else {
                    if (zeroWidthAssertionNode != null) {
                        AssertType assertType = getAssertType(zeroWidthAssertionNode);
                        if (assertType == AssertType.POSITIVE_AHEAD || assertType == AssertType.NEGATIVE_AHEAD) {  // (?= 和 (?!
                            TreeNode treeNode = createReDoSTree(zeroWidthAssertionNode.getData() + node.getData() + "[\\s\\S]*");
                            // 使用重写后的去首尾^$
                            treeNode.deleteCaretAndDollarSymbols();
                            // 新版重写空串
//                            treeNode = removeBlankStr(treeNode);
                            treeNode.removeBlankStr();
                            // 重写反向引用后 删除NonCapturingGroupFlag ?:
                            treeNode.deleteNonCapturingGroupFlag();
                            List<String> regexList1 = getTranslateRegexForAssertionsList(treeNode);
                            String example = getExampleByDkBricsAutomaton2(regexList1, 1);
                            if (example == null) example = getMatchLeafNode(node);
                            s.append(example.charAt(0));
                            zeroWidthAssertionNode = null;
                        } else if (assertType == AssertType.POSITIVE_BEHIND || assertType == AssertType.NEGATIVE_BEHIND){   // (?<= 和 (?<!
//                            TreeNode treeNode = createReDoSTree(node.getData() + zeroWidthAssertionNode.getData());
//                            // 使用重写后的去首尾^$
//                            treeNode.deleteCaretAndDollarSymbols();
//                            // 新版重写空串
//                            treeNode = removeBlankStr(treeNode);
//                            // 重写反向引用后 删除NonCapturingGroupFlag ?:
//                            treeNode.deleteNonCapturingGroupFlag();
//                            List<String> regexList1 = getTranslateRegexForAssertionsList(treeNode);
//                            String example = getExampleByDkBricsAutomaton2(regexList1, 1);
//                            if (example == null) example = getMatchLeafNode(node);
//                            s.append(example.charAt(0));
                            zeroWidthAssertionNode = null;
                            int num = getCountingFirstNum(node.getChild(1).getData());
                            num = num == 0 ? 1 : num;
                            String subStr = node.getChild(0).getMatchStrWithZeroWidthAssertions();
                            for (int i = 0; i < num; i++) {
                                s.append(subStr);
                            }
                        } else if (assertType == AssertType.WORD_BOUNDARY_ASSERT) { // \b
                            if (s.length() == 0) {
                                s.append(getMatchLeafNode(node));
                                zeroWidthAssertionNode = null;
                            } else {
                                char c = s.charAt(s.length() - 1);
                                Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                                if (w.matches()) {
                                    List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                    for (int i = 0; i < regexList1.size(); i++) {
                                        String regex = regexList1.get(i);
                                        regexList1.set(i, "(\\W)" + "＆" + "(" + regex + ")");
                                    }
                                    String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                    if (example == null) example = getMatchLeafNode(node);
                                    s.append(example);
                                    zeroWidthAssertionNode = null;
                                } else {
                                    List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                    for (int i = 0; i < regexList1.size(); i++) {
                                        String regex = regexList1.get(i);
                                        regexList1.set(i, "(\\w)" + "＆" + "(" + regex + ")");
                                    }
                                    String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                    if (example == null) example = getMatchLeafNode(node);
                                    s.append(example);
                                    zeroWidthAssertionNode = null;
                                }
                            }
                        } else if (assertType == AssertType.NON_WORD_BOUNDARY_ASSERT) { // \B
                            if (s.length() == 0) {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\W)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = getMatchLeafNode(node);
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            } else {
                                char c = s.charAt(s.length() - 1);
                                Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                                if (w.matches()) {
                                    List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                    for (int i = 0; i < regexList1.size(); i++) {
                                        String regex = regexList1.get(i);
                                        regexList1.set(i, "(\\w)" + "＆" + "(" + regex + ")");
                                    }
                                    String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                    if (example == null) example = getMatchLeafNode(node);
                                    s.append(example);
                                    zeroWidthAssertionNode = null;
                                } else {
                                    List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                    for (int i = 0; i < regexList1.size(); i++) {
                                        String regex = regexList1.get(i);
                                        regexList1.set(i, "(\\W)" + "＆" + "(" + regex + ")");
                                    }
                                    String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                    if (example == null) example = getMatchLeafNode(node);
                                    s.append(example);
                                    zeroWidthAssertionNode = null;
                                }
                            }
                        }
                    } else {
                        s.append(getMatchLeafNode(node));
                    }
                }
            } else if (isBracketsNode(node)) {
                if (zeroWidthAssertionNode != null) {
                    AssertType assertType = getAssertType(zeroWidthAssertionNode);
                    if (assertType == AssertType.POSITIVE_AHEAD || assertType == AssertType.NEGATIVE_AHEAD) {  // (?= 和 (?!
                        TreeNode treeNode = createReDoSTree(zeroWidthAssertionNode.getData() + node.getData() + "[\\s\\S]*");
                        // 使用重写后的去首尾^$
                        treeNode.deleteCaretAndDollarSymbols();
                        // 新版重写空串
//                        treeNode = removeBlankStr(treeNode);
                        treeNode.removeBlankStr();
                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
                        treeNode.deleteNonCapturingGroupFlag();
                        List<String> regexList1 = getTranslateRegexForAssertionsList(treeNode);
                        String example = getExampleByDkBricsAutomaton2(regexList1, 1);
                        if (example == null) example = getMatchBracketNode(node);
                        s.append(example.charAt(0));
                        zeroWidthAssertionNode = null;
                    } else if (assertType == AssertType.POSITIVE_BEHIND || assertType == AssertType.NEGATIVE_BEHIND){   // (?<= 和 (?<!
                        TreeNode treeNode = createReDoSTree(node.getData() + zeroWidthAssertionNode.getData());
                        // 使用重写后的去首尾^$
                        treeNode.deleteCaretAndDollarSymbols();
                        // 新版重写空串
//                        treeNode = removeBlankStr(treeNode);
                        treeNode.removeBlankStr();
                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
                        treeNode.deleteNonCapturingGroupFlag();
                        List<String> regexList1 = getTranslateRegexForAssertionsList(treeNode);
                        String example = getExampleByDkBricsAutomaton2(regexList1, 1);
                        if (example == null) example = getMatchBracketNode(node);
                        s.append(example.charAt(0));
                        zeroWidthAssertionNode = null;
                    } else if (assertType == AssertType.WORD_BOUNDARY_ASSERT) { // \b
                        if (s.length() == 0) {
                            s.append(getMatchBracketNode(node));
                            zeroWidthAssertionNode = null;
                        } else {
                            char c = s.charAt(s.length() - 1);
                            Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                            if (w.matches()) {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\W)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = getMatchBracketNode(node);
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            } else {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\w)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = getMatchBracketNode(node);
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            }
                        }
                    } else if (assertType == AssertType.NON_WORD_BOUNDARY_ASSERT) { // \B
                        if (s.length() == 0) {
                            List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                            for (int i = 0; i < regexList1.size(); i++) {
                                String regex = regexList1.get(i);
                                regexList1.set(i, "(\\W)" + "＆" + "(" + regex + ")");
                            }
                            String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                            if (example == null) example = getMatchBracketNode(node);
                            s.append(example);
                            zeroWidthAssertionNode = null;
                        } else {
                            char c = s.charAt(s.length() - 1);
                            Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                            if (w.matches()) {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\w)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = getMatchBracketNode(node);
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            } else {
                                List<String> regexList1 = getTranslateRegexForAssertionsList(node);
                                for (int i = 0; i < regexList1.size(); i++) {
                                    String regex = regexList1.get(i);
                                    regexList1.set(i, "(\\W)" + "＆" + "(" + regex + ")");
                                }
                                String example = getExampleByDkBricsAutomaton2(regexList1, 0);
                                if (example == null) example = getMatchBracketNode(node);
                                s.append(example);
                                zeroWidthAssertionNode = null;
                            }
                        }
                    }
                } else {
                    s.append(getMatchBracketNode(node));
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }


        if (zeroWidthAssertionNode != null) {
            AssertType assertType = getAssertType(zeroWidthAssertionNode);
            if (assertType == AssertType.WORD_BOUNDARY_ASSERT) {    // 最后一个如果是\b
                if (s.length() == 0) {
                    s.append("a");
                } else {
                    char c = s.charAt(s.length() - 1);
                    Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                    if (w.matches()) {

                    } else {
                        s.append("a");
                    }
                }
            } else if (assertType == AssertType.NON_WORD_BOUNDARY_ASSERT) { // 最后一个如果是\B
                if (s.length() == 0) {

                } else {
                    char c = s.charAt(s.length() - 1);
                    Matcher w = Pattern.compile("\\w").matcher(String.valueOf(c));
                    if (w.matches()) {
                        s.append("a");
                    } else {

                    }
                }
            }
        }

        String result = s.toString();
        result = repairLineFeedsCarriageReturnsAndTabCharacters(result);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) return result;
        else return null;
    }

    /**
     * 获取匹配串 不跳过计数符号
     *
     * @return
     */
    public String getMatchStrWithCounting() {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                node = node.getChild(0);
            }
            if (isCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
                num = num == 0 ? 1 : num;
                String subStr = node.getChild(0).getMatchStrWithCounting();
                for (int i = 0; i < num; i++) {
                    s.append(subStr);
                }
                continue;
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                s.append(getMatchLeafNode(node));
            } else if (isBracketsNode(node) || isNegateNode(node)) {    // 修改
                boolean flag = false;
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                for (int i = 0; i < 65536; i++) {
                    char c = (char) i;
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        break;
                    }
                }
            }
//            else if (isBracketsNode(node)) {
//                s.append(getMatchBracketNode(node));
//            }
            else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return s.toString();
    }


    /**
     * 获取匹配串
     * 生成倾向于conflictSet集合的匹配串
     *
     * @return
     */
    public String getMatchStr(List<String> conflictSet) {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isZeroCountingNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    node = node.getChild(0);
                }
            }
            if (isCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
                String subStr = node.getChild(0).getMatchStr();
                for (int i = 0; i < num; i++) {
                    s.append(subStr);
                }
                continue;
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                String data = node.getData();
                String str = getMatchLetter(data, conflictSet);
                if (NO_LETTER_MATCH.equals(str)) {
                    return NO_LETTER_MATCH;
                } else {
                    s.append(str);
                }
            } else if (isBracketsNode(node)) {
                List<String> subList = new ArrayList<>(node.getLetterSet(false));
                String str = getMatchLetter(subList, conflictSet);
                if (NO_LETTER_MATCH.equals(str)) {
                    return NO_LETTER_MATCH;
                } else {
                    s.append(str);
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return s.toString();
    }

    // for Lu generate counterexample
    @Deprecated
    public String getMatchStr(String ambiguousPointString) {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);

        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    boolean flag = true;
                    for (int i = 0; i < node.getChildCount(); i++) {
                        if (node.getChildDataList().get(i).contains(ambiguousPointString)) {
                            node = node.getChild(i);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        node = node.getChild(0);
                    } else if (node.getData().equals(ambiguousPointString)) {
                        break;
                    }
                }
            }
            if (isCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
                if (node.getData().contains(ambiguousPointString)) {
                    String subStr = node.getChild(0).getMatchStr();
                    for (int i = 0; i < num; i++) {
                        s.append(subStr);
                    }
                    break;
                } else {
                    String subStr = node.getChild(0).getMatchStr(ambiguousPointString);
                    for (int i = 0; i < num; i++) {
                        s.append(subStr);
                    }
                }
                continue;
            }
            if (node.getData().equals(ambiguousPointString)) {
                break;
            }
            if (node.isLeaf()) {
                // 注意这里在强确定性的时候加入了这三行if 如果弱确定性有问题 把这三行if删去
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                String data = node.getData();
                s.append(data);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    stack.push(child);
                }
            }
        }

        return s.toString();
    }

    // for the string between two point ap1 ap2
    // 生成 ((a4b1)|a1a2a3|a4(c1)?((a0){3}|a2){2,})+ 中 c1到b1之间的部分
    @Deprecated
    public String getMatchStrForTwoPoints(String ambiguousPointString1, String ambiguousPointString2) {
        StringBuilder s = new StringBuilder();

        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);

        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    boolean flag = true;
                    for (int i = 0; i < node.getChildCount(); i++) {
                        if (node.getChildDataList().get(i).contains(ambiguousPointString1)) {
                            node = node.getChild(i);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        node = node.getChild(0);
                    } else if (node.getData().equals(ambiguousPointString1)) {
//                        break;
                    }
                }
            }
            if (isCountingNode(node)) {
                int num = getCountingFirstNum(node.getChild(1).getData());
                if (node.getData().contains(ambiguousPointString1)) {
                    String subStr = node.getChild(0).getMatchStr();
                    for (int i = 0; i < num; i++) {
                        s.append(subStr);
                    }
//                    break;
                } else {
                    String subStr = node.getChild(0).getMatchStr(ambiguousPointString1);
                    for (int i = 0; i < num; i++) {
                        s.append(subStr);
                    }
                }
                continue;
            }
            if (node.getData().equals(ambiguousPointString1)) {
//                break;
            }
            if (node.isLeaf()) {
                // 注意这里在强确定性的时候加入了这三行if 如果弱确定性有问题 把这三行if删去
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                String data = node.getData();
                s.append(data);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    stack.push(child);
                }
            }
        }

        // 以上为生成包含有ambiguousPointString1部分的串
        // 如((a4b1)|a1a2a3|a4(c1)?((a0){3}|a2){2,})+
        // ambiguousPointString1 = c1
        // 以上生成为a4c1a0a0a0a0a0a0

//        System.out.println("s = " + s);
        // 截掉第一个ambiguousPointString1之前的部分
        int firstAmbiguousPointString1Pos = s.indexOf(ambiguousPointString1);
        s.replace(0, firstAmbiguousPointString1Pos, "");

        // 然后生成ambiguousPointString2之前的部分
        s.append(getMatchStr(ambiguousPointString2));

        return s.toString();
    }


    // 生成ambiguousPointString2之前的部分
    @Deprecated
    public String getMatchStrForTwoPointsHelper(String ambiguousPointString) {
        StringBuilder s = new StringBuilder();

        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);

        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isZeroWidthAssertionNode(node)) {
                    continue;
                }
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    boolean flag = true;
                    for (int i = 0; i < node.getChildCount(); i++) {
                        if (node.getChildDataList().get(i).contains(ambiguousPointString)) {
                            node = node.getChild(i);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        node = node.getChild(0);
                    } else if (node.getData().equals(ambiguousPointString)) {
                        break;
                    }
                }
            }
            if (isCountingNode(node)) {
                int num = getCountingFirstNum(node.getChild(1).getData());
                if (node.getData().contains(ambiguousPointString)) {
                    String subStr = node.getChild(0).getMatchStr();
                    for (int i = 0; i < num - 1; i++) {
                        s.append(subStr);
                    }
                    s.append(node.getChild(0).getMatchStr(ambiguousPointString));
                    break;
                } else {
                    String subStr = node.getChild(0).getMatchStr(ambiguousPointString);
                    for (int i = 0; i < num; i++) {
                        s.append(subStr);
                    }
                }
                continue;
            }
            if (node.getData().equals(ambiguousPointString)) {
                break;
            }
            if (node.isLeaf()) {
                // 注意这里在强确定性的时候加入了这三行if 如果弱确定性有问题 把这三行if删去
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                String data = node.getData();
                s.append(data);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    stack.push(child);
                }
            }
        }

        return s.toString();
    }


    /**
     * 获取不匹配串
     *
     * @return
     */
    public String getNonMatchStr() {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                if (isEmptyOrNode(node)) {
                    continue;
                } else {
                    node = node.getChild(0);
                }
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                return getNonMatchLeafNode(node);
            } else if (isBracketsNode(node) || isNegateNode(node)) {    // 修改
//                boolean flag = false;
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (!isMatch) {
                        return c;
                    }
                }
                for (int i = 0; i < 65536; i++) {
                    char c = (char) i;
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (!isMatch) {
                        return String.valueOf(c);
                    }
                }
            }
//            else if (isBracketsNode(node)) {
//                Set<String> set = new LinkedHashSet<>();
//                for (int i = 1; i < node.getChildCount() - 1; i++) {
//                    String childData = node.getChild(i).getData();
//                    if (childData.length() == 1 && isSpecialCharacterBracket(childData.charAt(0))) {
//                        // 中括号类的特殊字符，需要加上\才能在外部被识别
//                        set.add("\\" + childData);
//                    } else {
//                        //todo  childData 必须是叶子节点
//                        if (childData.contains("-") && childData.length() > 3 && childData.length() <= 5) {
//                            childData = childData.replace("\\\\", "special_slash_ios_ac_cn");
//                            childData = childData.replace("\\", "");
//                            childData = childData.replace("special_slash_ios_ac_cn", "\\");
//                            set.add(childData);
//                        } else if (isCollectionSymbol(childData)) {
//                            set.add(childData);
//                        } else {
//                            if (node.getChild(i).isLeaf()) {
//                                set.add(childData);
//                            } else {
//                                set.addAll(node.getChild(i).getLeafs());
//                            }
//                        }
//                    }
//                }
//                // 将叶子节点转换为单个字母集合
//                ArrayList<String> sortSet = new ArrayList<>();
//                for (String str : set) {
//                    if (isCollectionSymbol(str)) {
//                        long start = str.charAt(0);
//                        long end = str.charAt(2);
//                        for (; start <= end; start++) {
//                            char c = (char) start;
//                            sortSet.add(String.valueOf(c));
//                        }
//                    } else {
//                        sortSet.add(str);
//                    }
//                }
//                if (!sortSet.contains("!")) {
//                    return "!1 \n_";
//                } else if (!sortSet.contains("@")) {
//                    return "@1 \n_";
//                } else {
//                    return "◎1 \n_";
//                }
//            }
            else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取节点的所有叶子节点
     *
     * @return
     */
    public LinkedList<String> getLeafs() {
        LinkedList<String> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(this);
        // 获取叶子节点集合
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node.isLeaf()) {
                list.add(node.getData());
            } else {
                queue.addAll(node.getChildList());
            }
        }
        return list;
    }

    /**
     * 从叶子节点获取树对应的正则
     *
     * @return
     */
    public String getLeafsData() {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.isLeaf()) {
                stringBuilder.append(node.getData());
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        return stringBuilder.toString();
    }

    // 获取当前结点的所有叶子节点
    public LinkedList<TreeNode> getLeafNodes() {
        LinkedList<TreeNode> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.isLeaf()) {
                list.add(node);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        return list;
    }

    // 获取当前结点的所有补结点
    public LinkedList<TreeNode> getAllNegateChildren() {
        LinkedList<TreeNode> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isNegateNode(node)) {
                list.add(node);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        return list;
    }

    // 获取当前结点的所有孩子结点
    public List<TreeNode> getAllChildren() {
        List<TreeNode> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取所有的孩子结点
        while (! stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node);
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return list;
    }

    // 获取当前结点的所有(?!、(?<!结点
    public LinkedList<TreeNode> getAllNegativeAssertNode() {
        LinkedList<TreeNode> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isNegativeAssertNode(node)) {
                list.add(node);
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        return list;
    }

    // 获取当前结点的所有有效的孩子结点
    public ArrayList<TreeNode> getAllPossibleChildren() {
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        LinkedList<TreeNode> allLeafNode = this.getLeafNodes();
        LinkedList<TreeNode> allLeafNode2 = new LinkedList<>(allLeafNode);
        for (TreeNode leafNode : allLeafNode) {
            if (isQuantifierNode(leafNode)) continue;
//            if (isInLookAroundNode(leafNode)) continue;
            if (isInLookAroundNode(leafNode)) {
                TreeNode parent = leafNode.getParent();
                if (isLookAroundNode(parent)) {
                    if (parent.getData().startsWith("(?=") || parent.getData().startsWith("(?!")) {
                        if (leafNode.isFirstChild() || leafNode.isSecondChild() || leafNode.isThirdChild() || leafNode.isLastChild()) {
                            continue;
                        }
                    } else if (parent.getData().startsWith("(?<=") || parent.getData().startsWith("(?<!")) {
                        if (leafNode.isFirstChild() || leafNode.isSecondChild() || leafNode.isThirdChild() || leafNode.isForthChild() || leafNode.isLastChild()) {
                            continue;
                        }
                    }
                }
            }
            if (leafNode.getData().equals("|") && isOrNode(leafNode.getParent())) continue;

            TreeNode tempLeafNode = leafNode;
            while (!tempLeafNode.equals(this)) {
                if (!isInBrackets(tempLeafNode) && !isInNegateNode(tempLeafNode)) {
                    if (isBracketsNode(tempLeafNode) || isNegateNode(tempLeafNode)) allLeafNode2.add(tempLeafNode);
                    TreeNode arrayListNode = getGroupSubNode(tempLeafNode);
                    if (!arrayList.contains(arrayListNode)) {
                        arrayList.add(arrayListNode);
                    }
                }
                tempLeafNode = tempLeafNode.getParent();
            }
        }
        for (TreeNode leafNode : allLeafNode2) {
            if (isGeneralizedCountingNode(leafNode.getParent())) {
                arrayList.remove(leafNode);
            }
            if (leafNode.getData().equals("(") && leafNode.isFirstChild()) {
                arrayList.remove(leafNode);
            }
            if (leafNode.getData().equals(")") && leafNode.isLastChild()) {
                arrayList.remove(leafNode);
            }
        }

        // 按索引从大到小排列
        arrayList.sort(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode treeNode1, TreeNode treeNode2) {
                if (treeNode1.getChainIndex().compareTo(treeNode2.getChainIndex()) == 0) {
                    return 0;
                }
                String[] l1 = treeNode1.getChainIndex().split("\\.");
                String[] l2 = treeNode2.getChainIndex().split("\\.");
                int len = Math.min(l1.length, l2.length);
                for (int i = 0; i < len; i++) {
                    int n1 = Integer.parseInt(l1[i]);
                    int n2 = Integer.parseInt(l2[i]);
                    if (n1 > n2) {
                        return -1;
                    } else if (n1 < n2) {
                        return 1;
                    }
                }
                return Integer.compare(l2.length, l1.length);
            }
        });
        // 改为从小到大排
        Collections.reverse(arrayList);

        return arrayList;
    }


    // 获取当前结点的所有generalizedCountingNodeWithMaxNumLeqOne 接受a* a+ a{m,n} 其中n>1
    public ArrayList<TreeNode> getAllGeneralizedCountingWithMaxNumLeqOneNode() {
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        LinkedList<TreeNode> allLeafNode = this.getLeafNodes();
        for (TreeNode leafNode : allLeafNode) {
            TreeNode tempLeafNode = leafNode;
            while (!tempLeafNode.equals(this)) {
                if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(tempLeafNode)) {
                    if (!arrayList.contains(tempLeafNode)) {
                        arrayList.add(tempLeafNode);
                    }
                    break;
                }
                tempLeafNode = tempLeafNode.getParent();
            }
        }

        // 以上改法有问题 应该只将group的去掉group
        // 例如 ((a+b|c+b)+)? 则缺了(a+b|c+b)+这一个counting
        for (int i = 0; i < arrayList.size(); i++) {
            TreeNode temp = arrayList.get(i);
            while (isGroupNode(temp)) {
                temp = temp.getChild(1);
            }
            arrayList.set(i, temp);
        }

//        System.out.println(arrayList);


        return (ArrayList<TreeNode>) arrayList.stream().distinct().collect(Collectors.toList());
    }

    // 获取当前结点的所有或孩子结点
    public ArrayList<TreeNode> getAllOrNode() {
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        LinkedList<TreeNode> allLeafNode = this.getLeafNodes();
        for (TreeNode leafNode : allLeafNode) {
            TreeNode tempLeafNode = leafNode;
            while (!tempLeafNode.equals(this)) {
                if (isOrNode(tempLeafNode) && !isGroupNode(tempLeafNode)) {
                    if (!arrayList.contains(tempLeafNode)) {
                        arrayList.add(tempLeafNode);
                    }
                    break;
                }
                tempLeafNode = tempLeafNode.getParent();
            }
        }

        if (isOrNode(this) && !arrayList.contains(this)) {
            arrayList.add(this);
        }

        return arrayList;
    }

    // 获取当前结点的所有generalizedCountingNode 接受a* a+ a? a{m,n} m和n合法即可
    public ArrayList<TreeNode> getAllGeneralizedCountingNode() {
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        LinkedList<TreeNode> allLeafNode = this.getLeafNodes();
        for (TreeNode leafNode : allLeafNode) {
            TreeNode tempLeafNode = leafNode;
            while (!tempLeafNode.equals(this)) {
                if (isGeneralizedCountingNode(tempLeafNode)) {
                    if (!arrayList.contains(tempLeafNode)) {
                        arrayList.add(tempLeafNode);
                    }
                    break;
                }
                tempLeafNode = tempLeafNode.getParent();
            }
        }

//        System.out.println(arrayList);

        // 合并 精简tempLeafNode 保留更叶子结点的
//        for (int i = 0; i < arrayList.size(); i++) {
//            for (int j = 0; j < arrayList.size(); j++) {
//                if (arrayList.get(i).isNowNodeChildOrGrandchild(arrayList.get(j))) {
//                    arrayList.set(i, arrayList.get(j));
//                }
//            }
//        }
//
        // 以上改法有问题 应该只将group的去掉group
        // 例如 ((a+b|c+b)+)? 则缺了(a+b|c+b)+这一个counting
        for (int i = 0; i < arrayList.size(); i++) {
            TreeNode temp = arrayList.get(i);
            while (isGroupNode(temp)) {
                temp = temp.getChild(1);
            }
            arrayList.set(i, temp);
        }

//        System.out.println(arrayList);


        return (ArrayList<TreeNode>) arrayList.stream().distinct().collect(Collectors.toList());
    }


    /**
     * 1，将中括号变为括号；
     * 2，将中括号外的集合符号变为括号；
     * example1
     * input:  [\dabc]
     * output: (0|1|2|3|4|5|6|7|8|9|a|b|c)
     * example2
     * input:  \d
     * output: (0|1|2|3|4|5|6|7|8|9)
     */
    public TreeNode refactorBracketToParentheses() throws InterruptedException {
        Set<String> letterSet = getLetterSet(false);
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isBracketsNode(node)) {
                Set<String> set = simplifyLetters(node.getLetterSet(false), SimplyLevel.LOW);
                StringBuilder stringBuilder = new StringBuilder("(");
                for (String str : set) {
                    if (str.length() == 3) {
                        str = "[" + str + "]";
                    }
                    // 转义字符 需要添加 \ 才能被识别
                    if (str.length() == 1 && isSpecialCharacter(str.charAt(0))) {
                        str = "\\" + str;
                    }
                    stringBuilder.append(str).append("|");
                }
                stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
                node.setData(stringBuilder.toString());
                node.getChildList().clear();
            } else if (node.isLeaf()) {
                String value = node.getData();
                if (SLASH_SET_SYMBOL.containsKey(value)) {
                    node.setData(SLASH_SET_SYMBOL.get(value));
                    node.getChildList().clear();
                } else if (".".equals(node.getData())) {
                    Set<String> set = new HashSet<>();
                    set.add("!");
                    set.addAll(simplifyLetters(letterSet, SimplyLevel.LOW));
                    StringBuilder stringBuilder = new StringBuilder("(");
                    for (String str : set) {
                        if (str.length() == 3) {
                            str = "[" + str + "]";
                        }
                        // 转义字符 必须需要添加 \ 才能被识别
                        if (str.length() == 1 && isSpecialCharacter(str.charAt(0))) {
                            str = "\\" + str;
                        }
                        stringBuilder.append(str).append("|");
                    }
                    stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
                    node.setData(stringBuilder.toString());
                    node.getChildList().clear();
                } else if (node.getData().length() == 1 && isSpecialCharacterNonBracket(node.getData().charAt(0))) {

                    //外部的特殊字符，最好加上 \ 方便处理
                    node.setData("\\" + node.getData());
                    node.getChildList().clear();
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.add(node.getChild(i));
                }
            }
        }
        return createReDoSTree(getLeafsData());
    }


    /**
     * 将节点的 \xff 十六进制转为字符
     */
    public void escapeHexadecimal() throws InterruptedException {
        Matcher matcher_1 = PATTERN_ESCAPE_HEXADECIMAL_REGEX_1.matcher(this.data);
        Matcher matcher_2 = PATTERN_ESCAPE_HEXADECIMAL_REGEX_2.matcher(this.data);
        Matcher matcher_3 = PATTERN_ESCAPE_HEXADECIMAL_REGEX_3.matcher(this.data);
        if (!matcher_1.find() && !matcher_2.find() && !matcher_3.find()) {
            return;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            String nodeData = node.getData();
            if (node.isLeaf()) {
                if (nodeData.matches(ESCAPE_HEXADECIMAL_REGEX_1)) {
                    nodeData = nodeData.replace("\\x", "\\u00");
//                    if ("\\u0000".equals(nodeData)) {
//                        nodeData = "\\u0001";
//                    }
                    nodeData = unicodeToCn(nodeData);
                    node.updateTreeByModifyNode(nodeData);
                    continue;
                }
                if (nodeData.matches(ESCAPE_HEXADECIMAL_REGEX_2)) {
                    nodeData = nodeData.replace("\\x{", "\\u").replace("}", "");
//                    if ("\\u0000".equals(nodeData)) {
//                        nodeData = "\\u0001";
//                    }
                    nodeData = unicodeToCn(nodeData);
                    node.updateTreeByModifyNode(nodeData);
                }
            } else if (nodeData.matches(ESCAPE_HEXADECIMAL_REGEX_3)) {
                String back = String.valueOf(Integer.parseInt(nodeData.substring(1, 2)) * 64 + Integer.parseInt(nodeData.substring(2, 3)) * 8 + Integer.parseInt(nodeData.substring(3, 4)));
                if (back.length() == 2) {
                    nodeData = "\\u00" + back;
                }
                if (back.length() == 1) {
                    nodeData = "\\u000" + back;
                }
                if (back.length() == 3) {
                    nodeData = "\\u0" + back;
                }
//                if ("\\u0000".equals(nodeData)) {
//                    nodeData = "\\u0001";
//                }
                nodeData = unicodeToCn(nodeData);
                node.updateTreeByModifyNode(nodeData);
            } else {
                queue.addAll(node.getChildList());
            }
        }
    }

    public int getLevel() {
        if (this.isRoot()) {
            return 0;
        } else {
            return parent.getLevel() + 1;
        }
    }


    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }

    public String getText() {
        return toString();
    }


    @Override
    public Iterator<TreeNode> iterator() {
        TreeNodeIter iter = new TreeNodeIter(this);
        return iter;
    }

    // 通过链式索引返回结点的值
    public String getChildDataByChainIndexString(String chainIndexString) {
        String[] chainIndexStringList = chainIndexString.split("\\.");
        List<Integer> chainIndexIntList = new ArrayList<>();
        for (String i : chainIndexStringList) {
            chainIndexIntList.add(Integer.parseInt(i));
        }
        TreeNode root = this.getRoot();
        for (int i = 1; i < chainIndexIntList.size(); i++) {
            root = root.getChild(chainIndexIntList.get(i));
        }
        return root.data;
    }

    // 通过链式索引返回结点
    public TreeNode getChildByChainIndexString(String chainIndexString) {
        String[] chainIndexStringList = chainIndexString.split("\\.");
        List<Integer> chainIndexIntList = new ArrayList<>();
        for (String i : chainIndexStringList) {
            chainIndexIntList.add(Integer.parseInt(i));
        }
        TreeNode root = this.getRoot();
        for (int i = 1; i < chainIndexIntList.size(); i++) {
            root = root.getChild(chainIndexIntList.get(i));
        }
        return root;
    }


    // 通过索引添加孩子 默认为将当前结点作为根结点 参数为要添加的结点，索引链 比如0.3.2，0表示要添加的结点，3表示其下标为3的孩子,2表示其下标为3的孩子的下标为2的孩子
    public void insertChildByChainIndexString(String child, String chainIndexString) throws InterruptedException {
        String[] chainIndexStringList = chainIndexString.split("\\.");
        List<Integer> chainIndexIntList = new ArrayList<>();
        for (String i : chainIndexStringList) {
            chainIndexIntList.add(Integer.parseInt(i));
        }
        TreeNode root = this;
//		System.out.println(root.data);
        for (int i = 1; i < chainIndexIntList.size() - 1; i++) {
            root = root.getChild(chainIndexIntList.get(i));
//			System.out.println(root.data);
        }

        try {
            root.insertChild(child, chainIndexIntList.get(chainIndexIntList.size() - 1));
        } catch (Exception e) {
            root.insertChild(child, root.getChildCount());
        }
    }

    // 通过索引更新孩子 默认为将当前结点作为根结点 参数为要更新的结点，索引链 比如0.3.2，0表示要添加的结点，3表示其下标为3的孩子,2表示其下标为3的孩子的下标为2的孩子
    public void updateChildByChainIndexString(String child, String chainIndexString) throws InterruptedException {
        String[] chainIndexStringList = chainIndexString.split("\\.");
        List<Integer> chainIndexIntList = new ArrayList<>();
        for (String i : chainIndexStringList) {
            chainIndexIntList.add(Integer.parseInt(i));
        }
        TreeNode root = this;
//		System.out.println(root.data);
        for (int i = 1; i < chainIndexIntList.size() - 1; i++) {
            root = root.getChild(chainIndexIntList.get(i));
//			System.out.println(root.data);
        }

        try {
            root.updateChild(child, chainIndexIntList.get(chainIndexIntList.size() - 1));
        } catch (Exception e) {
            root.updateChild(child, root.getChildCount());
        }
    }

    // 通过索引删除孩子 默认为将当前结点作为根结点 参数索引链 比如0.3.2，0表示要删除的结点，3表示其下标为3的孩子,2表示其下标为3的孩子的下标为2的孩子
    public void deleteChildByChainIndexString(String chainIndexString) {
        String[] chainIndexStringList = chainIndexString.split("\\.");
        List<Integer> chainIndexIntList = new ArrayList<>();
        for (String i : chainIndexStringList) {
            chainIndexIntList.add(Integer.parseInt(i));
        }
        TreeNode root = this;
        for (int i = 1; i < chainIndexIntList.size() - 1; i++) {
            root = root.getChild(chainIndexIntList.get(i));
        }

        try {
            root.deleteChild(chainIndexIntList.get(chainIndexIntList.size() - 1));
        } catch (Exception e) {
            root.deleteChild(root.getChildCount());
        }
    }


    // 判断 - 是否为[a-c]中的横线 注意[-a-c]中第一个横线不是 第二个横线是
    // warning: [+-\/]
    public boolean isConnectorInBracketNode() {
        if (!isInBrackets(this) || !this.getData().equals("-") || this.getParent() == null) {
            return false;
        }
        if (this.getParent().getChildCount() == 3) {
            return this.getParent().getChild(1).getData().equals("-");
        }
        return false;
    }


    // 计算flexible中用到的N
    public int getFlexibleN() {
        //                 其中 N 计算过程为：首先找到距离最近的父连接结点 如果找到了 判断除所在父连接结点外是否全部结点的nullable为true
        //                                  若是 则迭代继续向上找 直到找到不符合的父连接结点或 直到根结点 为止
        //                                  若不是 则 N = 父连接结点到counting结点(不包括counting) 所经过的所有的{m,n}中所有n相乘
        //
        //                     例如 ((a[4,4])[2,3].b+)[4,5] 中 对于a[4,4] 其最近的父连接结点为(a[4,4])[2,3]
        //                     除(a[4,4])[2,3]外, b+的nullable为false 所以 N = 3
        //                     例如 ((a[4,4])[2,3].b?)[4,5] 中 对于a[4,4] 其最近的父连接结点为(a[4,4])[2,3]
        //                     除(a[4,4])[2,3]外, b?的nullable为true 所以迭代寻找 到根结点为止 所以 N = 3 * 5 = 15
        //                     若出现n为∞的情况 则 N = -1, (N×n)/(N×n-1) = n/(n+1) < 1 肯定成立
        int resultN = 1;
        TreeNode node = this;
        while (!node.isRoot()) {
            TreeNode child = node;
            node = node.getParent();
            // 如果是连接结点
            if (!node.isRoot() && !isBracketsNode(node) && !isNegateNode(node) && !isZeroWidthAssertionNode(node) && !node.isLeaf() && !isGroupNode(node) && !isGeneralizedCountingNode(node) && !isOrNode(node)) {
                // 现在node是连接结点 child是node的孩子结点中的this结点的(祖)父结点

                // 判断除了所在父结点外 同一层的其他结点nullable是否都为true
                boolean allOtherNodesNullableIsTrue = true;
                for (int i = 0; i < node.getChildCount(); i++) {
                    if (node.getChild(i) == child) continue;
                    if (node.getChild(i).getNullable() == 0) {
                        allOtherNodesNullableIsTrue = false;
                        break;
                    }
                }
                // 若不是 则 N = 父连接结点到counting结点(不包括counting) 所经过的所有的{m,n}中所有n相乘
                if (!allOtherNodesNullableIsTrue) {
                    TreeNode node1 = this.getParent();
                    while (node1 != node) {
                        if (isGeneralizedCountingNode(node1) && !isGroupNode(node1)) {
                            int countingSecondNum = getCountingSecondNum(node1.getChild(1).getData());
                            if (countingSecondNum != -1) {
                                resultN *= countingSecondNum;
                            } else {
                                return -1;
                            }
                        }
                        node1 = node1.getParent();
                    }
                    return resultN;
                }
            }
        }
        // 找不到 直到根节点了
        node = this.getParent();
        if (node == null) return resultN;
        while (!node.isRoot()) {
            if (isGeneralizedCountingNode(node) && !isGroupNode(node)) {
                int countingSecondNum = getCountingSecondNum(node.getChild(1).getData());
                if (countingSecondNum != -1) {
                    resultN *= countingSecondNum;
                } else {
                    return -1;
                }
            }
            node = node.getParent();
        }
        // 如果根节点也是个counting 得算一下
        if (isGeneralizedCountingNode(node) && !isGroupNode(node)) {
            int countingSecondNum = getCountingSecondNum(node.getChild(1).getData());
            if (countingSecondNum != -1) {
                resultN *= countingSecondNum;
            } else {
                return -1;
            }
        }
        return resultN;
    }


    // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
    public void rewriteIllegalBarSymbol() throws InterruptedException {
        if (!this.getData().contains("-")) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isBracketsNode(node) || isNegateNode(node)) {
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                if (isBracketsNode(node)) {
                    stringBuilder.append("[");
                    i = 1;
                } else {
                    stringBuilder.append("[^");
                    i = 2;
                }
                for (; i < node.getChildCount() - 1; i++) {
                    if (node.getChild(i).getData().equals("-") && i != 1 && i != node.getChildCount() - 2) {
                        if (node.getChild(i - 1).getData().length() > 1 || node.getChild(i + 1).getData().length() > 1) {
                            stringBuilder.append("\\-");
                        } else if (node.getChild(i - 1).getData().charAt(0) > node.getChild(i + 1).getData().charAt(0)) {
                            stringBuilder.append("\\-");
                        } else {
                            stringBuilder.append(node.getChild(i).getData());
                        }
                    } else if (isGeneralizedCollectionSymbol(node.getChild(i))) {
                        if (node.getChild(i).getFirstChild().getData().charAt(0) > node.getChild(i).getLastChild().getData().charAt(0)) {
                            stringBuilder.append(node.getChild(i).getFirstChild() + "\\-" + node.getChild(i).getLastChild());
                        } else {
                            stringBuilder.append(node.getChild(i).getData());
                        }
                    } else {
                        stringBuilder.append(node.getChild(i).getData());
                    }
                }
                stringBuilder.append("]");
                node.updateTreeByModifyNode(stringBuilder.toString());
            } else {
                queue.addAll(node.getChildList());
            }
        }
    }

    // 优化方括号结点, 将内部重复的字符删掉
    // 这里假设结点内部不会嵌套方括号结点/补结点
    public void optimizeBracketNode() throws InterruptedException {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isBracketsNode(node) || isNegateNode(node)) {
                Set<String> set = new HashSet<>();
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                if (isBracketsNode(node)) {
                    stringBuilder.append("[");
                    i = 1;
                } else {
                    stringBuilder.append("[^");
                    i = 2;
                }
                for (; i < node.getChildCount() - 1; i++) {
                    set.add(node.getChild(i).getData());
                }
                for (String s: set) {
                    if (s.equals("-")) {
                        stringBuilder.append("\\-");
                    } else {
                        stringBuilder.append(s);
                    }
                }
                stringBuilder.append("]");
                node.updateTreeByModifyNode(stringBuilder.toString());
            } else {
                queue.addAll(node.getChildList());
            }
        }
    }

    // 有默认全集的计算nullable、first、last、flexible、followLast
    public void calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast() throws InterruptedException {
        calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast(new HashSet<>());
    }

    // 计算nullable、first、last、flexible、followLast
    public void calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast(Set<String> rootAlphabet) throws InterruptedException {
        // 为处理补结点的问题, 首先获取树的字母表Σ, getLetterSet(true)

        // 按照后序遍历计算nullable
        // 若为方括号结点一律nullable算false
        // 若为补结点isNegateNode一律nullable算false
        // 在叶结点中出现的*?+{m,n}都是实意counting 应该设为无效值
        // 在叶结点中出现的[] 第一个和最后一个视为方括号的[] 应该设为无效值 若第二个节点为^ 也设为无效值 其他的按普通结点对待 其实这里隐藏了[a-z]中的-也应设为无效值
        // 在叶结点中出现的| 设为无效值
        // 在叶结点中出现的零宽断言结点 设为无效值
        // 若为counting结点 若isZeroNode返回true则nullable为true 否则等于getChild(0)的nullable
        // 若为或结点 则对其所有的孩子结点 跳过无效值 求并集 若并集中含有true 则为true 否则为false
        // 若为group结点 则nullable 为其孩子结点的nullable
        // 若为连接结点 则对其所有的孩子结点 跳过无效值 求并集 若并集中含有false 则为false 否则为true
        // 若为零宽断言结点isZeroWidthAssertionNode 设为无效值

        // 按后序遍历计算first
        // 若为方括号结点 将第一个结点[ 和 最后一个结点] 的first设为无效值∅ 对所有孩子结点的first集求并集
        // 若为补集结点 将第一个结点[ 第二个结点^ 和 最后一个结点] 的first设为无效值∅ 对所有孩子结点的first集求并集 然后用Σ减并集
        // 在叶结点中出现的*?+{m,n}都是实意counting 应设为无效值 在这里可设为∅
        // 在叶结点中出现的[] 第一个和最后一个视为方括号的[] 应该设为无效值∅
        //                 注意在[]内可能出现[\s]这种写法 要特别处理
        //                  若出现 - 则需判断是否属于a-c中的横杠 如果是 则设为无效值∅ 否则当普通结点对待
        // 在叶结点中出现的| 设为无效值∅
        // 在叶结点中出现的\w \s \d 和 . 按照各自的对应添加first 注意.是不匹配\r\n的  斜杠u000d是斜杠r 斜杠u000a是斜杠n
        // 在叶节点中出现的\W \S \D 按照Σ减\w \s \d的方式添加first
        // 在叶结点中出现零宽断言 设为无效值
        // 若为counting结点 则其first集直接为getChild(0)的first
        // 若为或结点 则对其所有的孩子结点 跳过无效值 求并集
        // 若为group结点 则first集直接为其孩子结点的first集
        // 若为连接结点 若是在bracketNode中的连接结点[a-c]中的a-c 使用BracketUtils.isGeneralizedCollectionSymbol 若是 则使用BracketUtils.getLetterSet
        //            否则对其所有的孩子结点 从左到右找到第一个nullable为false的孩子 则包括该孩子在内的 前面所有的孩子的first求并集
        //                                若找不到 则把所有的孩子的first集求并集
        // 若为零宽断言结点isZeroWidthAssertionNode 设为无效值

        // 按后续遍历计算last
        // 若为方括号结点 将第一个结点[ 和最后一个结点] 的last设为无效值∅ 对所有孩子结点的last集求并集
        // 若为补集结点 将第一个结点[ 第二个结点^ 和最后一个结点] 的last设为无效值∅ 对所有孩子结点的last集求并集 然后用Σ减并集
        // 在叶结点中出现的*?+{m,n}都是实意counting 应设为无效值 在这里可设为∅
        // 在叶结点中出现的[] 第一个和最后一个视为方括号的[] 应设为无效值 这里可设为∅
        //                 注意在[]内可能出现[\s]这种写法 要特别处理
        //                  若出现 - 则需判断是否属于a-c中的横杠 如果是 则设为无效值∅ 否则当普通结点对待
        // 在叶结点中出现的| 设为无效值∅
        // 在叶结点中出现的\w \s \d 和 . 按照各自的对应添加last 注意.是不匹配\r\n的 斜杠u000d是斜杠r 斜杠u000a是斜杠n
        // 在叶节点中出现的\W \S \D 按照Σ减\w \s \d的方式添加last
        // 在叶结点中出现零宽断言 设为无效值
        // 若为counting结点 则其last集直接为getChild(0)的last
        // 若为或结点 则对其所有的孩子结点 跳过无效值 求并集
        // 若为group结点 则last集直接为其孩子结点的last集
        // 若为连接结点 若是在bracketNode中的连接结点[a-c]中的a-c 使用BracketUtils.isGeneralizedCollectionSymbol 若是 则使用BracketUtils.getLetterSet
        //            否则对其所有的孩子结点 从右往左找到第一个nullable为false的孩子 则包括该孩子在内的 往右全部的孩子的last求并集
        //                                若找不到 则把所有的孩子的last集求并集
        // 若为零宽断言结点isZeroWidthAssertionNode 设为无效值

        // 按后序遍历计算dFlexible和bFlexible
        // 递推公式见doi:10.1016/j.ic.2006.12.003中定义4.1 按Lu Ping的代码 若为F = GH, 其中G和H均为可空 则F.dFlexible = -1; 意为∞
        // 迭代计算伪代码见10.1016/j.is.2010.10.001中图2 和Lu Ping的代码对应上了
        // 若为方括号结点 将第一个结点[ 和最后一个结点] 的dFlexible和bFlexible置为无效值 这里可置为-2和false
        //              对所有孩子结点的dFlexible求最大值 bFlexible为false
        // 若为补集结点 将第一个结点[ 第二个结点^ 和最后一个结点] 的dFlexible和bFlexible置为无效值
        //              对所有孩子结点的dFlexible求最大值 bFlexible为false
        // 在叶结点中出现*?+{m,n}都是实意counting dFlexible和bFlexible都设为无效值 这里可设为-2和false
        // 在叶结点中出现的[] 第一个和最后一个视为方括号的[] 应设为无效值 这里可设为-2和false
        //                  若出现 - 则需判断是否属于a-c中的横杠 如果是 则设为无效值-2和false 否则当成普通结点对待 设为1和false
        // 在叶结点中出现的| 设为无效值-2和false
        // 在叶结点中出现的\w \s \d \W \S \D 和 . bFlexible和dFlexible设为1和false
        // 在叶结点中出现零宽断言 设为无效值
        // 若为counting结点 则判断{m,n}中 如果 (1)m < n, 或 (2)getChild(0).dFlexible ≥ (N×n)/(N×n-1). 则bFlexible = true, 否则bFlexible = false
        //                 dFlexible = n/m × getChild(0).dFlexible
        //                 其中 N 计算过程为：首先找到距离最近的父连接结点 如果找到了 判断除所在父连接结点外是否全部结点的nullable为true
        //                                  若是 则迭代继续向上找 直到找到不符合的父连接结点或 直到根结点 为止
        //                                  若不是 则 N = 父连接结点到counting结点(不包括counting) 所经过的所有的{m,n}中所有n相乘
        //
        //                     例如 ((a[4,4])[2,3].b+)[4,5] 中 对于a[4,4] 其最近的父连接结点为(a[4,4])[2,3]
        //                     除(a[4,4])[2,3]外, b+的nullable为false 所以 N = 3
        //                     例如 ((a[4,4])[2,3].b?)[4,5] 中 对于a[4,4] 其最近的父连接结点为(a[4,4])[2,3]
        //                     除(a[4,4])[2,3]外, b?的nullable为true 所以迭代寻找 到根结点为止 所以 N = 3 * 5 = 15
        //                     若出现n为∞的情况 则 N = -1, (N×n)/(N×n-1) = n/(n+1) < 1 肯定成立
        //                 使用calculateN函数进行计算 注意这个函数需要在所有结点的nullable都计算出来后才能用 因此这棵树至少要遍历两次
        //                 需要注意 若counting值为?或{0,1} 则bFlexible = false, dFlexible = -1 表示∞
        //                 若出现{m,n}中m = 0, n ≠ 1 或 getChild(0).dFlexible = -1 则 bFlexible按上述公式判断, dFlexible = -1
        // 若为或结点 则对其所有孩子结点的dFlexible求最大值 bFlexible = false
        // 若为group结点 则dFlexible和bFlexible均为其孩子的对应值
        // 若为连接结点 判断是否在方括号内 若是 则对首[和]设为bFlexible = false, dFlexible = -2, 其他结点设为bFlexible = false, dFlexible = 1
        //            否则对其所有的孩子结点 有一个nullable = false的Child(i) 则bFlexible = false, dFlexible = getChild(i).dFlexible,
        //                                有两个及以上nullable = false的Child(i)和Child(j) 则bFlexible = false, dFlexible = 1,
        //                                不存在nullable = false的Child(i) 则bFlexible = false, dFlexible = -1.
        // 若为零宽断言结点isZeroWidthAssertionNode 设为无效值


        // 后序遍历计算followLast
        // 若为方括号结点 将第一个结点[ 和最后一个结点] 的followLast置为无效值 这里可置为∅
        //              对所有孩子结点的followLast求并集
        // 若为补集结点   将第一个结点[ 第二个结点^ 和 最后一个结点] 的followLast置为无效值 这里可置为∅
        //              对所有孩子结点的followLast求并集
        // 在叶结点中出现*?+{m,n}都是实意counting followLast设为无效值∅
        // 在叶结点中出现的[] 第一个和最后一个视为方括号的[] 应设为无效值∅
        //                  若出现 - 则需判断是否属于a-c中的横杠 如果是 则设为无效值∅ 否则当成普通结点对待 设为∅
        // 在叶结点中出现的| 设为无效值∅
        // 在叶结点中出现的\w \s \d \W \S \D 和 . 均设为∅
        // 在叶结点中出现零宽断言设为无效值
        // 若为counting结点 若其bFlexible = true, 则为getChild(0).getFollowLast() ∪ getChild(0).getFirst()
        //                 否则为getChild(0).getFollowLast()
        // 若为或结点 则对其所有的孩子结点的followLast求并集
        // 若为group结点 则followLast集直接为其孩子结点的followLast集
        // 若为连接结点 判断是否在方括号内 若是 则对首[和]设为followLast = ∅, 其他结点设为followLast = ∅
        //            否则从右往左找到第一个nullable为false的结点 若存在 记为child(i) 最后一个孩子结点记为child(n)
        //            该结点的followLast为 getChild(i).getFollowLast() ∪ ... ∪ getChild(n).getFollowLast() ∪ getChild(i+1).getFirst() ∪ ... ∪ getChild(n).getFirst()
        //                               若不存在 则上式中i = 0 依然成立
        // 若为零宽断言结点isZeroWidthAssertionNode 设为无效值


        // 统一修改 在方括号内的符号 如果是SPECIAL_CHARACTERS_BRACKET中的 则需要先加斜杠

        if (rootAlphabet.isEmpty()) {
            // 为处理补结点的问题, 首先获取树的字母表Σ, getLetterSet(true)
            TreeNode treeNodeCopy = SerializationUtils.clone(this);    // 深拷贝
            // 去补
            removeNegateSymbol(treeNodeCopy, Constant.SimplyLevel.HIGH);
            // 删除零宽断言
            deleteZeroWidthAssertion(treeNodeCopy);
            // 使用重写后的去首尾^$
            treeNodeCopy.deleteCaretAndDollarSymbols();
            // 新版重写空串
//            treeNodeCopy = removeBlankStr(treeNodeCopy);
            treeNodeCopy.removeBlankStr();
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            treeNodeCopy.deleteNonCapturingGroupFlag();


            rootAlphabet = treeNodeCopy.getLetterSet(true,true);
        }

        Stack<TreeNode> auxiliaryStack = new Stack<>();
        Stack<TreeNode> nodeStackForNullableAndFirstAndLast = new Stack<>();
        Stack<TreeNode> nodeStackForFlexibleAndFollowLast = new Stack<>();
        auxiliaryStack.push(this);
        while (!auxiliaryStack.isEmpty()) {
            TreeNode tmp = auxiliaryStack.pop();
            nodeStackForNullableAndFirstAndLast.push(tmp);
            nodeStackForFlexibleAndFollowLast.push(tmp);
            for (int i = 0; i < tmp.getChildCount(); i++) {
                auxiliaryStack.push(tmp.getChild(i));
            }
        }
        // 后续遍历计算nullable first last
        while (!nodeStackForNullableAndFirstAndLast.isEmpty()) {
            TreeNode node = nodeStackForNullableAndFirstAndLast.pop();
            if (isZeroWidthAssertionNode(node)) {
                node.setNullable(-1);   // 置为无效值-1
                node.setFirst(new HashSet<>()); // 置为无效值∅
                node.setLast(new HashSet<>());  // 置为无效值∅

                if (isLookAroundNode(node)) {
                    if (node.getData().startsWith("(?=") || node.getData().startsWith("(?!")) {
                        for (int i = 0; i <= 2; i++) {
                            node.getChild(i).setNullable(-1);
                            node.getChild(i).setFirst(new HashSet<>());
                            node.getChild(i).setLast(new HashSet<>());
                        }
                        node.getLastChild().setNullable(-1);
                        node.getLastChild().setFirst(new HashSet<>());
                        node.getLastChild().setLast(new HashSet<>());
                    } else if (node.getData().startsWith("(?<=") || node.getData().startsWith("(?<!")) {
                        for (int i = 0; i <= 3; i++) {
                            node.getChild(i).setNullable(-1);
                            node.getChild(i).setFirst(new HashSet<>());
                            node.getChild(i).setLast(new HashSet<>());
                        }
                        node.getLastChild().setNullable(-1);
                        node.getLastChild().setFirst(new HashSet<>());
                        node.getLastChild().setLast(new HashSet<>());
                    }
                }
                continue;
            }
            if (isBracketsNode(node)) {
                node.setNullable(0);    // 设置为false

                node.getFirstChild().setFirst(new HashSet<>()); // 把第一个孩子结点的first设为∅
                node.getLastChild().setFirst(new HashSet<>());  // 把最后一个孩子结点的first设为∅
                Set<String> auxiliaryFirstSet = new HashSet<>();
                for (int i = 1; i < node.getChildCount() - 1; i++) {    // 除掉第一个和最后一个求并集
//                    System.out.println(node.getChild(i).getData());
                    auxiliaryFirstSet.addAll(node.getChild(i).getFirst());
                }
                node.setFirst(auxiliaryFirstSet);    // 对所有孩子结点的first集求并集

                node.getFirstChild().setLast(new HashSet<>());  // 把第一个孩子结点的last设为∅
                node.getLastChild().setLast(new HashSet<>());   // 把最后一个孩子结点的last设为∅
                Set<String> auxiliaryLastSet = new HashSet<>();
                for (int i = 1; i < node.getChildCount() - 1; i++) {    // 除掉第一个和最后一个求并集
                    auxiliaryLastSet.addAll(node.getChild(i).getLast());
                }
                node.setLast(auxiliaryLastSet);     // 对所有孩子结点的last集求并集
                continue;
            }
            if (isNegateNode(node)) {
                node.setNullable(0);    // 设置为false

                node.getFirstChild().setFirst(new HashSet<>());     // 把第一个孩子结点的first设为∅
                node.getSecondChild().setFirst(new HashSet<>());    // 把第二个孩子结点的first设为∅
                node.getLastChild().setFirst(new HashSet<>());      // 把最后一个孩子结点的first设为∅
                Set<String> auxiliaryFirstSet = new HashSet<>();
                for (int i = 2; i < node.getChildCount() - 1; i++) {    // 除掉第一个第二个和最后一个求并集
                    auxiliaryFirstSet.addAll(node.getChild(i).getFirst());  // 对所有孩子结点的first集求并集
                }
                Set<String> differenceSet = new HashSet<>(rootAlphabet);
                differenceSet.removeAll(auxiliaryFirstSet);                 // 用Σ减并集
                node.setFirst(differenceSet);

                node.getFirstChild().setLast(new HashSet<>());  // 把第一个孩子结点的last设为∅
                node.getSecondChild().setLast(new HashSet<>()); // 把第二个孩子结点的last设为∅
                node.getLastChild().setLast(new HashSet<>());   // 把最后一个孩子结点的last设为∅
                Set<String> auxiliaryLastSet = new HashSet<>();
                for (int i = 2; i < node.getChildCount() - 1; i++) {    // 除掉第一个第二个和最后一个求并集
                    auxiliaryLastSet.addAll(node.getChild(i).getLast());    // 对所有孩子结点的last集求并集
                }
                differenceSet = new HashSet<>(rootAlphabet);
                differenceSet.removeAll(auxiliaryLastSet);                 // 用Σ减并集
                node.setLast(differenceSet);
                continue;
            }
            if (node.isLeaf()) {
                String data = node.getData();
                if (data.length() == 2) {
                    if (!(data.equals("\\w") || data.equals("\\d") || data.equals("\\s") || data.equals("\\W") || data.equals("\\D") || data.equals("\\S"))) {
                        data = data.substring(1, 2);
                    }
                }
//                if (isSpecialCharacterBracket(data)) {
//                    data = "\\" + data;
//                }
                node.setFirst(new HashSet<>(Collections.singletonList(data)));  // 先按照普通结点来看 置为当前结点
                node.setLast(new HashSet<>(Collections.singletonList(data)));   // 先按照普通结点来看 置为当前结点

                if ((isInBrackets(node) || isInNegateNode(node)) &&
                        !node.getData().equals("\\w") &&
                        !node.getData().equals("\\d") &&
                        !node.getData().equals("\\s") &&
                        !node.getData().equals("\\W") &&
                        !node.getData().equals("\\D") &&
                        !node.getData().equals("\\S")) {
                    node.setNullable(0);    // 置为false
                    if (node.getData().equals("[") && node.isFirstChild()) {
                        if (node.getParent().getLastChild().getData().equals("]")) {
                            node.setNullable(-1);   // 置为无效值-1
                            node.setFirst(new HashSet<>()); // 置为无效值∅
                            node.setLast(new HashSet<>());  // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("]") && node.isLastChild()) {
                        if (node.getParent().getFirstChild().getData().equals("[")) {
                            node.setNullable(-1);   // 置为无效值-1
                            node.setFirst(new HashSet<>()); // 置为无效值∅
                            node.setLast(new HashSet<>());  // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("^") && node.isSecondChild()) {
                        if (node.getParent().getFirstChild().getData().equals("[") && node.getParent().getLastChild().getData().equals("]")) {
                            node.setNullable(-1);   // 置为无效值-1
                            node.setFirst(new HashSet<>()); // 置为无效值∅
                            node.setLast(new HashSet<>());  // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("-") && node.isConnectorInBracketNode()) {    // 如果是在方括号内的特殊的连接符- e.g. [a-c]
                        node.setNullable(-1);   // 置为无效值-1
                        node.setFirst(new HashSet<>()); // 置为无效值∅
                        node.setLast(new HashSet<>());  // 置为无效值∅
                        continue;
                    }
                    if (node.getData().equals("\\t")) {
                        node.setFirst(Collections.singleton("\t"));
                        node.setLast(Collections.singleton("\t"));
                        continue;
                    } else if (node.getData().equals("\\f")) {
                        node.setFirst(Collections.singleton("\f"));
                        node.setLast(Collections.singleton("\f"));
                        continue;
                    } else if (node.getData().equals("\\n")) {
                        node.setFirst(Collections.singleton("\n"));
                        node.setLast(Collections.singleton("\n"));
                        continue;
                    } else if (node.getData().equals("\\v") || node.getData().equals("\\u000b")) {
                        node.setFirst(Collections.singleton("\u000b"));
                        node.setLast(Collections.singleton("\u000b"));
                        continue;
                    } else if (node.getData().equals("\\r")) {
                        node.setFirst(Collections.singleton("\r"));
                        node.setLast(Collections.singleton("\r"));
                        continue;
                    }
                } else if (isZeroWidthAssertionNode(node)) {
                    node.setNullable(-1);   // 置为无效值
                    node.setFirst(new HashSet<>()); // 置为无效值∅
                    node.setLast(new HashSet<>());  // 置为无效值∅
                    continue;
                } else if (Pattern.matches(ALL_COUNTING, node.getData())) {
                    node.setNullable(-1);   // 置为无效值
                    node.setFirst(new HashSet<>()); // 置为无效值∅
                    node.setLast(new HashSet<>());  // 置为无效值∅
                    continue;
                } else if (node.getData().equals("|")) {
                    node.setNullable(-1);
                    node.setFirst(new HashSet<>()); // 置为无效值∅
                    node.setLast(new HashSet<>());  // 置为无效值∅
                    continue;
                } else {
                    node.setNullable(0);

                    if (node.getData().equals("\\w")) {
                        Set<String> auxiliarySet = new HashSet<>(Arrays.asList(w_MATCH_CHARACTER_LIST));
                        node.setFirst(auxiliarySet);
                        node.setLast(auxiliarySet);
                    } else if (node.getData().equals("\\d")) {
                        Set<String> auxiliarySet = new HashSet<>(Arrays.asList(d_MATCH_CHARACTER_LIST));
                        node.setFirst(auxiliarySet);
                        node.setLast(auxiliarySet);
                    } else if (node.getData().equals("\\s")) {
//                        Set<String> auxiliarySet = new HashSet<>(Arrays.asList(s_MATCHE_CHARACTER));
                        Set<String> auxiliarySet = new HashSet<>();
                        auxiliarySet.add(" ");
                        auxiliarySet.add("\t");
                        auxiliarySet.add("\f");
                        auxiliarySet.add("\r");
                        auxiliarySet.add("\n");
                        auxiliarySet.add("\u000b");
                        node.setFirst(auxiliarySet);
                        node.setLast(auxiliarySet);
                    } else if (node.getData().equals(".")) {
//                    node.setFirst(dot_MATCH_CHARACTER);
                        // 这里做了个优化
                        // 如果根节点获取getLetterSet为空 则说明是全集 则使用dot_MATCH_CHARACTER
                        // 否则按getLetterSet来算
                        // 如果输入.*这种只有.的 则默认添加个符号!
                        Set<String> auxiliarySet = new HashSet<>(rootAlphabet);
                        // .删除\n和\r并强制加入!
                        auxiliarySet.remove("\\n");
                        auxiliarySet.remove("\\r");
                        if (auxiliarySet.contains("\\t")) {
                            auxiliarySet.remove("\\t");
                            auxiliarySet.add("\t");
                        }
                        if (auxiliarySet.contains("\\f")) {
                            auxiliarySet.remove("\\f");
                            auxiliarySet.add("\f");
                        }
                        if (auxiliarySet.contains("\\v") || auxiliarySet.contains("\\u000b")) {
                            auxiliarySet.remove("\\v");
                            auxiliarySet.remove("\\u000b");
                            auxiliarySet.add("\u000b");
                        }
                        auxiliarySet.add("!");
                        node.setFirst(auxiliarySet);

//                    node.setLast(dot_MATCH_CHARACTER);
                        // 这里做了个优化
                        // 如果根节点获取getLetterSet为空 则说明是全集 则使用dot_MATCH_CHARACTER
                        // 否则按getLetterSet来算
                        node.setLast(auxiliarySet);
                    } else if (node.getData().equals("\\W")) {
                        Set<String> auxiliarySet = new HashSet<>(Arrays.asList(w_MATCH_CHARACTER_LIST));
                        Set<String> differenceSet = new HashSet<>(rootAlphabet);
                        differenceSet.removeAll(auxiliarySet);                 // 用Σ减并集
                        node.setFirst(differenceSet);
                        node.setLast(differenceSet);
                    } else if (node.getData().equals("\\D")) {
                        Set<String> auxiliarySet = new HashSet<>(Arrays.asList(d_MATCH_CHARACTER_LIST));
                        Set<String> differenceSet = new HashSet<>(rootAlphabet);
                        differenceSet.removeAll(auxiliarySet);                 // 用Σ减并集
                        node.setFirst(differenceSet);
                        node.setLast(differenceSet);
                    } else if (node.getData().equals("\\S")) {
                        Set<String> auxiliarySet = new HashSet<>(Arrays.asList(s_MATCH_CHARACTER_LIST));
                        Set<String> differenceSet = new HashSet<>(rootAlphabet);
                        differenceSet.removeAll(auxiliarySet);                 // 用Σ减并集
                        node.setFirst(differenceSet);
                        node.setLast(differenceSet);
                    } else if (node.getData().equals("\\t")) {
                        node.setFirst(Collections.singleton("\t"));
                        node.setLast(Collections.singleton("\t"));
                    } else if (node.getData().equals("\\f")) {
                        node.setFirst(Collections.singleton("\f"));
                        node.setLast(Collections.singleton("\f"));
                    } else if (node.getData().equals("\\n")) {
                        node.setFirst(Collections.singleton("\n"));
                        node.setLast(Collections.singleton("\n"));
                    } else if (node.getData().equals("\\v") || node.getData().equals("\\u000b")) {
                        node.setFirst(Collections.singleton("\u000b"));
                        node.setLast(Collections.singleton("\u000b"));
                    } else if (node.getData().equals("\\r")) {
                        node.setFirst(Collections.singleton("\r"));
                        node.setLast(Collections.singleton("\r"));
                    }
                }
                continue;
            }
            if (isGroupNode(node)) {
                node.getFirstChild().setNullable(-1);   // 置为无效值-1
                node.getFirstChild().setFirst(new HashSet<>()); // 置为无效值∅
                node.getFirstChild().setLast(new HashSet<>());  // 置为无效值∅
                node.getLastChild().setNullable(-1);   // 置为无效值-1
                node.getLastChild().setFirst(new HashSet<>()); // 置为无效值∅
                node.getLastChild().setLast(new HashSet<>());  // 置为无效值∅
                node.setNullable(node.getChild(1).getNullable());
                node.setFirst(node.getChild(1).getFirst());
                node.setLast(node.getChild(1).getLast());
                continue;
            }
            if (isGeneralizedCountingNode(node)) {
//                System.out.println(node.getData() + " " + isZeroNode(node));
                int nullableValue = isZeroCountingNode(node) ? 1 : node.getChild(0).getNullable();
                node.setNullable(nullableValue);

                node.setFirst(node.getChild(0).getFirst());
                node.setLast(node.getChild(0).getLast());
                continue;
            }
            if (isOrNode(node)) {
                boolean trueInNodeChildrenNullableFlag = false;
                for (int i = 0; i < node.getChildCount(); i++) {
                    if (node.getChild(i).getNullable() == 1) {
                        trueInNodeChildrenNullableFlag = true;
                        break;
                    }
                }
                if (trueInNodeChildrenNullableFlag) node.setNullable(1);
                else node.setNullable(0);

                Set<String> auxiliaryFirstSet = new HashSet<>();
                for (int i = 0; i < node.getChildCount(); i++) {    // 除掉第一个和最后一个求并集
                    auxiliaryFirstSet.addAll(node.getChild(i).getFirst());
                }
                node.setFirst(auxiliaryFirstSet);    // 对所有孩子结点的first集求并集

                Set<String> auxiliaryLastSet = new HashSet<>();
                for (int i = 0; i < node.getChildCount(); i++) {    // 除掉第一个和最后一个求并集
                    auxiliaryLastSet.addAll(node.getChild(i).getLast());
                }
                node.setLast(auxiliaryLastSet);    // 对所有孩子结点的last集求并集
                continue;
            } else {    // 如果是连接结点
                boolean falseInNodeChildrenNullableFlag = false;
                for (int i = 0; i < node.getChildCount(); i++) {
                    if (node.getChild(i).getNullable() == 0) {
                        falseInNodeChildrenNullableFlag = true;
                        break;
                    }
                }
                if (falseInNodeChildrenNullableFlag) node.setNullable(0);
                else node.setNullable(1);

                if ((isInBrackets(node) || isInNegateNode(node)) && isGeneralizedCollectionSymbol(node)) {
//                    if (isGeneralizedCollectionSymbol(node)) {
                    Set<String> auxiliarySet = new HashSet<>();
                    Set<Character> letterSet = BracketUtils.getLetterSet(node.getData());
                    for (Character c : letterSet) {
                        auxiliarySet.add(c.toString());
                    }
                    node.setFirst(auxiliarySet);
                    node.setLast(auxiliarySet);
//                    }
                } else {
                    Set<String> auxiliaryFirstSet = new HashSet<>();
                    for (int i = 0; i < node.getChildCount(); i++) {
                        auxiliaryFirstSet.addAll(node.getChild(i).getFirst());
                        if (node.getChild(i).getNullable() == 0 && !isZeroWidthAssertionNode(node.getChild(i))) break;
                    }
                    node.setFirst(auxiliaryFirstSet);

                    Set<String> auxiliaryLastSet = new HashSet<>();
                    for (int i = node.getChildCount() - 1; i >= 0; i--) {
                        auxiliaryLastSet.addAll(node.getChild(i).getLast());
                        if (node.getChild(i).getNullable() == 0 && !isZeroWidthAssertionNode(node.getChild(i))) break;
                    }
                    node.setLast(auxiliaryLastSet);
                }
            }
        }


        // 后序遍历计算followLast
        // 若为方括号结点 将第一个结点[ 和最后一个结点] 的followLast置为无效值 这里可置为∅
        //              对所有孩子结点的followLast求并集
        //
        // 若为补集结点   将第一个结点[ 第二个结点^ 和最后一个结点] 的followLast置为无效值 这里可置为∅
        //              对所有孩子结点的followLast求并集
        //
        // 在叶结点中出现*?+{m,n}都是实意counting followLast设为无效值∅
        // 在叶结点中出现的[] 第一个和最后一个视为方括号的[] 应设为无效值∅
        //                  若出现 - 则需判断是否属于a-c中的横杠 如果是 则设为无效值∅ 否则当成普通结点对待 设为∅
        // 在叶结点中出现的| 设为无效值∅
        // 在叶结点中出现的\w \s \d 和 . 均设为∅
        // 在叶结点中出现零宽断言 均设为无效值
        //
        // 若为counting结点 若其bFlexible = true, 则为getChild(0).getFollowLast() ∪ getChild(0).getFirst()
        //                 否则为getChild(0).getFollowLast()
        //
        // 若为或结点 则对其所有的孩子结点的followLast求并集
        //
        // 若为group结点 则followLast集直接为其孩子结点的followLast集
        //
        // 若为连接结点 判断是否在方括号内 若是 则对首[和]设为followLast = ∅, 其他结点设为followLast = ∅
        //            否则从右往左找到第一个nullable为false的结点 若存在 记为child(i) 最后一个孩子结点记为child(n)
        //            该结点的followLast为 getChild(i).getFollowLast() ∪ ... ∪ getChild(n).getFollowLast() ∪ getChild(i+1).getFirst() ∪ ... ∪ getChild(n).getFirst()
        //                               若不存在 则上式中i = 0 依然成立
        //
        // 若为零宽断言结点isZeroWidthAssertionNode 设为无效值


        // 后续遍历计算bFlexible dFlexible followLast
        while (!nodeStackForFlexibleAndFollowLast.isEmpty()) {
            TreeNode node = nodeStackForFlexibleAndFollowLast.pop();
            if (isZeroWidthAssertionNode(node)) {
                node.setdFlexible(-2);      // 置为无效值-2
                node.setbFlexible(false);   // 置为无效值false

                node.setFollowLast(new HashSet<>());    // 置为无效值∅

                if (isLookAroundNode(node)) {
                    if (node.getData().startsWith("(?=") || node.getData().startsWith("(?!")) {
                        for (int i = 0; i <= 2; i++) {
                            node.getChild(i).setdFlexible(-2);
                            node.getChild(i).setbFlexible(false);
                            node.getChild(i).setFollowLast(new HashSet<>());
                        }
                        node.getLastChild().setdFlexible(-2);
                        node.getLastChild().setbFlexible(false);
                        node.getLastChild().setFollowLast(new HashSet<>());
                    } else if (node.getData().startsWith("(?<=") || node.getData().startsWith("(?<!")) {
                        for (int i = 0; i <= 3; i++) {
                            node.getChild(i).setdFlexible(-2);
                            node.getChild(i).setbFlexible(false);
                            node.getChild(i).setFollowLast(new HashSet<>());
                        }
                        node.getLastChild().setdFlexible(-2);
                        node.getLastChild().setbFlexible(false);
                        node.getLastChild().setFollowLast(new HashSet<>());
                    }
                }
                continue;
            }
            if (isBracketsNode(node)) {
                node.getFirstChild().setdFlexible(-2);      // 置为无效值-2
                node.getFirstChild().setbFlexible(false);   // 置为无效值false
                node.getLastChild().setdFlexible(-2);
                node.getLastChild().setbFlexible(false);

                node.getFirstChild().setFollowLast(new HashSet<>());    // 置为无效值∅
                node.getLastChild().setFollowLast(new HashSet<>());     // 置为无效值∅
                Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合

                double maxDFlexible = 1.0;
                for (int i = 1; i < node.getChildCount() - 1; i++) {
                    maxDFlexible = Math.max(maxDFlexible, node.getChild(i).getdFlexible());
                    auxiliaryFollowLastSet.addAll(node.getChild(i).getFollowLast());    // 对所有孩子的followLast求并集
                }
                node.setdFlexible(maxDFlexible);
                node.setbFlexible(false);

                node.setFollowLast(auxiliaryFollowLastSet);

                continue;
            }
            if (isNegateNode(node)) {
                node.getFirstChild().setdFlexible(-2);      // 置为无效值-2
                node.getFirstChild().setbFlexible(false);   // 置为无效值false
                node.getSecondChild().setdFlexible(-2);
                node.getSecondChild().setbFlexible(false);
                node.getLastChild().setdFlexible(-2);
                node.getLastChild().setbFlexible(false);

                node.getFirstChild().setFollowLast(new HashSet<>());    // 置为无效值∅
                node.getSecondChild().setFollowLast(new HashSet<>());   // 置为无效值∅
                node.getLastChild().setFollowLast(new HashSet<>());     // 置为无效值∅
                Set<String> auxiliaryFollowLastSet = new HashSet<>();   // 辅助集合

                double maxDFlexible = 1.0;
                for (int i = 2; i < node.getChildCount() - 1; i++) {
                    maxDFlexible = Math.max(maxDFlexible, node.getChild(i).getdFlexible());
                    auxiliaryFollowLastSet.addAll(node.getChild(i).getFollowLast());    // 对所有孩子的followLast求并集
                }
                node.setdFlexible(maxDFlexible);
                node.setbFlexible(false);

//                Set<String> differenceSet = new HashSet<>(rootAlphabet);
//                differenceSet.removeAll(auxiliaryFollowLastSet);                 // 用Σ减并集
//                node.setFollowLast(differenceSet);
                node.setFollowLast(auxiliaryFollowLastSet);

                continue;
            }
            if (node.isLeaf()) {
                if (isInBrackets(node) || isInNegateNode(node)) {
                    node.setdFlexible(1);       // 按普通结点对待
                    node.setbFlexible(false);   // 按普通结点对待

                    node.setFollowLast(new HashSet<>());    // 按普通结点对待

                    if (node.getData().equals("[") && node.isFirstChild()) {
                        if (node.getParent().getLastChild().getData().equals("]")) {
                            node.setdFlexible(-2);      // 置为无效值-2
                            node.setbFlexible(false);   // 置为无效值false

                            node.setFollowLast(new HashSet<>());    // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("]") && node.isLastChild()) {
                        if (node.getParent().getFirstChild().getData().equals("[")) {
                            node.setdFlexible(-2);      // 置为无效值-2
                            node.setbFlexible(false);   // 置为无效值false

                            node.setFollowLast(new HashSet<>());    // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("^") && node.isSecondChild()) {
                        if (node.getParent().getFirstChild().getData().equals("[") && node.getParent().getLastChild().getData().equals("]")) {
                            node.setdFlexible(-2);      // 置为无效值-2
                            node.setbFlexible(false);   // 置为无效值false

                            node.setFollowLast(new HashSet<>());    // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("-") && node.isConnectorInBracketNode()) {    // 如果是在方括号内的特殊的连接符- e.g. [a-c]
                        node.setdFlexible(-2);      // 置为无效值-2
                        node.setbFlexible(false);   // 置为无效值false

                        node.setFollowLast(new HashSet<>());    // 置为无效值∅
                        continue;
                    }
                } else if (isZeroWidthAssertionNode(node)) {
                    node.setdFlexible(-2);      // 置为无效值-2
                    node.setbFlexible(false);   // 置为无效值false

                    node.setFollowLast(new HashSet<>());    // 置为无效值∅
                    continue;
                } else if (Pattern.matches(ALL_COUNTING, node.getData())) {
                    node.setdFlexible(-2);      // 置为无效值-2
                    node.setbFlexible(false);   // 置为无效值false

                    node.setFollowLast(new HashSet<>());    // 置为无效值∅
                    continue;
                } else if (node.getData().equals("|")) {
                    node.setdFlexible(-2);      // 置为无效值-2
                    node.setbFlexible(false);   // 置为无效值false

                    node.setFollowLast(new HashSet<>());    // 置为无效值∅
                    continue;
                } else {    // 普通结点
                    node.setdFlexible(1);      // 置为1
                    node.setbFlexible(false);   // 置为false

                    node.setFollowLast(new HashSet<>());    // 置为∅
                }
                continue;
            }
            if (isGroupNode(node)) {
                node.getFirstChild().setdFlexible(-2);   // 置为无效值-2
                node.getFirstChild().setbFlexible(false); // 置为无效值false

                node.getFirstChild().setFollowLast(new HashSet<>());  // 置为无效值∅

                node.getLastChild().setdFlexible(-2);   // 置为无效值-2
                node.getLastChild().setbFlexible(false); // 置为无效值false

                node.getLastChild().setFollowLast(new HashSet<>());  // 置为无效值∅

                node.setdFlexible(node.getChild(1).getdFlexible());
                node.setbFlexible(node.getChild(1).isbFlexible());

                node.setFollowLast(node.getChild(1).getFollowLast());
                continue;
            }
            if (isGeneralizedCountingNode(node)) {
                if (node.getChild(1).getData().equals("*") || node.getChild(1).getData().equals("+")) {
                    node.setbFlexible(true);
                    node.setdFlexible(-1);

                    // 计算followLast 只根据bFlexible判断
                    if (node.isbFlexible()) {
                        Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFirst());
                        node.setFollowLast(auxiliaryFollowLastSet);
                    } else {
                        node.setFollowLast(node.getChild(0).getFollowLast());
                    }

                    continue;
                }
                if (node.getChild(0).getdFlexible() == -1) {
                    node.setbFlexible(true);
                    node.setdFlexible(-1);

                    // 计算followLast 只根据bFlexible判断
                    if (node.isbFlexible()) {
                        Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFirst());
                        node.setFollowLast(auxiliaryFollowLastSet);
                    } else {
                        node.setFollowLast(node.getChild(0).getFollowLast());
                    }

                    continue;
                }
                if (node.getChild(1).getData().equals("?") || node.getChild(1).getData().equals("{0,1}")) {
                    node.setbFlexible(false);
                    node.setdFlexible(-1);

                    // 计算followLast 只根据bFlexible判断
                    if (node.isbFlexible()) {
                        Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFirst());
                        node.setFollowLast(auxiliaryFollowLastSet);
                    } else {
                        node.setFollowLast(node.getChild(0).getFollowLast());
                    }

                    continue;
                }

                // 如果是{m,}形式
                int countingSecondNum = getCountingSecondNum(node.getChild(1).getData());
                if (countingSecondNum == -1) {
                    node.setbFlexible(true);
                    node.setdFlexible(-1);

                    // 计算followLast 只根据bFlexible判断
                    if (node.isbFlexible()) {
                        Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFirst());
                        node.setFollowLast(auxiliaryFollowLastSet);
                    } else {
                        node.setFollowLast(node.getChild(0).getFollowLast());
                    }

                    continue;
                }

                // 否则是{m,n}形式
                int countingFirstNum = getCountingFirstNum(node.getChild(1).getData());
                int flexibleN = node.getFlexibleN();    // 公式中的N
                if (countingFirstNum < countingSecondNum || node.getChild(0).getdFlexible() >= (double) (flexibleN * countingSecondNum) / (double) (flexibleN * countingSecondNum - 1)) {
                    node.setbFlexible(true);
                    node.setdFlexible((double) countingSecondNum / (double) countingFirstNum * node.getChild(0).getdFlexible());

                    // 计算followLast 只根据bFlexible判断
                    if (node.isbFlexible()) {
                        Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFirst());
                        node.setFollowLast(auxiliaryFollowLastSet);
                    } else {
                        node.setFollowLast(node.getChild(0).getFollowLast());
                    }
                } else {
                    node.setbFlexible(false);
                    node.setdFlexible((double) countingSecondNum / (double) countingFirstNum * node.getChild(0).getdFlexible());

                    // 计算followLast 只根据bFlexible判断
                    if (node.isbFlexible()) {
                        Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(0).getFirst());
                        node.setFollowLast(auxiliaryFollowLastSet);
                    } else {
                        node.setFollowLast(node.getChild(0).getFollowLast());
                    }
                }

                continue;
            }
            if (isOrNode(node)) {
                double maxDFlexible = 1.0;
                Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合

                for (int i = 0; i < node.getChildCount(); i++) {
                    maxDFlexible = Math.max(maxDFlexible, node.getChild(i).getdFlexible());
                    auxiliaryFollowLastSet.addAll(node.getChild(i).getFollowLast());
                }
                node.setdFlexible(maxDFlexible);
                node.setbFlexible(false);

                node.setFollowLast(auxiliaryFollowLastSet);

                continue;
            } else {    // 如果是连接结点
                if (isInBrackets(node)) {
                    node.setdFlexible(1);       // 按普通结点对待
                    node.setbFlexible(false);   // 按普通结点对待
                    node.setFollowLast(new HashSet<>());    // 按普通结点对待
                    if (node.getData().equals("[") && node.isFirstChild()) {
                        if (node.getParent().getLastChild().getData().equals("]")) {
                            node.setdFlexible(-2);      // 置为无效值-2
                            node.setbFlexible(false);   // 置为无效值false

                            node.setFollowLast(new HashSet<>());    // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("]") && node.isLastChild()) {
                        if (node.getParent().getFirstChild().getData().equals("[")) {
                            node.setdFlexible(-2);      // 置为无效值-2
                            node.setbFlexible(false);   // 置为无效值false

                            node.setFollowLast(new HashSet<>());    // 置为无效值∅
                            continue;
                        }
                    }
                    if (node.getData().equals("-") && node.isConnectorInBracketNode()) {    // 如果是在方括号内的特殊的连接符- e.g. [a-c]
                        node.setdFlexible(-2);      // 置为无效值-2
                        node.setbFlexible(false);   // 置为无效值false

                        node.setFollowLast(new HashSet<>());        // 置为无效值∅
                        continue;
                    }
                } else {
                    int countNullableFalseChild = 0;    // 计数nullable为false的孩子结点个数
                    int nullableFalseChildIndex = 0;
                    for (int i = 0; i < node.getChildCount(); i++) {
                        if (node.getChild(i).getNullable() == 0 && !isZeroWidthAssertionNode(node.getChild(i))) {
                            countNullableFalseChild++;
                            if (countNullableFalseChild == 1) nullableFalseChildIndex = i;
                            if (countNullableFalseChild == 2) break;
                        }
                    }
                    // 不存在nullable = false的Child(i) 则bFlexible = false, dFlexible = -1.
                    if (countNullableFalseChild == 0) {
                        node.setbFlexible(false);
                        node.setdFlexible(-1);
                    } else if (countNullableFalseChild == 1) {
                        // 有一个nullable = false的Child(i) 则bFlexible = false, dFlexible = getChild(i).dFlexible,
                        node.setbFlexible(false);
                        node.setdFlexible(node.getChild(nullableFalseChildIndex).getdFlexible());
                    } else if (countNullableFalseChild == 2) {
                        // 有两个及以上nullable = false的Child(i)和Child(j) 则bFlexible = false, dFlexible = 1,
                        node.setbFlexible(false);
                        node.setdFlexible(1);
                    }


                    // 否则从右往左找到第一个nullable为false的结点 若存在 记为child(i) 最后一个孩子结点记为child(n)
                    // 该结点的followLast为 getChild(i).getFollowLast() ∪ ... ∪ getChild(n).getFollowLast() ∪ getChild(i+1).getFirst() ∪ ... ∪ getChild(n).getFirst()
                    //                    若不存在 则上式中i = 0 依然成立
                    int i = node.getChildCount() - 1;
                    for (; i >= 0; i--) {
                        if (node.getChild(i).getNullable() == 0 && !isZeroWidthAssertionNode(node.getChild(i))) break;
                    }
                    if (i == -1) i = 0; // 修复索引超限
                    Set<String> auxiliaryFollowLastSet = new HashSet<>();    // 辅助集合
                    auxiliaryFollowLastSet.addAll(node.getChild(i).getFollowLast());
                    i += 1;
                    for (; i < node.getChildCount(); i++) {
                        auxiliaryFollowLastSet.addAll(node.getChild(i).getFollowLast());
                        auxiliaryFollowLastSet.addAll(node.getChild(i).getFirst());
                    }
                    node.setFollowLast(auxiliaryFollowLastSet);
                }

            }
        }
    }



    // 重写去groupName 将(?<x>y)(?P=x)转换成(y)\1
    public void deleteGroupName() throws InterruptedException {
        // 前序遍历 找到所有的capturingGroup结点 并记录序号
        int capturingGroupNodeId = 0;
        HashMap<String, Integer> hashMap = new HashMap<>(); // groupName, 序号
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.add(this);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            if (isGeneralizedCapturingGroupNode(node)) {
                capturingGroupNodeId++;
                String groupName = getGroupNameForCapturingGroupNode(node);
                if (!groupName.equals("")) hashMap.put(groupName, capturingGroupNodeId);
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack1.add(node.getChild(i));
            }
        }

        // 后序遍历处理奇奇怪怪的反向引用和namedCapturingGroup结点
        Stack<TreeNode> auxiliaryStack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        auxiliaryStack.push(this);
        while (!auxiliaryStack.isEmpty()) {
            TreeNode tmp = auxiliaryStack.pop();
            stack2.push(tmp);
            for (int i = 0; i < tmp.getChildCount(); i++) {
                auxiliaryStack.push(tmp.getChild(i));
            }
        }
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            if (isUnusualBackReferencesNode1(node)) {
                String newData = node.getData().replace("\\g", "\\");
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode2(node)) {
                String newData = node.getData().replace("\\g{", "\\").replace("}", "");
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode3(node)) {
                int refNum = Integer.parseInt(node.getData().replace("\\g{", "").replace("}", ""));
                String newData = "\\" + (capturingGroupNodeId + 1 + refNum);
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode4(node)) {
                String refName = node.getChild(2).getData();
                String newData = "\\" + hashMap.get(refName);
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode5(node)) {
                String refName = node.getChild(2).getData();
                String newData = "\\" + hashMap.get(refName);
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode6(node)) {
                String refName = node.getChild(2).getData();
                String newData = "\\" + hashMap.get(refName);
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode7(node)) {
                String refName = node.getChild(2).getData();
                String newData = "\\" + hashMap.get(refName);
                node.updateTreeByModifyNode(newData);
            } else if (isUnusualBackReferencesNode8(node)) {
                String refName = node.getChild(4).getData();
                String newData = "\\" + hashMap.get(refName);
                node.updateTreeByModifyNode(newData);
            }

            if (isNamedCapturingGroupNode1(node)) {
                node.deleteChild(1);    // 删除?
                node.deleteChild(1);    // 删除P
                node.deleteChild(1);    // 删除<
                node.deleteChild(1);    // 删除groupName
                node.deleteChild(1);    // 删除>
            } else if (isNamedCapturingGroupNode2(node)) {
                node.deleteChild(1);    // 删除?
                node.deleteChild(1);    // 删除<
                node.deleteChild(1);    // 删除groupName
                node.deleteChild(1);    // 删除>
            } else if (isNamedCapturingGroupNode3(node)) {
                node.deleteChild(1);    // 删除?
                node.deleteChild(1);    // 删除'
                node.deleteChild(1);    // 删除groupName
                node.deleteChild(1);    // 删除'
            }
        }
    }


    // 重写反向引用 利用深度优先遍历
    // (?: (?= (?<= 不算是group 调用prism.js去掉了
    public void rewriteBackReferences() throws InterruptedException {
        List<GroupContent> groupContentChainIndexList = new LinkedList<>();    // 索引内容及其列表
        groupContentChainIndexList.add(new GroupContent(createReDoSTree("-"), "", ".", "."));
        TreeNode tree = this;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(tree);
        boolean flag = false;
        while (!stack.isEmpty()) {
            tree = stack.pop();
            if (isBracketsNode(tree)) {
                continue;
            }
            if (isGroupNode(tree)) {
//                groupContentChainIndexList.add(new GroupContent(tree, tree.data, tree.chainIndex));
                TreeNode groupSubNode = getGroupSubNode(tree);
                groupContentChainIndexList.add(new GroupContent(groupSubNode, groupSubNode.data, groupSubNode.chainIndex, groupSubNode.initialChainIndex));
            }
            Pattern pattern = Pattern.compile("^\\\\[0-9]+$");
            Matcher isNum = pattern.matcher(tree.data);
            if (isNum.matches()) {
                flag = true;
            }

            if (tree.getChildCount() > 0) {
                stack.addAll(tree.getChildList());
            }
        }
        if (!flag) {
            return;
        }
        // 重写排序
        groupContentChainIndexList.sort(new Comparator<GroupContent>() {
            @Override
            public int compare(GroupContent o1, GroupContent o2) {
                if (o1.groupContentChainIndex.compareTo(o2.groupContentChainIndex) == 0) {
                    return 0;
                }
                String[] l1 = o1.groupContentChainIndex.split("\\.");
                String[] l2 = o2.groupContentChainIndex.split("\\.");
                int len = Math.min(l1.length, l2.length);
                for (int i = 0; i < len; i++) {
                    int n1 = Integer.parseInt(l1[i]);
                    int n2 = Integer.parseInt(l2[i]);
                    if (n1 > n2) {
                        return 1;
                    } else if (n1 < n2) {
                        return -1;
                    }
                }
                return Integer.compare(l1.length, l2.length);
            }
        });

        // 反着消灭
        for (int i = groupContentChainIndexList.size(); i > 0; i--) {
            stack.add(this);
            while (!stack.isEmpty()) {
                tree = stack.pop();
                if (isBracketsNode(tree)) {
                    continue;
                }
                Pattern pattern = Pattern.compile("^\\\\" + i + "$");
                Matcher isNum = pattern.matcher(tree.getData());
                if (isNum.matches()) {
                    int index = Integer.parseInt(tree.getData().substring(1));
                    if (index < groupContentChainIndexList.size()) {
                        tree.updateTreeByModifyNode("(?:" + groupContentChainIndexList.get(index).groupContent + ")");
                        tree.isChildReferencesNode = true;
                        tree.parentReferenceNodeChainIndex = groupContentChainIndexList.get(index).groupContentChainIndex;
                        tree.parentReferenceNodeInitialChainIndex = groupContentChainIndexList.get(index).groupContentInitialChainIndex;
                        // 将所有的孩子结点统一设成相同的值
                        List<TreeNode> list = tree.getAllChildren();
                        for (TreeNode node: list) {
                            node.isChildReferencesNode = true;
                            node.parentReferenceNodeChainIndex = groupContentChainIndexList.get(index).groupContentChainIndex;
                            node.parentReferenceNodeInitialChainIndex = groupContentChainIndexList.get(index).groupContentInitialChainIndex;
                        }
                    } else {
                        int decimal = Integer.parseInt(tree.getData().replace("\\", ""), 8);
                        tree.updateTreeByModifyNode(String.valueOf(decimal));
                    }
                } else {
                    stack.addAll(tree.getChildList());
                }
            }
        }


//        for (int i = 0; i < groupContentChainIndexList.size(); i++) {   // 一遍不够 (\\d)(\1)\2
//            stack.add(this);
//            while (!stack.isEmpty()) {
//                tree = stack.pop();
//                if (isBracketsNode(tree)) {
//                    continue;
//                }
//                Pattern pattern = Pattern.compile("^\\\\[0-9]+$");
//                Matcher isNum = pattern.matcher(tree.getData());
//                if (isNum.matches()) {
//                    int index = Integer.parseInt(tree.getData().substring(1));
//                    if (index < groupContentChainIndexList.size()) {
//                        tree.updateTreeByModifyNode("(?:"+ groupContentChainIndexList.get(index).groupContent + ")");
//                        tree.isRefercencesNode = true;
//                    } else {
//                        int decimal = Integer.parseInt(tree.getData().replace("\\", ""), 8);
//                        tree.updateTreeByModifyNode(String.valueOf(decimal));
//                    }
//                } else {
//                    stack.addAll(tree.getChildList());
//                }
//            }
//        }
    }

    // 去掉(?: 在去掉反向引用之后使用
    public void deleteNonCapturingGroupFlag() throws InterruptedException {
        Pattern pattern = Pattern.compile("\\(\\?\\:");
        Matcher matcher = pattern.matcher(this.getData());
        while (!matcher.find()) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isNonCapturingGroupNode(node)) {
//                String newData = "(" + node.getData().substring(3);
//                node.updateTreeByModifyNode(newData);
                node.deleteChild(1);
                node.deleteChild(1);
            }
            queue.addAll(node.getChildList());

        }
    }

    // 去首尾^$完全重写
    public void deleteCaretAndDollarSymbols() throws InterruptedException {
        List<String> chainIndexList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        // 修正当regex = $ 时候的问题
        String data = this.getData();
        if ((data.equals("^") || data.equals("$") || data.equals("\\A") || data.equals("\\Z") || data.equals("\\z"))) {
            this.updateTreeByModifyNode("");
            return;
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            List<TreeNode> children = node.getChildList();
            for (TreeNode child : children) {
//            if (children.get(i).data.equals("^") || children.get(i).data.equals("$")) {
                // 修正 不应该将出现在[]内的^$删除
//                if ((child.getData().equals("^") || child.getData().equals("$")) && (!node.getChildDataList().contains("["))) {
//                    chainIndexList.add(child.getChainIndex());
//                }
                // 再次修正 不应该将出现在[]内的^$删除
                data = child.getData();
                if ((data.equals("^") || data.equals("$") || data.equals("\\A") || data.equals("\\Z") || data.equals("\\z")) && !isInBrackets(child) && !isInNegateNode(child)) {
//                    chainIndexList.add(child.getChainIndex());
                    child.updateTreeByModifyNode("");
                }
                queue.offer(child);
            }
        }

//        // 将索引从大到小排列 下一步从大到小删除 就不会影响索引
//        chainIndexList.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.compareTo(o2) == 0) {
//                    return 0;
//                }
//                String[] l1 = o1.split("\\.");
//                String[] l2 = o2.split("\\.");
//                int len = Math.min(l1.length, l2.length);
//                for (int i = 0; i < len; i++) {
//                    int n1 = Integer.parseInt(l1[i]);
//                    int n2 = Integer.parseInt(l2[i]);
//                    if (n1 > n2) {
//                        return -1;
//                    } else if (n1 < n2) {
//                        return 1;
//                    }
//                }
//                return Integer.compare(l2.length, l1.length);
//            }
//        });
//
////        for (String chainindex : chainIndexList) {
////            System.out.println(chainindex);
////        }
//
//        for (String s : chainIndexList) {
//            this.deleteChildByChainIndexString(s);
//        }
    }



    // 将方括号中的\b重写为\u0008 但\u0008表示退格符 就删掉方括号里的\b
    public void reWriteBackspace() throws InterruptedException {
        Pattern pattern = Pattern.compile("\\\\b");
        Matcher matcher = pattern.matcher(this.getData());
        if (!matcher.find()) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isBracketsNode(node) || isNegateNode(node)) {
                if (isBracketsNode(node)) {
                    if (node.getChildCount() == 3 && node.getChild(1).getData().equals("\\b")) {
                        // 如果是[\b]这样的 就直接删除
                        TreeNode parent = node.getParent();
                        if (parent != null) parent.deleteChild(node);
                        else {
                            this.updateTreeByModifyNode("");
                            return;
                        }
                    } else {
                        // 如果是[abc\b]这样的 删除\b 改为[abc]
                        for (int i = 0; i < node.getChildCount(); i++) {
                            String data = node.getChild(i).getData();
                            if (data.equals("\\b")) {
                                node.deleteChild(node.getChild(i));
                            }
                        }
                    }
                } else {
                    if (node.getChildCount() == 4 && node.getChild(2).getData().equals("\\b")) {
                        // 如果是[^\b]这样的 就直接删除
                        TreeNode parent = node.getParent();
                        if (parent != null) parent.deleteChild(node);
                        else {
                            this.updateTreeByModifyNode("");
                            return;
                        }
                    } else {
                        // 如果是[^abc\b]这样的 删除\b 改为[abc]
                        for (int i = 0; i < node.getChildCount(); i++) {
                            String data = node.getChild(i).getData();
                            if (data.equals("\\b")) {
                                node.deleteChild(node.getChild(i));
                            }
                        }
                    }
                }

            } else {
                queue.addAll(node.getChildList());
            }
        }
    }

    // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
    public void addBackslashBeforeSomeCharacters() throws InterruptedException {
        List<TreeNode> allLeafNodes = this.getLeafNodes();
        for (TreeNode leafNode : allLeafNodes) {
            if (isSomeCharactersNeedAddABackslashBeforeIt(leafNode.getData())) {
                leafNode.updateTreeByModifyNode("\\" + leafNode.getData());
            }
        }
    }

    // 压缩计数
    public void reduceQuantifier() throws InterruptedException {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isQuantifierNode(node)) {
                int firstNum = getCountingFirstNum(node.getData());
                int secondNum = getCountingSecondNum(node.getData());
                if (firstNum > 10) {
                    if (secondNum > 10) {
                        if (firstNum == secondNum) node.updateTreeByModifyNode("{10}");
                        else node.updateTreeByModifyNode("{10,11}");
                    } else {
                        if (secondNum == -1) node.updateTreeByModifyNode("{10,}");
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    // 将特殊字符反斜杠字符替换掉
    // 根据SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA   SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON  SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT
    public void rewriteSpecialBackslashCharacterForDifferentLanguage (String language) throws InterruptedException {
        Pattern pattern = Pattern.compile("\\\\\\w");
        Matcher matcher = pattern.matcher(this.getData());
        if (!matcher.find()) {
            return;
        }

        language = language.toLowerCase();

        HashMap<String, String> hashMap = null;
        if (language.equals("java")) {
            hashMap = SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA;
        } else if (language.equals("python")) {
            hashMap = SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON;
        } else if (language.equals("javascript")) {
            hashMap = SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT;
        } else {
            return;
        }

        List<TreeNode> allLeafNodes = this.getLeafNodes();
        for (TreeNode leafNode: allLeafNodes) {
            String data = leafNode.getData();
            String newData = hashMap.getOrDefault(data, data);
            if (!newData.equals(data)) {
                leafNode.updateTreeByModifyNode(newData);
            }
        }
    }

    // 将方括号中的\0~\777重写为\u0000~\u0777
    public void rewriteUnicodeNumberInBracketNode() throws InterruptedException {
        Pattern pattern = Pattern.compile("\\\\\\d{1,3}");
        Matcher matcher = pattern.matcher(this.getData());
        if (!matcher.find()) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isBracketsNode(node) || isNegateNode(node)) {
                for (int i = 0; i < node.getChildCount(); i++) {
                    String data = node.getChild(i).getData();
                    if (isGeneralizedCollectionSymbol(node.getChild(i))) {
                        TreeNode child1 = node.getChild(0);
                        TreeNode child2 = node.getChild(1);
                        node = child1;
                        if (data.startsWith("\\") && data.length() > 1) {
                            int num = -1;
                            try {
                                num = Integer.parseInt(data.substring(1));
                            } catch (NumberFormatException e) {

                            }
                            if (num != -1) {
                                if (node.getChild(i).getNextNode() != null) {
                                    int num2 = -1;
                                    try {
                                        num2 = Integer.parseInt(node.getChild(i).getNextNode().getData());
                                    } catch (NumberFormatException e) {

                                    }
                                    if (0 <= num2 && num2 <= 9) {
                                        num = num * 10 + num2;
                                        // 把后一个值删了
                                        node.deleteChild(node.getChild(i).getNextNode());
                                    }
                                }
                                // 把当前的值转换了
                                String newData = OCTAL_TO_HEX.get("\\" + num);
                                if (newData != null)
                                    node.getChild(i).updateTreeByModifyNode(newData);
                            }
                        }
                        node = child2;
                        if (data.startsWith("\\") && data.length() > 1) {
                            int num = -1;
                            try {
                                num = Integer.parseInt(data.substring(1));
                            } catch (NumberFormatException e) {

                            }
                            if (num != -1) {
                                if (node.getChild(i).getNextNode() != null) {
                                    int num2 = -1;
                                    try {
                                        num2 = Integer.parseInt(node.getChild(i).getNextNode().getData());
                                    } catch (NumberFormatException e) {

                                    }
                                    if (0 <= num2 && num2 <= 9) {
                                        num = num * 10 + num2;
                                        // 把后一个值删了
                                        node.deleteChild(node.getChild(i).getNextNode());
                                    }
                                }
                                // 把当前的值转换了
                                String newData = OCTAL_TO_HEX.get("\\" + num);
                                if (newData != null)
                                    node.getChild(i).updateTreeByModifyNode(newData);
                            }
                        }
                    } else if (data.startsWith("\\") && data.length() > 1) {
                        int num = -1;
                        try {
                            num = Integer.parseInt(data.substring(1));
                        } catch (NumberFormatException e) {

                        }
                        if (num != -1) {
                            if (node.getChild(i).getNextNode() != null) {
                                int num2 = -1;
                                try {
                                    num2 = Integer.parseInt(node.getChild(i).getNextNode().getData());
                                } catch (NumberFormatException e) {

                                }
                                if (0 <= num2 && num2 <= 9) {
                                    num = num * 10 + num2;
                                    // 把后一个值删了
                                    node.deleteChild(node.getChild(i).getNextNode());
                                }
                            }
                            // 把当前的值转换了
                            String newData = OCTAL_TO_HEX.get("\\" + num);
                            if (newData != null)
                                node.getChild(i).updateTreeByModifyNode(newData);
                        }
                    }
                }
            } else {
                queue.addAll(node.getChildList());
            }
        }
    }

    // 判断是否需要进行处理 标准为 内部含有交集 或 嵌套括号
    private boolean needDealWithCharacterClassInJava() {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = this.getChildCount() - 1; i >= 0; i--) {
            stack.push(this.getChild(i));
        }
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isBracketsNode(node) || isNegateNode(node) || isIntersectionNode(node)) {
                return true;
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return false;
    }

    // 加一个转换奇奇怪怪嵌套的character set的函数
    // [^abc[d]] 在不同引擎下解释不一样 在java8下 [^abc[d]]是匹配d的 而[^abcd]是不匹配d的
    // 在antlr4上解析与PCRE2 PCRE JS python Golang 上是一致的 与java8是不一致的
    // 以及处理java中的集合交集问题
    public void dealWithCharacterClassInJava() throws InterruptedException {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isBracketsNode(node) || isNegateNode(node)) {
                if (node.needDealWithCharacterClassInJava()) {
                    Pattern pattern =  Pattern.compile(node.getData());
                    int matchCount = 0;
                    int notMatchCount = 0;
                    boolean matchFlag = true;
                    boolean notMatchFlag = true;
                    Character matchStart = null;
                    Character matchEnd = null;
                    Character notMatchStart = null;
                    Character notMatchEnd = null;
                    StringBuilder matchStringBuilder = new StringBuilder();
                    StringBuilder notMatchStringBuilder = new StringBuilder();
                    for (int i = 0; i < 65536; i++) {
                        char c = (char) i;
                        boolean isMatch = pattern.matcher(String.valueOf(c)).find();
//                        System.out.println(Integer.toHexString(i) + " " + isMatch);
                        if (isMatch) {
                            if (matchFlag) {
                                matchStart = c;
                                matchEnd = c;
                                matchFlag = false;
                                if (notMatchStart != null && notMatchEnd != null) {
                                    if (notMatchStart == notMatchEnd) {
                                        if (notMatchStart == '-' || notMatchStart == '\\') {
                                            notMatchStringBuilder.append("\\");
                                        }
                                        notMatchStringBuilder.append(notMatchStart);
                                    } else {
                                        if (notMatchStart == '-' || notMatchStart == '\\') {
                                            notMatchStringBuilder.append("\\");
                                        }
                                        notMatchStringBuilder.append(notMatchStart);
                                        notMatchStringBuilder.append("-");
                                        if (notMatchEnd == '-' || notMatchEnd == '\\') {
                                            notMatchStringBuilder.append("\\");
                                        }
                                        notMatchStringBuilder.append(notMatchEnd);
                                    }
                                }
                            } else {
                                matchEnd = c;
                            }
                            notMatchFlag = true;
                            matchCount ++;
                        } else {
                            if (notMatchFlag) {
                                notMatchStart = c;
                                notMatchEnd = c;
                                notMatchFlag = false;
                                if (matchStart != null && matchEnd != null) {
                                    if (matchStart == matchEnd) {
                                        if (matchStart == '-' || matchStart == '\\') {
                                            matchStringBuilder.append("\\");
                                        }
                                        matchStringBuilder.append(matchStart);
                                    } else {
                                        if (matchStart == '-' || matchStart == '\\') {
                                            matchStringBuilder.append("\\");
                                        }
                                        matchStringBuilder.append(matchStart);
                                        matchStringBuilder.append("-");
                                        if (matchEnd == '-' || matchEnd == '\\') {
                                            matchStringBuilder.append("\\");
                                        }
                                        matchStringBuilder.append(matchEnd);
                                    }
                                }
                            } else {
                                notMatchEnd = c;
                            }
                            matchFlag = true;
                            notMatchCount ++;
                        }
                    }
                    if (matchCount < notMatchCount) {
                        if (matchCount == 0 && notMatchCount == 65536) {    // 啥也不接收
                            node.updateTreeByModifyNode("[^\\s\\S]");
                        } else {
                            node.updateTreeByModifyNode("[" +
                                    matchStringBuilder.toString()
                                            .replace("\t", "\\t")
                                            .replace("\f", "\\f")
                                            .replace("\t", "\\t")
                                            .replace("\r", "\\r")
                                            .replace("\n", "\\n")
                                            .replace("\u000b", "\\v")
                                    + "]");
                        }
                    } else {
                        if (matchCount == 65536 && notMatchCount == 0) {    // 啥都接收
                            node.updateTreeByModifyNode("[\\s\\S]");
                        } else {
                            node.updateTreeByModifyNode("[^" +
                                    notMatchStringBuilder.toString()
                                            .replace("\t", "\\t")
                                            .replace("\f", "\\f")
                                            .replace("\t", "\\t")
                                            .replace("\r", "\\r")
                                            .replace("\n", "\\n")
                                            .replace("\u000b", "\\v")
                                    + "]");
                        }
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    // 处理java中的集合交集问题
    @Deprecated
    public void dealWithIntersectOperationInJava() throws InterruptedException {
        if (! this.getData().contains("&&")) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isBracketsNode(node) || isNegateNode(node)) {
                if (isBracketsNode(node)) {
                    node = node.getChild(1);
                } else {
                    node = node.getChild(2);
                }
                if (isIntersectionNode(node)) {
                    List<TreeNode> positiveNodeList = new ArrayList<>();    // 接收字符的结点
                    List<TreeNode> negativeNodeList = new ArrayList<>();    // 不接收字符的结点
                    for (int i = 0; i < node.getChildCount(); i += 3) {
                        if (isNegateNode(node.getChild(i))) {
                            negativeNodeList.add(node.getChild(i));
                        } else if (isBracketsNode(node.getChild(i))) {
                            positiveNodeList.add(node.getChild(i));
                        } else {
                            positiveNodeList.add(createReDoSTree("[" + node.getChild(i).getData() + "]"));
                        }
                    }

                    if (positiveNodeList.isEmpty()) {   // 全是 [^a] [^b] 这种 只需要把内部合并
                        StringBuilder stringBuilder = new StringBuilder();
                        for (TreeNode negativeNode: negativeNodeList) {
                            for (int i = 2; i < negativeNode.getChildCount() - 1; i++) {
                                stringBuilder.append(negativeNode.getChild(i).getData());
                            }
                        }
                        node.updateTreeByModifyNode(stringBuilder.toString());
                    } else {    // 存在正例子节点
                        Set<String> set = new HashSet<>();
                        set.addAll(positiveNodeList.get(0).getLetterSet(false, false));
                        for (int i = 1; i < positiveNodeList.size(); i++) {
                            set.retainAll(positiveNodeList.get(i).getLetterSet(false, false));
                        }
                        for (TreeNode negativeNode : negativeNodeList) {
                            set.removeAll(negativeNode.getLetterSet(false, false));
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String s : set) {
                            if (s.equals("-")) {
                                stringBuilder.append("\\-");
                            } else {
                                stringBuilder.append(s);
                            }
                        }
                        node.updateTreeByModifyNode(stringBuilder.toString());
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    // 处理python中奇怪的quantifier {,4}
    public void dealWithUnusualQuantifierInPython() {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            String data = node.getData();
            if (data.startsWith("{,") && data.endsWith("}") && data.length() >= 4) {
                String num = data.substring(2, data.length() - 1);
                try {
                    Integer.parseInt(num);
                    node.updateQuantifier("{0," + num + "}");
                } catch (NumberFormatException ignore) {

                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
    }

    // 修改quantifier for python
    private void updateQuantifier(String data) {
        TreeNode node = new TreeNode(data);
        node.data = data;
        TreeNode root = this;
        root.children = node.children;
        root.flags = node.flags;
        root.data = node.data;
        while (!root.isRoot()) {
            root = root.parent;
            StringBuilder stringBuilder = new StringBuilder();
            for (TreeNode i : root.children) {
                stringBuilder.append(i.data);
            }
            root.data = stringBuilder.toString();
        }
        root = this.getRoot();
        updateChainIndex(root, "0");
    }

    public static void main(String[] args) throws InterruptedException {
        String regex = "a*b+";
        TreeNode treeNode = createReDoSTree(regex);
        printTree(treeNode);
        treeNode.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast();
        System.out.println(treeNode.getFollowLast());


//        String regex = "[[\\d]]";
////        regex = "[^a\\w-.b]&&";
////        regex = "[A-Z&&[^FIOQUY]abc]";
////        regex = "[^\\da-c]";
////        regex = "[^ [a-z ]]";
//        regex = "[a-zA-Z[._][\\d]]";
////        regex = "[[\\--a]]";
////        regex = "[^\\s[\\S]]";
////        regex = "[[\\s]]";
////        regex = "[[\\u0000-\\u0002]]";
////        regex = unicodeToCn(regex);
////        regex = "^[a-zA-Z]([a-zA-Z[._][\\d]])*[@][a-zA-Z[.][\\d]]*[.][a-z[.][\\d]]*";
//        regex = "[<>'\"!&\\[\\]]";
////        regex = "([a-zA-Z0-9\\\\-\\\\$\\\\s\\\\.#@%^*(){}|:;,?+=/]*[<>'\\\"!&\\\\[\\\\]]+((\\\\s|)CDATA(\\\\s|))*[a-zA-Z0-9<>'\\\"!&\\\\[\\\\]\\\\-\\\\$\\\\s\\\\.#@%^*(){}|:;,?+=/]*)+";
//        TreeNode treeNode = createReDoSTree(regex, "java");
//        printTree(treeNode);
//        treeNode.optimizeBracketNode();
//        printTree(treeNode);
//        treeNode.dealWithCharacterClassInJava();
//        printTree(treeNode);
    }
}
