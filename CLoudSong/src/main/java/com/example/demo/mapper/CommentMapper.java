package com.example.demo.mapper;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Songlist;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    @Results(id = "commentMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "user_id",property = "user",javaType = User.class,one =
            @One(select = "com.example.demo.mapper.UserMapper.findById")),
            @Result(column = "id",property = "songlist",javaType = Songlist.class,one =
            @One(select = "com.example.demo.mapper.SonglistMapper.findByIdCommentSonglist"))
    })

    public List<Comment> findAllComment();

    @Select("select * from comment where id =#{id}")
    @ResultMap("commentMap")
    public Comment findByIdComment(Long id);

    @Select("select * from comment where id in (select comment_id from songlist_comment where songlist_id = #{songlistId})")
    @ResultMap("commentMap")
    public Comment findByUser1IdComment(Long songlistId);

    @Select("select * from comment where user_id =#{id}")
    @ResultMap("commentMap")
    public List<Comment> findByUserIdComment(Long id);

    @Insert("insert into comment(content,user_id) values(#{content},#{user.id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertComment(Comment comment);

    @Delete("delete from comment where id = #{id}")
    public int deleteComment(Long id);

    @Update("update comment set content=#{content} where id=#{id}")
    public int updateComment(Comment comment);
}
