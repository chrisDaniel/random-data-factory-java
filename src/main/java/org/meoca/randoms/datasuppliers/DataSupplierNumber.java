package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.function.Function;

@Builder
@Data
public class DataSupplierNumber<X extends Number> implements IDataSupplier<X> {

    public static Function<Number, Number> CAST_FUNCTION_IDENTITY = (n) -> n;
    public static Function<Number, Integer> CAST_FUNCTION_INT = (n) -> n.intValue();
    public static Function<Number, Double> CAST_FUNCTION_DOUBLE = (n) -> n.doubleValue();
    public static Function<Number, Long> CAST_FUNCTION_LONG = (n) -> n.longValue();

    @Builder.Default Number min = Double.MIN_VALUE;
    @Builder.Default Number max = Double.MAX_VALUE;
    @NonNull final Function<Number, X> castFunction;
    @NonNull @Builder.Default Function<X, X> decorator = (x) -> x;

    @Override
    public X get(){
        return castFunction.apply(IDataSupplier.RANDOM_NUMBER(min, max));
    }
}
