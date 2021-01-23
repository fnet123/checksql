package com.kailo.checksql.project.service.impl;

import com.kailo.checksql.project.service.PerformanceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional(readOnly = true, propagation = Propagation.NEVER)
public class PerformanceServiceImpl implements PerformanceService {

    public void test() {
        int value = 1;
        while (true) {
            ++value;
        }
    }
}
