package com.itdom.graph;

/**
 * 加权无向边的实现
 */
public class Edge implements Comparable<Edge> {
    /**
     * 端点1
     */
    private int v;
    /**
     * 端点2
     */
    private int w;
    /**
     * 边的权重
     */
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that) {
        int cmp = 0;
        if (this.weight < that.weight) {
            cmp = -1;
        } else if (this.weight > that.weight) {
            cmp = 1;
        } else {
            cmp = 0;
        }
        return cmp;
    }

    /**
     * 获取边上的一个顶点
     *
     * @return
     */
    public int either() {
        return v;
    }

    /**
     * 获取边上除了顶点vertex外的另外顶点
     *
     * @param vertex
     * @return
     */
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else {
            return v;
        }

    }

    public double weight() {
        return weight;
    }
}

