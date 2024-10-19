package dev.hyunec.squirrelvault.coredomain.repository

import dev.hyunec.squirrelvault.coredomain.model.Acorn

interface AcornRepository {
    fun save(acorn: Acorn)
    fun saveAll(acorns: List<Acorn>)

    fun findById(id: String): Acorn?
    fun findAll(): List<Acorn>
}
