package org.example.java.smartdoc_qa.Service;

import org.example.java.smartdoc_qa.Entity.Chunk;
import org.example.java.smartdoc_qa.Entity.Document;
import org.example.java.smartdoc_qa.Entity.DocumentResponse;
import org.example.java.smartdoc_qa.Repo.DocumentRepo;
import org.example.java.smartdoc_qa.Utility.DocumentParserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DocumentService {

    private final DocumentRepo documentRepo;
    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024; // 20 MB

    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public Document saveDocument(MultipartFile file) throws Exception {
        if (file.getSize() > MAX_FILE_SIZE)
            throw new RuntimeException("File size exceeds 20MB limit");

        String extractedText = DocumentParserUtil.extractText(file);

        if (extractedText == null || extractedText.trim().isEmpty())
            throw new RuntimeException("Unable to extract text from the uploaded file");

        List<Chunk> chunkList = splitIntoChunks(extractedText,500);
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setUploadedTime(LocalDateTime.now());

        for(Chunk chunk: chunkList){
            chunk.setDocument(document);
        }
        document.setChunks(chunkList);
        return documentRepo.save(document);
    }

    public List<Chunk> splitIntoChunks(String text, int chunkSize){
        List<Chunk> chunkList = new ArrayList<>();
        int start = 0;
        int end;
        int chunkIndex = 0;
        while (start<text.length()){
            end = Math.min(start+chunkSize, text.length());
            String chunkText = text.substring(start,end);
            Chunk chunk = new Chunk();
            chunk.setContent(chunkText);
            chunk.setChunkIndex(chunkIndex++);
            chunkList.add(chunk);
            start=end;
        }
        return chunkList;
    }

    public List<DocumentResponse> getDocuments(){
        return documentRepo.findAll()
                .stream()
                .map(document -> new DocumentResponse(
                        document.getId(),
                        document.getFileName()
                ))
                .toList();
    }

    public DocumentResponse getDocumentById(Long id){
        Document document=  documentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Document not found with ID: "+id));
        return new DocumentResponse(document.getId(), document.getFileName());
    }

    public void deleteDocumentById(Long id){
        documentRepo.deleteById(id);
    }
}
