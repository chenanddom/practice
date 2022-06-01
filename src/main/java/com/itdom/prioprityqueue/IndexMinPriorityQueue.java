package com.itdom.prioprityqueue;

public class IndexMinPriorityQueue<T extends Comparable<T>> {
    public static void main(String[] args) {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        IndexMinPriorityQueue<String> indexMinPQ = new IndexMinPriorityQueue<>(20);
        //插入
        for (int i = 0; i < arr.length; i++) {
            indexMinPQ.insert(i, arr[i]);
        }
        System.out.println(indexMinPQ.size());
        //获取最小值的索引
        System.out.println("最小索引:" + indexMinPQ.minIndex() + "---" + "最小值:" + arr[indexMinPQ.minIndex()]);
    }

    //存储元素的数组
    private T[] items;
    //元素个数
    private int N;
    //存储item的元素的索引
    private int[] pq;
    //存储pq的逆序
    private int[] qp;


    public IndexMinPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        N = 0;
        for (int i = 0; i <capacity; i++) {
            qp[i] = -1;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void exchange(int i, int j) {
        int temp = pq[i];
        //交换pq中的数据
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;


        //更新qp中的数据
        qp[pq[i]]=i;
        qp[pq[j]] =j;
    }

    /**
     * 判断是否包含此元素，如果此索引被插入了元素就不再等于-1;
     *
     * @param k
     * @return
     */
    public boolean contain(int k) {
        return qp[k] != -1;
    }

    //    判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    //    最小元素关联的索引
    public int minIndex() {
        //pq的索引1处，存放的是最小元素在items中的索引.
        return pq[1];
    }

    public void insert(int i, T t) {
        if (contain(i)) {
            return;
        }
        N++;
        items[i] = t;
        pq[N] = i;
        qp[i] = N;
        swim(N);
    }

    public void delete(int i) {
        if (!contain(i)) {
            return;
        }
        int k = pq[i];
        //将k索引的值和最后索引的的值进行交换
        exchange(k, N);
        qp[pq[N]] = -1;
        pq[N] = -1;
        items[i] = null;
        N--;
        sink(k);
        swim(k);


    }

    //把与索引i关联的元素修改为为t
    public void changeItem(int i, T t) {
//        //
//        items[i] = t;
//        int k = pq[i];
//        sink(k);
//        swim(k);


        //修改items数组中i位置的元素为t
        items[i] = t;
        //找到i在pq中出现的位置
        int k = qp[i];
        //堆调整
        sink(k);
        swim(k);

    }

    /**
     * 删除堆中的最小元素
     *
     * @return
     */
    public int delMin() {
        //获取最小元素关联的索引
        int minIndex = pq[1];

        //交换pq中索引1处和最大索引处的元素
        exchange(1,N);
        //删除qp中对应的内容
        qp[pq[N]] = -1;
        //删除pq最大索引处的内容
        pq[N]=-1;
        //删除items中对应的内容
        items[minIndex] = null;
        //元素个数-1
        N--;
        //下沉调整
        sink(1);

        return minIndex;


//        //数组中的最小的元素是索引
//        int minIndex = pq[1];
//        //将第一个和最后一个位置的处的数据进行交换
//        exchange(1, N);
//        //将qp中的
//        qp[pq[N]] = -1;
//        //将pq的最后一个元素删除
//        pq[N] = -1;
//        //将item的元素置空
//        items[minIndex] = null;
//        //将第一个元素进行下沉
//        sink(1);
//        return minIndex;
    }


    private void swim(int k) {
        while (k > 1) {
            if (less(k, k / 2)) {
                exchange(k, k / 2);
            }
            k = k / 2;
        }
    }


    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {

        //如果当前结点已经没有子结点了，则结束下沉
        while (2 * k <= N) {
            //找出子结点中的较小值
            int min = 2 * k;
            if (2 * k + 1 <= N && less(2 * k + 1, 2 * k)) {
                min = 2 * k + 1;
            }
//如果当前结点的值比子结点中的较小值小，则结束下沉
            if (less(k, min)) {
                break;
            }
            exchange(k, min);
            k = min;
        }
    }

}
