package com.indexer.bilemo.database


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.indexer.bilemo.login.dao.LoginUserDao
import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.user.dao.UserListDao
import com.indexer.bilemo.user.enitity.UserResponse

@Database(
    entities = [LoginUser::class,UserResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    open val isDatabaseCreated: LiveData<Boolean> get() = mIsDatabaseCreated
    abstract val userLoginUserDao: LoginUserDao
    abstract val userListDao : UserListDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        private var databaseName: String = "bilemo"
        private val mIsDatabaseCreated = MutableLiveData<Boolean>()

        fun getDatabase(context: Context): AppDatabase {
            mIsDatabaseCreated.value = false
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    databaseName
                ).fallbackToDestructiveMigration()
                    .build()
                mIsDatabaseCreated.value = true
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

