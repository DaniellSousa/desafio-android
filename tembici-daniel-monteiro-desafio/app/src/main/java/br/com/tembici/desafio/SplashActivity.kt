package br.com.tembici.desafio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.tembici.desafio.view.activities.repositories.RepositoriesActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            Thread.sleep(2000)
        }

        val intent = Intent(this, RepositoriesActivity::class.java)
        startActivity(intent)

        finish()
    }
}
