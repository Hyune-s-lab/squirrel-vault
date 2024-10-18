package dev.hyunec.squirrelvault.acorncollectorapi.controller

import com.fasterxml.jackson.databind.JsonNode
import dev.hyunec.squirrelvault.coredomain.mapper.AcornMapper.Companion.objectMapper
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import dev.hyunec.squirrelvault.coredomain.repository.AcornMapRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import net.datafaker.Faker
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@RestController
class DemoController(
    private val acornRepository: AcornMapRepository
) {
    private val log = KotlinLogging.logger {}
    private val faker = Faker()
    private val webClient: WebClient = WebClient.create("http://localhost:8080")

    private val saveDirectory = "saved_jsons"
    private val requesterIds = listOf(
        "a1b2c3d4e5f6",
        "1a2b3c4d5e6f",
        "a1b2c3d4e5f6",
        "1a2b3c4d5e6f",
        "a1b2c3d4e5f6",
        "1a2b3c4d5e6f",
        "a1b2c3d4e5f6",
        "1a2b3c4d5e6f",
        "a1b2c3d4e5f6",
        "1a2b3c4d5e6f"
    )


    @PostMapping("/demo/clear")
    fun clear() {
        val directoryPath = Paths.get(saveDirectory)

        Files.walk(directoryPath)
            .filter { it != directoryPath }
            .forEach { Files.delete(it) }

        acornRepository.init()
    }

    @PostMapping("/demo/create-json")
    fun createJson(@RequestBody request: CreateJson.Request) {
        // 디렉토리가 존재하지 않으면 생성
        val directoryPath = Paths.get(saveDirectory)
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath)
        }

        // JSON 데이터 생성
        request.data.entries.forEach { req ->
            repeat((1..req.value.toInt()).count()) {
                val schemaVersion = req.key
                val jsonData = generateJsonData(schemaVersion)
                val filePath = generateFile(schemaVersion)
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath, jsonData)
            }
        }
    }

    @PostMapping("/demo/send/{schemaVersion}")
    fun send(@PathVariable schemaVersion: String) {
        val directoryPath = Paths.get(saveDirectory)

        val filesToSend = Files.walk(directoryPath)
            .filter { it != directoryPath }
            .filter { it.fileName.toString().startsWith(schemaVersion) }
            .toList()

        val totalCount = filesToSend.size
        var successCount = 0
        var failureCount = 0
        val uri = if (schemaVersion == "v4") "/api/v1/acorn" else "/api/v1/acorn/$schemaVersion"

        filesToSend.forEach { filePath ->
            webClient.post()
                .uri(uri)
                .bodyValue(Files.readString(filePath))
                .retrieve()
                .bodyToMono(String::class.java)
                .doOnNext { successCount++ }
                .doOnError {
                    log.error { "### Error: $it" }
                    failureCount++
                }
                .subscribe()
        }

        log.info { "### Total: $totalCount, Success: $successCount, Failure: $failureCount" }
    }

    class CreateJson {
        data class Request(
            val data: Map<String, String>
        )
    }

    private fun generateFile(schemaVersion: String): File {
        val uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6)
        val fileName = "${schemaVersion}_$uuid.json"
        val filePath = Paths.get(saveDirectory, fileName).toString()
        return File(filePath)
    }

    fun getRandomInstantInPreviousMonth(zoneId: ZoneId = ZoneId.of("Asia/Seoul")): Instant {
        val previousMonth = ZonedDateTime.now(zoneId).minusMonths(1)
        val startInstant = previousMonth.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS).toInstant().epochSecond
        val endInstant = previousMonth
            .withDayOfMonth(previousMonth.month.length(previousMonth.toLocalDate().isLeapYear))
            .with(LocalTime.MAX).toInstant().epochSecond
        return Instant.ofEpochSecond(
            kotlin.random.Random.nextLong(startInstant, endInstant),
            kotlin.random.Random.nextLong(0, 1_000_000_000)
        )
    }

    private fun generateJsonData(schemaVersion: String): JsonNode {
        return when (schemaVersion) {
            "v1" -> {
                val taskNode = objectMapper.createObjectNode().apply {
                    put("id", UUID.randomUUID().toString())
                    put("name", faker.job().title())
                    put("completedAt", getRandomInstantInPreviousMonth().toString())
                    put("requesterId", requesterIds.random())
                }

                val sourceNode = objectMapper.createObjectNode().apply {
                    put("name", faker.company().name())
                    set<JsonNode>("task", taskNode)
                    put("memo", faker.lorem().sentence())
                }

                objectMapper.createObjectNode().apply {
                    set<JsonNode>("source", sourceNode)

                    put("type", Acorn.Type.entries.random().toString())
                    put("subType", Acorn.SubType.entries.random().toString())
                    put("memo", faker.lorem().sentence())
                }
            }

            "v2" -> {
                val taskNode = objectMapper.createObjectNode().apply {
                    put("id", UUID.randomUUID().toString())
                    put("name", faker.job().title())
                    put("completedAt", getRandomInstantInPreviousMonth().toString())
                    put("requesterId", requesterIds.random())
                }

                val sourceNode = objectMapper.createObjectNode().apply {
                    put("name", faker.company().name())
                    set<JsonNode>("task", taskNode)
                    put("memo", faker.lorem().sentence())
                    put("memo2", faker.lorem().sentence())
                }

                objectMapper.createObjectNode().apply {
                    set<JsonNode>("source", sourceNode)

                    put("type", Acorn.Type.entries.random().toString())
                    put("subType", Acorn.SubType.entries.random().toString())
                    put("memo", faker.lorem().sentence())
                }
            }

            "v3" -> {
                val taskNode = objectMapper.createObjectNode().apply {
                    put("id", UUID.randomUUID().toString())
                    put("name", faker.job().title())
                    put("completedAt", getRandomInstantInPreviousMonth().toString())
                    put("requesterId", requesterIds.random())
                }

                val sourceNode = objectMapper.createObjectNode().apply {
                    put("name", faker.company().name())
                    set<JsonNode>("task", taskNode)
                    put("memo", faker.lorem().sentence())
                }

                objectMapper.createObjectNode().apply {
                    set<JsonNode>("source", sourceNode)

                    put("type", Acorn.Type.entries.random().toString())
                    put("subType", Acorn.SubType.entries.random().toString())
                    put("memo", faker.lorem().sentence())
                }
            }

            "v4" -> {
                val metadata = objectMapper.createObjectNode().apply {
                    put("schemaName", "event.billing.acorn")
                    put("schemaVersion", "v4")
                    put("traceId", UUID.randomUUID().toString())
                }

                val taskNode = objectMapper.createObjectNode().apply {
                    put("id", UUID.randomUUID().toString())
                    put("name", faker.job().title())
                    put("completedAt", getRandomInstantInPreviousMonth().toString())
                    put("requesterId", requesterIds.random())
                    put("memo", faker.lorem().sentence())
                }

                val sourceNode = objectMapper.createObjectNode().apply {
                    put("name", faker.company().name())
                    set<JsonNode>("task", taskNode)
                    put("memo", faker.lorem().sentence())
                }

                objectMapper.createObjectNode().apply {
                    set<JsonNode>("metadata", metadata)
                    set<JsonNode>("source", sourceNode)

                    put("type", Acorn.Type.entries.random().toString())
                    put("subType", Acorn.SubType.entries.random().toString())
                    put("memo", faker.lorem().sentence())
                }
            }

            else -> throw UnsupportedOperationException("Unsupported schema version: $schemaVersion")
        }
    }
}
