package org.example.java.smartdoc_qa.Controller;

import org.example.java.smartdoc_qa.Entity.QARequest;
import org.example.java.smartdoc_qa.Service.QAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QAController {

    private final QAService qaService;

    public QAController(QAService qaService) {
        this.qaService = qaService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody QARequest qaRequest){
        return ResponseEntity.ok(qaService.getAnswer(qaRequest.getQuestion(), qaRequest.getDocumentId()));
    }
}