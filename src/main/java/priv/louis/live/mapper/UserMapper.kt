package priv.louis.live.mapper

import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository
import priv.louis.live.vo.User


@Repository
interface UserMapper {
    @Select("SELECT * FROM user_info")
    @Results(
            Result(property = "id", column = "id"),
            Result(property = "userName", column = "username"),
            Result(property = "password", column = "password"),
            Result(property = "gender", column = "gender"),
            Result(property = "nick", column = "nick"),
            Result(property = "registerDate", column = "register_date"),
            Result(property = "lastLoginDate", column = "last_login_date"),
            Result(property = "trueNameCert", column = "true_name_cert"),
            Result(property = "trueName", column = "true_name"),
            Result(property = "phoneNumber", column = "phone_number"),
            Result(property = "email", column = "email"),
            Result(property = "status", column = "status")
    )
    fun getAll(): List<User>

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    @Results(
            Result(property = "id", column = "id"),
            Result(property = "userName", column = "username"),
            Result(property = "password", column = "password"),
            Result(property = "gender", column = "gender"),
            Result(property = "nick", column = "nick"),
            Result(property = "registerDate", column = "register_date"),
            Result(property = "lastLoginDate", column = "last_login_date"),
            Result(property = "trueNameCert", column = "true_name_cert"),
            Result(property = "trueName", column = "true_name"),
            Result(property = "phoneNumber", column = "phone_number"),
            Result(property = "email", column = "email"),
            Result(property = "status", column = "status")
    )
    fun getOne(userId: String): User


    @Select("SELECT * FROM user_info WHERE username = #{username}")
    @Results(
            Result(property = "id", column = "id"),
            Result(property = "userName", column = "username"),
            Result(property = "password", column = "password"),
            Result(property = "gender", column = "gender"),
            Result(property = "nick", column = "nick"),
            Result(property = "registerDate", column = "register_date"),
            Result(property = "lastLoginDate", column = "last_login_date"),
            Result(property = "trueNameCert", column = "true_name_cert"),
            Result(property = "trueName", column = "true_name"),
            Result(property = "phoneNumber", column = "phone_number"),
            Result(property = "email", column = "email"),
            Result(property = "status", column = "status")
    )
    fun findByName(username: String): User

    @Insert("INSERT INTO user_info VALUES(#{id},#{userName},#{password},#{gender},#{nick}," + "#{registerDate},#{lastLoginDate},#{trueNameCert},#{trueName},#{phoneNumber},#{email},#{status})")
    fun insert(user: User)

    @Update("update user_info set last_login_date=#{date} where id=#{id}")
    fun updateLoginDate(params: Map<*, *>)

    @Update("update user_info set status=#{status} where id=#{id}")
    fun changeStatus(params: Map<*, *>)

    @Select("select count(id) count from user_info where username=#{userName}")
    fun checkName(params: Map<*, *>): Int
}