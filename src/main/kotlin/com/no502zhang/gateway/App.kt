package com.no502zhang.gateway

import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableZuulProxy
@SpringCloudApplication
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}