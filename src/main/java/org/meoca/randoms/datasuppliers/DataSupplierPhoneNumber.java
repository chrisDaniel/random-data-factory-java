package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import org.meoca.randoms.datasets.DataWords;

@Builder
@Data
public class DataSupplierPhoneNumber implements IDataSupplier<String> {

    @Override
    public String get(){
        return ""
                + getDigit() + getDigit() + getDigit() + getDigit() + getDigit()
                + "-"
                + getDigit() + getDigit() + getDigit() + getDigit();
    }

    public Character getDigit() {
        return IDataSupplier.RANDOM_FROM_ARRAY(DataWords.numbers);
    }
}
