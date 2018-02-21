package priv.louis.live.config

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyInterceptor : HandlerInterceptor {
    override fun preHandle(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: Any?): Boolean {
        //TODO
        return true;
    }

    override fun postHandle(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: Any?, p3: ModelAndView?) {
    }

    override fun afterCompletion(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: Any?, p3: Exception?) {
    }

}