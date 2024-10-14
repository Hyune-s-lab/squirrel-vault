package dev.hyunec.squirrelvault.acorncollectorapi.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["dev.hyunec.squirrelvault.coredomain"])
@ConfigurationPropertiesScan(basePackages = ["dev.hyunec.squirrelvault.coredomain.config"])
class AcornCollectApiConfig
