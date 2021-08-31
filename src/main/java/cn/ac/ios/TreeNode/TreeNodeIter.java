package cn.ac.ios.TreeNode;

import java.util.Iterator;

public class TreeNodeIter implements Iterator<TreeNode> {

    enum ProcessStages {
        ProcessParent, ProcessChildCurNode, ProcessChildSubNode
    }

    private TreeNode treeNode;

    public TreeNodeIter(TreeNode treeNode) {
        this.treeNode = treeNode;
        this.doNext = ProcessStages.ProcessParent;
        this.childrenCurNodeIter = treeNode.getChildList().iterator();
    }

    private ProcessStages doNext;
    private TreeNode next;
    private Iterator<TreeNode> childrenCurNodeIter;
    private Iterator<TreeNode> childrenSubNodeIter;


    @Override
    public boolean hasNext() {

        if (this.doNext == ProcessStages.ProcessParent) {
            this.next = this.treeNode;
            this.doNext = ProcessStages.ProcessChildCurNode;
            return true;
        }

        if (this.doNext == ProcessStages.ProcessChildCurNode) {
            if (childrenCurNodeIter.hasNext()) {
                TreeNode childDirect = childrenCurNodeIter.next();
                childrenSubNodeIter = childDirect.iterator();
                this.doNext = ProcessStages.ProcessChildSubNode;
                return hasNext();
            } else {
                this.doNext = null;
                return false;
            }
        }

        if (this.doNext == ProcessStages.ProcessChildSubNode) {
            if (childrenSubNodeIter.hasNext()) {
                this.next = childrenSubNodeIter.next();
                return true;
            } else {
                this.next = null;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return hasNext();
            }
        }

        return false;
    }


    @Override
    public TreeNode next() {
        return this.next;
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
