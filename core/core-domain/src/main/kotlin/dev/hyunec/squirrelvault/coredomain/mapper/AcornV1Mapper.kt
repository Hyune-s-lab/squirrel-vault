package dev.hyunec.squirrelvault.coredomain.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import org.springframework.stereotype.Component

@Component
class AcornV1Mapper(
    private val objectMapper: ObjectMapper
) : AcornMapper {
    override fun map(jsonString: String): Acorn {
        return objectMapper.readValue<Acorn>(jsonString)
    }
}
