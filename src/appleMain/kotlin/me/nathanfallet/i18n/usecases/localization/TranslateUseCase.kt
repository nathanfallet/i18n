package me.nathanfallet.i18n.usecases.localization

import me.nathanfallet.usecases.localization.ITranslateUseCase
import me.nathanfallet.usecases.localization.Locale
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.stringWithFormat

actual class TranslateUseCase(
    private val tableName: String = "Localizable"
) : ITranslateUseCase {

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
            1 -> NSString.stringWithFormat(localizedString, input3[0])
            2 -> NSString.stringWithFormat(localizedString, input3[0], input3[1])
            3 -> NSString.stringWithFormat(localizedString, input3[0], input3[1], input3[2])
            4 -> NSString.stringWithFormat(localizedString, input3[0], input3[1], input3[2], input3[3])
            5 -> NSString.stringWithFormat(localizedString, input3[0], input3[1], input3[2], input3[3], input3[4])
            6 -> NSString.stringWithFormat(
                localizedString,
                input3[0],
                input3[1],
                input3[2],
                input3[3],
                input3[4],
                input3[5]
            )

            7 -> NSString.stringWithFormat(
                localizedString,
                input3[0],
                input3[1],
                input3[2],
                input3[3],
                input3[4],
                input3[5],
                input3[6]
            )

            8 -> NSString.stringWithFormat(
                localizedString,
                input3[0],
                input3[1],
                input3[2],
                input3[3],
                input3[4],
                input3[5],
                input3[6],
                input3[7]
            )

            9 -> NSString.stringWithFormat(
                localizedString,
                input3[0],
                input3[1],
                input3[2],
                input3[3],
                input3[4],
                input3[5],
                input3[6],
                input3[7],
                input3[8]
            )

            else -> error("Too many arguments.")
        }
    }

}
