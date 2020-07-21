package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class DataSupplierUUID implements IDataSupplier<String> {

    @Override
    public String get(){
        return UUID.randomUUID().toString();
    }
}
