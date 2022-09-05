package com.danielpriddle.drpnews.models

/**
 * Language
 *
 * This enum class defines the finite list of Source languages available to us from NewsAPI
 * @author Dan Priddle
 */
enum class Language(val apiName: String) {
    AR("ar"),
    DE("de"),
    EN("en"),
    ES("es"),
    FR("fr"),
    HE("he"),
    IT("it"),
    NL("nl"),
    NO("no"),
    PT("pt"),
    RU("ru"),
    SV("sv"),
    UD("ud"),
    ZH("zh"),
}