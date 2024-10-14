package dev.hyunec.squirrelvault.coredomain.mapper

import dev.hyunec.squirrelvault.coredomain.model.Acorn

interface AcornMapper {
    fun map(jsonString: String): Acorn
}
