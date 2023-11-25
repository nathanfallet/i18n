package me.nathanfallet.i18n.usecases.localization

import me.nathanfallet.i18n.models.localization.UTF8Control
import me.nathanfallet.usecases.localization.ITranslateUseCase
import me.nathanfallet.usecases.localization.Locale
import java.text.Format
import java.text.MessageFormat
import java.util.*

actual class TranslateUseCase(
    private val baseName: String = DEFAULT_RESOURCE_BUNDLE,
    private val control: ResourceBundle.Control = UTF8Control()
) : ITranslateUseCase {

    companion object {

        const val DEFAULT_RESOURCE_BUNDLE = "i18n.Messages"

    }

    private val cache = mutableMapOf<Pair<String, Locale>, Format>()

    override fun invoke(input1: Locale, input2: String, input3: List<String>): String {
        val bundle = ResourceBundle.getBundle(baseName, input1, control)
        val string = bundle.getString(input2)
        return input3.takeUnless { it.isEmpty() }?.let {
            cache.computeIfAbsent(Pair(string, bundle.locale)) {
                MessageFormat(string, bundle.locale)
            }.format(it)
        } ?: string
    }

}
