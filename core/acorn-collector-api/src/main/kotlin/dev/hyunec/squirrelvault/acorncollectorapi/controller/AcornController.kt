package dev.hyunec.squirrelvault.acorncollectorapi.controller

import dev.hyunec.squirrelvault.acorncollectorapi.facade.AcornCollectorFacade
import dev.hyunec.squirrelvault.acorncollectorapi.facade.AcornCollectorV2Facade
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AcornController(
    private val acornCollectorFacade: AcornCollectorFacade,
    private val acornCollectorV2Facade: AcornCollectorV2Facade
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
}
