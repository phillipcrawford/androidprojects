package com.example.geoquiz

import org.junit.jupiter.api.Assertions.*
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
        scenario.close()
    }
}