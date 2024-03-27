package dev.ankit.cleanarchitectureforandroid.stubs

import ai.grazie.utils.capitalize

fun emptyDao(
    packageName: String
) = """
    package $packageName.data.room.dao

    import androidx.room.Dao
    import androidx.room.Insert
    import $packageName.data.room.entity.Demo

    @Dao
    interface DemoDao {

        //@Insert
        //suspend fun insert(demo: Demo)
    }
""".trimIndent()

fun emptyEntity(
    packageName: String
) = """
    package $packageName.data.room.entity

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "demo")
    class Demo {
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
    }
""".trimIndent()

fun emptyDatbase(
    packageName: String,
    appName: String
) = """
    package $packageName.data.room.db

    import android.content.Context


    import androidx.room.Database
    import androidx.room.Room
    import androidx.room.RoomDatabase
    import $packageName.data.room.dao.DemoDao
    import $packageName.data.room.entity.Demo
    import net.sqlcipher.database.SQLiteDatabase
    import net.sqlcipher.database.SupportFactory


    @Database(entities = [Demo::class], version = 1, exportSchema = false)
    abstract class ${appName}Db: RoomDatabase() {

        abstract fun demoDao(): DemoDao

        companion object {
            private const val SECRET_KEY = "${appName.lowercase()}_app"
            fun buildEncryptedDb(context: Context): ${appName}Db{
                val passphrase = SQLiteDatabase.getBytes(SECRET_KEY.toCharArray())

                val factory = SupportFactory(passphrase)

                return Room.databaseBuilder(
                    context,
                    ${appName}Db::class.java,
                    "${appName.lowercase()}_app_database"
                ).openHelperFactory(factory)
                    .build()
            }
        }
    }
""".trimIndent()