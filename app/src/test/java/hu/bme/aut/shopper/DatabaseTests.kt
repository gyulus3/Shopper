package hu.bme.aut.shopper

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import hu.bme.aut.shopper.database.AppDatabase
import hu.bme.aut.shopper.di.PersistenceModule
import hu.bme.aut.shopper.model.db.ShoppingListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@UninstallModules(PersistenceModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class DatabaseTests {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var database: AppDatabase

    @ExperimentalCoroutinesApi
    @Test
    fun insertCharacterTest() = runTest {
        val dao = database.shoppingListItemDAO()
        dao.insert(
            ShoppingListItem(
                id = 1,
                content = "content1",
                completed = false,
                created = null,
                project_id = null,
                description = null
            )
        )
        dao.insert(
            ShoppingListItem(
                id = 2,
                content = "content2",
                completed = false,
                created = null,
                project_id = null,
                description = null
            )
        )
        dao.insert(
            ShoppingListItem(
                id = 3,
                content = "content3",
                completed = false,
                created = null,
                project_id = null,
                description = null
            )
        )
        val items = dao.getApprovedItems()
        assertEquals(1, items.value!!.size)
        database.close()
    }
}