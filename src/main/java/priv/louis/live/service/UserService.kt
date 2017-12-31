package priv.louis.live.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import priv.louis.live.mapper.UserMapper
import priv.louis.live.vo.User


@Service
class UserService {
    @Autowired
    private val userMapper: UserMapper? = null

    fun getAll(): List<User> {
        return userMapper!!.getAll()
    }

    fun getOne(userId: String): User {
        return userMapper!!.getOne(userId)
    }

    fun getByName(userName: String): User {
        return userMapper!!.findByName(userName)
    }

    fun addUser(user: User): String {
        var ret = ""
        var existUser: User? = User()
        existUser = getByName(user.userName)
        if (existUser == null) {
            try {
                userMapper!!.insert(user)
                ret = "success"
            } catch (e: Exception) {
                ret = "error"
            }

        } else
            ret = "exist"
        return ret
    }

    fun updateLoginDate(params: Map<*, *>) {
        userMapper!!.updateLoginDate(params)
    }

    fun deleteUser(params: Map<*, *>) {
        userMapper!!.changeStatus(params)
    }

    fun checkName(params: Map<*, *>) {
        userMapper!!.checkName(params)
    }
}