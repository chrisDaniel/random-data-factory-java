package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.function.Function;

import static java.time.temporal.ChronoUnit.DAYS;

@Builder
@Data
public class DataSupplierLocalDate implements IDataSupplier<LocalDate> {

    @NonNull @Builder.Default LocalDate minDate = LocalDate.MIN;
    @NonNull @Builder.Default LocalDate maxDate = LocalDate.MAX;
    @NonNull @Builder.Default Function<LocalDate, LocalDate> decorator = (x) -> x;

    @Override
    public LocalDate get(){
        final int daysBetween = ((Long) DAYS.between(minDate, maxDate)).intValue();
        final int randomToPlus = IDataSupplier.RANDOM_NUMBER(0, daysBetween).intValue();
        return decorator.apply(minDate.plusDays(randomToPlus));
    }
}
