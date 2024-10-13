package dev.hyunec.squirrelvault.coredomain.model

import java.time.Instant

/**
 * 수집된 정보의 출처
 *
 * @property name 사전에 정의된 서비스명
 * @property task 수행된 작업
 */
data class Source(
    val name: String,

    val task: Task,
    val memo: String
) {
    data class Task(
        val id: String,
        val name: String,
        val completedAt: Instant,

        val requesterId: String
    )
}
