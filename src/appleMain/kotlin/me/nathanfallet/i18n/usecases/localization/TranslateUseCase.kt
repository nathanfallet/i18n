package me.nathanfallet.i18n.usecases.localization

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import me.nathanfallet.usecases.localization.ITranslateUseCase
import me.nathanfallet.usecases.localization.Locale
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.stringWithFormat

actual class TranslateUseCase(
    private val tableName: String = "Localizable"
) : ITranslateUseCase {

    @OptIn(ExperimentalForeignApi::class)
    override fun invoke(input1: Locale, input2: String, input3: List<String>): String {
        val localizedString = NSBundle.mainBundle
            .localizedStringForKey(input2, input2, tableName)
            .takeIf { it != input2 }
            ?: run {
                NSBundle.mainBundle
                    .pathForResource("Base", "lproj")
                    ?.let { NSURL(fileURLWithPath = it) }
                    ?.let { NSBundle(it) }
                    ?.localizedStringForKey(input2, input2, tableName)
            } ?: input2

        return when (input3.size) {
            0 -> NSString.stringWithFormat(localizedString)
            1 -> NSString.stringWithFormat(localizedString, input3[0].cstr)
            2 -> NSString.stringWithFormat(localizedString, input3[0].cstr, input3[1].cstr)
            3 -> NSString.stringWithFormat(localizedString, input3[0].cstr, input3[1].cstr, input3[2].cstr)
            4 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr
            )

            5 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr
            )

            6 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr
            )

            7 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr,
                input3[6].cstr
            )

            8 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr,
                input3[6].cstr,
                input3[7].cstr
            )

            9 -> NSString.stringWithFormat(
                localizedString,
                input3[0].cstr,
                input3[1].cstr,
                input3[2].cstr,
                input3[3].cstr,
                input3[4].cstr,
                input3[5].cstr,
                input3[6].cstr,
                input3[7].cstr,
                input3[8].cstr
            )

            else -> error("Too many arguments.")
        }
    }

}
