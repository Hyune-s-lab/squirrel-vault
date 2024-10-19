package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.acorncollectorapi.Fixture
import dev.hyunec.squirrelvault.acorncollectorapi.TestSupport
import dev.hyunec.squirrelvault.acorncollectorapi.extension.RepositoryCleanerExtension
import dev.hyunec.squirrelvault.coredomain.repository.AcornMapRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@ExtendWith(RepositoryCleanerExtension::class)
@SpringBootTest
class AcornCollectorFacadeTest(
    private val sut: AcornCollectorFacade,

    private val acornRepository: AcornMapRepository
) : TestSupport() {
    @Test
    fun `v3 스키마의 acorn json 은 collect 과정을 거쳐 영속화 됩니다`() {
        sut.collect("v3", Fixture.Json().acornV3())

        acornRepository.findAll().size shouldBe 1
    }
}
