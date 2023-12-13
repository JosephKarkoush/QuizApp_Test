package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        supportFragmentManager.commit {
            add<HomeFragment>(R.id.container, null)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> goToHome()

                R.id.action_shop -> goToStatements()

                else -> false
            }
        }
    }

    private fun goToStatements(): Boolean {
        supportFragmentManager.commit {
            replace<QuizFragment>(R.id.container, null, null)
        }

        return true
    }

    private fun goToHome(): Boolean {
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.container, null, null)
        }

        return true
    }

}