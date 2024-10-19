package dev.hyunec.squirrelvault.coredomain.mapper

import com.jayway.jsonpath.PathNotFoundException
import dev.hyunec.squirrelvault.coredomain.Fixture
import dev.hyunec.squirrelvault.coredomain.TestSupport
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AcornMapperTest(
    private val v1Sut: AcornV1Mapper,
    private val v2Sut: AcornV2Mapper,
    private val v3Sut: AcornV3Mapper,
) : TestSupport() {

    @Test
    fun `json to object) v1 - v1`() {
        val jsonString = Fixture.Json().acornV1()

        val result = v1Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }

    @Test
    fun `json to object) v1 - v2, error`() {
        val jsonString = Fixture.Json().acornV1()

        shouldThrow<PathNotFoundException> { v2Sut.map(jsonString) }
            .run { log.error { this } }
    }

    @Test
    fun `json to object) v1 - v3, error`() {
        val jsonString = Fixture.Json().acornV1()

        shouldThrow<PathNotFoundException> { v3Sut.map(jsonString) }
            .run { log.error { this } }
    }

    @Test
    fun `json to object) v2 - v1`() {
        val jsonString = Fixture.Json().acornV2()

        val result = v1Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }

    @Test
    fun `json to object) v2 - v2`() {
        val jsonString = Fixture.Json().acornV2()

        val result = v2Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }

    @Test
    fun `json to object) v3 - v1`() {
        val jsonString = Fixture.Json().acornV3()

        val result = v1Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }

    @Test
    fun `json to object) v3 - v2`() {
        val jsonString = Fixture.Json().acornV3()

        val result = v2Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }

    @Test
    fun `json to object) v3 - v3`() {
        val jsonString = Fixture.Json().acornV3()

        val result = v3Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }
}
