package org.austral.ingsis.demo.testing

import org.austral.ingsis.demo.consumer.ProductCreated
import org.austral.ingsis.demo.producer.ProductCreatedProducer
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * This class is only to showcase how we workaround not using Redis in our E2E Tests.
 * We should test whether events are emitted or not.
 *
 * When using this Spy we have to be very careful because this is a Singleton so we have to take care of state.
 */

@Primary // favour this implementation instead of the real one
@Component
class SpyProductCreatedProducer: ProductCreatedProducer {

    private var seen = emptyList<ProductCreated>()

    override suspend fun publishEvent(name: String) {
        val event = ProductCreated(name)
        seen = seen + event
    }

    fun events() = seen

    fun reset() {
        seen = emptyList()
    }
}