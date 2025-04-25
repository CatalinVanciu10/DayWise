package org.daywise.com

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform