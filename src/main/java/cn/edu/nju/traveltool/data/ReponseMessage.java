package cn.edu.nju.traveltool.data;

import cn.edu.nju.traveltool.constant.Constant;
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

    public static final ReponseMessage OK = new ReponseMessage(Constant.OK,Constant.REQUEST_SUCCESS);

    public ReponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
