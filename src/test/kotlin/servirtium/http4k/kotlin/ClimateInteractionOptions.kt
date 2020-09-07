package servirtium.http4k.kotlin

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.servirtium.InteractionOptions

object ClimateInteractionOptions : InteractionOptions {
    override fun modify(request: Request) = request
        .replaceHeader("Host", "localhost")
        .replaceHeader("User-agent", "agent")
        .replaceHeader("Date", "Tue, 28 Jan 2020 14:15:55 GMT")

    override fun modify(response: Response) = response
        .removeHeader("Set-Cookie")
        .replaceHeader("Date", "Tue, 28 Jan 2020 14:15:55 GMT")
}