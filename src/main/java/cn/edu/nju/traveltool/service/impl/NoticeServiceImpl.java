package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.controller.vo.NoticeVO;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.NoticeRepository;
import cn.edu.nju.traveltool.service.NoticeService;
import cn.edu.nju.traveltool.wrapper.NoticeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 15:19
 **/
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeWrapper noticeWrapper;
    @Override
    public List<NoticeVO> getNoticeList(long activityId) {
        return noticeRepository.findByActivityId(activityId,new Sort(Sort.Direction.DESC,"id"))
                .stream().map(x -> noticeWrapper.wrapper(x)).collect(Collectors.toList());
    }

    @Override
    public void saveNotice(NoticeVO noticeVO, User user) {
        noticeVO.setUserId(user.getId());
        noticeRepository.save(noticeWrapper.unwrapper(noticeVO));
    }
}
