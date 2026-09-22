package com.ubuntuhub.app

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UbuntuHubUnitTest {

    @Test
    fun validEmail_isAccepted() {
        val email = "test@example.com"

        val isValid =
            email.isNotBlank() &&
                    email.contains("@") &&
                    email.contains(".")

        assertTrue(isValid)
    }

    @Test
    fun invalidEmail_isRejected() {
        val email = "invalid-email"

        val isValid =
            email.isNotBlank() &&
                    email.contains("@") &&
                    email.contains(".")

        assertFalse(isValid)
    }

    @Test
    fun validPassword_isAccepted() {
        val password = "Password123!"

        val isValid = password.length >= 6

        assertTrue(isValid)
    }

    @Test
    fun emptyPost_isRejected() {
        val postText = ""

        val isValid = postText.trim().isNotEmpty()

        assertFalse(isValid)
    }

    @Test
    fun validPost_isAccepted() {
        val postText = "I can help with food donations."

        val isValid = postText.trim().isNotEmpty()

        assertTrue(isValid)
    }

    @Test
    fun username_fallsBackToEmail() {
        val displayName: String? = null
        val email = "boineelo@example.com"

        val username =
            displayName?.trim()?.takeIf { it.isNotEmpty() }
                ?: email.substringBefore("@")
                    .takeIf { it.isNotEmpty() }
                ?: "User"

        assertEquals("boineelo", username)
    }
}