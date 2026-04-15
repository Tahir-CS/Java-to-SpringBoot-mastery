package com.tahir.milestone5;

import java.util.List;

// Record: concise immutable data model.
public record StudentRecord(String name, int age, double gpa, List<String> courses) {
}
