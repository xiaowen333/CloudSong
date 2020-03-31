package com.example.demo.mapper;

import com.example.demo.domain.Authority;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    @Select("select * from authority where id = #{id}")
    public List<Authority> findByIdAuthority(Long id);

    @Select("select * from authority where id in(select authority_id from user_authority where user_id=#{userId})")
    public List<Authority> findByIdUserAuthority(Long userId);

    @Insert("insert into authority(name) values (#{name})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertAuthority(Authority authority);

    @Delete("delete from authority where id = #{id}")
    public int deleteAuthorityID (Long id);

    @Update({"update authority set name=#{name} where id=#{id}"})
    public int updateAuthority(Authority authority);
}
