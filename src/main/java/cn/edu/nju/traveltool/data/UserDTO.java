package cn.edu.nju.traveltool.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-15 14:11
 **/
@Data
public class UserDTO implements Serializable {

    private long id;
    private Date createdDate;
    private Date lastModifiedDate;
    private Long version;
    private String email;
    private String nickname;
}
