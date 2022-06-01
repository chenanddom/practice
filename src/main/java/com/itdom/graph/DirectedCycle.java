package com.itdom.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 检测有向图是否有环，需要以每一个顶点为开头做一次搜索
 */
public class DirectedCycle {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Traffic_Project.class.getResourceAsStream("cycle_text.txt")));
        String line = null;
        /**
         * 获取城市道路数据
         */
        Integer cityNumber = Integer.valueOf(reader.readLine());
        /**
         * 获取道路的数目
         */
        Integer roadNumber = Integer.valueOf(reader.readLine());
        DiGraph graph = new DiGraph(cityNumber);
        for (int i = 0; i < roadNumber; i++) {
            String[] points = reader.readLine().split(" ");
            Integer v = Integer.valueOf(points[0]);
            Integer w = Integer.valueOf(points[1]);
            graph.addEdge(v, w);
        }
        DirectedCycle directedCycle = new DirectedCycle(graph);
        System.out.println("是否有环?"+(directedCycle.hashCycle?"有环":"无环"));
    }

    //索引代表顶带你，值标识当前订单是否已经被搜索过了
    private boolean[] marked;
    //记录图中是否有环
    private boolean hashCycle;
    //索引代表顶点，使用栈的思想，记录当前顶带你有没有已经处于正在搜索的路径上
    private boolean[] onStack;

    public DirectedCycle(DiGraph graph) {
        marked = new boolean[graph.V()];
        onStack = new boolean[graph.V()];
        this.hashCycle = false;

        //遍历搜索图中的每一个顶带你
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }


    }

    /**
     * 深度优先有所，检测图中是否有环
     *
     * @param graph
     * @param v
     */
    public void dfs(DiGraph graph, int v) {
        //标记当前顶点已经被搜索
        marked[v] = true;
        //当前结点进栈
        onStack[v] = true;
        //遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : graph.adj(v)) {
            //如果当前顶点没有被搜索过，则，需要递归搜索与w顶点相通的其他顶点
            if (!marked[w]) {
                dfs(graph, w);
            }
            //如果顶点w已经被搜索过，则查看当前订单w是否在栈中如果在就证明图中有环，修改hasCycle标记，结束循环
            if (onStack[w]) {
                hashCycle = true;
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hashCycle() {
        return this.hashCycle;
    }
}
