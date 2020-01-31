package servirtium.http4k.java;

import org.http4k.core.ContentType;
import org.http4k.core.Request;
import org.http4k.core.Response;
import org.http4k.servirtium.InteractionOptions;
import org.jetbrains.annotations.NotNull;

public class ClimateInteractionOptions implements InteractionOptions {
    @NotNull
    @Override
    public Request modify(@NotNull Request request) {
        return request
                .replaceHeader("Host", "localhost")
                .replaceHeader("User-agent", "agent")
                .replaceHeader("Date", "Tue, 28 Jan 2020 14:15:55 GMT");
    }

    @NotNull
    @Override
    public Response modify(@NotNull Response response) {
        return response
                .removeHeader("Set-Cookie")
                .replaceHeader("Date", "Tue, 28 Jan 2020 14:15:55 GMT");
    }

    @Override
    public boolean isBinary(@NotNull ContentType contentType) {
        return false;
    }
}
