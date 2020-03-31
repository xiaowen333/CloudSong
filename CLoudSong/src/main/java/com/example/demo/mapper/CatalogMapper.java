package com.example.demo.mapper;

import com.example.demo.domain.Catalog;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CatalogMapper {
    @Select("select * from catalog")
    @Results(id = "catalogMap" ,value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "user_id",property = "user",javaType = User.class,one =
            @One(select = "com.example.demo.mapper.UserMapper.findById"))
    })
    public List<Catalog> findAllCatalog();

    @Select("select * from catalog where id = #{id}")
    @ResultMap("catalogMap")
    public Catalog findByIdCatalog(Long id);

    @Select("select * from catalog where user_id = #{id}")
    @ResultMap("catalogMap")
    public List<Catalog> findByUserIdCatalog(Long id);

    @Select("select * from catalog where name= #{name}")
    @ResultMap("catalogMap")
    public Catalog findByUserNameCatalog(String name);

    @Insert("insert into catalog(name,user_id) values(#{name},#{user.id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int insertCatalog(Catalog catalog);

    @Delete("delete from catalog where id = #{id}")
    public int deleteCatalog(Long id);

    @Update("update catalog set name =#{name} where id=#{id}")
    public int updateCatalog(Catalog catalog);




}
