import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [VendorEntity::class, ItemEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vendorDao(): VendorDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "foodapp.db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            ioThread {
                                INSTANCE?.let { database ->
                                    populateDatabase(database.vendorDao())
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = db
                db
            }
        }
    }
}

private fun populateDatabase(dao: VendorDao) {
    val vendors = (1..10).map {
        VendorEntity(
            name = "Vendor $it",
            lat = 44.0 + it,
            lng = 110.0 + it,
            region = it,
            delivery = it % 2 == 0,
            takeout = true,
            website = "www.vendor$it.com",
            grubhub = true,
            doordash = true,
            ubereats = false,
            postmates = false,
            yelp = true,
            googleReviews = true,
            tripadvisor = false,
            hours = "mon:7-23|tues:8-22|wed:8-22|thurs:8-22|fri:7-23|sat:9-23|sun:9-22",
            seo = "american|burgers",
            phone = 6512228888 + it,
            address = "25$it Lake Ave, St. Paul",
            zipcode = 55110 + it,
            customByNature = false
        )
    }

    val items = vendors.flatMap { vendor ->
        (1..10).map { i ->
            ItemEntity(
                vendorId = vendor.id, // Will fix later with generated ids
                name = "Item $i for Vendor ${vendor.name}",
                vegetarian = false,
                pescetarian = false,
                vegan = false,
                keto = true,
                organic = i % 2 == 0,
                gmoFree = i % 3 == 0,
                locallySourced = i % 4 == 0,
                raw = false,
                entree = true,
                sweet = false,
                kosher = true,
                halal = true,
                beef = true,
                chicken = false,
                pork = false,
                seafood = false,
                lowSugar = false,
                highProtein = true,
                lowCarb = false,
                noAlliums = false,
                noPorkProducts = true,
                noRedMeat = false,
                noMsg = true,
                noSesame = true,
                noMilk = true,
                noEggs = true,
                noFish = true,
                noShellfish = true,
                noPeanuts = true,
                noTreenuts = true,
                glutenFree = false,
                noSoy = true,
                price = 7.99 + i,
                upvotes = i,
                totalVotes = i + 5,
                pictures = "burger$i.jpg,pizza$i.jpg"
            )
        }
    }

    dao.insertVendors(vendors)
    dao.insertItems(items)
}

private fun ioThread(f: () -> Unit) = Thread(f).start()
