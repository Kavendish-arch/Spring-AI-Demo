package com.chen.springaidemo.repository.Impl;

import com.chen.springaidemo.repository.FileRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.repository
* @className com.chen.springaidemo.repository.Impl.LocalPdfFileRepository

* @author chenyingtao
* @date 2025/4/6 17:24
* @version 1.0
* @description @todo 
*/


//@Slf4j
@Component
@RequiredArgsConstructor
public class LocalPdfFileRepository implements FileRepository {

    /**
     * 保存到本地磁盘
     * 向量库持久化
     */
    private final VectorStore vectorStore;

    // 会话id 与 文件名的对应关系，方便查询会话历史时重新加载文件
    // 持久化能力，Properties自带持久化能力
    private final Properties chatFiles = new Properties();

    /**
     *
     * @param chatId 会话id
     * @param resource 文件
     * @return
     */
    @Override
    public boolean save(String chatId, Resource resource) {

        // 2.保存到本地磁盘 相对于当前项目
        String filename = resource.getFilename();
        File target = new File(Objects.requireNonNull(filename));
        // 如果文件不存在，则复制到本地磁盘
        if (!target.exists()) {
            try {
                Files.copy(resource.getInputStream(), target.toPath());
            } catch (IOException e) {
//                log.error("Failed to save PDF resource.", e);
                return false;
            }
        }
        // 3.保存映射关系
        // 阿里云oss 地址
        chatFiles.put(chatId, filename);
        return true;
    }

    @Override
    public Resource getFile(String chatId) {
        // 1.从本地磁盘加载文件
        if (!chatFiles.containsKey(chatId)) {
            return null;
        }
        return new FileSystemResource(chatFiles.getProperty(chatId));
    }

    @PostConstruct
    private void init() {
        // 1.加载映射关系
        FileSystemResource pdfResource = new FileSystemResource("chat-pdf.properties");
        if (pdfResource.exists()) {
            try {
                chatFiles.load(new BufferedReader(new InputStreamReader(pdfResource.getInputStream(), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 2.加载向量库
        FileSystemResource vectorResource = new FileSystemResource("chat-pdf.json");
        if (vectorResource.exists()) {
            SimpleVectorStore simpleVectorStore = (SimpleVectorStore) vectorStore;
            simpleVectorStore.load(vectorResource);
        }
    }

    @PreDestroy
    private void persistent() {
        try {
            chatFiles.store(new FileWriter("chat-pdf.properties"), LocalDateTime.now().toString());
            SimpleVectorStore simpleVectorStore = (SimpleVectorStore) vectorStore;
            simpleVectorStore.save(new File("chat-pdf.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
