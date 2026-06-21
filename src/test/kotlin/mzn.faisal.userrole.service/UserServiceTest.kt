package mzn.faisal.userRole.service

import kotlinx.coroutines.test.runTest
import mzn.faisal.userRole.enums.UserRole
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class UserServiceTest {

    @Test
    fun `should register user correctly`() = runTest {
        val userService = UserService()

        assertEquals("faisal_albarq", userService.registerUser("faisal_albarq"))
    }

    @Test
    fun `should handle user with only the first role`() = runTest {
        val userService = UserService()
        val userId = "faisal_first"
        userService.registerUser(userId)

        val mask = userService.assignRoles(userId, listOf(0))

        assertEquals(1L, mask)

        assertTrue(userService.checkRole(userId, 0))
        assertFalse(userService.checkRole(userId, 30))
        assertFalse(userService.checkRole(userId, 59))

        val rolesList = userService.getUserRoles(userId)
        assertEquals(1, rolesList.size)
        assertEquals(UserRole.USER, rolesList.first())
    }

    @Test
    fun `should handle user with only the last role`() = runTest {
        val userService = UserService()
        val userId = "faisal_last"
        userService.registerUser(userId)

        val mask = userService.assignRoles(userId, listOf(59))

        val expectedMask = 1L shl 59
        assertEquals(expectedMask, mask)

        assertFalse(userService.checkRole(userId, 0))
        assertFalse(userService.checkRole(userId, 30))
        assertTrue(userService.checkRole(userId, 59))

        val rolesList = userService.getUserRoles(userId)
        assertEquals(1, rolesList.size)
        assertEquals(UserRole.SUPER_ADMIN, rolesList.first())
    }

    @Test
    fun `should handle user with all possible roles`() = runTest {
        val userService = UserService()
        val userId = "faisal_all"
        userService.registerUser(userId)

        val allRoleOrdinals = UserRole.entries.map { it.ordinal }

        val mask = userService.assignRoles(userId, allRoleOrdinals)

        val expectedAllRolesMask = UserRole.entries.fold(0L) { acc, role -> acc or role.value }
        assertEquals(expectedAllRolesMask, mask)

        assertTrue(userService.checkRole(userId, 0))
        assertTrue(userService.checkRole(userId, 30))
        assertTrue(userService.checkRole(userId, 59))

        val rolesList = userService.getUserRoles(userId)
        assertEquals(60, rolesList.size)
    }

}