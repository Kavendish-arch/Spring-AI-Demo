package com.chen.springaidemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.controller
* @className com.chen.springaidemo.controller.GameController

* @author chenyingtao
* @date 2025/4/5 19:54
* @version 1.0
* @description @todo 
*/
@AllArgsConstructor
@RestController
@RequestMapping("/ai")
@Tag(name = "游戏接口", description = "游戏接口")
public class GameController {

    private final ChatClient gameChatClient;


    @Operation(summary = "游戏接口", description = "流式调用")
    @RequestMapping(value = "/game", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId) {
        return gameChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .stream()
                .content();
    }
}
