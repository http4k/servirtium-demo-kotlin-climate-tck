package servirtium.http4k.java;

import org.junit.jupiter.api.TestInfo;

interface JUnitUtil {
    static String getMarkdownNameFrom(TestInfo info) {
        return info.getDisplayName().substring(0, info.getDisplayName().indexOf('('));
    }
}
