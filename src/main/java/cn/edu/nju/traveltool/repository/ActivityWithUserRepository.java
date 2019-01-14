package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.ActivityWithUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 16:57
 **/
public interface ActivityWithUserRepository extends PagingAndSortingRepository<ActivityWithUser,Long> {
    List<ActivityWithUser> findAllByUserId(long userId, Sort sort);
    List<ActivityWithUser> findActivityWithUserByActivityIdAndStatusIn(long activityId, List<ActivityWithUser.Status> statuses);
    ActivityWithUser findFirstByActivityIdAndUserId(long activityId,long userId);
}
