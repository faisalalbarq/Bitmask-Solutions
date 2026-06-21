package mzn.faisal.userRole

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
class UserRoleBackendApplication

fun main(args: Array<String>) {
    runApplication<UserRoleBackendApplication>(*args)
}
