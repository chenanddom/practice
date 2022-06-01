package com.itdom.tree;

import com.alibaba.fastjson.JSON;
import com.itdom.linkedlist.Queue;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import netscape.javascript.JSObject;

import java.util.Iterator;

public class BinaryTree<Key extends Comparable<Key>, Value> {
    //记录根结点
    private Node root;
    //记录树中元素的个数
    private int N;

    public int size() {
        return N;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmpValue = key.compareTo(x.key);
        if (cmpValue > 0) {
            x.right = delete(x.right, key);
        } else if (cmpValue < 0) {
            x.left = delete(x.left, key);
        } else {
            this.N--;
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            //删除右子节点的最小节点
            Node n = x.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
                    n = n.left;
                }
            }
            //将要删除的节点的左子树赋值给新的代替节点
            minNode.left = x.left;
            minNode.right = x.right;
            //此处返回就可以让父结点指向它
            x = minNode;
        }
        return x;
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmpValue = key.compareTo(x.key);
        if (cmpValue > 0) {
            return get(x.right, key);
        } else if (cmpValue < 0) {
            return get(x.left, key);
        } else {
            return x.value;
        }
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val) {
        //当前树不存在的情况
        if (x == null) {
            this.N++;
            x = new Node(key, val, null, null);
            return x;
        }
        //如果x节点不为空，比较节点的key值，
        int cmpValue = key.compareTo(x.key);
        if (cmpValue > 0) {
            //1. 如果需要插入的元素的key值大于x节点的key值，则往右子树走
            x.right = put(x.right, key, val);
        } else if (cmpValue < 0) {
            //2. 如果需要插入的元素的key值小于x节点的key值，则往左子树走
            x.left = put(x.left, key, val);
        } else {
            //key相等则覆盖
            x.value = val;
        }
        return x;
    }

    //前序遍历
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }

    public void preErgodic(Node node, Queue queue) {
        if (node == null) {
            return;
        }
        queue.enqueue(node.key);
        if (node.left != null) {
            preErgodic(node.left, queue);
        }
        if (node.right != null) {
            preErgodic(node.right, queue);
        }

    }

    public Queue<Key> midErgodic() {
        Queue<Key> queue = new Queue<>();
        midErgodic(root, queue);
        return queue;
    }

    public void midErgodic(Node node, Queue<Key> queue) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            midErgodic(node.left, queue);
        }
        queue.enqueue(node.key);
        if (node.right != null) {
            midErgodic(node.right, queue);
        }
    }

    public Queue lastErgodic() {
        Queue<Key> keys = new Queue<>();
        lastErgodic(root, keys);
        return keys;
    }

    public void lastErgodic(Node node, Queue queue) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            lastErgodic(node.left, queue);
        }
        if (node.right != null) {
            lastErgodic(node.right, queue);
        }
        queue.enqueue(node.key);
    }

    /**
     * 层遍历结合了队列和二叉树的相关的结构来进行实现
     *
     * @return
     */
    public Queue<Key> layerErgodic() {
        //创建两个队列分别存储键和节点
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();
        //将根节点直接放入节点队列
        nodes.enqueue(root);
        //从队列里面获取节点进行遍历
        while (!nodes.isEmpty()) {
            Node currentNode = nodes.dequeue();
            keys.enqueue(currentNode.key);
            //如果当前节点有左子结点就放入左子结点
            if (currentNode.left != null) {
                nodes.enqueue(currentNode.left);
            }
            if (currentNode.right != null) {
                nodes.enqueue(currentNode.right);
            }
        }
        return keys;
    }

    public int maxDept() {
        return maxDept(root);
    }

    public int maxDept(Node node) {
        if (node == null) {
            return 0;
        }
        int max = 0;
        int maxL = 0;
        int maxR = 0;
        if (node.left != null) {
            maxL = maxDept(node.left);
        }
        if (node.right != null) {
            maxR = maxDept(node.right);
        }
        max = maxR > maxL ? maxR + 1 : maxL + 1;
        return max;
    }

    public static void main(String[] args) {

        BinaryTree<Integer, Object> binaryTree = new BinaryTree<>();
        binaryTree.put(4, "4");
        binaryTree.put(2, "2");
        binaryTree.put(5, "5");
        binaryTree.put(6, "6");
        binaryTree.put(1, "1");
        binaryTree.put(3, "3");

//        System.out.println(binaryTree.get(5));
//        Queue queue = binaryTree.preErgodic();
//        Queue<Integer> queue = binaryTree.midErgodic();
//        Queue<Integer> queue = binaryTree.lastErgodic();
        Queue<Integer> queue = binaryTree.layerErgodic();
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(JSON.toJSONString(next));
        }
        System.out.println("树的最大深度为:"+binaryTree.maxDept());

    }


    public class Node {
        //存储键
        public Key key;
        //存储值
        private Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


}
