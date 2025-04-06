package com.chen.springaidemo.controller;

import com.chen.springaidemo.constants.AiType;
import com.chen.springaidemo.entity.vo.Result;
import com.chen.springaidemo.repository.ChatHisotryRepository;
import com.chen.springaidemo.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.controller
* @className com.chen.springaidemo.controller.PdfFileControllerTest

* @author chenyingtao
* @date 2025/4/6 18:14
* @version 1.0
* @description @todo 
*/
import com.chen.springaidemo.constants.AiType;
import com.chen.springaidemo.entity.vo.Result;
import com.chen.springaidemo.repository.ChatHisotryRepository;
import com.chen.springaidemo.repository.FileRepository;
import com.chen.springaidemo.tools.VectorStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PdfFileControllerTest {



    @Mock
    private FileRepository fileRepository;

    @Mock
    private VectorStore vectorStore;

    @Mock
    private ChatHisotryRepository chatHisotryRepository;

    @Mock
    private Resource resource;

    @InjectMocks
    private PdfFileController pdfFileController;

    private final String testChatId = "chat-001";

    @Test
    public void testUploadPdf() {
        System.out.println("Hello World!");
    }

    // uploadPdf 测试 ------------------------------------------------------
    @Test
    void uploadPdf_WhenNotPdf_ShouldReturnFail() throws IOException {
        // 准备非PDF文件
        MultipartFile file = new MockMultipartFile(
            "file", "test.txt", "text/plain", "content".getBytes());

        Result result = pdfFileController.uploadPdf(testChatId, file);

        assertEquals("只能上传PDF文件！", result.getMsg());
        assertEquals(0, result.getOk().intValue());
    }

    @Test
    void uploadPdf_WhenSaveFailed_ShouldReturnFail() throws IOException {
        // 准备PDF文件
        MultipartFile file = new MockMultipartFile(
            "file", "test.pdf", "application/pdf", "content".getBytes());
        when(fileRepository.save(anyString(), any())).thenReturn(false);

        Result result = pdfFileController.uploadPdf(testChatId, file);

        assertEquals("保存文件失败！", result.getMsg());
        verify(vectorStore, never()).add(any()); // 验证未调用向量库
    }

    // download 测试 ------------------------------------------------------
    @Test
    void download_WhenFileNotExist_ShouldReturn404() throws IOException {
        when(fileRepository.getFile(testChatId)).thenReturn(resource);
        when(resource.exists()).thenReturn(false);

        ResponseEntity<Resource> response = pdfFileController.download(testChatId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // chat 测试 ----------------------------------------------------------
    @Test
    void chat_WhenFileNotExist_ShouldThrowException() {
        when(fileRepository.getFile(testChatId)).thenReturn(resource);
        when(resource.exists()).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
            pdfFileController.chat("test prompt", testChatId));
    }

//    @Test
//    void chat_WhenNormalFlow_ShouldReturnFlux() {
//        when(fileRepository.getFile(testChatId)).thenReturn(resource);
//        when(resource.exists()).thenReturn(true);
//        when(resource.getFilename()).thenReturn("test.pdf");
//        when(pdfChatClient.prompt(anyString())).thenReturn(/* 构造Flux响应 */);
//
//        Flux<String> result = pdfFileController.chat("prompt", testChatId);
//
//        assertNotNull(result);
//        verify(chatHisotryRepository).save(testChatId, AiType.PDF.getType());
//    }
}
