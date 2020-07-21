package org.meoca.randoms.objectsupplier;

import org.meoca.randoms.RandomData;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.meoca.randoms.objectsupplier.ObjectSupplier.FieldDataSupplier;

public class ObjectSupplierBuilder<X> {


    //------------------------------------------------------------
    // Builder
    //
    //------------------------------------------------------------
    private Class<X> clazz;
    private Map<String, Supplier> supplierOverridesByFieldName = new HashMap<>();
    private Map<Class, Supplier> supplierOverridesByFieldType = new HashMap<>();

    public ObjectSupplierBuilder(Class<X> clazz) {
        this.clazz = clazz;
    }

    public <F> ObjectSupplierBuilder<X> withSupplierForFieldName(String fieldName, Class<F> fieldTypeClass, Supplier<F> valueSupplier) {
        try {
            final Field f = this.clazz.getDeclaredField(fieldName);
            if(!f.getType().isAssignableFrom(fieldTypeClass)) throw new RuntimeException("Wrong Type");
            this.supplierOverridesByFieldName.put(fieldName, valueSupplier);
            return this;
        }
        catch (Exception e){
            throw new RuntimeException("Cant Find Field on Object", e);
        }
    }
    public <F> ObjectSupplierBuilder<X> withSupplierForFieldType(Class<F> fieldTypeClass, Supplier<F> valueSupplier) {
        this.supplierOverridesByFieldType.put(fieldTypeClass, valueSupplier);
        return this;
    }
    public ObjectSupplier<X> build() {

        //step 1...
        //get object field suppliers to use
        final List<ObjectSupplier.FieldDataSupplier> objectFieldSuppliers = new ArrayList<>();
        for (Field f : this.clazz.getDeclaredFields()) {
            if (this.supplierOverridesByFieldName.containsKey(f.getName())) {
                final FieldDataSupplier fieldSupplier = new FieldDataSupplier(f, this.supplierOverridesByFieldName.get(f.getName()));
                objectFieldSuppliers.add(fieldSupplier);
            }
            else if(this.supplierOverridesByFieldType.containsKey(f.getType())){
                final FieldDataSupplier fieldSupplier = new FieldDataSupplier(f, this.supplierOverridesByFieldType.get(f.getType()));
                objectFieldSuppliers.add(fieldSupplier);
            }
            else {
                final Supplier fieldValueSupplierF = GetDefaultFieldValueSupplier(f);
                final FieldDataSupplier fieldSupplier = new FieldDataSupplier(f, fieldValueSupplierF);
                objectFieldSuppliers.add(fieldSupplier);
            }
        }

        //finally...
        //return the supplier
        return new ObjectSupplier<X>(this.clazz, objectFieldSuppliers);
    }

    //------------------------------------------------------------
    // Static - Default Field Suppliers
    //
    //------------------------------------------------------------
    private static Supplier GetDefaultFieldValueSupplier(Field f){
        final Class fieldTypeClazz = f.getType();

        if(fieldTypeClazz.equals(Boolean.class)){
            return RandomData.ValueSuppliers.NEW_BOOLEAN_SUPPLIER().build();
        }
        if(fieldTypeClazz.equals(Double.class)){
            return RandomData.ValueSuppliers.NEW_DOUBLE_SUPPLIER().build();
        }
        if(fieldTypeClazz.isEnum()){
            return RandomData.ValueSuppliers.NEW_ENUM_VALUE_SUPPLIER().enumClass(fieldTypeClazz).build();
        }
        if(fieldTypeClazz.equals(Integer.class)){
            return RandomData.ValueSuppliers.NEW_INT_SUPPLIER().build();
        }
        if(fieldTypeClazz.equals(LocalDate.class)){
            return RandomData.ValueSuppliers.NEW_LOCAL_DATE_SUPPLIER().build();
        }
        if(fieldTypeClazz.equals(LocalDateTime.class)){
            return RandomData.ValueSuppliers.NEW_LOCAL_DATE_TIME_SUPPLIER().build();
        }
        if(fieldTypeClazz.equals(Long.class)){
            return RandomData.ValueSuppliers.NEW_LONG_SUPPLIER().build();
        }
        if(fieldTypeClazz.equals(String.class)){
            return RandomData.ValueSuppliers.NEW_WORD_SUPPLIER().build();
        }
        if(fieldTypeClazz.equals(ZonedDateTime.class)){
            return RandomData.ValueSuppliers.NEW_ZONED_DATE_TIME_SUPPLIER().build();
        }
        return () -> null;
    }
}
