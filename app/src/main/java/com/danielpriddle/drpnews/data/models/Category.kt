package com.danielpriddle.drpnews.data.models

/**
 * Category
 *
 * This enum class defines the finite list of Source categories available to us from NewsAPI
 * @author Dan Priddle
 */

enum class Category(val apiName: String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
}