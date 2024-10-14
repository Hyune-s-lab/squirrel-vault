package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.coredomain.mapper.AcornV1Mapper
import dev.hyunec.squirrelvault.coredomain.mapper.AcornV2Mapper
import dev.hyunec.squirrelvault.coredomain.mapper.AcornV3Mapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AcornCollector(
    private val v1Mapper: AcornV1Mapper,
    private val v2Mapper: AcornV2Mapper,
    private val v3Mapper: AcornV3Mapper,
) {
    private val log = KotlinLogging.logger {}

    fun collect(schemaVersion: String, jsonString: String) {
        val acorn = when (schemaVersion) {
            "v1" -> v1Mapper.map(jsonString)
            "v2" -> v2Mapper.map(jsonString)
            "v3" -> v3Mapper.map(jsonString)

            else -> throw UnsupportedOperationException("Unsupported schema version: $schemaVersion")
        }

        log.debug { "### acorn: $acorn" }

        // todo save to db
    }
}
