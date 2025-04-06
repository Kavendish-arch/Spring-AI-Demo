package com.chen.springaidemo;

import com.chen.springaidemo.utils.VectorDistanceUtils;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.springaidemo
 * @className com.chen.springaidemo.AiApplicationTests
 * @date 2025/4/5 22:07
 * @description @todo
 */
@SpringBootTest
public class AiApplicationTests {


    @Test
    void contextLoads() {
        System.out.println("Hello World!");

    }

//     自动注入向量模型
    @Autowired
    private OpenAiEmbeddingModel embeddingModel;

    @Test
    public void testEmbedding() {
        // 1.测试数据
        // 1.1.用来查询的文本，国际冲突
        String query = "global conflicts";

        // 1.2.用来做比较的文本
        String[] texts = new String[]{
                "哈马斯称加沙下阶段停火谈判仍在进行 以方尚未做出承诺",
                "土耳其、芬兰、瑞典与北约代表将继续就瑞典“入约”问题进行谈判",
                "日本航空基地水井中检测出有机氟化物超标",
                "国家游泳中心（水立方）：恢复游泳、嬉水乐园等水上项目运营",
                "我国首次在空间站开展舱外辐射生物学暴露实验",
        };
        // 2.向量化
        // 2.1.先将查询文本向量化
        float[] queryVector = embeddingModel.embed(query);

        // 2.2.再将比较文本向量化，放到一个数组
        List<float[]> textVectors = embeddingModel.embed(Arrays.asList(texts));

        // 知识库文档

        //
        // 3.比较欧氏距离
        // 3.1.把查询文本自己与自己比较，肯定是相似度最高的
        System.out.println(VectorDistanceUtils.euclideanDistance(queryVector, queryVector));
        // 3.2.把查询文本与其它文本比较
        for (float[] textVector : textVectors) {
            System.out.println(VectorDistanceUtils.euclideanDistance(queryVector, textVector));
        }
        System.out.println("------------------");
        /**
         * 0.0
         * 1.0722205301828829
         * 1.0844350869313875
         * 1.1185223356097924
         * 1.1693257901084286
         * 1.1499045763089124

         */
        // 4.比较余弦距离
        // 4.1.把查询文本自己与自己比较，肯定是相似度最高的
        System.out.println(VectorDistanceUtils.cosineDistance(queryVector, queryVector));
        // 4.2.把查询文本与其它文本比较
        for (float[] textVector : textVectors) {
            System.out.println(VectorDistanceUtils.cosineDistance(queryVector, textVector));
        }
        /**
         * ------------------
         * 0.9999999999999998
         * 0.4251716163869882
         * 0.41200032867283726
         * 0.37445397231274447
         * 0.3163386320532005
         * 0.3388597327534832
         */
    }
}
