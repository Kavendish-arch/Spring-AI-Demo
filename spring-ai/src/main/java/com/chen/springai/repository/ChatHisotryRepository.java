package com.chen.springai.repository;

import org.springframework.ai.chat.messages.Message;

import java.util.List;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.springaidemo.repository
 * @className com.chen.springaidemo.repository.ChatHisotryRepository
 * @date 2025/4/4 15:22
 * @description @todo
 */
public interface ChatHisotryRepository {
    /**
     * 保存会话记录
     * @param chatId 会话id
     * @param type 会话类型
     */
    void save(String chatId, String type);

    /**
     * 获取会话id列表
     * @param type 业务类型 chat service pdf
     * @return 会话id列表
     */
    List<String> getChatIds( String type);


    /**
     * 删除会话
     * @param chatId 会话id
     */
    void delete(String chatId);
}
