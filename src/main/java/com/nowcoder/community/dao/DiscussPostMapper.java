package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //我的帖子时需要参数，所有帖子时不需要参数，为零时不拼接，考虑分页查询，考虑一共有多少数据
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //查询帖子的总数
    //起别名，方便在sql中书写
    //sql里需要用到动态拼接参数且参数只有一个时必须要起别名
    int selectDiscussPostRows(@Param("userId") int userId);


}
