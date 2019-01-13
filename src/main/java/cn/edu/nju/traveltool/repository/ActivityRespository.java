package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:41
 **/
public interface ActivityRespository extends PagingAndSortingRepository<Activity,Long> {
    Page<Activity> findByClosed(int closed, Pageable pageable);
}
