package org.example.java.smartdoc_qa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private LocalDateTime uploadedTime;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Chunk> chunks;

}
