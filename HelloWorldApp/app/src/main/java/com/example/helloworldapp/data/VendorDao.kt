//import androidx.room.*
//
//@Dao
//interface VendorDao {
//    @Transaction
//    @Query("SELECT * FROM vendors")
//    suspend fun getVendorsWithItems(): List<VendorWithItems>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertVendors(vendors: List<VendorEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertItems(items: List<ItemEntity>)
//
//    @Query("DELETE FROM vendors")
//    suspend fun clearVendors()
//
//    @Query("DELETE FROM items")
//    suspend fun clearItems()
//}
