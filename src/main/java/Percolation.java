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
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Initial size must be greater than 0.");
        }

        int totalNodes = initialSize * initialSize + 2;
        bottomVirtualNodeIndex = totalNodes - 1;

        size = initialSize;
        siteOpen = new boolean[totalNodes];

        uf = new WeightedQuickUnionUF(totalNodes);
    }

    public boolean isFull(int down, int across) {
        return isOpen(down, across) && uf.connected(topVirtualNodeIndex, coordinatesToIndex(down, across));
    }

    public boolean isOpen(int down, int across) {
        if (down <= 0 || across <= 0 || down > size || across > size) {
            throw new IndexOutOfBoundsException();
        }
        return siteOpen[coordinatesToIndex(down, across)];
    }

    public void open(int down, int across) {
        markOpen(down, across);

        if (down == 1) {
            uf.union(topVirtualNodeIndex, coordinatesToIndex(down, across));
        }

        if (down == size) {
            uf.union(bottomVirtualNodeIndex, coordinatesToIndex(down, across));
        }

        if (down != size) {
            if (isOpen(down + 1, across)) {
                uf.union(coordinatesToIndex(down, across), coordinatesToIndex(down + 1, across));
            }
        }

        if (down != 1) {
            if (isOpen(down - 1, across)) {
                uf.union(coordinatesToIndex(down, across), coordinatesToIndex(down - 1, across));
            }
        }

        if (across != size) {
            if (isOpen(down, across + 1)) {
                uf.union(coordinatesToIndex(down, across), coordinatesToIndex(down, across + 1));
            }
        }

        if (across != 1) {
            if (isOpen(down, across - 1)) {
                uf.union(coordinatesToIndex(down, across), coordinatesToIndex(down, across - 1));
            }
        }
    }

    public boolean percolates() {
        return uf.connected(topVirtualNodeIndex, bottomVirtualNodeIndex);
    }

    private int coordinatesToIndex(int down, int across) {
        return ((down - 1) * size) + across;
    }

    private void markOpen(int down, int across) {
        siteOpen[coordinatesToIndex(down, across)] = true;
    }
}
