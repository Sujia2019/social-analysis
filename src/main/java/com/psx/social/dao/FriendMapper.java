package com.psx.social.dao;

import com.psx.social.entity.FriendRequest;
import com.psx.social.entity.Tips;
import com.psx.social.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper {

    @Select("select COUNT(*) from pyq where account1=#{account} and addStatus=1")
    int countFriends(String account);
    @Select("select COUNT(*) from pyq where account2=#{account} and addStatus=0")
    int countRequest(String account);

    @Select("select requestCount from UserMore where user_account=#{account}")
    int historyRequestCount(String account);
    @Update("update UserMore set requestCount=#{count} where user_account=#{account}")
    int setHistoryRequestCount(Tips tips);

    @Select("select a.* from psx_db.UserInfo a left join psx_db.pyq p on a.user_account=p.account2 where p.addStatus=1;")
    List<UserInfo> friendList(String account);
    @Select("select a.* from psx_db.UserInfo a left join psx_db.pyq p on a.user_account=p.account2 where p.addStatus=0;")
    List<UserInfo> friendRequestList(String account);

    @Delete(" delete from pyq where account1=#{account1} and account2=#{account2};")
    boolean delFriends(FriendRequest del);


    @Insert("insert into pyq(account1,account2,detail,addStatus) values (#{account1},#{account2},#{detail},#{addStatus})")
    boolean insert(FriendRequest request);

    //    @Update("<script> update pyq set" +
//            "    addStatus=#{addStatus}" +
//            " <if test=\"null !=detail\"> " +
//            "    detail=#{detail}, " +
//            " </if> +" +
//            "    where account1=#{account1} and account2=#{account2}" +
//            "</script>")
    @Update("<script> update pyq set addStatus=#{addStatus}" +
            "   <if test=\"null != detail\">" +
            "       detail = #{detail}," +
            "   </if>" +
            "       where account1=#{account1} and account2=#{account2}" +
            "   </script>")
    boolean updateRequest(FriendRequest request);

    @Delete(" delete from pyq where account1=#{account1} and account2=#{account2}")
    boolean cancelRequest(FriendRequest request);

    @Results({
            @Result(column = "account1", property = "account1"),
            @Result(column = "account2", property = "account2"),
            @Result(column = "addStatus", property = "addStatus"),
            @Result(column = "detail", property = "detail")
    })
    @Select("select * from pyq where account1=#{account1} and account2=#{account2}")
    FriendRequest findRelationByAccount(String account1, String account2);

    @Results({
            @Result(column = "account1", property = "account1"),
            @Result(column = "account2", property = "account2"),
            @Result(column = "addStatus", property = "addStatus"),
            @Result(column = "detail", property = "detail")
    })
    @Select("select * from pyq where account2=#{account} and addStatus=0")
    List<FriendRequest> findRequest2(String account);

    @Results({
            @Result(column = "account1", property = "account1"),
            @Result(column = "account2", property = "account2"),
            @Result(column = "addStatus", property = "addStatus"),
            @Result(column = "detail", property = "detail")
    })
    @Select("select * from pyq where account1=#{account} and addStatus=0")
    List<FriendRequest> findRequest1(String account);


}
