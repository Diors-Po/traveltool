package cn.edu.nju.traveltool.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-03-24 11:05
 **/
@Data
@AllArgsConstructor
public class TopicWithUserVO {
    private TopicVO topicVO;
    private UserVO userVO;
}
