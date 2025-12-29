package org.example.java.smartdoc_qa.Repo;

import org.example.java.smartdoc_qa.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
}
