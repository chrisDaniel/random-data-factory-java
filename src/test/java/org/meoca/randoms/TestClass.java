package org.meoca.randoms;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class TestClass {

    enum  TestEnum1{ EnumVal1, EnumVal2, EnumVal3};

    TestEnum1 enum1;
    String string1;
    String string2;
    Integer int1;
    Double double1;
    Long long1;
    LocalDate localDate;
    LocalDateTime localDateTime;
    ZonedDateTime zonedDateTime;
    TestClass2 testClass2;
}
