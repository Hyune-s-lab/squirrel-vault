package dev.hyunec.squirrelvault.coredomain.mapper

import dev.hyunec.squirrelvault.coredomain.Fixture
import dev.hyunec.squirrelvault.coredomain.TestSupport
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AcornV1MapperTest(
    private val sut: AcornV1Mapper
) : TestSupport() {

    @Test
    fun `json to object - v1`() {
        val jsonString = Fixture.Json().acornV1()

        val result = sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }
}
