package dev.hyunec.squirrelvault.acorncollectorapi.controller

import dev.hyunec.squirrelvault.acorncollectorapi.facade.StatisticsFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

@RestController
class StatisticsController(
    private val statisticsFacade: StatisticsFacade
) {
    @GetMapping("/api/v1/statistics/summary")
    fun summary(
        @RequestParam startDate: LocalDate,
        @RequestParam endDate: LocalDate,
        @RequestParam requesterIds: List<String>
    ): Response {
        val startInstant = startDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toInstant()
        val endInstant = endDate.atTime(LocalTime.MAX).atZone(ZoneId.of("Asia/Seoul")).toInstant()

        return Response(
            count = statisticsFacade.summary(startInstant, endInstant, requesterIds),
            policy = statisticsFacade.billingPolicy()
        )
    }

    data class Response(
        val count: Map<String, String>,
        val policy: Map<String, String>,
    )
}
