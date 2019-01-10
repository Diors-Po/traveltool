package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 10:47
 **/
public interface DemoRepository extends PagingAndSortingRepository<Demo,Long> {
}
