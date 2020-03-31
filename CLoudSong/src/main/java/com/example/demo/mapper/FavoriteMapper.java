package com.example.demo.mapper;

import com.example.demo.domain.Favorite;
import com.example.demo.domain.Songlist;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Select("select * from favorite")
    @Results(id = "favoriteMap" ,value ={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "user_id",property = "user",javaType = User.class,one =
            @One(select = "com.example.demo.mapper.UserMapper.findById")),
            @Result(column = "id",property = "songlist",javaType = Songlist.class,one =
            @One(select = "com.example.demo.mapper.SonglistMapper.findByIdSonglist"))

    })
    public List<Favorite> findAllFavorite();
    @Select("select * from favorite where id = #{id}")
    @ResultMap("favoriteMap")
    public Favorite findByIdFavorite(Long id);
    @Select("select * from favorite where user_id = #{id}")
    @ResultMap("favoriteMap")
    public List<Favorite> findByUserIdFavorite(Long id);
    @Select("select * from favorite where id in (select favorite_id from songlist_favorite where songlist_id = #{songlistId})")
    @ResultMap("favoriteMap")
    public List<Favorite> findByUser1IdFavorite(Long songlistId);
    @Insert("insert into favorite(user_id) values(#{user.id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertFavorite(Favorite favorite);
    @Delete("delete from favorite where id =#{id}")
    public int deleteFavorite(Long id);

    //public int updateFavorite(Long id);
}
