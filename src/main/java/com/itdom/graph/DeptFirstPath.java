package com.itdom.graph;

import com.itdom.linkedlist.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeptFirstPath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Traffic_Project.class.getResourceAsStream("road_find.txt")));
        String line = null;
        /**
         * 获取城市道路数据
         */
        Integer cityNumber = Integer.valueOf(reader.readLine());
        /**
         * 获取道路的数目
         */
        Integer roadNumber = Integer.valueOf(reader.readLine());
        Graph graph = new Graph(cityNumber);
        for (int i = 0; i < roadNumber; i++) {
            String[] points = reader.readLine().split(" ");
            Integer v = Integer.valueOf(points[0]);
            Integer w = Integer.valueOf(points[1]);
            graph.addEdge(v, w);
        }
        StringBuilder sb = new StringBuilder();
        DeptFirstPath deptFirstSearch = new DeptFirstPath(graph, 0);
        Stack<Integer> stack = deptFirstSearch.pathTo(4);
        for (Integer p : stack) {
            sb.append(p);
            sb.append("-");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    /**
     * 标识端点是否被搜索过。
     */
    private boolean[] marked;
    /**
     * 标识起点位置
     */
    private int s;
    /**
     * 索引代表顶点，值代表从起点s到当前顶点路径上的最后一个顶点
     */
    private int[] edgeTo;

    public DeptFirstPath(Graph graph, int s) {
        marked = new boolean[graph.V()];
        this.s = s;
        edgeTo = new int[graph.V()];
        dfs(graph, s);
    }

    public void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean hashPathTo(int v) {
        return marked[v];
    }

    public Stack<Integer> pathTo(int v) {
        if (!hashPathTo(v)) {
            return null;
        }
        Stack<Integer> paths = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            paths.push(i);
        }
        paths.push(s);
        return paths;
    }
}
