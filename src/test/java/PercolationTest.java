import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PercolationTest {

    @Test
    public void newPercolationIsAlwaysFull() {
        Percolation perc = new Percolation(2);

        assertTrue(perc.isFull(1, 1));
        assertTrue(perc.isFull(1, 2));
        assertTrue(perc.isFull(2, 1));
        assertTrue(perc.isFull(2, 2));

        assertFalse(perc.isOpen(1, 1));
        assertFalse(perc.isOpen(1, 2));
        assertFalse(perc.isOpen(2, 1));
        assertFalse(perc.isOpen(2, 2));
    }

    @Test
    public void openingASquare() {
        Percolation perc = new Percolation(2);
        perc.open(1, 2);
        assertTrue(perc.isOpen(1, 2));
    }

    @Test
    public void percolatesWithABigSizedSample() {
        Percolation perc = new Percolation(4);

        perc.open(1, 1);
        perc.open(1, 2);
        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(3, 3);
        perc.open(3, 2);
        perc.open(4, 2);
        perc.open(4, 3);
        perc.open(4, 4);

        assertTrue(perc.percolates());
    }
}
