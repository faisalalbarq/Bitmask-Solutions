package mzn.faisal.userRole.controller.role

import mzn.faisal.userRole.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/role")
class CheckRoleController(
    private val userService: UserService
){

    @GetMapping("/check")
    suspend fun checkRole(
        checkRoleForm: CheckRoleForm
    ): Boolean {
        val hasRole = userService.checkRole(checkRoleForm.userIdentifier, checkRoleForm.role)
        return hasRole
    }
}

data class CheckRoleForm(val userIdentifier: String, val role: Int)