package dev.hyunec.squirrelvault.coredomain.mapper

import dev.hyunec.squirrelvault.coredomain.Fixture
import dev.hyunec.squirrelvault.coredomain.TestSupport
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AcornMapperTest(
    private val v1Sut: AcornV1Mapper,
    private val v2Sut: AcornV2Mapper
) : TestSupport() {

    @Test
    fun `json to object) v1 - v1`() {
        val jsonString = Fixture.Json().acornV1()

        val result = v1Sut.map(jsonString)

        result shouldNotBe null
        log.info { "### result: $result" }
    }

    @Test
    fun `json to object) v2 - v1, 신규 필드는 하위 호환성을 해치지 않음`() {
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
}
