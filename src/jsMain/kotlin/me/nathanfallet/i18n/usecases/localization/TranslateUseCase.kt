package me.nathanfallet.i18n.usecases.localization

import me.nathanfallet.usecases.localization.ITranslateUseCase
import me.nathanfallet.usecases.localization.Locale

actual class TranslateUseCase : ITranslateUseCase {

    override fun invoke(input1: Locale, input2: String, input3: List<String>): String {
        TODO("Not yet implemented")
    }

}
