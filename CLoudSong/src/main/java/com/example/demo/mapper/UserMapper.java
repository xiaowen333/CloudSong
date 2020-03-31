package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username" , property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "password",property = "password"),
            @Result(column = "id",property = "authoritys",javaType = List.class,
                    many = @Many(select = "com.example.demo.mapper.AuthorityMapper.findByIdUserAuthority"))
    })
    public List<User> findAll();

    @Select("select * from user where username=#{username}")
    @ResultMap("userMap")
    public User findByName(String username);


    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    public User findById(Long id);

    @Insert("insert into user(email,password,username) values(#{email},#{password},#{username})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertUser(User user);

    @Insert("insert into user_authority(user_id,authority_id) values(#{userId},#{authorityId})")
    public int insertUserAuthority(@Param("userId") Long userId,@Param("authorityId")Long authorityId);

    @Delete("delete from user where id = #{id}")
    public int deleteUser(Long id);

    @Delete("delete from user_authority where user_id =#{userId}")
    public int deleteUserAuthority(Long userId);

    @Update({"update user set avatar=#{avatar},email=#{email} where id =#{id}"})
    public int updateUser(User user);
}
