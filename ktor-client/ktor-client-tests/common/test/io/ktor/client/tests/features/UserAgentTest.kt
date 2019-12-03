/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package io.ktor.client.tests.features

import io.ktor.client.engine.mock.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.tests.utils.*
import io.ktor.http.*
import kotlin.test.*

class UserAgentTest {

    @Test
    fun simpleInstallTest(): Unit = testWithEngine(MockEngine) {
        config {
            BrowserUserAgent()

            engine {
                addHandler { request ->
                    assertEquals(
                        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/70.0.3538.77 Chrome/70.0.3538.77 Safari/537.36",
                        request.headers[HttpHeaders.UserAgent]
                    )
                    respondOk()
                }
            }
        }

        test { client ->
            client.get<String>("http://goog.gl")
        }
    }
}
