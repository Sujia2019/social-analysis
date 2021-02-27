package com.psx.social.dao;

import com.psx.social.entity.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {

    @Insert("insert into psx_db.UserInfo(user_account, sname, sage,email,phone,birth,gender,major,collage,detail)" +
            " values (#{user_account},#{sname},#{sage},#{email},#{phone},#{birth},#{gender},#{major},#{collage},#{detail})")
    int insert(UserInfo userInfo);

    @Select("select * from UserInfo")
    List<UserInfo> findAll();

    @Select("select * from UserInfo where user_account like concat('%',#{user_account},'%')")
    List<UserInfo> findUsersByAccount(String account);

    @Select("select * from UserInfo where sname concat('%',#{sname},'%')")
    List<UserInfo> findUsersByName(String name);

    @Select("select * from UserInfo where sage=#{age}")
    List<UserInfo> findUsersByAge(int age);
    @Select("select * from UserInfo where collage=#{collage}")
    List<UserInfo> findUsersByCollage(String collage);
    @Select("select * from UserInfo where major=#{major}")
    List<UserInfo> findUsersByMajor(String major);
    @Select("select * from UserInfo where user_account=#{account}")
    UserInfo findUserByAccount(String account);

    @Update("update psx_db.UserInfo SET " +
            "sname = #{sname},sage=#{sage}," +
            "email=#{email},phone=#{phone},major=#{major}," +
            "gender=#{gender},birth=#{birth},collage=#{collage},detail=#{detail}" +
            "where user_account=#{user_account}")
    boolean updateUser(UserInfo user);

    @Select("<script>" +
            "select * from UserInfo" +
            "        <where>" +
            "            <if test=\"null !=user_account\">" +
            "                and user_account like concat('%',#{user_account},'%')"+
            "            </if>" +
            "            <if test=\"null !=sname\">" +
            "                and sname like concat('%',#{sname},'%')"+
            "            </if>" +
            "            <if test=\"null != sage\">" +
            "                and sage like concat('%',#{sage},'%')" +
            "            </if>" +
            "            <if test=\"null !=phone\">" +
            "                and phone like concat('%',#{phone},'%')"+
            "            </if>" +
            "            <if test=\"null !=email\">" +
            "                and email like concat('%',#{email},'%')"+
            "            </if>" +
            "            <if test=\"null !=major\">" +
            "                and major like concat('%',#{major},'%')"+
            "            </if>" +
            "            <if test=\"null !=collage\">" +
            "                and collage like concat('%',#{collage},'%')"+
            "            </if>" +
            "            <if test=\"null != null\">" +
            "                and gender=#{gender}" +
            "            </if>" +
            "        </where>"
            +"</script>")
    List<UserInfo> findUsersInfoByLikeQuery(UserInfo userInfo);

    @Delete("delete from psx_db.UserInfo where user_account=#{account}")
    int delete(String account);
}
