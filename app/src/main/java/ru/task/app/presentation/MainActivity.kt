package ru.task.app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.task.app.R.layout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
    }
}
