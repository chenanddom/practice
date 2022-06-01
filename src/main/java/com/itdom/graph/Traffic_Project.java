package com.itdom.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 交通畅通工程的实例
 */
public class Traffic_Project {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Traffic_Project.class.getResourceAsStream("traffic_project.txt")));
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
        for (int i = 0; i <roadNumber ; i++) {
            String[] points = reader.readLine().split(" ");
            Integer v = Integer.valueOf(points[0]);
            Integer w = Integer.valueOf(points[1]);
            graph.addEdge(v,w);
        }
        DeptFirstSearch deptFirstSearch = new DeptFirstSearch(graph, 9);
        System.out.println(deptFirstSearch.marked(8));
        System.out.println(deptFirstSearch.marked(10));

    }
}
