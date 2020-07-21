package org.meoca.randoms;

import org.junit.Test;
import org.meoca.randoms.objectsupplier.ObjectSupplier;

import static org.junit.Assert.*;

public class ObjectSupplierTest {
    
    @Test
    public void testObjectFactory(){

        final ObjectSupplier<TestClass2> supplierTestClass2 = RandomData.ObjectSuppliers.NEW_OBJECT_SUPPLIER(TestClass2.class)
                .build();

        final ObjectSupplier<TestClass> supplierTestClass = RandomData.ObjectSuppliers.NEW_OBJECT_SUPPLIER(TestClass.class)
                .withSupplierForFieldName("string1", String.class, () -> "Overriden Field 1")
                .withSupplierForFieldType(TestClass2.class, supplierTestClass2)
                .build();
        
        final TestClass o1 = supplierTestClass.get();
        System.out.println("Suplied Object 1: " + o1);

        final TestClass o2 = supplierTestClass.get();
        System.out.println("Suplied Object 2: " + o2);

        assertNotEquals(o1, o2);
        assertEquals(o1.string1, o2.string1);
        assertNotEquals(o1.string2, o2.string2);
    }
}
