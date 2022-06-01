package com.itdom.graph;

import com.itdom.linkedlist.Queue;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 6);
        graph.addEdge(5, 3);
        graph.addEdge(5, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);

        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
        graph.addEdge(11, 12);
        BreadthFirstSearch deptFirstSearch = new BreadthFirstSearch(graph, 0);
        System.out.println("与顶点-相通的顶点是:" + deptFirstSearch.count());
        System.out.println("顶点0与顶点5是否相通:" + deptFirstSearch.marked(5));
        System.out.println("顶点0与顶点7是否相通:" + deptFirstSearch.marked(7));
    }

    /**
     * 标记顶点是否被搜索过
     */
    private boolean[] marked;
    /**
     * 记录边的条数
     */
    private int count;
    /**
     * 记录待搜索的顶点
     */
    private Queue<Integer> waitSeach;

    public BreadthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        count = 0;
        waitSeach = new Queue<>();
        bfs(graph, s);
    }

    public void bfs(Graph graph, int v) {
        marked[v] = true;
        waitSeach.enqueue(v);
        while (!waitSeach.isEmpty()) {
            Integer waitP = waitSeach.dequeue();
            /**
             * 获取待搜索的顶点的邻接表
             */
            for (Integer w : graph.adj(waitP)) {
                if (!marked[w]) {
                    bfs(graph, w);
                }
            }

        }
        count++;
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
