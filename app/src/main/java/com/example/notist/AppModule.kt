package com.example.notist

import com.example.notist.data.service.CourseService
import com.example.notist.data.service.ICourseService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module{
    viewModel {MainViewModel()}
    single<ICourseService> { CourseService() }
}