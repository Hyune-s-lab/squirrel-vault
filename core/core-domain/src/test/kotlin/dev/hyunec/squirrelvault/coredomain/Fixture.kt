package dev.hyunec.squirrelvault.coredomain

import dev.hyunec.squirrelvault.coredomain.model.Acorn
import dev.hyunec.squirrelvault.coredomain.model.Source
import net.datafaker.Faker
import java.time.Instant
import java.util.*

object Fixture {
    private val faker = Faker()

    fun acornV1() = Acorn(
        source = Source(
            name = faker.lorem().word(),
            task = Source.Task(
                id = UUID.randomUUID().toString(),
                name = faker.lorem().word(),
                completedAt = Instant.now(),
                requesterId = UUID.randomUUID().toString(),
            ),
            memo = faker.lorem().sentence()
        ),
        type = Acorn.Type.MODEL_CALL,
        subType = Acorn.SubType.CHAT,
        memo = faker.lorem().sentence()
    )

    class Json {
        fun acornV1() = run {
            return@run (this::class.java.getResourceAsStream("/json/acornV1.json"))!!
                .bufferedReader().use { it.readText() }
        }
    }
}
