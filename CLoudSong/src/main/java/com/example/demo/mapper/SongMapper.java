package com.example.demo.mapper;

import com.example.demo.domain.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SongMapper {

    @Select("select * from song")
    public List<Song> findAllSong();
    @Select("select * from song where id=#{id}")
    public Song findByIdSong(Long id);
    @Select("select * from song where id in (select song_id from songlist_song where songlist_id=#{songlistId})")
    public Song findByUserIdSong(Long songlistId);
    @Insert("insert into song(title,album,songplay) values(#{title},#{album},#{songplay})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertSong(Song song);
    @Delete("delete from song where id = #{id}")
    public int deleteSong(Long id);

}
