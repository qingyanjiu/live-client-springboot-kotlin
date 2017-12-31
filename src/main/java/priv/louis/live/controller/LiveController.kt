package priv.louis.live.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import priv.louis.live.service.LiveService
import priv.louis.live.vo.Live
import priv.louis.live.vo.LiveInfo
import java.util.*
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/live")
class LiveController {
    @Autowired
    private val liveService: LiveService? = null

    @Value("\${live.server.snapshoturl}")
    private val snapshotUrl: String? = null

    @Value("\${live.server.streamUrl}")
    private val streamUrl: String? = null

    @RequestMapping("/")
    @ResponseBody
    fun getAll(): List<LiveInfo> {
        return liveService!!.getAll()
    }

    @RequestMapping("/toList")
    fun toList(model: Model): String {
        var lives: List<LiveInfo> = ArrayList()
        lives = liveService!!.getAll()
        val title = "直播列表"
        model.addAttribute("lives", lives)
        model.addAttribute("title", title)
        model.addAttribute("snapshotUrl", snapshotUrl)
        return "live_list"
    }

    @RequestMapping("/userpage")
    fun myroom(model: Model, request: HttpServletRequest): String {
        val params = HashMap<Any, Any>()
        var live = LiveInfo()
        val userName = request.getUserPrincipal().getName()
        params.put("userName", userName)
        live = liveService!!.getActiveLiveOfUser(params)
        val title = "我的房间"
        model.addAttribute("live", live)
        model.addAttribute("title", title)
        model.addAttribute("streamUrl", streamUrl)
        return "user_page"
    }

    @RequestMapping("/show")
    fun toLive(model: Model, userName: String, password: String?): String {
        var result = "live_room"
        val params = HashMap<Any, Any>()
        params.put("userName", userName)
        var live: LiveInfo? = LiveInfo()
        live = liveService?.getActiveLiveOfUser(params)
        if (live == null) {
            result = "noLive"
        } else {
            if (password != null) {
                if (password == live.password)
                    model.addAttribute("pass", true)
                else
                    model.addAttribute("pass", false)
            } else {
                if (live.password == null)
                    model.addAttribute("pass", true)
                else
                    model.addAttribute("pass", false)
            }
            model.addAttribute("live", live)
            model.addAttribute("streamUrl", streamUrl)
        }
        return result
    }

    @RequestMapping("/add")
    @ResponseBody
    fun add(userName: String, liveName: String): Map<*, *> {
        val result = HashMap<Any, Any>()
        val params = HashMap<Any, Any>()
        params.put("userName", userName)
        params.put("liveName", liveName)
        try {
            liveService!!.add(params)
            result.put("result", "success")
        } catch (e: Exception) {
            e.printStackTrace()
            result.put("result", "error")
        }

        return result
    }

//    @RequestMapping("/delete")
//    public void delete(Map params){
//        liveService.delete(params);
//    }

    @RequestMapping("/end")
    @ResponseBody
    fun end(streamCode: String): Map<*, *> {
        val result = HashMap<Any, Any>()
        val params = HashMap<Any, Any>()
        params.put("streamCode", streamCode)
        try {
            liveService!!.end(params)
            result.put("result", "success")
        } catch (e: Exception) {
            e.printStackTrace()
            result.put("result", "error")
        }

        return result
    }

    @RequestMapping("/history")
    @ResponseBody
    fun getHistoryLivesOfUser(userId: String): List<Live> {
        val params = HashMap<Any, Any>()
        params.put("userId", userId)
        return liveService!!.getHistoryLivesOfUser(params)
    }

    @RequestMapping("/findByName/{liveName}")
    @ResponseBody
    fun getLiveByName(@PathVariable("liveName") liveName: String): List<Live> {
        val params = HashMap<Any, Any>()
        params.put("liveName", liveName)
        return liveService!!.getLiveByName(params)
    }

    @RequestMapping("/updateName")
    @ResponseBody
    fun updateLiveName(streamCode: String, liveName: String): Map<*, *> {
        val result = HashMap<Any, Any>()
        val params = HashMap<Any, Any>()
        params.put("streamCode", streamCode)
        params.put("liveName", liveName)
        try {
            liveService!!.updateLiveName(params)
            result.put("result", "success")
        } catch (e: Exception) {
            e.printStackTrace()
            result.put("result", "error")
        }

        return result
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    fun updateLivePassword(streamCode: String, password: String): Map<*, *> {
        val result = HashMap<Any, Any>()
        val params = HashMap<Any, Any>()
        params.put("streamCode", streamCode)
        params.put("password", password)
        try {
            liveService!!.updateLivePassword(params)
            result.put("result", "success")
        } catch (e: Exception) {
            e.printStackTrace()
            result.put("result", "error")
        }

        return result
    }

    @RequestMapping("/danmuList")
    fun danmuList(model: Model, request: HttpServletRequest): String {
        val params = HashMap<Any, Any>()
        val userName = request.userPrincipal.name
        params.put("userName", userName)
        val live = liveService?.getActiveLiveOfUser(params)
        if (live != null) {
            model.addAttribute("streamCode", live.streamCode)
        }
        return "danmuList"
    }
}