package cn.edu.nju.traveltool.service;

import cn.edu.nju.traveltool.controller.vo.TopicVO;
import cn.edu.nju.traveltool.controller.vo.TopicWithUserVO;
import cn.edu.nju.traveltool.entity.User;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 15:19
 **/
public interface TopicService {
    List<TopicWithUserVO> getTopicList(long activityId);
    void saveTopic(TopicVO topicVO, User user);
}
