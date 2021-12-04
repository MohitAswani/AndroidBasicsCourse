package com.example.todo
//
//import androidx.room.Room
//import androidx.test.platform.app.InstrumentationRegistry
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.todo.database.TodoDatabase
//import com.example.todo.database.TodoDatabaseDao
//import com.example.todo.database.TodoTask
//import org.junit.After
//
//import org.junit.Test
//import org.junit.runner.RunWith
//
//import org.junit.Assert.*
//import org.junit.Before
//import java.io.IOException
//import java.lang.Exception
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//@RunWith(AndroidJUnit4::class)
//class TodoDatabaseTest {
//
//    private lateinit var todoDao:TodoDatabaseDao
//    private lateinit var db:TodoDatabase
//
//    @Before
//    fun createDb(){
//        val context=InstrumentationRegistry.getInstrumentation().targetContext
//
//        db= Room.inMemoryDatabaseBuilder(context,TodoDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//
//        todoDao=db.todoDatabaseDao
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb(){
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetTask(){
//        val task=TodoTask()
//        todoDao.insert(task)
//        val currentTask=todoDao.getCurrentTask()
//        assertEquals(currentTask.satisfaction,-1)
//    }
//}