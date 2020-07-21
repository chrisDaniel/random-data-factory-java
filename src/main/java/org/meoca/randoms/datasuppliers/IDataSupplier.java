package org.meoca.randoms.datasuppliers;

import java.util.function.Supplier;

public interface IDataSupplier<X> extends Supplier<X> {

    static Number RANDOM_NUMBER(Number min, Number max){
        return (Math.random() * ((max.doubleValue() - min.doubleValue()) + 1)) + min.doubleValue();
    }
    static <X> X RANDOM_FROM_ARRAY(X[] choices){
        if(choices==null || choices.length==0) return null;
        if(choices.length == 1) return choices[0];

        final int index = RANDOM_NUMBER(0, choices.length-1).intValue();
        return choices[index];
    }
}
