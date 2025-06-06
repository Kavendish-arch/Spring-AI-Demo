package com.chen.springai.controller;

import com.chen.springai.constants.AiType;
import com.chen.springai.repository.ChatHisotryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.controller
* @className com.chen.springaidemo.controller.CustomerServiceController

* @author chenyingtao
* @date 2025/4/5 21:08
* @version 1.0
* @description @todo 
*/
@AllArgsConstructor
@RestController
@RequestMapping("/ai")
@Tag(name = "客服接口", description = "客服接口")
public class CustomerServiceController {

    private final ChatClient serviceChatClient;

    private final ChatHisotryRepository chatHisotryRepository;

//    @RequestMapping(value = "service", produces = "text/html;charset=utf-8")
//    public Flux<String> chat(String prompt, String chatId) {
//        // 1. 保存会话ID
//        // @todo 改成枚举类型
//        chatHisotryRepository.save(chatId, "service");
//        // 2. 请求模型
//        return serviceChatClient
//                .prompt()
//                .user(prompt)
//                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
//                .stream()
//                .content();
//    }
    @Operation(summary = "客服接口", description = "客服接口")
    @RequestMapping(value = "service", produces = "text/html;charset=utf-8")
    public String chat(String prompt, String chatId) {
        // 1. 保存会话ID
        // @todo 改成枚举类型
        chatHisotryRepository.save(chatId, String.valueOf(AiType.SERVICE));
        // 2. 请求模型

        // 阻塞时调用 阿里云百联，
        return serviceChatClient
                .prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .call()
                .content();
    }
}
