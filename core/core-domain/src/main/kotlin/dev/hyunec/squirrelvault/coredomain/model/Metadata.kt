package dev.hyunec.squirrelvault.coredomain.model

/**
 * 메타 데이터
 */
data class Metadata(
    val schemaName: String,
    val schemaVersion: String,
    val traceId: String
)
