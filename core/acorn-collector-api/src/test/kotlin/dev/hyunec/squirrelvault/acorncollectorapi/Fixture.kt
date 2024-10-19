package dev.hyunec.squirrelvault.acorncollectorapi

import net.datafaker.Faker

object Fixture {
    private val faker = Faker()

    class Json {
        fun acornV1() = run {
            return@run (this::class.java.getResourceAsStream("/json/acornV1.json"))!!
                .bufferedReader().use { it.readText() }
        }

        fun acornV2() = run {
            return@run (this::class.java.getResourceAsStream("/json/acornV2.json"))!!
                .bufferedReader().use { it.readText() }
        }

        fun acornV3() = run {
            return@run (this::class.java.getResourceAsStream("/json/acornV3.json"))!!
                .bufferedReader().use { it.readText() }
        }

        fun acorn(schemaVersion: String) = run {
            return@run (this::class.java.getResourceAsStream("/json/acorn${schemaVersion.uppercase()}.json"))!!
                .bufferedReader().use { it.readText() }
        }
    }
}
