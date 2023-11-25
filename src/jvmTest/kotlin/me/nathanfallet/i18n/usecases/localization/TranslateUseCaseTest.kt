package me.nathanfallet.i18n.usecases.localization

import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslateUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = TranslateUseCase()
        assertEquals("Hello world!", useCase(Locale.ENGLISH, "hello_world"))
    }

    @Test
    fun testInvokeFrench() {
        val useCase = TranslateUseCase()
        assertEquals("Coucou tout le monde !", useCase(Locale.FRENCH, "hello_world"))
    }

}
