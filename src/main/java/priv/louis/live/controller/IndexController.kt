package priv.louis.live.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller("/")
class IndexController {

    @RequestMapping("")
    open fun index() : String{
        return "index"
    }

    @RequestMapping("hello")
    @ResponseBody
    fun hello() : String = "Hello"
}