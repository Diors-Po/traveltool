package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.ActivityWithUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 16:57
 **/
public interface ActivityWithUserRepository extends PagingAndSortingRepository<ActivityWithUser,Long> {
}
