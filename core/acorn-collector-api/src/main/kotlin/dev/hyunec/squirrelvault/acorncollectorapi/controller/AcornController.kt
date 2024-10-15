package dev.hyunec.squirrelvault.acorncollectorapi.controller

import dev.hyunec.squirrelvault.acorncollectorapi.facade.AcornCollectorFacade
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AcornController(
    private val acornCollectorFacade: AcornCollectorFacade
) {
    @PostMapping("/api/v1/acorn/{schemaVersion}")
    fun collectAcorn(
        @PathVariable schemaVersion: String,
        @RequestBody jsonString: String
    ) {
        acornCollectorFacade.collect(schemaVersion, jsonString)
    }
}
