package cn.edu.nju.traveltool.controller.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:26
 **/
@Data
public class ActivityVO {
    private long id;
    private long userId;
    private String topic;
    private String description;
    private String location;
    private Date start;
    private Date end;
    private int totalCount;
    private int hasEnterCount;
}
