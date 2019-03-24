package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.TopicVO;
import cn.edu.nju.traveltool.controller.vo.TopicWithUserVO;
import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.Topic;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.ActivityWithUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 14:20
 **/
@Service
public class TopicWrapper {
    @Autowired
    private UserWrapper userWrapper;
    @Autowired
    private ActivityWithUserRepository activityWithUserRepository;
    public Topic unwrapper(TopicVO topicVO) {
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicVO,topic);
        return topic;
    }

    public TopicVO wrapper(Topic topic) {
        TopicVO topicVO = new TopicVO();
        BeanUtils.copyProperties(topic,topicVO);
        return topicVO;
    }

    public TopicWithUserVO wrapperWithUser(Topic topic, User user) {
        TopicVO topicVO = new TopicVO();
        BeanUtils.copyProperties(topic,topicVO);
        topicVO.setCreatedDate(topic.getCreatedDate().toString("yyyy-MM-dd HH:mm:ss"));
        UserVO userVO = userWrapper.wrapper(user);
        ActivityWithUser activityWithUser = activityWithUserRepository.findFirstByActivityIdAndUserId(topic.getActivityId(),user.getId());
        if(activityWithUser==null){
            userVO.setRole(ActivityWithUser.Status.UNMEMBER);
        }else {
            userVO.setRole(activityWithUser.getStatus());
        }

        return new TopicWithUserVO(topicVO,userVO);
    }
}
