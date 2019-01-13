package cn.edu.nju.traveltool.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:44
 **/
@Data
@Entity
@Table(name = "activity_w_user")
public class ActivityWithUser extends BaseEntity {
    public enum Status {
        OWNER, MEMBER, PREMEMBER, EXITMEMBER,UNMEMBER,
    }
    @Column(name = "activity_id")
    private long activityId;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "reason")
    private String reason;
    @Column(name = "answer")
    private String answer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
