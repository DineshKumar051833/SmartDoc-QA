package org.example.java.smartdoc_qa.Controller;
import java.util.List;
import org.example.java.smartdoc_qa.Entity.Chunk;
import org.example.java.smartdoc_qa.Entity.Document;
import org.example.java.smartdoc_qa.Entity.DocumentResponse;
import org.example.java.smartdoc_qa.Service.ChunkService;
import org.example.java.smartdoc_qa.Service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;
    private final ChunkService chunkService;

    public DocumentController(DocumentService documentService, ChunkService chunkService) {
        this.documentService = documentService;
        this.chunkService = chunkService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file) throws Exception {
        Document document = documentService.saveDocument(file);
        chunkService.saveChunks(document.getChunks());
        return ResponseEntity.ok(document);  // returns 200 OK with the document JSON
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getDocuments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable Long id){
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    @GetMapping("/{id}/chunks")
    public ResponseEntity<List<Chunk>> getChunksByDocumentId(@PathVariable Long id){
        return ResponseEntity.ok(chunkService.getChunksByDocumentId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id){
        chunkService.deleteChunksByDocumentId(id);
        documentService.deleteDocumentById(id);
        return ResponseEntity.noContent().build();
    }
}