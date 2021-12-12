package test;

import java.util.Arrays;

public class NodeMap {
    public static void main(String[] args) {
        NodeMap map = new NodeMap();
        int[][] ints = {
                {0,1},
                {0,2},
                {0,3},
                {0,4},
                {2,1},
                {3,2},
                {4,3},
        };
        int[] a = map.findOrder(5, ints);
        for (int i=0; i<a.length; i++) {
            System.out.println("a[i] = " + a[i]);
        }
    }


    // 返回最长边
    public int[] findOrder(int nodeCount, int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return null;
        }
        // 找入度为0
        int start = 0;
        for (int i = 0; i < edges.length; i++) {
            boolean flag = false;
            for (int j = 0; j < edges.length; j++) {
                if (edges[i][0] == edges[j][1]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                start = i;
            }
        }
        // 记录每条路径，每条路径，为records的一行，
        // 每行的第一个数值为该路径的节点数
        int[][] records = new int[nodeCount-1][];
        records[0] = new int[nodeCount+1];
        records[0][0] = 2;
        records[0][1] = edges[start][0];
        records[0][2] = edges[start][1];
        handle(edges,records,start,0);
        // 在第一列找最大值，同时记录下第几行
        int max = 0;
        int index = 0;
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && records[i][0] > max) {
                index = i;
                max = records[i][0];
            }
        }
        return Arrays.copyOfRange(records[index],1, records[index][0]+1);
    }

    // 回溯法记录每条路径
    public void handle(int[][] edges, int[][] records, int current, int index) {
        int recCount = records[index][0];
        int next = edges[current][1];
        int count = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == next) {
                count++;
                // 不止一个，则意味着有多条路径
                if (count > 1) {
                    // 拷贝index列到index+1列
                    records[index+1] = Arrays.copyOf(records[index], records[index].length);
                    index++;
                }
                records[index][0] = recCount+1;
                records[index][recCount+1] = edges[i][1];
                handle(edges, records, i, index);
            }
        }
    }
}
