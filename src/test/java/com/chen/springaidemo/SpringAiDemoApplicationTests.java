package com.chen.springaidemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringAiDemoApplicationTests {

    @Autowired
    private OpenAiEmbeddingModel embeddingModel;

    @Autowired
    private VectorStore vectorStore;

    @Test
    void contextLoads() {
        System.out.println("Hello World!");
        float[] floats = embeddingModel.embed("Hello World!");
        System.out.println(Arrays.toString(floats));

    }

    @Test
    void testVectorStore() {
        // 1. 创建PDF 读取器
        Resource resource = new FileSystemResource("Pdfs/中二知识笔记.pdf");
        String textexpression = "file_name == '中二知识笔记.pdf'";
        PagePdfDocumentReader reader = new PagePdfDocumentReader(
                resource,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.defaults())
                        .withPagesPerDocument(1)
                        .build()
        );

        // 2. 读取PDF文件，并解析为Document对象
        List<Document> documents = reader.read();
        // 3. 向知识库添加Document对象 写入词向量
        vectorStore.add(documents);
//        vectorStore.add(Arrays.asList(
//                new VectorStore.Document("Hello World!", "Hello World!", "Hello World!", "Hello World!")
//        ));
        // 4. 搜索
        String question = "教育的意义是什么";
        SearchRequest request = SearchRequest.builder()
                .query(question)
                .topK(10)                   // 返回的topK个结果
                .similarityThreshold(0.6) // 相似度阈值
                .filterExpression(textexpression)
                .build();

        List<Document> docs = vectorStore.similaritySearch(question);
        if (null == docs) {
            System.out.println("没有搜索到");
            return;
        } else {
            for (Document doc : docs) {
                System.out.println(doc.getId());
                System.out.println(doc.getScore());
                System.out.println(doc.getText());
            }
        }
    }
}
