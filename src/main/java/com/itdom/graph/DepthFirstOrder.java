package com.itdom.graph;

import com.itdom.linkedlist.Stack;

/**
 * 顶点排序
 */
public class DepthFirstOrder {
    public static void main(String[] args) {

    }
    private boolean[] marked;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(DiGraph graph) {
        marked = new boolean[graph.V()];
        reversePost = new Stack<>();
        for (int i = 0; i < graph.V(); i++) {
            dfs(graph,i);
        }

    }
    public void dfs(DiGraph graph,int v){
        marked[v]=true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]){
                dfs(graph,w);
            }
        }
    reversePost.push(v);
    }

    //获取顶点线性序列
    public Stack<Integer> reversePost(){
        return reversePost;
    }


}
