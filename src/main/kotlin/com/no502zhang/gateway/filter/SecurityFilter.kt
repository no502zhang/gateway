package com.no502zhang.gateway.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.apache.commons.lang.StringUtils

class SecurityFilter : ZuulFilter() {
    override fun run(): Any? {
        var ctx = RequestContext.getCurrentContext()
        var request = ctx.request
        var token = request?.getHeader("z-token")
        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true) // 对该请求进行路由
            ctx.responseStatusCode = 200
            ctx.set("isSuccess", true)// 设值，让下一个Filter看到上一个Filter的状态
        } else {
            ctx.setSendZuulResponse(false) // 过滤该请求，不对其进行路由
            ctx.responseStatusCode = 401 // 返回错误码
            ctx.responseBody = "{\"result\":\"token is not correct!\"}" // 返回错误内容
            ctx.set("isSuccess", false)
        }

        return null
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return "pre"
    }

    override fun filterOrder(): Int {
        return 0
    }

}