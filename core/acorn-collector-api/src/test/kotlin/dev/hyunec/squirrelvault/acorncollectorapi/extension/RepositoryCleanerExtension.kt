package dev.hyunec.squirrelvault.acorncollectorapi.extension

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.stereotype.Repository
import org.springframework.test.context.junit.jupiter.SpringExtension

class RepositoryCleanerExtension : AfterAllCallback {
    override fun afterAll(context: ExtensionContext) {
        val reposiories = SpringExtension
            .getApplicationContext(context)
            .getBeansWithAnnotation(Repository::class.java).values

        reposiories.forEach { bean ->
            bean.javaClass.getMethod("init").invoke(bean)
        }
    }
}
