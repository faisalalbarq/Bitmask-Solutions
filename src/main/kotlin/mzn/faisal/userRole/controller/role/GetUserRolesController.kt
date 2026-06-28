package mzn.faisal.userRole.controller.role

import mzn.faisal.userRole.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/role")
class GetUserRolesController(
    private val userService: UserService
) {

    @GetMapping("/list")
    suspend fun getUserRoles(@RequestParam userIdentifier: String): List<UserRoleResponse> {
        val roles = userService.getUserRoles(userIdentifier)

        return roles.map { (id, name) ->
            UserRoleResponse(id = id, name = name)
        }
    }
}

data class UserRoleResponse(val id: Int, val name: String)