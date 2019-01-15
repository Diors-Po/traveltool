package cn.edu.nju.traveltool.controller.vo;

import lombok.Data;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 11:07
 **/
@Data
public class ActivityListVO {
    private ActivityVO activityVO;
    private UserVO userVO;
    private ActivityWithUserVO activityWithUserVO;

}
