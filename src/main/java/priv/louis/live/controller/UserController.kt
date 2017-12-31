package priv.louis.live.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import priv.louis.live.service.UserService
import priv.louis.live.utils.Tools
import priv.louis.live.vo.User
import java.text.SimpleDateFormat
import java.util.*


@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    var userService: UserService? = null

    @RequestMapping("/")
    @ResponseBody
    fun getAll(): List<User> {
        return userService!!.getAll()
    }

    @RequestMapping("/{userId}")
    @ResponseBody
    fun getOne(@PathVariable("userId") userId: String): User {
        return userService!!.getOne(userId)
    }

    @RequestMapping("/findByName/{userName}")
    @ResponseBody
    fun getByName(@PathVariable("userName") userName: String): User {
        return userService!!.getByName(userName)
    }

    @RequestMapping("/add")
    @ResponseBody
    fun add(userName: String, password: String): Map<*, *> {
        val result = HashMap<Any, Any>()
        val sdf = SimpleDateFormat("yyyy-MM-dd ")
        val user = User()
        user.id = Tools.generateUUID()
        user.userName = userName
        user.password = Tools.encoderByMd5(password)
        user.status = "1"
        user.lastLoginDate = sdf.format(Date())
        user.registerDate = sdf.format(Date())
        val ret = userService!!.addUser(user)
        result.put("result", ret)
        return result
    }

    @RequestMapping("/checkName")
    @ResponseBody
    fun checkName(@RequestBody params: Map<*, *>) {
        userService!!.checkName(params)
    }

    @RequestMapping("/delete")
    @ResponseBody
    fun delete(@RequestBody params: Map<*, *>) {
        userService!!.deleteUser(params)
    }
}