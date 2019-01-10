package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.controller.vo.HelloWorldVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 11:27
 **/
@RestController

public class DemoController {

    @GetMapping("hello")
    public ReponseMessage<HelloWorldVO> hello(){
        HelloWorldVO helloWorldVO = new HelloWorldVO();
        helloWorldVO.setWhat("你好，阿皮");
        return new ReponseMessage<>(200,"get请求success",helloWorldVO);
    }
}
