package com.example.potatoservice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.potatoservice.ui.sign.SignInActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 로그인 버튼 클릭 시 SignUpInfoActivity로 이동
        val kakaoLoginButton: Button = findViewById(R.id.kakaoLoginButton)
        kakaoLoginButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish() // SplashActivity 종료
        }
    }
}
