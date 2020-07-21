package org.meoca.randoms.objectsupplier;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.meoca.randoms.datasuppliers.IDataSupplier;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class ObjectSupplier<X> implements IDataSupplier<X> {

    //------------------------------------------------------------
    // Field Data Supplier Subclass
    //
    //------------------------------------------------------------
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    public static class FieldDataSupplier<X> {
        private final Field field;
        private final Supplier<X> supplier;
    }


    //------------------------------------------------------------
    // Object Supplier
    //
    //------------------------------------------------------------
    private final Class<X> clazz;
    private final Collection<FieldDataSupplier> fieldSuppliers;

    @Override
    public X get(){
        try {
            final X x = this.clazz.newInstance();
            for(FieldDataSupplier fieldSupplier: fieldSuppliers){
                final Field f = fieldSupplier.field;
                f.setAccessible(true);
                f.set(x, fieldSupplier.supplier.get());
            }
            return x;
        }
        catch (Exception e){
            throw new RuntimeException("Failure Supplying Object", e);
        }
    }
}
