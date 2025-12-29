package org.example.java.smartdoc_qa.Service;

import org.example.java.smartdoc_qa.AI.OllamaClient;
import org.example.java.smartdoc_qa.Entity.Chunk;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QAService {

    private final ChunkService chunkService;

    private final OllamaClient ollamaClient;

    public QAService(ChunkService chunkService, OllamaClient ollamaClient) {
        this.chunkService = chunkService;
        this.ollamaClient = ollamaClient;
    }

    public String getAnswer(String question, Long id){
        List<Chunk> chunkList = chunkService.getChunksByDocumentId(id);
        if (chunkList==null || chunkList.isEmpty()) return "No Content available for this Document";

        String context = chunkList.stream()
                                  .limit(5)
                                  .map(Chunk::getContent)
                                  .collect(Collectors.joining("\n"));

        String prompt = "You are an AI assistant. Use the following document content to answer the question.\n\n"
                        + context
                        + "\n\nQuestion: " + question;
        return ollamaClient.ask(prompt);
    }
}