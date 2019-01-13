package cn.edu.nju.traveltool.service;

import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import org.springframework.data.domain.Page;


/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:19
 **/
public interface ActivityService {
    ActivityVO insertActivity(ActivityVO activityVO);
    void updateActivity(ActivityVO activityVO);
    Page<ActivityVO> listActivity(int page, int size);
    void closedActivity(ActivityVO activityVO);

    void joinActivity(JoinActivityVO joinActivityVO);
}
