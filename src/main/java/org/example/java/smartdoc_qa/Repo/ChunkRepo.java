package org.example.java.smartdoc_qa.Repo;

import org.example.java.smartdoc_qa.Entity.Chunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChunkRepo extends JpaRepository<Chunk, Long> {
    List<Chunk> findByDocumentId(Long documentId);
    void deleteByDocumentId(Long id);
}
