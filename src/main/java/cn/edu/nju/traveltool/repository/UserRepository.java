package cn.edu.nju.traveltool.repository;

import cn.edu.nju.traveltool.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 21:56
 **/
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByEmail(String email);
    User findByEmailAndPwd(String email,String pwd);
}
