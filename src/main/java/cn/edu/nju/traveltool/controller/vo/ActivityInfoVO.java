package cn.edu.nju.traveltool.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 13:59
 **/
@Data
public class ActivityInfoVO {
    ActivityVO activityVO;
    UserVO userVO; //活动拥有者
    List<UserVO> userVOList; //所有参与者
    List<NoticeVO> noticeVOList;
}
