package com.itdom.graph;

import com.itdom.linkedlist.Queue;
import com.itdom.prioprityqueue.IndexMinPriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimMST {
    public static void main(String[] args) throws IOException {
        //创建输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(PrimMST.class.getResourceAsStream("min_create_tree.txt")));
        //读取顶点数目，初始化EdgeWeightedGraph图
        int number = Integer.parseInt(reader.readLine());
        EdgeWeightedGraph G = new EdgeWeightedGraph(number);
        //读取边的数目
        int edgeNumber = Integer.parseInt(reader.readLine());
        //循环读取每一条边，并调用addEdge方法
        for (int i = 0; i < edgeNumber; i++) {
            String line = reader.readLine();
            int v = Integer.parseInt(line.split(" ")[0]);
            int w = Integer.parseInt(line.split(" ")[1]);
            double weight = Double.parseDouble(line.split(" ")[2]);
            G.addEdge(new Edge(v, w, weight));
        }
        //构建PrimMST对象
        PrimMST mst = new PrimMST(G);
        //获取最小生成树的边
        Queue<Edge> edges = mst.edges();
        //打印输出
        for (Edge edge : edges) {
            if (edge != null) {
                System.out.println(edge.either() + "-" + edge.other(edge.either()) + "::" +
                        edge.weight());
            }
        }
    }

    /**
     * 索引代表顶点，值标识当前顶点和最小生成树之间的最短路径
     */
    private Edge[] edgeTo;
    /**
     * 索引代表顶点，值标识当前顶点和最小生成树之间的最短边的权重
     */
    private double[] distTo;
    /**
     * 索引代表顶点，如果当前订单已经在树中则标识为true，否则为false
     */
    private boolean[] marked;
    /**
     * 存放树中顶点与非树中顶点之间的有效横切边
     */
    private IndexMinPriorityQueue<Double> pq;


    public PrimMST(EdgeWeightedGraph graph) {
        //创建一个和图的顶带你数一样大小的Edge数组，标识边
        this.edgeTo = new Edge[graph.V()];
        //这个double数组标识的是权重，并且初始化数组中的内容为无穷大，即不存在这样的边
        this.distTo = new double[graph.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        //表示当前顶点是否已经在树中。
        this.marked = new boolean[graph.V()];
        //创建一个和图的顶带你数一样大小的索引优先队列，存储有效横切边
        this.pq = new IndexMinPriorityQueue<>(graph.V());
        //默认让顶点0进入树中，但是0顶点目前没有与树中其他的顶点相连接，因为初始化distTo[0]=0.0
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(graph, pq.delMin());
        }

    }

    public Queue<Edge> edges() {
        Queue<Edge> edges = new Queue<>();
        for (int i = 0; i < marked.length; i++) {
             if (edgeTo[i] != null) {
                edges.enqueue(edgeTo[i]);
            }
        }
        return edges;
    }

    //将顶带你v添加到最小生成树中，并且更新数据
    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            //获取边e的另一个顶点w
            int w = e.other(v);
            //检测是否已经在树中，如果在，则继续下一次循环，如果不在，则需要修正当前顶带你w距离最小生成树的
            // 最小边edgeTo[w]以及它的权重distTo[w]，还有有效横切边也需要修正
            if (marked[w]) {
                continue;
            }
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contain(w)) {
                    pq.changeItem(w, e.weight());
                } else {
                    pq.insert(w, e.weight());
                }
            }
        }
    }
}
