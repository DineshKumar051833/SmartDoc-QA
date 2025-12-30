# SmartDoc-QA

SmartDoc-QA is an AI-powered document question answering system built using Spring Boot and a local Large Language Model (LLM).  
It allows users to upload documents, extract and process their content, and ask questions to get AI-generated answers based on the document context.

---

## 🚀 Features

- Upload documents
- Extract and split document text into manageable chunks
- Store documents and chunks in a database
- Ask questions related to a specific document
- Generate answers using a local LLM (Ollama)
- Clean, RESTful API design
- Lightweight and efficient for local development

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** JPA / Hibernate (MySQL or H2)
- **AI Integration:** Spring AI + Ollama
- **LLM Model:** TinyLlama (lightweight local model)
- **Build Tool:** Maven
- **API Testing:** Postman

---

## ⚙️ How It Works (High-Level)

1. User uploads a document
2. The document text is extracted and split into chunks
3. Chunks are stored in the database
4. User asks a question related to a document
5. Relevant chunks are sent as context to the LLM
6. The LLM generates an answer based on the document content

---

## ▶️ How to Run the Project

### Prerequisites
- Java 17+
- Maven
- Ollama installed locally

### Steps

1. Start Ollama server:
   ```bash
   ollama serve

2. Start the Maven Project
