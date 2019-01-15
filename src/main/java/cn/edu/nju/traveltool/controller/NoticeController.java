package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.vo.NoticeVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.data.UserDTO;
import cn.edu.nju.traveltool.service.NoticeService;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 15:07
 **/
@RestController
@LoginRequired
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserWrapper userWrapper;

    @GetMapping("list/{activityId}")
    public ReponseMessage<List<NoticeVO>> list(@PathVariable("activityId")long activityId) {
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,noticeService.getNoticeList(activityId));
    }

    @PostMapping("save")
    public ReponseMessage save(@CurrentUser UserDTO user, @RequestBody NoticeVO noticeVO){
        noticeService.saveNotice(noticeVO,userWrapper.unwrapperFromDTO(user));
        return ReponseMessage.OK;
    }
}
