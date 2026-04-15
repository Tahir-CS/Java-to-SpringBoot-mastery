package com.tahir.milestone5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JacksonDtoDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Java -> JSON.
        StudentDTO student = new StudentDTO(
                "Ali",
                20,
                3.9,
                List.of("Math", "CS101")
        );

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println("Serialized JSON:\n" + json);

        // JSON -> Java.
        String incoming = """
                {
                  "name": "Sara",
                  "age": 21,
                  "gpa": 3.7,
                  "courses": ["Chemistry", "Biology"]
                }
                """;

        StudentDTO received = mapper.readValue(incoming, StudentDTO.class);
        System.out.println("Deserialized object: " + received);
    }
}
