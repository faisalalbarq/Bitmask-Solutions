package mzn.faisal.userRole.controller.role

import mzn.faisal.userRole.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/role")
class RoleController(
    private val userService: UserService
) {

    @PostMapping("/assign")
    suspend fun assignRole(@RequestBody assignRoleForm: AssignRoleForm): Long {

        val userRole = userService.assignRoles(assignRoleForm.userIdentifier, assignRoleForm.roles)

        return  userRole
    }
}

data class AssignRoleForm(val userIdentifier: String, val roles: List<Int>)