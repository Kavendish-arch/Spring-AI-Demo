package com.chen.springai.controller;

import com.chen.springai.constants.AiType;
import com.chen.springai.repository.ChatHisotryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "AI chat 接口", description = "调用此接口和Deepseek对话 需要部署大模型默认是本地")
public class ChatController {

    private final ChatClient chatClient;

    private final ChatHisotryRepository chatHisotryRepository;

    @Operation(summary = "AI对话接口", description = "流式调用")
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

    @Operation(summary = "AI对话接口", description = "非流式调用")
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
