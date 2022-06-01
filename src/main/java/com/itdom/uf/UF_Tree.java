package com.itdom.uf;

import java.util.Scanner;

/**
 * 并查集
 */
public class UF_Tree {
    /**
     * 记录结点元素和该元素所在分组的标识
     */
    private int[] eleGroup;
    /**
     * 记录并查集中数据的分组的个数
     */
    private int count;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请录入并查集中元素的个数:");
        int N = sc.nextInt();
        UF_Tree uf = new UF_Tree(N);
        while (true) {
            System.out.println("请录入您要合并的第一个点:");
            int p = sc.nextInt();
            System.out.println("请录入您要合并的第二个点:");
            int q = sc.nextInt();
            if (uf.connected(p, q)) {
                System.out.println("结点：" + p + "结点" + q + "已经在同一个组");
                continue;
            }
            uf.union(p, q);
            System.out.println("总共还有" + uf.count() + "个分组");
        }

    }

    public UF_Tree(int N) {
        this.count = N;
        eleGroup = new int[N];
        for (int i = 0; i < N; i++) {
            eleGroup[i] = i;
        }
    }

    /**
     * 获取分组个数
     *
     * @return
     */
    public int count() {
        return count;
    }

    //元素p所在分组的标识符
    public int find(int p) {
//        return eleGroup[p];
        while (true){
            if (p==eleGroup[p]){
                return p;
            }
            p = eleGroup[p];
        }

    }

    /**
     * //判断并查集中元素p和元素q是否在同一分组中
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并p和q
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        int pg = find(p);
        int qg = find(q);
//        for (int i = 0; i < eleGroup.length; i++) {
//            if (eleGroup[i] == pg) {
//                eleGroup[i] = qg;
//            }
//        }
        if (pg == qg) {
            return;
        }
        eleGroup[pg] = qg;
        count--;
    }
}
