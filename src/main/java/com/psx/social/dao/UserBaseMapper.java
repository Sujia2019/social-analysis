package com.psx.social.dao;

import com.psx.social.entity.UserBase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseMapper {

    @Insert("insert into psx_db.UserBase(user_account, user_pwd, auth,email,phone)" +
            " values (#{user_account},#{user_pwd},${0},#{email},#{phone})")
    public int insert(UserBase userBase);

    @Update("update psx_db.UserBase SET " +
            "user_pwd = #{user_pwd},user_account=#{user_account},email=#{email},phone=#{phone}" +
            "where user_account=#{user_account}")
    public int update(UserBase userBase);

    @Update("update psx_db.UserBase SET " +
            "user_pwd = #{user_pwd} where phone=#{phone}")
    public int updatePwd(UserBase userBase);


    @Delete("delete from psx_db.UserBase where user_account=#{account}")
    public int delete(String account);

    @Select("<script>" +
            "select * from UserBase" +
            "        <where>" +
            "            <if test=\"null !=user_account\">" +
            "                and user_account=#{user_account}" +
            "            </if>" +
            "            <if test=\"null !=phone\">" +
            "                and phone=#{phone}" +
            "            </if>" +
            "            <if test=\"null !=email\">" +
            "                and email=#{email}" +
            "            </if>" +
            "                and auth=#{auth}"+
            "        </where>"+
            "                and user_pwd=#{user_pwd}"
            +"</script>")
    public UserBase findByAccountAndPwd(UserBase userBase);

    @Select("select * from UserBase where phone=#{phone}")
    public UserBase findByPhone(String phone);

    @Select("select * from UserBase where user_account=#{user_account}")
    public UserBase findByAccount(String user_account);

    @Select("select * from UserBase where email=#{email}")
    public UserBase findByEmail(String email);

    @Update("update UserBase set user_pwd=#{pwd} where user_account=account")
    int updateUserPwd(String account, String pwd);

    @Select("select count(*) from userBase where user_account=#{account}")
    int checkAccount(String account);
}
