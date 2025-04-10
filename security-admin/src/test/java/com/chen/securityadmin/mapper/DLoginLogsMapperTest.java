package com.chen.securityadmin.mapper;

import com.chen.securityadmin.entiry.po.DLoginLogs;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.mapper
* @className com.chen.securityadmin.mapper.DLoginLogsMapperTest

* @author chenyingtao
* @date 2025/4/10 20:41
* @version 1.0
* @description @todo 
*/
@SpringBootTest
public class DLoginLogsMapperTest {

    @Autowired
    private DLoginLogsMapper dLoginLogsMapper;


    @Test
    void testInsert() {
        DLoginLogs log = new DLoginLogs(/* initialize fields */);
        int result = dLoginLogsMapper.insert(log);
        assertThat(result).isEqualTo(1);
    }

    /**
     * Test case: Select existing ID
     * Expect: Return corresponding entity
     */
    @Test
    void testSelectById() {
        DLoginLogs log = dLoginLogsMapper.selectById("1L");
        System.out.println(log);
        assertThat(log).isNotNull();
    }

    /**
     * Test case: Select non-existing ID
     * Expect: Return null
     */
    @Test
    void testSelectInvalidId() {
        DLoginLogs log = dLoginLogsMapper.selectById(-1L);
        assertThat(log).isNull();
    }

    // Similar tests for update and delete
}
