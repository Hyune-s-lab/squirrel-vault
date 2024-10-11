package dev.hyunec.squirrelvault.coredomain.model

import java.time.Instant

data class Acorn(
    val type: String,
    val collectedAt: Instant
)
