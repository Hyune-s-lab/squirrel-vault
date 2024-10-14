package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.coredomain.mapper.AcornV1Mapper
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AcornCollector(
    private val v1Mapper: AcornV1Mapper
) {
    private val log = KotlinLogging.logger {}

    fun collect(schemaVersion: String, jsonString: String) {
        val a = Acorn(
            "",
        )
//        val acorn = when (schemaVersion) {
//            "v1" -> v1Mapper.map(jsonString)
//
//            else -> throw UnsupportedOperationException("Unsupported schema version: $schemaVersion")
//        }
//
//        log.debug { "### acorn: $acorn" }

        // todo save to db
    }
}
