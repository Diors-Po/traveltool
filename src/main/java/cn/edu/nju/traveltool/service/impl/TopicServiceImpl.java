package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.controller.vo.TopicVO;
import cn.edu.nju.traveltool.controller.vo.TopicWithUserVO;
import cn.edu.nju.traveltool.entity.Topic;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.TopicRepository;
import cn.edu.nju.traveltool.repository.UserRepository;
import cn.edu.nju.traveltool.service.TopicService;
import cn.edu.nju.traveltool.wrapper.TopicWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-03-24 10:57
 **/
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicWrapper topicWrapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<TopicWithUserVO> getTopicList(long activityId) {
        return topicRepository.findByActivityId(activityId,new Sort(Sort.Direction.DESC,"id"))
                .stream().map(x -> topicWrapper.wrapperWithUser(x, userRepository.findById(x.getUserId()).get())
                ).collect(Collectors.toList());
    }

    @Override
    public void saveTopic(TopicVO topicVO, User user) {
        topicVO.setUserId(user.getId());
        topicRepository.save(topicWrapper.unwrapper(topicVO));
    }

    @Override
    public int deleteTopic(long id, long userId) {
        Topic topic = topicRepository.findByIdAndUserId(id,userId);
        if(topic==null) return -1;
        topicRepository.delete(topic);
        return 1;
    }
}
