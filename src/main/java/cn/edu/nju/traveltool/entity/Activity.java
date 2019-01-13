package cn.edu.nju.traveltool.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:31
 **/
@Data
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity {
    @Column(name = "topic")
    private String topic;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "start")
    private Date start;
    @Column(name = "end")
    private Date end;
    @Column(name = "total_count")
    private int totalCount;
    @Column(name = "has_enter_count")
    private int hasEnterCount;
    @Column(name = "closed")
    private int closed;
}
