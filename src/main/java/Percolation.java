import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int OUTSIDE = -1;
    private final int virtualTop;
    private final int virtualBottom;
    private final int side;
    private final WeightedQuickUnionUF uf;
    private final boolean[] openIndexes;
    private int openCount = 0;

    public Percolation(int side) {
        if (side <= 0) throw new IllegalArgumentException("side's size should be > 0");
        this.side = side;
        virtualTop = 0;
        virtualBottom = this.side * this.side + 1;

        openIndexes = new boolean[virtualBottom + 1];
        open(virtualTop);
        open(virtualBottom);
        uf = new WeightedQuickUnionUF(virtualBottom + 1);
    }

    public void open(int row, int col) {
        int index = getIndex(row, col);
        if (!withinRange(index))
            throw new IllegalArgumentException("not in the range");

        if (!isOpen(index)) {
            open(index);
            for (int neighbour: getNeighbourIndexes(row, col)) {
                if (neighbour != OUTSIDE && isOpen(neighbour) && !uf.connected(neighbour, index)) {
                    uf.union(neighbour, index);
                }
            }
        }
    }

    private void open(int index) {
        openIndexes[index] = true;
        openCount++;
    }

    public boolean isOpen(int row, int col) {
        int index = getIndex(row, col);
        if (!withinRange(index))
            throw new IllegalArgumentException("not in the range");
        return isOpen(index);
    }

    private boolean isOpen(int index) {
        return openIndexes[index];
    }

    public boolean isFull(int row, int col) {
        int index = getIndex(row, col);
        if (!withinRange(index))
            throw new IllegalArgumentException("not in the range");
        return isOpen(index) && uf.connected(index, virtualTop);
    }

    public int numberOfOpenSites() {
        return openCount - 2;
    }

    public boolean percolates() {
        return uf.connected(virtualBottom, virtualTop);
    }

    private int[] getNeighbourIndexes(int row, int col) {

        int north = (row > 1) ? getIndex(row - 1, col) : virtualTop;
        int south = (row < side) ? getIndex(row + 1, col) : virtualBottom;
        int east = (col < side) ? getIndex(row, col + 1) : OUTSIDE;
        int west = (col > 1) ? getIndex(row, col - 1) : OUTSIDE;

        return new int[]{ north, south, east, west };
    }

    private int getIndex(int row, int col) {
        return (row - 1) * side + col;
    }

    private boolean withinRange(int index) {
        return index > 0 && index < virtualBottom;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        Percolation percolation = new Percolation(in.readInt());

        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            percolation.open(row, col);
        }

        System.out.println(percolation.openCount);
    }
}
