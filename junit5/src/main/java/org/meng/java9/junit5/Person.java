package org.meng.java9.junit5;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Person {
    private String firstName;
    private String lastName;
}
