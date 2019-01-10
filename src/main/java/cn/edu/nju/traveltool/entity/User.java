package cn.edu.nju.traveltool.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 21:53
 **/
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Column(name = "email")
    private String email;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "user")
    private String nickname;
}
