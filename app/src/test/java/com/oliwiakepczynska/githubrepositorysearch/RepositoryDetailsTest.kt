package com.oliwiakepczynska.githubrepositorysearch

import com.oliwiakepczynska.githubrepositorysearch.domain.data.NetworkClient
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.RepositoryDetails
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.util.concurrent.CountDownLatch
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class RepositoryDetailsTest {

    private lateinit var sut: RepositoryDetails
    private val mockWebServer = MockWebServer()
    private val errorsStream = PublishSubject.create<Throwable>()

    @Before
    fun setup() {
        mockWebServer.start()
        sut = RepositoryDetails.create(
            NetworkClient.create(mockWebServer.url("/").toString()), errorsStream
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_errors_stream_publishes_exception_when_error_occurs_on_http_request() {
        val latch = CountDownLatch(2)
        mockWebServer.enqueue(MockResponse().setResponseCode(500).setBody("[]"))
        errorsStream.subscribe {
            Assert.assertTrue(it is HttpException)
            Assert.assertTrue((it as HttpException).code() == 500)
            latch.countDown()
        }
        sut.getAllRepositories().subscribe {
            Assert.assertTrue(it.isEmpty())
            latch.countDown()
        }
        latch.await()
    }
}