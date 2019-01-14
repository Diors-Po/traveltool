package cn.edu.nju.traveltool.service;

import cn.edu.nju.traveltool.controller.vo.NoticeVO;
import cn.edu.nju.traveltool.entity.User;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 15:19
 **/
public interface NoticeService {
    List<NoticeVO> getNoticeList(long activityId);

    void saveNotice(NoticeVO noticeVO, User user);
}
