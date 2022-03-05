
import java.util.ArrayDeque;
import java.util.Queue;

import "./BinaryTree.java";
import "./GeneralTree.java";

public class Preorder_Iterator {
    private BinaryTreeNode currentNode = null;
    public GeneralTree myTree;
    Queue<BinaryTreeNode> levelsQueue = new ArrayDeque<BinaryTreeNode>();
    Queue<BinaryTreeNode> sortedQueue = new ArrayDeque<BinaryTreeNode>();

    public Preorder_Iterator(GeneralTree myTree) throws Exception {
        this.myTree = myTree;
        this.currentNode = null;
        if (myTree.isEmpty()) {
            throw new Exception("Empty Tree");
        }

        sortedQueue = this.iter();
    }

    public BinaryTreeNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(BinaryTreeNode currentNode) {
        this.currentNode = currentNode;
    }

    public GeneralTree getMyTree() {
        return myTree;
    }

    public void setMyTree(GeneralTree myTree) {
        this.myTree = myTree;
    }

    public Queue<BinaryTreeNode> getlevelsQueue() {
        return levelsQueue;
    }

    public void setlevelsQueue(Queue<BinaryTreeNode> levelsQueue) {
        this.levelsQueue = levelsQueue;
    }

    public Queue<BinaryTreeNode> getsortedQueue() {
        return sortedQueue;
    }

    public void setsortedQueue(Queue<BinaryTreeNode> sortedQueue) {
        this.sortedQueue = sortedQueue;
    }

    public boolean hasNext() {
        if (sortedQueue.isEmpty()) {
            return false;
        } else {
            return sortedQueue.element() != null;
        }
    }

    /* THIS METHOD RETURNS THE CORRESPONDING NODE */
    public BinaryTreeNode nextNode() {
        if (sortedQueue.isEmpty()) {
            return null;
        } else {
            return sortedQueue.poll();
        }

    }

    /* THIS METHOD SORT THE TREE IN PREORDER */
    public Queue<BinaryTreeNode> iter() throws Exception {
        Queue<BinaryTreeNode> auxiliarQueue = new ArrayDeque<BinaryTreeNode>();
        currentNode = (BinaryTreeNode) myTree.getRoot();
        levelsQueue.offer(currentNode);
        BinaryTreeNode auxiliar;
        auxiliar = levelsQueue.element();
        while (!levelsQueue.isEmpty()) {
            if (auxiliarQueue.size() == myTree.totalNodes() - 1) {
                auxiliarQueue.offer(auxiliar);
                return auxiliarQueue;
            }
            auxiliarQueue.offer(auxiliar);
            auxiliar = auxiliar.getRight();
            if (auxiliar != null) {
                levelsQueue.offer(auxiliar);
            } else {
                if (levelsQueue.isEmpty()) {
                    return auxiliarQueue;
                }
                while (levelsQueue.element().getLeft() == null) {

                    levelsQueue.poll();
                }
                auxiliar = levelsQueue.poll().getLeft();
                levelsQueue.offer(auxiliar);
            }
        }
        return auxiliarQueue;

    }
}
