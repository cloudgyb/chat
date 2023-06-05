package com.github.cloudgyb.heartchat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户与朋友关联实体类
 *
 * @author cloudgyb
 * @since 1.0.0
 */
@TableName("user_friends")
public class UserFriendsEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String userId;
    private String friendId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

}
