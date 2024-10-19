package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.coredomain.model.Acorn
import dev.hyunec.squirrelvault.coredomain.repository.AcornRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AcornFindFacade(
    private val acornRepository: AcornRepository
) {
    private val log = KotlinLogging.logger {}

    fun find(
        startInstant: Instant,
        endInstant: Instant,
        requesterIds: List<String>
    ): List<Acorn> {
        return acornRepository.findAll()
            .filter { it.source.task.requesterId in requesterIds }
            .filter { it.source.task.completedAt in startInstant..endInstant }
    }
}
