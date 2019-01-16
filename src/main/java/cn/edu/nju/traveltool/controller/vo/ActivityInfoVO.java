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
    private ActivityVO activityVO;
    private UserVO userVO;
    private List<UserVO> userVOList; //所有参与者
    private List<NoticeVO> noticeVOList;
}
