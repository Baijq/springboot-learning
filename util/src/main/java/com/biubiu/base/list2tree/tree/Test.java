package com.biubiu.base.list2tree.tree;

public class Test {

    public static void main(String[] args) {

        testCreate();

    }

    private static void testCreate() {
        BinaryTree node = new BinaryTree("1");
        node.insertLeft(node, "2");
        node.insertRight(node, "5");

        node.left.insertLeft(node.left, "3");
        node.left.insertRight(node.left, "4");

        node.right.insertLeft(node.right, "6");
        node.right.insertRight(node.right, "7");

        node.preOrder(node);
        node.inOrder(node);
        node.postOrder(node);
    }

}
