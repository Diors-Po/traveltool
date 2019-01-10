package cn.edu.nju.traveltool.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: campusqa
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2018-12-10 14:03
 **/
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories("cn.edu.nju.traveltool.repository")
@EntityScan("cn.edu.nju.traveltool.entity")
@EnableTransactionManagement
public class JpaConfig {

}
