/*
for 2x2
0
1 2
3 4
5

for 3x3
0
1 2 3
4 5 6
7 8 9
10

for 4x4
0
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
17
 */

public class Percolation {
    private boolean[] siteOpen;
    private WeightedQuickUnionUF uf;
    private int size;
    private int topVirtualNodeIndex = 0;
    private int bottomVirtualNodeIndex;


    public Percolation(int initialSize) {
        int totalNodes = initialSize * initialSize + 2;
        bottomVirtualNodeIndex = totalNodes - 1;

        size = initialSize;
        siteOpen = new boolean[totalNodes];

        uf = new WeightedQuickUnionUF(totalNodes);
        for (int i = 1; i <= initialSize; i++) {
            uf.union(topVirtualNodeIndex, i);
            uf.union(bottomVirtualNodeIndex, bottomVirtualNodeIndex - i);
        }
    }

    public boolean isFull(int x, int y) {
        return isOpen(x, y) && uf.connected(topVirtualNodeIndex, coordinatesToIndex(x, y));
    }

    public void open(int x, int y) {
        markOpen(x, y);

        if (x != size) {
            if (isOpen(x + 1, y)) {
                uf.union(coordinatesToIndex(x, y), coordinatesToIndex(x + 1, y));
            }
        }

        if (x != 1) {
            if (isOpen(x - 1, y)) {
                uf.union(coordinatesToIndex(x, y), coordinatesToIndex(x - 1, y));
            }
        }

        if (y != size) {
            if (isOpen(x, y + 1)) {
                uf.union(coordinatesToIndex(x, y), coordinatesToIndex(x, y + 1));
            }
        }

        if (y != 1) {
            if (isOpen(x, y - 1)) {
                uf.union(coordinatesToIndex(x, y), coordinatesToIndex(x, y - 1));
            }
        }
    }

    public boolean isOpen(int x, int y) {
        return siteOpen[coordinatesToIndex(x, y)];
    }

    public boolean percolates() {
        return uf.connected(topVirtualNodeIndex, bottomVirtualNodeIndex);
    }

    private int coordinatesToIndex(int x, int y) {
        if (y == 1) {
            return x;
        } else {
            return (y - 1) * size + x;
        }
    }

    private void markOpen(int x, int y) {
        siteOpen[coordinatesToIndex(x, y)] = true;
    }
}
