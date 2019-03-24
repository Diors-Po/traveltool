package cn.edu.nju.traveltool.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-03-24 10:51
 **/
@Data
@Entity
@Table(name = "topic")
public class Topic  extends BaseEntity{
    @Column(name = "activity_id")
    private long activityId;
    @Column(name = "info")
    private String info;
    @Column(name = "user_id")
    private long userId;
}
