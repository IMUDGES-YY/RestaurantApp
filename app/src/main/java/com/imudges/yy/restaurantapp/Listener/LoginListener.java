package com.imudges.yy.restaurantapp.Listener;

/**
 * Created by yangyang on 2017/7/1.
 */

import com.imudges.yy.restaurantapp.Bean.User;

/**
 * @see LoginListener 登录时的监听者
 * */
public interface LoginListener{
    /**
     * @param user 登录者的对象
     * @exception Exception e 网络错误 登录失败消息
     * @return 没有返回值
     * */
    public void done(User user, Exception e);
}