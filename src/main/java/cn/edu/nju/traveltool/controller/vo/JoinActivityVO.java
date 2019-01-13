package cn.edu.nju.traveltool.controller.vo;

import lombok.Data;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 17:17
 **/
@Data
public class JoinActivityVO {
    private long userId;
    private long activityId;
    private String reason;
}
