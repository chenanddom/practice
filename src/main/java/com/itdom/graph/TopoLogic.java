package com.itdom.graph;

import com.itdom.linkedlist.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TopoLogic {
    private Stack<Integer> order;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Traffic_Project.class.getResourceAsStream("topologic.txt")));
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
        TopoLogic topoLogic = new TopoLogic(graph);
        Stack<Integer> order = topoLogic.order();
//遍历打印
        StringBuilder sb = new StringBuilder();
        for (Integer v : order) {
            sb.append(v+"->");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);


    }

    public TopoLogic(DiGraph graph) {
        DirectedCycle directedCycle = new DirectedCycle(graph);
        if (directedCycle.hashCycle()) {
            return;
        }
        DepthFirstOrder firstOrder = new DepthFirstOrder(graph);
        order = firstOrder.reversePost();
    }

    /**
     * 判断当前图是否有环
     *
     * @return
     */
    public boolean hashCycle() {
        return order == null;
    }

    /**
     * 获取所有拓扑排序的顶点
     * @return
     */
    public Stack<Integer> order() {
        return order;
    }
}
