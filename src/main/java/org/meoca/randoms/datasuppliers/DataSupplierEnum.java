package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class DataSupplierEnum<X extends Enum<?>> implements IDataSupplier<X> {

    @NonNull Class<X> enumClass;

    @Override
    public X get(){
        return IDataSupplier.RANDOM_FROM_ARRAY(enumClass.getEnumConstants());
    }
}
