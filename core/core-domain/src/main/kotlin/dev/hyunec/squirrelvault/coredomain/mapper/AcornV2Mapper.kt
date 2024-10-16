package dev.hyunec.squirrelvault.coredomain.mapper

import com.jayway.jsonpath.JsonPath
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import dev.hyunec.squirrelvault.coredomain.model.Source
import org.springframework.stereotype.Component
import java.time.Instant

/**
 * v1 -> v2
 * - source.memo 추가
 * - source.memo2 추가
 */
@Component
class AcornV2Mapper : AcornMapper {
    override fun map(jsonString: String): Acorn {
        val acorn = Acorn(
            source = source(jsonString),
            type = Acorn.Type.valueOf(JsonPath.read(jsonString, "$.type")),
            subType = Acorn.SubType.valueOf(JsonPath.read(jsonString, "$.subType")),
            memo = JsonPath.read(jsonString, "$.memo"),
        )

        return acorn
    }

    override fun source(jsonString: String): Source {
        val task = Source.Task(
            id = JsonPath.read(jsonString, "$.source.task.id"),
            name = JsonPath.read(jsonString, "$.source.task.name"),
            completedAt = Instant.parse(JsonPath.read(jsonString, "$.source.task.completedAt")),
            requesterId = JsonPath.read(jsonString, "$.source.task.requesterId"),
            memo = JsonPath.read(jsonString, "$.source.task.memo"),
        )
        return Source(
            name = JsonPath.read(jsonString, "$.source.name"),
            task = task,
            memo = JsonPath.read(jsonString, "$.source.memo"),
        )
    }
}
