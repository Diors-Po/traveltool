package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 14:03
 **/
public interface  NoticeRepository extends PagingAndSortingRepository<Notice,Long> {
    List<Notice> findByActivityIdAndUserId(long activityId,long userId, Sort sort);
    List<Notice> findByActivityId(long activityId,Sort sort);
}
