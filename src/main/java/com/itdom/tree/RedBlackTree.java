package com.itdom.tree;

/**
 * 1.private boolean isRed(Node x)：判断当前结点的父指向链接是否为红色
 * 2.private Node rotateLeft(Node h):左旋调整
 * 3.private Node rotateRight(Node h):右旋调整
 * 4.private void flipColors(Node h)：颜色反转,相当于完成拆分4-结点
 * 5.public void put(Key key, Value val):在整个树上完成插入操作
 * 6.private Node put(Node h, Key key, Value val):在指定树中，完成插入操作,并返回添加元素后
 * 新的树
 * 7.public Value get(Key key):根据key，从树中找出对应的值
 * 8.private Value get(Node x, Key key):从指定的树x中，找出key对应的值
 * 9.public int size():获取树中元素的个数
 * <p>
 * 1.private Node root : 记录根结点
 * 2.private int N:记录树中元素的个数
 * 3.private static final boolean RED：红色链接标识
 * 4.private static final boolean BLACK:黑色链接标识
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    public static void main(String[] args) {
        RedBlackTree<Integer,String> rbt = new RedBlackTree<>();
        rbt.put(4,"张三");
        rbt.put(1,"李四");
        rbt.put(2,"王五");
        rbt.put(5,"赵六");
        System.out.println(rbt.size());
        System.out.println(rbt.get(1));
        System.out.println(rbt.get(2));
        System.out.println(rbt.get(4));
        System.out.println(rbt.get(5));
    }

    //记录根节点
    private Node root;
    //记录树中元素的个数
    private int N;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public RedBlackTree() {
        this.root = null;
        this.N = 0;
    }

    /**
     *
     * @return
     */
    public int size(){
        return N;
    }
    /**
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 获取
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }


    public void put(Key key, Value val) {
        root = put(root, key, val);
        //根节点没有指向的连接，索引颜色只能是黑色
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            //标准的插入操作，和父节点用连接相连
            this.N++;
            return new Node(key, val, null, null, RED);
        }
        //比较要差人的键和但钱结点的键
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.value = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    /**
     * 判断当前节点的父指向连接是否是红色。
     *
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        return x == null ? false : x.color == RED ? true : false;
    }

    /**
     * 左旋
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        //将x的左子节点走哦为h的右子节点
        h.right = x.left;
        //将当前节点作为x节点的左子节点
        x.left = h;
        //将当前节点的右子节点的颜色置为h节点的颜色
        x.color = h.color;
        //将当前节点的颜色设置为红色
        h.color = RED;
        return x;
    }

    /**
     * 右旋调整
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        //将x的右子节点设置为h的左子节点
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        //当前节点的color属性变为RED
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //结点类
    private class Node {
        //存储键
        public Key key;
        //存储值
        private Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;
        //由其父结点指向它的链接的颜色
        public boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

}
