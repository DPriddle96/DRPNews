package com.danielpriddle.drpnews.data.dao

import com.danielpriddle.drpnews.data.database.entities.SourceEntity

object SourceListFactory {
    fun makeSourceListSeed(): List<SourceEntity> {
        return listOf(
            SourceEntity(
                id = "1",
                name = "Test Source 1",
            ),
            SourceEntity(
                id = "2",
                name = "Test Source 2",
            ),
            SourceEntity(
                id = "3",
                name = "Test Source 3",
            ),
        )
    }

    fun makeSourceListToAdd(): List<SourceEntity> {
        return listOf(
            SourceEntity(
                id = "4",
                name = "Test Source 4",
            ),
            SourceEntity(
                id = "5",
                name = "Test Source 5",
            ),
            SourceEntity(
                id = "6",
                name = "Test Source 6",
            ),
        )
    }
}