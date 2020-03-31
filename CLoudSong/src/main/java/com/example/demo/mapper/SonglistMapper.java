package com.example.demo.mapper;

import com.example.demo.domain.Catalog;
import com.example.demo.domain.Songlist;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: SonglistMapper
 * 编辑日期: 2020/3/13
 * 编辑时间: 14:14
 *
 * @Description:
 */
@Mapper
public interface SonglistMapper {

    @Select("select * from songlist")
    @Results(id = "songlistMap" , value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "song_list_name",property = "songListName"),
            @Result(column = "comment_size",property = "commentSize"),
            @Result(column = "favorite_size",property = "favoriteSize"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "read_size",property = "readSize"),
            @Result(column = "song_size",property = "songSize"),
            @Result(column = "user_id",property = "user",javaType = User.class,one =
            @One(select = "com.example.demo.mapper.UserMapper.findById")),
            @Result(column = "catalog_id",property = "catalog",javaType = Catalog.class,one =
            @One(select = "com.example.demo.mapper.CatalogMapper.findByIdCatalog")),
            @Result(column = "id",property = "comments",javaType = List.class,many =
            @Many(select = "com.example.demo.mapper.CommentMapper.findByUser1IdComment")),
            @Result(column = "id",property = "songs",javaType = List.class,many =
            @Many(select = "com.example.demo.mapper.SongMapper.findByUserIdSong")),
            @Result(column = "id",property = "favorites",javaType = List.class,many =
            @Many(select = "com.example.demo.mapper.FavoriteMapper.findByUser1IdFavorite"))
    })
    public List<Songlist> findAllSonglist();

    @Select("select * from songlist where id in (select songlist_id from songlist_favorite where favorite_id = #{favoriteId})")
    @ResultMap("songlistMap")
    public Songlist findByIdSonglist(Long favoriteId);

    @Select("select * from songlist where id in (select songlist_id from songlist_comment where comment_id = #{commentId})")
    @ResultMap("songlistMap")
    public Songlist findByIdCommentSonglist(Long commentId);

    @Select("select * from songlist where user_id = #{userId}")
    @ResultMap("songlistMap")
    public List<Songlist> findByUserIdSonglist(Long userId);



    @Insert("insert into songlist(song_list_name,comment_size,favorite_size,read_size,song_size,user_id,catalog_id) " +
            "values(#{songListName},#{commentSize},#{favoriteSize},#{readSize},#{songSize},#{user.id},#{catalog.id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertSonglist(Songlist songlist);

    @Delete("delete  from songlist where id = #{id}")
    public int deleteSonglist(Long id);

    @Update("update songlist set song_list_name=#{songListName},catalog_id=#{catalog.id} where id =#{id}")
    public int updateSonglist(Songlist songlist);

    @Update("update songlist set song_size=#{songSize} where id =#{id}")
    public int updateSongSize(Songlist songlist);

    @Update("update songlist set favorite_size=#{favoriteSize} where id =#{id}")
    public int updateFavoriteSize(Songlist songlist);

    @Select("select count(*) from songlist_comment where songlist_id = #{id}")
    public Integer comentSize(Long id);

    @Select("select count(*) from songlist_favorite where songlist_id = #{id}")
    public Integer favoriteSize(Long id);

    @Select("select count(*) from songlist_song where songlist_id = #{id}")
    public Integer songSize(Long id);

    @Insert("insert into songlist_comment(songlist_id,comment_id) values(#{songlistId},#{commentId})")
    public int insertSonglistComment(@Param("songlistId")Long songlistId,@Param("commentId")Long commentId);

    @Insert("insert into songlist_favorite(songlist_id,favorite_id) values(#{songlistId},#{favoriteId})")
    public int insertSonglistFavorite(@Param("songlistId")Long songlistId,@Param("favoriteId")Long favoriteId);

    @Insert("insert into songlist_song(songlist_id,song_id) values(#{songlistId},#{songId})")
    public int insertSonglistSong(@Param("songlistId")Long songlistId,@Param("songId")Long songId);

    @Delete("delete from songlist_comment where comment_id = #{id}")
    public int deleteSonglistComment(Long id);
    @Delete("delete from songlist_favorite where favorite_id = #{id}")
    public int deleteSonglistFavorite(Long id);

    @Delete("delete from songlist_song where song_id= #{id}")
    public int deleteSonglistSong(Long id);

    @Select("select songlist_id from songlist_song where song_id =#{id} ")
    public Long selectSongListId(Long id);

}
