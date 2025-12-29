package org.example.java.smartdoc_qa.Service;

import org.example.java.smartdoc_qa.Entity.Chunk;
import org.example.java.smartdoc_qa.Repo.ChunkRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChunkService {

    private final ChunkRepo chunkRepo;

    public ChunkService(ChunkRepo chunkRepo) {
        this.chunkRepo = chunkRepo;
    }

    public List<Chunk> getChunksByDocumentId(Long id){
        return chunkRepo.findByDocumentId(id);
    }

    public void saveChunks(List<Chunk> chunks){
        chunkRepo.saveAll(chunks);
    }

    public void deleteChunksByDocumentId(Long id){
        chunkRepo.deleteByDocumentId(id);
    }
}
