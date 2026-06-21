package mzn.faisal.userRole.controller.auth.register

import mzn.faisal.userRole.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user/auth")
class RegisterController(
    private val userService: UserService
) {

    @PostMapping("/register")
    suspend fun register(@RequestBody registerForm: RegisterForm): String {

        val userIdentity = userService.registerUser(registerForm.userIdentity)
        return userIdentity
    }
}

data class RegisterForm(val userIdentity: String)