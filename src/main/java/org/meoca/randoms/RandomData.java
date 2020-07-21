package org.meoca.randoms;

import org.meoca.randoms.datasets.DataAddresses;
import org.meoca.randoms.datasets.DataNames;
import org.meoca.randoms.datasets.DataWebContent;
import org.meoca.randoms.datasets.DataWords;
import org.meoca.randoms.objectsupplier.ObjectSupplierBuilder;
import org.meoca.randoms.datasuppliers.*;

public class RandomData {

    public interface CommonDataSets {
        Boolean[] booleans = { Boolean.TRUE, Boolean.FALSE };

        String[] addressCities = DataAddresses.cities;
        String[] addressStates = DataAddresses.states;
        
        String[] namesFirst = DataNames.firstNames;
        String[] namesLast = DataNames.lastNames;
        String[] namesPrefixes = DataNames.prefixes;
        String[] namesSuffixes = DataNames.suffixes;

        String[] webEmailHosts = DataWebContent.emailHosts;
        String[] webTopLevelDomains = DataWebContent.tlds;

        Character[] letters = DataWords.letters;
        Character[] numbers = DataWords.numbers;
        String[] words = DataWords.words;
    }

    public interface ValueSuppliers {

        static DataSupplierFromArray.DataSupplierFromArrayBuilder NEW_ADDRESS_CITY_SUPPLIER() {
            return DataSupplierFromArray.builder().allowedValues(CommonDataSets.addressCities);
        }

        static DataSupplierFromArray.DataSupplierFromArrayBuilder<String> NEW_ADDRESS_STATE_SUPPLIER() {
            final DataSupplierFromArray.DataSupplierFromArrayBuilder<String> builder = DataSupplierFromArray.builder();
            return builder.allowedValues(CommonDataSets.addressStates);
        }

        static DataSupplierZip.DataSupplierZipBuilder NEW_ADDRESS_ZIP_SUPPLIER() {
            return DataSupplierZip.builder();
        }

        static DataSupplierFromArray.DataSupplierFromArrayBuilder<Boolean> NEW_BOOLEAN_SUPPLIER() {
            final DataSupplierFromArray.DataSupplierFromArrayBuilder<Boolean> builder = DataSupplierFromArray.builder();
            return builder.allowedValues(CommonDataSets.booleans);
        }

        static DataSupplierNumber.DataSupplierNumberBuilder<Double> NEW_DOUBLE_SUPPLIER() {
            final DataSupplierNumber.DataSupplierNumberBuilder<Double> builder = DataSupplierNumber.builder();
            return builder.castFunction(DataSupplierNumber.CAST_FUNCTION_DOUBLE);
        }

        static DataSupplierEnum.DataSupplierEnumBuilder NEW_ENUM_VALUE_SUPPLIER() {
            return DataSupplierEnum.builder();
        }

        static <X> DataSupplierFromArray.DataSupplierFromArrayBuilder<X> NEW_FROM_ARRAY_SUPPLIER(Class<X> clazz) {
            final DataSupplierFromArray.DataSupplierFromArrayBuilder<X> builder = DataSupplierFromArray.builder();
            return builder;
        }

        static DataSupplierNumber.DataSupplierNumberBuilder<Integer> NEW_INT_SUPPLIER() {
            final DataSupplierNumber.DataSupplierNumberBuilder<Integer> builder = DataSupplierNumber.builder();
            return builder.castFunction(DataSupplierNumber.CAST_FUNCTION_INT);
        }

        static DataSupplierLocalDate.DataSupplierLocalDateBuilder NEW_LOCAL_DATE_SUPPLIER() {
            return DataSupplierLocalDate.builder();
        }

        static DataSupplierLocalDateTime.DataSupplierLocalDateTimeBuilder NEW_LOCAL_DATE_TIME_SUPPLIER() {
            return DataSupplierLocalDateTime.builder();
        }

        static DataSupplierNumber.DataSupplierNumberBuilder<Long> NEW_LONG_SUPPLIER() {
            final DataSupplierNumber.DataSupplierNumberBuilder<Long> builder = DataSupplierNumber.builder();
            return builder.castFunction(DataSupplierNumber.CAST_FUNCTION_LONG);
        }

        static DataSupplierSentance.DataSupplierSentanceBuilder NEW_SENTENCE_SUPPLIER() {
            return DataSupplierSentance.builder().allowedWords(DataWords.words);
        }

        static DataSupplierFromArray.DataSupplierFromArrayBuilder<String> NEW_WORD_SUPPLIER() {
            final DataSupplierFromArray.DataSupplierFromArrayBuilder<String> builder = DataSupplierFromArray.builder();
            return builder.allowedValues(CommonDataSets.words);
        }

        static DataSupplierFromArray.DataSupplierFromArrayBuilder<String> NEW_URL_SUPPLIER() {
            final DataSupplierFromArray.DataSupplierFromArrayBuilder<String> builder = DataSupplierFromArray.builder();
            builder.allowedValues(CommonDataSets.words);
            builder.decorator((x) -> "www." + x + ".com");
            return builder;
        }

        static DataSupplierZonedDateTime.DataSupplierZonedDateTimeBuilder NEW_ZONED_DATE_TIME_SUPPLIER() {
            return DataSupplierZonedDateTime.builder();
        }

        static DataSupplierUUID.DataSupplierUUIDBuilder NEW_UUID_SUPPLIER() {
            return DataSupplierUUID.builder();
        }
    }

    public interface ObjectSuppliers {

        static <X> ObjectSupplierBuilder<X> NEW_OBJECT_SUPPLIER(Class<X> clazz) {
            return new ObjectSupplierBuilder<X>(clazz);
        }
    }
}
