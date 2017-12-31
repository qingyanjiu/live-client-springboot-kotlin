package priv.louis.live.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import priv.louis.live.mapper.LiveMapper
import priv.louis.live.mapper.UserMapper
import priv.louis.live.utils.Tools
import priv.louis.live.vo.Live
import priv.louis.live.vo.LiveInfo

@Service
class LiveService {
    @Autowired
    private val liveMapper: LiveMapper? = null
    @Autowired
    private val userMapper: UserMapper? = null

    fun getAll(): List<LiveInfo> {
        return liveMapper!!.getAll()
    }

    @Throws(Exception::class)
    fun add(params: Map<*, *>) {
        val id = Tools.generateUUID()
        val streamCode = Tools.generateUUID()
        val user = userMapper!!.findByName(params["userName"].toString()) ?: throw Exception("开启直播时未匹配到用户")
        val live = Live()
        live.id = id
        live.liveName = params["liveName"].toString()
        live.userId = user.id
        live.streamCode = streamCode
        liveMapper!!.add(live)
    }

//    public void delete(Map params){
//        liveMapper.delete(params);
//    }

    @Throws(Exception::class)
    fun end(params: Map<*, *>) {
        try {
            liveMapper!!.end(params)
        } catch (e: Exception) {
            throw Exception("修改直播间名出错")
        }

    }

    fun getActiveLiveOfUser(params: Map<*, *>): LiveInfo {
        var live: LiveInfo? = liveMapper!!.getActiveLiveOfUser(params)
        if (live == null) {
            live = LiveInfo()
            live.userName = params["userName"].toString()
        }
        return live
    }

    fun getHistoryLivesOfUser(params: Map<*, *>): List<Live> {
        return liveMapper!!.getHistoryLivesOfUser(params)
    }

    fun getLiveByName(params: Map<*, *>): List<Live> {
        return liveMapper!!.getLiveByName(params)
    }

    @Throws(Exception::class)
    fun updateLiveName(params: Map<*, *>) {
        try {
            liveMapper!!.updateLiveName(params)
        } catch (e: Exception) {
            throw Exception("修改直播间名出错")
        }

    }

    @Throws(Exception::class)
    fun updateLivePassword(params: Map<*, *>) {
        try {
            liveMapper!!.updateLivePassword(params)
        } catch (e: Exception) {
            throw Exception("修改直播间密码出错")
        }

    }
}