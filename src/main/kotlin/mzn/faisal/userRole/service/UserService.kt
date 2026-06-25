package mzn.faisal.userRole.service

import mzn.faisal.userRole.enums.UserRole
import org.springframework.stereotype.Service


@Service
class UserService {
    private val users = mutableMapOf<String, Long>()


    suspend fun registerUser(userIdentifier: String): String {
        if (users.containsKey(userIdentifier)) {
            throw Exception("User already exists")
        }

        users[userIdentifier] = 0L
        return userIdentifier
    }


    suspend fun assignRoles(userIdentifier: String, roles: List<Int>): Long {
        if (!users.contains(userIdentifier)) throw Exception("User not found")

        var mask = users[userIdentifier] ?: 0L
        for (curRole in UserRole.entries) {
            if (roles.contains(curRole.ordinal))
                mask = mask or curRole.value
        }

        users[userIdentifier] = mask
        return mask
    }


    suspend fun checkRole(userIdentifier: String, role: Int): Boolean{
        if (!users.contains(userIdentifier)) throw Exception("User not found")
        val mask = users[userIdentifier] ?: 0L

        val targetRole = UserRole.entries.getOrNull(role) ?: return false

        return (mask and targetRole.value) == targetRole.value
    }


    suspend fun getUserRoles(userIdentifier: String): List<UserRole> {
        if (!users.contains(userIdentifier)) throw Exception("User not found")
        val mask = users[userIdentifier] ?: 0L

        return UserRole.entries.filter { curRole ->
            (mask and curRole.value) == curRole.value
        }
    }

}

