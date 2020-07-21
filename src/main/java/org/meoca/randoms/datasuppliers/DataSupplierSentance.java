package org.meoca.randoms.datasuppliers;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.function.Function;

@Data
@Builder
public class DataSupplierSentance implements IDataSupplier<String> {

    public static Function<String, String> decoratorCapFirstLetter = (String x) -> {
        return ((Character) x.charAt(0)).toString().toUpperCase() +
                x.substring(1).toLowerCase() + ".";
    };


    @NonNull String[] allowedWords;
    @NonNull @Builder.Default Number minLength = 20;
    @NonNull @Builder.Default Number maxLength = 100;
    @NonNull @Builder.Default Function<String, String> decoratorFunction = DataSupplierSentance.decoratorCapFirstLetter;

    @Override
    public String get(){
        final int length = IDataSupplier.RANDOM_NUMBER(minLength, maxLength).intValue();
        return build("", length);
    }

    private String build(String str, int length){
        if(null == str) throw new RuntimeException("Recursive Function needs to start with non null");

        final String updStr = str + IDataSupplier.RANDOM_FROM_ARRAY(allowedWords);

        if(updStr.length() > length){
            return updStr.substring(0,length);
        }
        return build(updStr, length);
    }
}
