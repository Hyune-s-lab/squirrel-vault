package dev.hyunec.squirrelvault.coredomain.repository

import dev.hyunec.squirrelvault.coredomain.model.Acorn
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class AcornMapRepository(
    private val acornMap: ConcurrentHashMap<String, Acorn> = ConcurrentHashMap()
) : AcornRepository {
    override fun save(acorn: Acorn) {
        acornMap[generateId()] = acorn
    }

    override fun saveAll(acorns: List<Acorn>) {
        acorns.forEach { save(it) }
    }

    override fun findById(id: String): Acorn? {
        return acornMap[id]
    }

    override fun findAll(): List<Acorn> {
        return acornMap.values.toList()
    }

    private fun generateId(): String {
        return "${acornMap.size + 1}"
    }
}
