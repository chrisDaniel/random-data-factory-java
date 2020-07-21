package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.function.Function;

import static java.time.temporal.ChronoUnit.MINUTES;

@Builder
@Data
public class DataSupplierLocalDateTime implements IDataSupplier<LocalDateTime> {

    @NonNull @Builder.Default LocalDateTime minDate = LocalDateTime.MIN;
    @NonNull @Builder.Default LocalDateTime maxDate = LocalDateTime.MAX;
    @NonNull @Builder.Default Function<LocalDateTime, LocalDateTime> decorator = (x) -> x;
    
    @Override
    public LocalDateTime get(){
        final int minutesBetween = ((Long) MINUTES.between(minDate, maxDate)).intValue();
        final int randomToPlus = IDataSupplier.RANDOM_NUMBER(0, minutesBetween).intValue();
        return decorator.apply(minDate.plusMinutes(randomToPlus));
    }
}
