package com.itdom.graph;

import com.itdom.linkedlist.Queue;

/**
 * 加权无向图的实现
 */
public class EdgeWeightedGraph {
    /**
     * 顶点的个数
     */
    private final int V;
    /**
     * 边的条数
     */
    private int E;

    /**
     * 顶点的邻接表
     *
     * @param V
     */

    private Queue<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        /**
         * 初始化邻接表
         */
        this.adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<>();
        }
    }

    /**
     * 获取顶点的数量
     *
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 获取边的数量
     *
     * @return
     */
    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        //获取其中的一个顶点
        int v = e.either();
        //获取边的另一个顶点
        int w = e.other(v);
        //因为是无向图，所以边需要在两个顶点的邻接矩阵都需要有
        adj[v].enqueue(e);
        adj[w].enqueue(e);
        this.E++;
    }

    /**
     * 获取和顶点V关联的所有边
     */
    public Queue<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取加权无向图
     *
     * @return
     */
    public Queue<Edge> edges() {
        Queue<Edge> allEdge = new Queue<>();
        for (int i = 0; i < this.V; i++) {
            for (Edge e : adj(i)) {
          /*
            因为无向图中，每条边对象Edge都会在两个顶点的邻接表中各出现一次，为了不重复获取，暂定
            一条规则：
            除了当前顶点v，再获取边e中的另外一个顶点w，如果v<w则添加，这样可以保证同一条边
            只会被统计一次
            */
                if (e.other(i) < i) {
                    allEdge.enqueue(e);
                }
            }
        }
        return allEdge;
    }
}
