package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.function.Function;

@Builder
@Data
public class DataSupplierFromArray<X> implements IDataSupplier<X> {

    @NonNull X[] allowedValues;
    @NonNull @Builder.Default Function<X, X> decorator = (x) -> x;

    @Override
    public X get(){
        return decorator.apply(IDataSupplier.RANDOM_FROM_ARRAY(allowedValues));
    }
}
