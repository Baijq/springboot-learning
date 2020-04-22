package com.biubiu.base.list2tree.tree;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BinaryTree {

    /** 树的内容 **/
    protected String data;

    protected BinaryTree left;

    protected BinaryTree right;

    public BinaryTree() {
    }

    public BinaryTree(String data, BinaryTree left, BinaryTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryTree(String data) {
        this(data, null, null);
    }

    /** 插入节点 ，如果当前的节点没有左节点，我们就创建一个新节点，然后将其设置为当前节点的左节点。 **/
    public static void insertLeft(BinaryTree node, String value) {
        if (null != node) {
            if (null == node.left) {
                node.setLeft(new BinaryTree(value));
            } else {
                BinaryTree newNode = new BinaryTree(value);
                newNode.left = node.left;
                node.left = newNode;
            }
        }
    }

    /** 插入节点 ，如果当前的节点没有右节点，我们就创建一个新节点，然后将其设置为当前节点的右节点。 **/
    public static void insertRight(BinaryTree node, String value) {
        if (null != node) {
            if (node.right == null) {
                node.setRight(new BinaryTree(value));
            } else {
                BinaryTree newNode = new BinaryTree(value);
                newNode.right = node.right;
                node.right = newNode;
            }
        }
    }

    /** 深度优先搜索（Depth-First Search，DFS）--前序遍历 **/
    public static void preOrder(BinaryTree node) {
        if (null != node) {
            System.out.println(node.data);
            if (null != node.left) {
                node.left.preOrder(node.left);
            }
            if (null != node.right) {
                node.right.preOrder(node.right);
            }
        }
    }

    /** 深度优先搜索（Depth-First Search，DFS）--中序遍历 **/
    public static void inOrder(BinaryTree node) {
        if (null != node) {
            if (null != node.left) {
                node.left.inOrder(node.left);
            }
            System.out.println(node.data);
            if (null != node.right) {
                node.right.inOrder(node.right);
            }
        }
    }

    /** 深度优先搜索（Depth-First Search，DFS）--后序遍历 **/
    public static void postOrder(BinaryTree node) {
        if (null != node) {
            if (null != node.left) {
                node.left.postOrder(node.left);
            }
            if (node.right != null) {
                node.right.postOrder(node.right);
            }
            System.out.println(node.data);
        }
    }
}
