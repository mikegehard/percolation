import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PercolationTest {
    // Start of error cases

    @Test(expected = IllegalArgumentException.class)
    public void initialSizeEquals0() {
        Percolation perc = new Percolation(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheLowEndOfXAxis() {
        Percolation perc = new Percolation(10);

        perc.isOpen(0, 6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheHighEndOfXAxis() {
        Percolation perc = new Percolation(10);

        perc.isOpen(11, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheLowEndOfYAxis() {
        Percolation perc = new Percolation(10);

        perc.isOpen(6, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenOutOfBoundsToTheHighEndOfYAxis() {
        Percolation perc = new Percolation(10);

        perc.isOpen(1, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openOutOfBoundsToTheLowEndOfXAxis() {
        Percolation perc = new Percolation(10);

        perc.open(0, 6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openOutOfBoundsToTheHighEndOfXAxis() {
        Percolation perc = new Percolation(10);

        perc.open(11, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openOutOfBoundsToTheLowEndOfYAxis() {
        Percolation perc = new Percolation(10);

        perc.open(6, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openOutOfBoundsToTheHighEndOfYAxis() {
        Percolation perc = new Percolation(10);

        perc.open(1, 11);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheLowEndOfXAxis() {
        Percolation perc = new Percolation(10);

        perc.isFull(0, 6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheHighEndOfXAxis() {
        Percolation perc = new Percolation(10);

        perc.isFull(11, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheLowEndOfYAxis() {
        Percolation perc = new Percolation(10);

        perc.isFull(6, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullOutOfBoundsToTheHighEndOfYAxis() {
        Percolation perc = new Percolation(10);

        perc.isFull(1, 11);
    }

    // End of error cases

    @Test
    public void sizeOfOneWorksProperly() {
        Percolation perc = new Percolation(1);

        assertFalse(perc.percolates());

        perc.open(1, 1);

        assertTrue(perc.percolates());
    }

    @Test
    public void newPercolationIsAlwaysClosed() {
        Percolation perc = new Percolation(2);

        assertFalse(perc.isOpen(1, 1));
        assertFalse(perc.isOpen(1, 2));
        assertFalse(perc.isOpen(2, 1));
        assertFalse(perc.isOpen(2, 2));
    }

    /*
    0
1 2 3
4 5 6
7 8 9
10
     */

    @Test
    public void openingASquare() {
        Percolation perc = new Percolation(3);

        assertFalse(perc.isFull(1, 2));
        perc.open(1, 2);
        assertTrue(perc.isOpen(1, 2));
        assertTrue(perc.isFull(1, 2));

        assertFalse(perc.isFull(2, 1));
        perc.open(2, 1);
        assertTrue(perc.isOpen(2, 1));
        assertFalse(perc.isFull(2, 1));

        assertFalse(perc.isFull(2, 2));
        perc.open(2, 2);
        assertTrue(perc.isOpen(2, 2));
        assertTrue(perc.isFull(2, 2));

        assertFalse(perc.isFull(3, 2));
        perc.open(3, 2);
        assertTrue(perc.isOpen(3, 2));
        assertTrue(perc.isFull(3, 2));
    }

    @Test
    public void percolatesWithABigSizedSample() {
        Percolation perc = new Percolation(6);

        perc.open(1, 6);
        perc.open(2, 6);
        perc.open(3, 6);
        perc.open(4, 6);
        perc.open(5, 6);
        perc.open(5, 5);
        perc.open(4, 4);
        perc.open(3, 4);
        perc.open(2, 4);
        perc.open(2, 3);
        perc.open(2, 2);
        perc.open(2, 1);
        perc.open(3, 1);
        perc.open(4, 1);
        perc.open(5, 1);
        perc.open(5, 2);
        perc.open(6, 2);
        perc.open(5, 4);

        assertTrue(perc.percolates());
    }

    @Test
    public void testBackwashPrevention(){
        Percolation perc = new Percolation(3);
        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(3, 3);
        perc.open(3, 1);
        assertFalse(perc.isFull(3,1));
    }
}
