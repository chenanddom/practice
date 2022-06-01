package com.itdom.graph;

import com.itdom.linkedlist.Queue;

public class DeptFirstSearch {
    /**
     * 记录与顶点s相连的边的数量
     */
    private int count;
    /**
     * 记录顶点是否被搜索过
     */
    private boolean[] marked;

    public DeptFirstSearch(Graph graph,int s) {
        marked  = new boolean[graph.V()];
        count=0;
        dfs(graph,s);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0,5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,6);
        graph.addEdge(5,3);
        graph.addEdge(5,4);
        graph.addEdge(3,4);
        graph.addEdge(4,6);

        graph.addEdge(7,8);
        graph.addEdge(9,11);
        graph.addEdge(9,10);
        graph.addEdge(9,12);
        graph.addEdge(11,12);
        DeptFirstSearch deptFirstSearch = new DeptFirstSearch(graph, 0);
        System.out.println("与顶点-相通的顶点是:"+deptFirstSearch.count());
        System.out.println("顶点0与顶点5是否相通:"+deptFirstSearch.marked(5));
        System.out.println("顶点0与顶点7是否相通:"+deptFirstSearch.marked(7));
    }


    public void dfs(Graph graph,int s){
        marked[s]=true;
        Queue<Integer> queue = graph.adj(s);
        if (queue!=null){
            for (Integer w : queue) {
                if (!marked[w]) {
                    dfs(graph, w);
                }

            }
        }
        count++;
    }

    /**
     * 查看顶点v是否被搜索过
     * @param v
     * @return
     */
    public boolean marked(int v){
        return marked[v];
    }
    public int count(){
        return count;
    }


}
