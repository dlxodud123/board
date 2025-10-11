package com.taeyoung.board.transaction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class TransactionTest {
    @Autowired
    BasicService basicService;

    @Test
    void proxyCheck() {
        log.info("aop class={}", basicService.getClass());
        assertThat(AopUtils.isAopProxy(basicService)).isTrue();
    }

    @Test
    void txTest() {
        basicService.tx1();
        basicService.tx2();
    }

    @TestConfiguration
    static class TxApplyBasicConfig{
        @Bean
        BasicService basicService(){
            return new BasicService();
        }
    }

    @Slf4j
    @Transactional(readOnly = true)
    static class BasicService{

        @Transactional(readOnly = false)
        public void tx1() {
            log.info("call tx1");
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("ts action={}", txActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("tx readOnly={}", readOnly);
        }

        public void tx2() {
            log.info("call tx2");
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("ts action={}", txActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("tx readOnly={}", readOnly);
        }
    }
}
