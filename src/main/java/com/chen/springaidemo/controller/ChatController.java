package com.chen.springaidemo.controller;

import com.chen.springaidemo.constants.AiType;
import com.chen.springaidemo.repository.ChatHisotryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.springaidemo.controller
 * @className com.chen.springaidemo.controller.ChatController
 * @date 2025/4/4 13:55
 * @description @todo
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    private final ChatHisotryRepository chatHisotryRepository;

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId) {

        // 1. 保存会话ID
        // @todo 改成枚举类型
        chatHisotryRepository.save(chatId, AiType.CHAT.getType());
        // 2. 请求模型
        return chatClient
                .prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .stream()
                .content();
    }

    @RequestMapping(value = "/chat2", produces = "text/html;charset=utf-8")
    public String chat2(String prompt) {
        return chatClient
                .prompt()
                .user(prompt)
                .call()
//                .stream()
                .content();
    }
}
