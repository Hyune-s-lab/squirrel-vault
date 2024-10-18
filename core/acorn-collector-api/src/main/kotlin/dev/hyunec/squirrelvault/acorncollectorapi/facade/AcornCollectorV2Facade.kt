package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.coredomain.mapper.AcornV4Mapper
import dev.hyunec.squirrelvault.coredomain.repository.AcornRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AcornCollectorV2Facade(
    private val v4Mapper: AcornV4Mapper,

    private val acornRepository: AcornRepository
) {
    private val log = KotlinLogging.logger {}

    private val validSchemaName = "event.billing.acorn"
    private val validSchemaVersions = listOf("v4")


    fun collect(jsonString: String) {
        // metadata 검증
        val metadata = v4Mapper.metadata(jsonString)

        require(metadata.schemaName == validSchemaName) {
            "Unsupported schema name: ${metadata.schemaName}"
        }

        require(metadata.schemaVersion in validSchemaVersions) {
            "Unsupported schema version: ${metadata.schemaVersion}"
        }

        // jsonString 을 arcon 객체로 변환
        val acorn = when (metadata.schemaVersion) {
            "v4" -> v4Mapper.map(jsonString)

            else -> throw UnsupportedOperationException("Unsupported schema version: ${metadata.schemaVersion}")
        }

        log.debug { "### acorn: $acorn" }

        // acorn 객체 영속화
        acornRepository.save(acorn)
    }
}
