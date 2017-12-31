package priv.louis.live.mapper

import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository
import priv.louis.live.vo.Live
import priv.louis.live.vo.LiveInfo


@Repository
interface LiveMapper {
    @Select("select * from live_list where status in ('0','9') order by status desc,start_time desc")
    @Results(Result(property = "userName", column = "username"), Result(property = "trueName", column = "true_name"), Result(property = "startTime", column = "start_time"), Result(property = "streamCode", column = "streamcode"), Result(property = "status", column = "status"), Result(property = "liveName", column = "live_name"), Result(property = "password", column = "password"))
    fun getAll(): List<LiveInfo>

    @Insert("INSERT INTO live_info VALUES(#{id},#{userId},sysdate(),#{endTime}," + "#{streamCode},#{status},#{liveName},#{password})")
    fun add(live: Live)

//    @Update("update live_info set status='1' where user_id=\${userId} and status='0'")
//    fun delete(params: Map<*, *>)

    @Update("update live_info set end_time=sysdate(),status='1' where streamcode=#{streamCode}")
    fun end(params: Map<*, *>)

    @Select("<script>", "select * from live_list where username=#{userName} ", "and status in ('0','9')", "</script>")
    @Results(Result(property = "id", column = "id"), Result(property = "userId", column = "user_id"), Result(property = "startTime", column = "start_time"), Result(property = "endTime", column = "end_time"), Result(property = "streamCode", column = "streamcode"), Result(property = "status", column = "status"), Result(property = "liveName", column = "live_name"), Result(property = "password", column = "password"))
    fun getActiveLiveOfUser(params: Map<*, *>): LiveInfo

    @Select("select * from live_info where user_id=#{userId} order by start_time desc")
    @Results(Result(property = "id", column = "id"), Result(property = "userId", column = "user_id"), Result(property = "startTime", column = "start_time"), Result(property = "endTime", column = "end_time"), Result(property = "streamCode", column = "streamcode"), Result(property = "status", column = "status"), Result(property = "liveName", column = "live_name"), Result(property = "password", column = "password"))
    fun getHistoryLivesOfUser(params: Map<*, *>): List<Live>

    @Select("select * from live_list where userid=\${userId} and status in ('0','9')")
    @Results(Result(property = "userId", column = "userid"), Result(property = "userName", column = "username"), Result(property = "trueName", column = "true_name"), Result(property = "startTime", column = "start_time"), Result(property = "streamCode", column = "streamcode"), Result(property = "status", column = "status"), Result(property = "liveName", column = "live_name"), Result(property = "password", column = "password"))
    fun getLiveByName(params: Map<*, *>): List<Live>

    @Update("update live_info set live_name=#{liveName} where streamcode=#{streamCode}")
    fun updateLiveName(params: Map<*, *>)


    @Update("update live_info set password=#{password} where streamcode=#{streamCode}")
    fun updateLivePassword(params: Map<*, *>)
}