package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.NoticeVO;
import cn.edu.nju.traveltool.entity.Notice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 14:20
 **/
@Service
public class NoticeWrapper {
    public Notice unwrapper(NoticeVO NoticeVO) {
        Notice Notice = new Notice();
        BeanUtils.copyProperties(NoticeVO,Notice);
        return Notice;
    }

    public NoticeVO wrapper(Notice Notice) {
        NoticeVO NoticeVO = new NoticeVO();
        BeanUtils.copyProperties(Notice,NoticeVO);
        return NoticeVO;
    }
}
