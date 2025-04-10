package com.chen.springai.entity.vo;

import lombok.Data;
import org.springframework.ai.chat.messages.Message;
/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.entity.vo
* @className com.chen.springaidemo.entity.vo.MessageVo

* @author chenyingtao
* @date 2025/4/4 15:43
* @version 1.0
* @description @todo 
*/

@Data
public class MessageVo {
    private String role;
    private String content;

    public MessageVo(String role, String content) {
        this.role = role;
        this.content = content;
    }
    public MessageVo(Message message) {
        switch (message.getMessageType()){
            case USER:
                this.role = "user";
                break;
            case ASSISTANT:
                this.role = "assistant";
                break;
            case SYSTEM:
                this.role = "system";
                break;
            case TOOL:
                this.role = "tool";
                break;
            default:
                this.role = "unknown";
                break;
        }
        this.content = message.getText();
    }
}
