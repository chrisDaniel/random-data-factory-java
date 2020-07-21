package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;
import java.util.function.Function;

import static java.time.temporal.ChronoUnit.MINUTES;

@Builder
@Data
public class DataSupplierZonedDateTime implements IDataSupplier<ZonedDateTime> {

    @NonNull @Builder.Default ZonedDateTime minDate = ZonedDateTime.now().minusDays(0);
    @NonNull @Builder.Default ZonedDateTime maxDate = ZonedDateTime.now().plusDays(70);
    @NonNull @Builder.Default Function<ZonedDateTime, ZonedDateTime> decorator = (x) -> x;
    
    @Override
    public ZonedDateTime get(){
        final int minutesBetween = ((Long) MINUTES.between(minDate, maxDate)).intValue();
        final int randomToPlus = IDataSupplier.RANDOM_NUMBER(0, minutesBetween).intValue();
        return decorator.apply(minDate.plusMinutes(randomToPlus));
    }
}
