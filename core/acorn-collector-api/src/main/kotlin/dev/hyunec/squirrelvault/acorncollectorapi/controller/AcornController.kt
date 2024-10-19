package dev.hyunec.squirrelvault.acorncollectorapi.controller

import dev.hyunec.squirrelvault.acorncollectorapi.facade.AcornCollectorFacade
import dev.hyunec.squirrelvault.acorncollectorapi.facade.AcornCollectorV2Facade
import dev.hyunec.squirrelvault.acorncollectorapi.facade.AcornFindFacade
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

@RestController
class AcornController(
    private val acornCollectorFacade: AcornCollectorFacade,
    private val acornCollectorV2Facade: AcornCollectorV2Facade,

    private val acornFindFacade: AcornFindFacade
) {
    @Deprecated("api spec 변경")
    @PostMapping("/api/v1/acorn/{schemaVersion}")
    fun collectAcorn(
        @PathVariable schemaVersion: String,
        @RequestBody jsonString: String
    ) {
        acornCollectorFacade.collect(schemaVersion, jsonString)
    }

    @PostMapping("/api/v1/acorn")
    fun collectAcornV2(@RequestBody jsonString: String) {
        return acornCollectorV2Facade.collect(jsonString)
    }

    @GetMapping("/api/v1/acorn")
    fun find(
        @RequestParam startDate: LocalDate,
        @RequestParam endDate: LocalDate,
        @RequestParam requesterIds: List<String>
    ): Response.Find {
        val startInstant = startDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toInstant()
        val endInstant = endDate.atTime(LocalTime.MAX).atZone(ZoneId.of("Asia/Seoul")).toInstant()

        return acornFindFacade.find(startInstant, endInstant, requesterIds).run {
            Response.Find(
                acorns = this,
                startAt = startInstant,
                endAt = endInstant,
                totalCount = this.size
            )
        }
    }

    class Response {
        data class Find(
            val acorns: List<Acorn>,
            val startAt: Instant,
            val endAt: Instant,
            val totalCount: Int
        )
    }
}
