package demo.UnionFind;

public class UnionFind {
    int[] parent;

    UnionFind(int n){
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x){
        if (parent[x] != x) {
            parent[x] = find(parent[x]);// 路径压缩
        }
        return parent[x];
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}
