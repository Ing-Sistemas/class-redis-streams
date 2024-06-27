package org.austral.ingsis.demo

import org.austral.ingsis.demo.testing.SpyProductCreatedProducer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class ClassRedisStreamsApplicationTests @Autowired constructor(val client: WebTestClient, val spy: SpyProductCreatedProducer) {

    @Test
    fun `POST to v1_stream_ciclon produces event with ciclon`() {
        client.post().uri("/v1/stream/ciclon").exchange().expectStatus().isOk
        assert(spy.events().size == 1) { "Expected one event to be produced but got ${spy.events().size}" }
        assert(spy.events().first().name == "ciclon") { "Expected first event to have name 'ciclon' but got ${spy.events().first().name}" }
    }
}
