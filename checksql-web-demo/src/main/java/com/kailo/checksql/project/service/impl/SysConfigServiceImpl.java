package com.kailo.checksql.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kailo.checksql.project.entity.SysConfigEntity;
import com.kailo.checksql.project.mapper.SysConfigMapper;
import com.kailo.checksql.project.service.SysConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements SysConfigService {
}