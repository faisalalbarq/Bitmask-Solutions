package mzn.faisal.userRole.service

import mzn.faisal.userRole.enums.UserRole
import org.springframework.stereotype.Service


@Service
class UserService {
    private val users = mutableMapOf<String, Long>()
    private val rolesMap = mutableMapOf<Int, String>()

    init {
        rolesMap[0] = "USER"
        rolesMap[1] = "ADMIN"
        rolesMap[2] = "GUEST"
        rolesMap[3] = "MODERATOR"
        rolesMap[4] = "SUPPORT"
        rolesMap[5] = "MANAGER"
        rolesMap[6] = "DEVELOPER"
        rolesMap[7] = "ANALYST"
        rolesMap[8] = "AUDITOR"
        rolesMap[9] = "ACCOUNTANT"
        rolesMap[10] = "HR_MANAGER"
        rolesMap[11] = "SALES_REP"
        rolesMap[12] = "MARKETING_EXECUTIVE"
        rolesMap[13] = "BILLING_ADMIN"
        rolesMap[14] = "CONTENT_EDITOR"
        rolesMap[15] = "PRODUCT_OWNER"
        rolesMap[16] = "SCRUM_MASTER"
        rolesMap[17] = "DEVOPS_ENGINEER"
        rolesMap[18] = "SECURITY_ANALYST"
        rolesMap[19] = "DATABASE_ADMIN"
        rolesMap[20] = "SYSTEM_ADMIN"
        rolesMap[21] = "NETWORK_ENGINEER"
        rolesMap[22] = "QA_TESTER"
        rolesMap[23] = "CUSTOMER_SERVICE"
        rolesMap[24] = "LEGAL_COUNSEL"
        rolesMap[25] = "COMPLIANCE_OFFICER"
        rolesMap[26] = "FINANCE_DIRECTOR"
        rolesMap[27] = "OPERATIONS_MANAGER"
        rolesMap[28] = "TEAM_LEAD"
        rolesMap[29] = "PROJECT_MANAGER"
        rolesMap[30] = "VIP_MEMBER"
        rolesMap[31] = "PARTNER"
        rolesMap[32] = "VENDOR"
        rolesMap[33] = "CONSULTANT"
        rolesMap[34] = "CONTRACTOR"
        rolesMap[35] = "INTERN"
        rolesMap[36] = "EXECUTIVE"
        rolesMap[37] = "DIRECTOR"
        rolesMap[38] = "VICE_PRESIDENT"
        rolesMap[39] = "CEO"
        rolesMap[40] = "CTO"
        rolesMap[41] = "CFO"
        rolesMap[42] = "CIO"
        rolesMap[43] = "CMO"
        rolesMap[44] = "COO"
        rolesMap[45] = "CISO"
        rolesMap[46] = "REGIONAL_MANAGER"
        rolesMap[47] = "BRANCH_MANAGER"
        rolesMap[48] = "SUPERVISOR"
        rolesMap[49] = "COORDINATOR"
        rolesMap[50] = "SPECIALIST"
        rolesMap[51] = "RESELLER"
        rolesMap[52] = "AFFILIATE"
        rolesMap[53] = "API_USER"
        rolesMap[54] = "BOT"
        rolesMap[55] = "SYSTEM_INTERNAL"
        rolesMap[56] = "MAINTENANCE"
        rolesMap[57] = "BACKUP_ADMIN"
        rolesMap[58] = "RESTRICTED_USER"
        rolesMap[59] = "SUPER_ADMIN"
    }

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


    suspend fun checkRole(userIdentifier: String, role: Int): Boolean {
        val mask = users[userIdentifier] ?: throw Exception("User not found")
        rolesMap[role] ?: return false

        val roleMask = 1L shl role
        return (mask and roleMask) == roleMask
    }


    suspend fun getUserRoles(userIdentifier: String): List<Pair<Int, String>> {
        val mask = users[userIdentifier] ?: throw Exception("User not found")

        return rolesMap.entries
            .filter { curRole ->
                val curRoleValue = 1L shl curRole.key
                (mask and curRoleValue) == curRoleValue
            }
            .map { curRole -> Pair(curRole.key, curRole.value) }
    }

}

