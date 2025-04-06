package com.chen.springaidemo.controller;

import com.chen.springaidemo.entity.vo.MessageVo;
import com.chen.springaidemo.repository.ChatHisotryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.springaidemo.controller
 * @className com.chen.springaidemo.controller.ChatHistoryController
 * @date 2025/4/4 14:52
 * @description @todo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/history")
public class ChatHistoryController {


    private final ChatHisotryRepository chatHisotryRepository;

    private final ChatMemory chatMemory;

    @GetMapping(value = "/{type}")
    public Object getChatIds(@PathVariable("type") String type) {

        return chatHisotryRepository.getChatIds(type);
    }

    @GetMapping(value = "/{type}/{chatId}")
    public List<MessageVo> getChatHistory(@PathVariable("chatId") String chatId,
                                          @PathVariable("type") String type) {

        List<Message> messages = chatMemory.get(chatId, Integer.MAX_VALUE);
        if(null == messages){
            return List.of();
        }
//        return messages.stream().map(m -> new MessageVo(m)).toList();
        return messages.stream().map(MessageVo::new).toList();
    }

}
