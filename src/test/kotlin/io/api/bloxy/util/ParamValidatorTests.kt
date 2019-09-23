package io.api.bloxy.util

import io.api.bloxy.error.ParamException
import org.junit.Test
import kotlin.test.assertFalse


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.11.2018
 */
class ParamValidatorTests : ParamValidator() {

    @Test
    fun `address empty check`() {
        assertFalse{  isAddressValid("") }
    }

    @Test
    fun `hash empty check`() {
        assertFalse { isTxHashValid("") }
    }

    @Test
    fun `check blank for valid`() {
        assertFalse { checkNonBlank("ab").isEmpty() }
    }

    @Test(expected = ParamException::class)
    fun `check blank for empty`() {
        checkNonBlank("")
    }

    @Test(expected = ParamException::class)
    fun `check blank for blank`() {
        checkNonBlank("          ")
    }

    @Test(expected = ParamException::class)
    fun `check list blank for empty list`() {
        checkNonBlank(emptyList())
    }

    @Test(expected = ParamException::class)
    fun `check list blank for empty value`() {
        checkNonBlank(listOf(""))
    }

    @Test(expected = ParamException::class)
    fun `check list blank for blank value`() {
        checkNonBlank(listOf("          "))
    }

    @Test
    fun `check list blank for valid`() {
        assertFalse { checkNonBlank(listOf("  a  ")).isEmpty() }
    }
}