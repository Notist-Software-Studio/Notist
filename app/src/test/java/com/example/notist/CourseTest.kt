package com.example.notist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notist.data.dto.Course
import com.example.notist.data.service.CourseService
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.BeforeClass
import java.util.concurrent.TimeUnit

class CourseTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var courseService: CourseService
    var allCourses : List<Course>? = ArrayList<Course>()
    lateinit var mvm : MainViewModel
    @MockK
    lateinit var mockCourseService : CourseService

//    private val mainThreadSurrogate = newSingleThreadContext("Main Thread")
//
//    @BeforeClass //run this before every test
//    fun initMocksAndMainThread(){
//        MockKAnnotations.init(this)
//        Dispatchers.setMain(mainThreadSurrogate)
//    }
//    //make object from MockK
//
//    @After //run this after every test
//    fun tearDown(){
//        Dispatchers.resetMain()
//        mainThreadSurrogate.close()
//    }


    @Test
    fun`Search data`()= runTest{
        givenCourseServiceIsInitialized()
        whenCourseDataAreReadAndParsed()
        thenTheCoursesCollectionShouldContainDataStructures()

    }
    private fun givenCourseServiceIsInitialized(){
        courseService = CourseService()
    }
    private suspend fun whenCourseDataAreReadAndParsed() {
        allCourses = courseService.fetchCourses()
    }
    private fun thenTheCoursesCollectionShouldContainDataStructures() {
        assertNotNull(allCourses)
        assertTrue(allCourses!!.isNotEmpty())
        var containsDataStructures = false
        allCourses!!.forEach{
            if(it.class_name.equals("Data Structures"))
            {
                containsDataStructures = true
            }
        }
        assertTrue(containsDataStructures)
    }


    @Test
    fun`search from given view model with live data`()= runTest {
        givenViewModelIsInitializedWithMockData()
        whenCourseServiceFetchCoursesInvoked()
        thenResultShouldContainDataStructures()
    }

    private fun givenViewModelIsInitializedWithMockData() {
        val courses = ArrayList<Course>()
        courses.add(Course(class_name = "Logic Design", major = "EECS"))
        val SoftwareStudio = Course("Software Studio","CS")
        courses.add(SoftwareStudio)
        coEvery { mockCourseService.fetchCourses()} returns courses
        //for everytime fetchCourses is called lets return courses
        mvm = MainViewModel()
        mvm.courseService = mockCourseService
        //MainViewModel take mockCourseService

    }

    private fun whenCourseServiceFetchCoursesInvoked() {
       mvm.fetchCourses()
    }

    private fun thenResultShouldContainDataStructures() {
        //observing on liveData
        var allCourses : List<Course>? = ArrayList<Course>()
        var latch = CountDownLatch(1)
        //expecting to have 1 process that are going to occurs on seperate threads
        val observer = object : Observer<List<Course>>{
            override fun onChanged(receivedCourses: List<Course>?) {
                allCourses = receivedCourses
                latch.countDown()
                mvm.courses.removeObserver(this)
            }
        }
        //inserting a singleton object
        mvm.courses.observeForever(observer)
        latch.await(10, TimeUnit.SECONDS)
        //if the countDown latch never reach 0 we can use 10 seconds timer
        assertNotNull(allCourses)
        assertTrue(allCourses!!.isNotEmpty())
        var containsDataStructures = false
        allCourses!!.forEach{
            if(it.class_name.equals("Data Structures"))
            {
                containsDataStructures = true
            }
        }
        assertTrue(containsDataStructures)
    }

}