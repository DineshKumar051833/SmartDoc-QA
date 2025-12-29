package org.example.java.smartdoc_qa.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentResponse {

    private long id;
    private String fileName;
}