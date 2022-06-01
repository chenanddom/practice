package com.itdom.graph;

import com.itdom.linkedlist.Queue;

/**
 * 1.private final int V: 记录顶点数量
 * 2.private int E: 记录边数量
 * 3.private Queue[] adj: 邻接表
 * 1.public int V():获取图中顶点的数量
 * 2.public int E():获取图中边的数量
 * 3.public void addEdge(int v,int w):向图中添加一条边 v-w
 * 4.public Queue adj(int v)：获取和顶点v相邻的所有顶点
 */
public class Graph {
    /**
     * 记录顶点的个数
     */
    private final int V;
    /**
     * 记录边的数量
     */
    private int E;
    /**
     * 邻接表
     */
    private Queue[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Queue[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Queue();
        }
    }

    /**
     * 获取顶点的个数
     *
     * @return
     */
    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 向图中添加一条边 v-w
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        if (adj[v] != null) {
            adj[v].enqueue(w);
        }
        if (adj[w] != null) {
            adj[w].enqueue(v);
        }
        this.E++;
    }

    /**
     * 获取顶点V所有的边
     *
     * @param v
     * @return
     */
    public Queue<Integer> adj(int v) {
        return adj[v];
    }
}
