package dev.hyunec.squirrelvault.acorncollectorapi.controller

import dev.hyunec.squirrelvault.acorncollectorapi.facade.StatisticsFacade
import dev.hyunec.squirrelvault.coredomain.model.Acorn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatisticsController(
    private val statisticsFacade: StatisticsFacade
) {
    @GetMapping("/api/v1/statistics/")
    fun readAll(): List<Acorn> {
        return statisticsFacade.readAll()
    }
}
