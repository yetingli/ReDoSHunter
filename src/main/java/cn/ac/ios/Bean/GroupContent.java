package cn.ac.ios.Bean;

import cn.ac.ios.TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupContent {
    public TreeNode groupTree;
    public String groupContent = "";
    public String groupContentChainIndex = "";
    public String groupContentInitialChainIndex = "";
    public ArrayList<String> references = new ArrayList<>();

    public GroupContent(TreeNode groupTree, String groupContent, String chainIndex, String initialChainIndex) {
        this.groupTree = groupTree;
        this.groupContent = groupContent;
        this.groupContentChainIndex = chainIndex;
        this.groupContentInitialChainIndex = initialChainIndex;
    }


    public void refactor(List<GroupContent> groupContentChainIndexList) {

        Stack<TreeNode> stack = new Stack<>();
        stack.add(groupTree);
        while (!stack.isEmpty()) {
            TreeNode tree = stack.pop();
            Pattern pattern = Pattern.compile("^\\\\[0-9]+$");
            Matcher isNum = pattern.matcher(tree.getData());
            if (isNum.matches()) {
                int index = Integer.parseInt(tree.getData().substring(1));
                tree.setData(groupContentChainIndexList.get(index).groupContent);
                tree.getChildList().clear();
            } else {
                if (tree.getChildCount() > 0) {
                    stack.addAll(tree.getChildList());
                }
            }
        }
        groupContent = groupTree.getLeafsData();
    }
}
