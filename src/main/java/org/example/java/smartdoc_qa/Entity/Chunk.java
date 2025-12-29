package org.example.java.smartdoc_qa.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Chunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String content;
    private int chunkIndex;
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

}
