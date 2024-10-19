package dev.hyunec.squirrelvault.acorncollectorapi.support

import com.fasterxml.jackson.annotation.JsonInclude
import com.jayway.jsonpath.PathNotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant
import java.util.*

@RestControllerAdvice
class ApiControllerAdvice {
    private val log = KotlinLogging.logger {}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class, PathNotFoundException::class])
    fun handleBadRequestException(e: Exception): ErrorResponse {
        return e.toResponse()
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse {
        return e.toResponse().also { log.error { e } }
    }

    /**
     * 확장 함수를 통해 간결화할 수도 있습니다.
     */
    private fun Exception.toResponse(): ErrorResponse {
        return ErrorResponse(code = this.javaClass.simpleName, message = this.message)
    }

    /**
     * 표준 예외 응답 객체
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class ErrorResponse(
        val code: String,
        val message: String?,
        val data: Any? = null,
    ) {
        val timestamp: Instant = Instant.now()
        val traceId: String = UUID.randomUUID().toString()
    }
}
