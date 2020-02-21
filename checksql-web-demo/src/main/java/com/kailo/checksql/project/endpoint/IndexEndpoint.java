package com.kailo.checksql.project.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kailo.checksql.project.entity.SysConfigEntity;
import com.kailo.checksql.project.service.SysConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
public class IndexEndpoint {

    private SysConfigService sysConfigService;

    @GetMapping({"", "/index"})
    public ResponseEntity<List<SysConfigEntity>> index() {

        List<SysConfigEntity> list = sysConfigService.list(Wrappers.<SysConfigEntity>lambdaQuery().eq(SysConfigEntity::getVariable, "c"));

//        List<SysConfigEntity> list = sysConfigService.list();

        return ResponseEntity.ok(list);
    }
}
