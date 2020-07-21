package org.meoca.randoms;

import org.junit.Test;
import org.meoca.randoms.datasuppliers.IDataSupplier;

import static org.junit.Assert.*;

public class DataSupplierTest {

    @Test
    public void testDouble() {
        double min = 100;
        double max = 1000;
        final IDataSupplier<Double> provider = RandomData.ValueSuppliers.NEW_DOUBLE_SUPPLIER()
                .min(min).max(max)
                .build();

        final double r1 = provider.get();
        final double r2 = provider.get();
        assertNotEquals(r1, r2);
        assertTrue(r1 >= min);
        assertTrue(r1 <= max);
    }

    @Test
    public void testInt() {
        int min = 1;
        int max = 20;
        final IDataSupplier<Integer> intProvider = RandomData.ValueSuppliers.NEW_INT_SUPPLIER()
                .min(min).max(max)
                .build();

        final int r1 = intProvider.get();
        final int r2 = intProvider.get();
        assertNotEquals(r1, r2);
        assertTrue(r1 >= min);
        assertTrue(r1 <= max);
    }

    @Test
    public void testIntNoRange() {
        int min = 1;
        int max = 1;
        final IDataSupplier<Integer> intProvider = RandomData.ValueSuppliers.NEW_INT_SUPPLIER()
                .min(min).max(max)
                .build();

        final int r1 = intProvider.get();
        final int r2 = intProvider.get();
        assertEquals(r1, min);
        assertEquals(r1, r2);
    }


}
