package dev.hyunec.squirrelvault.coredomain

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import net.datafaker.Faker
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode


@ActiveProfiles(value = ["test"])
@TestConstructor(autowireMode = AutowireMode.ALL)
abstract class TestSupport {

    companion object {
        @JvmStatic
        protected val log = KotlinLogging.logger {}

        @JvmStatic
        protected val faker = Faker()

        @JvmStatic
        protected val objectMapper = jacksonObjectMapper().apply {
            registerModule(JavaTimeModule())
        }
    }
}
