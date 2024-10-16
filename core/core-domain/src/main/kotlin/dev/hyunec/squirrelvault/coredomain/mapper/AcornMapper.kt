package dev.hyunec.squirrelvault.coredomain.mapper

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import dev.hyunec.squirrelvault.coredomain.model.Metadata
import dev.hyunec.squirrelvault.coredomain.model.Source

/**
 * acorn schema version 에 대응되는 mapper 를 정의합니다.
 */
interface AcornMapper {
    fun map(jsonString: String): Acorn

    fun source(jsonString: String): Source

    fun metadata(jsonString: String): Metadata {
        return Metadata("", "", "")
    }

    companion object {
        val objectMapper = jacksonObjectMapper().apply {
            registerModule(JavaTimeModule())
        }
    }
}
