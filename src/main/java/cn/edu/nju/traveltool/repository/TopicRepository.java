package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.Topic;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 14:03
 **/
public interface TopicRepository extends PagingAndSortingRepository<Topic,Long> {
    List<Topic> findByActivityIdAndUserId(long activityId, long userId, Sort sort);
    List<Topic> findByActivityId(long activityId, Sort sort);
}
