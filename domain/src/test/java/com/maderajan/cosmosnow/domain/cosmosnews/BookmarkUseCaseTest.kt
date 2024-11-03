package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import com.maderajan.cosmosnow.domain.cosmosnews.fake.FakeBookmarkRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookmarkUseCaseTest: BaseUnitTest() {

    private lateinit var bookmarkUseCase: BookmarkUseCase
    private lateinit var fakeBookmarkRepository: IBookmarkRepository

    override fun setup() {
        fakeBookmarkRepository = FakeBookmarkRepository()

        bookmarkUseCase = BookmarkUseCase(
            bookmarkRepository = fakeBookmarkRepository
        )
    }

    @Test
    fun isBookmarkSaved_WhenIsNotInBookmarks() {
        testScope.runTest {
            bookmarkUseCase.toggleBookmark(CosmosNews.fake(isBookmarked = false))

            fakeBookmarkRepository.getAllBookmarksFlow().test {
                assert(awaitItem().isNotEmpty())

                awaitComplete()
            }
        }
    }

    @Test
    fun isBookmarkRemoved_WhenIsAlreadyInBookmarks() {
        testScope.runTest {
            bookmarkUseCase.toggleBookmark(CosmosNews.fake(id = 1, isBookmarked = false))
            bookmarkUseCase.toggleBookmark(CosmosNews.fake(id = 1, isBookmarked = true))

            fakeBookmarkRepository.getAllBookmarksFlow().test {
                assert(awaitItem().isEmpty())

                awaitComplete()
            }
        }
    }
}