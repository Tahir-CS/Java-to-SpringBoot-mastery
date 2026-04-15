package com.tahir.milestone5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Java object -> JSON (serialization).
        StudentRecord studentRecord = new StudentRecord(
                "Ali Ahmed",
                20,
                3.9,
                List.of("Math", "Physics", "CS101")
        );

        String recordJson = mapper.writeValueAsString(studentRecord);
        String prettyRecordJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentRecord);

        System.out.println("Record as JSON: " + recordJson);
        System.out.println("Pretty JSON:\n" + prettyRecordJson);

        // JSON -> Java object (deserialization) using DTO class.
        String incomingJson = """
                {
                  "name": "Sara Khan",
                  "age": 21,
                  "gpa": 3.7,
                  "courses": ["Chemistry", "Biology"]
                }
                """;

        StudentDTO dto = mapper.readValue(incomingJson, StudentDTO.class);
        System.out.println("Deserialized DTO: " + dto);
        System.out.println("First course: " + dto.getCourses().get(0));

        // HashMap -> JSON to show JSON is key/value based.
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("count", 2);
        response.put("students", List.of(studentRecord.name(), dto.getName()));

        String responseJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        System.out.println("Map as JSON:\n" + responseJson);
    }
}
