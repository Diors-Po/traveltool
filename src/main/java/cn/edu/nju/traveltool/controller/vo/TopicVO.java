package cn.edu.nju.traveltool.controller.vo;

import lombok.Data;


/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 14:13
 **/
@Data
public class TopicVO {
    private long activityId;
    private String info;
    private long userId;
    private String createdDate;
}
