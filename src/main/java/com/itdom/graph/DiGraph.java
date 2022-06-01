package com.itdom.graph;

import com.itdom.linkedlist.Queue;

/**
 * 有向图
 * 1.public int V():获取图中顶点的数量
 * 2.public int E():获取图中边的数量
 * 3.public void addEdge(int v,int w):向有向图中添加一条边 v->w
 * 4.public Queue adj(int v)：获取由v指出的边所连接的所有顶点
 * 5.private Digraph reverse():该图的反向图
 */
public class DiGraph {
    public static void main(String[] args) {

    }

    /**
     * 记录顶点的个数
     */
    private final int v;
    /**
     * 记录边的条数
     */
    private int E;
    /**
     * 每个顶点相关的邻接表
     */
    private Queue<Integer>[] adj;

    public DiGraph(int v) {
        this.v = v;
        this.E=0;
        this.adj = new Queue[v];
        for (int i = 0; i < v; i++) {
            adj[i]=new Queue<>();
        }
    }

    /**
     * 返回顶点的个数
     * @return
     */
    public int V(){
        return this.v;
    }

    /**
     * 返回有向边的条数
     * @return
     */
    public int E(){
        return this.E;
    }
    public void addEdge(int v,int w){

        adj[v].enqueue(w);
        this.E++;

    }

    /**
     * 返回顶点v的邻接矩阵
     * @param v
     * @return
     */
    public Queue<Integer> adj(int v){
        return adj[v];
    }
    private DiGraph reverse(){
        DiGraph reverseDiGraph = new DiGraph(v);
        for (int i = 0; i < this.v; i++) {
            for (Integer w : adj(i)) {
                //得到原图中的v顶点对应的邻接表,原图中的边为 v->w,则反向图中边为w->v;
                reverseDiGraph.addEdge(w,v);
            }
        }
        return reverseDiGraph;
    }



}
