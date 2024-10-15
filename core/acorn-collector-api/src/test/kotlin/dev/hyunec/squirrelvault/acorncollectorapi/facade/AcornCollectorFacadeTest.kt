package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.acorncollectorapi.Fixture
import dev.hyunec.squirrelvault.acorncollectorapi.TestSupport
import dev.hyunec.squirrelvault.coredomain.repository.AcornRepository
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AcornCollectorFacadeTest(
    private val sut: AcornCollectorFacade,

    private val acornRepository: AcornRepository
) : TestSupport() {
    @Test
    fun `v3 스키마의 acorn json 은 collect 과정을 거쳐 영속화 됩니다`() {
        sut.collect("v3", Fixture.Json().acornV3())

        acornRepository.findAll().size shouldBe 1
    }
}
