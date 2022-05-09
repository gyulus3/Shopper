package hu.bme.aut.shopper

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.model.network.ShoppingListResult
import hu.bme.aut.shopper.network.ShoppingListAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class, sdk = [31])
@RunWith(RobolectricTestRunner::class)
class NetworkUniTests {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var api: ShoppingListAPI

    @Inject
    lateinit var mockWebServer: MockWebServer

    @ApplicationContext
    @Inject
    lateinit var context: Context

    private fun readResponseBodyFromFile(context: Context, filePath: String): String? {
        return try {
            val source = context.assets.open(filePath).source().buffer()
            source.readByteString().string(Charset.forName("utf-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getItemAndConvertIt() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(200)
            .setBody(readResponseBodyFromFile(context, "getItem.json").orEmpty()))

        val response = api.getItemById(5828941074, "", "")

        val expected = ShoppingListResult(
            id = 0,
            content = "content0",
            description = "description0",
            created = "someDateHere0",
            completed = false
        )
        assertEquals(expected, response)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAllItems() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(200)
            .setBody(readResponseBodyFromFile(context, "getItems.json").orEmpty()))

        val response = api.getItems("", "")

        val expected = listOf(ShoppingListResult(
            id = 0,
            content = "content0",
            description = "description0",
            created = "someDateHere0",
            completed = false
        ),ShoppingListResult(
            id = 1,
            content = "content1",
            description = "description1",
            created = "someDateHere1",
            completed = false
        ))
        assertEquals(expected, response)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

}