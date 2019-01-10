package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 10:38
 **/
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {
    @Override
    public void hello() {
        log.info("hello");
    }
}
