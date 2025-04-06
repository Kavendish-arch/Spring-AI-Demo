package com.chen.springaidemo.tools;

import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentWriter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.filter.Filter;

import java.util.List;
import java.util.Optional;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.tools
* @className com.chen.springaidemo.tools.VectorStore

* @author chenyingtao
* @date 2025/4/6 14:42
* @version 1.0
* @description @todo 
*/
public interface VectorStore extends DocumentWriter {

    default String getName() {
        return this.getClass().getSimpleName();
    }
    // 保存文档到向量库
    void add(List<Document> documents);
    // 根据文档id删除文档
    void delete(List<String> idList);

    void delete(Filter.Expression filterExpression);

    default void delete(String filterExpression) {

    }

    // 根据条件检索文档
    List<Document> similaritySearch(String query);
    // 根据条件检索文档
    List<Document> similaritySearch(SearchRequest request);

    default <T> Optional<T> getNativeClient() {
        return Optional.empty();
    }
}
