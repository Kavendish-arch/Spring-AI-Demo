package com.chen.springai.constants;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.springaidemo.constants
 * @className com.chen.springaidemo.constants.AiType
 * @date 2025/4/5 21:53
 * @description @todo
 */
public enum AiType {
    CHAT("CHAT"),
    PDF("PDF"),
    SERVICE("SERVICE");
    private final String type;

    private AiType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
