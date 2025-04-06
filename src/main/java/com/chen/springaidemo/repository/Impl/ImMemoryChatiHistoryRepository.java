package com.chen.springaidemo.repository.Impl;

import com.chen.springaidemo.repository.ChatHisotryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.repository
* @className com.chen.springaidemo.repository.Impl.ImMemoryChatiHistoryRepository

* @author chenyingtao
* @date 2025/4/4 15:25
* @version 1.0
* @description @todo 
*/
@Component
public class ImMemoryChatiHistoryRepository implements ChatHisotryRepository {

    private final Map<String, List<String>> chatHistory = new HashMap<>();

    @Override
    public void save(String chatId, String type) {
//        if(!chatHistory.containsKey(type)){
//            chatHistory.put(type, List.of());
//        }
//        List<String> chatHistoryList = chatHistory.get(type);
        // 简化写法
        List<String> chatHistoryList = chatHistory.computeIfAbsent(type, k -> new ArrayList<>());
        if(chatHistoryList.contains(chatId)){
            return;
        }
        chatHistoryList.add(chatId);
    }

    @Override
    public List<String> getChatIds(String type) {
//        List<String> chatHistoryList = chatHistory.get(type);
//        return chatHistoryList != null ? chatHistoryList : new ArrayList<>();
        return chatHistory.getOrDefault(type, List.of());
    }



    @Override
    public void delete(String chatId) {

//        List<String> chatHistoryList = chatHistory.computeIfAbsent(type, k -> List.of());
//        if(chatHistoryList.contains(chatId)){
//            return;
//        }
//        chatHistoryList.add(chatId);
    }
}
