# 算法

## 一、深度优先搜索（Depth-First Search，DFS）

当我们深入到叶结点时回溯，这就被称为 DFS 算法。

让我们分解一下：

1. 从根结点（1）开始。输出
2. 进入左结点（2）。输出
3. 然后进入左孩子（3）。输出
4. 回溯，并进入右孩子（4）。输出
5. 回溯到根结点，然后进入其右孩子（5）。输出
6. 进入左孩子（6）。输出
7. 回溯，并进入右孩子（7）。输出
8. 完成

### 1. 前序遍历

>输出结果为： 1–2–3–4–5–6–7

这和我们在上述示例中的作法基本类似。

1. 输出节点的值
2. 进入其左结点并输出。当且仅当它拥有左结点。
3. 进入右结点并输出之。当且仅当它拥有右结点

```java
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
```

### 2. 中序遍历

>输出结果3–2–4–1–6–5–7

左结点优先，之后是中间，最后是右结点。

1. 进入左结点并输出之。当且仅当它有左结点。
2. 输出根结点的值。
3. 进入结节点并输出之。当且仅当它有结节点。

```java
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
```

### 2. 后序遍历

>输出结果3–4–2–6–7–5–1

左结点优先，之后是右结点，根结点的最后。

1. 进入左结点输出，
2. 进入右结点输出
3. 输出根结点

```java
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
```

[博客](https://blog.csdn.net/dandandeshangni/article/details/79972501)