package cn.edu.nju.traveltool.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 11:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseMessage<T> {
    private int code;
    private String msg;
    private T data;


}
