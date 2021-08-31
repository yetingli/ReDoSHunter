package cn.ac.ios.Bean;

import cn.ac.ios.TreeNode.TreeNode;

public class GroupLabel {
    public TreeNode groupTree;
    public String groupLabel;
    public String groupLabelChainIndex = "";

    public GroupLabel(TreeNode groupTree, String groupLabel, String chainIndex) {
        this.groupTree = groupTree;
        this.groupLabel = groupLabel;
        this.groupLabelChainIndex = chainIndex;
    }
}
