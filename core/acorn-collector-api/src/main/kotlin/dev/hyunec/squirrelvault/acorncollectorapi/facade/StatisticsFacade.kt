package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.coredomain.repository.AcornRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class StatisticsFacade(
    private val acornRepository: AcornRepository
) {
    private val log = KotlinLogging.logger {}

    fun summary(
        startInstant: Instant,
        endInstant: Instant,
        requesterIds: List<String>
    ): Map<String, String> {
        return acornRepository.findAll()
            .filter { it.source.task.requesterId in requesterIds }
            .filter { it.source.task.completedAt in startInstant..endInstant }
            .groupBy { "${it.type}.${it.subType}" }
            .mapValues { it.value.size.toString() }
    }
}
