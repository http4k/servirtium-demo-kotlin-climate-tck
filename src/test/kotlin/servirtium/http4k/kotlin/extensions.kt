package servirtium.http4k.kotlin

import org.junit.jupiter.api.TestInfo

fun TestInfo.markdownName() = displayName.removeSuffix("()")