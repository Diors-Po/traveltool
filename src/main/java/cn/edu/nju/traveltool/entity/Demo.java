package cn.edu.nju.traveltool.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: recommendation
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2018-12-20 21:17
 **/
@Data
@Entity
@Table(name = "T_Demo")
public class Demo extends BaseEntity {
    @Column(name = "name")
    private String name;
}
