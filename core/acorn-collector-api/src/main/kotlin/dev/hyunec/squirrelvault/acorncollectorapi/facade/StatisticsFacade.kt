package dev.hyunec.squirrelvault.acorncollectorapi.facade

import dev.hyunec.squirrelvault.coredomain.model.Acorn
import dev.hyunec.squirrelvault.coredomain.repository.AcornRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class StatisticsFacade(
    private val acornRepository: AcornRepository
) {
    private val log = KotlinLogging.logger {}

    fun count(): Int {
        return acornRepository.findAll().count()
    }

    fun readAll(): List<Acorn> {
        return acornRepository.findAll()
    }
}
