package com.example.potatoservice.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.common.KakaoSdk
import com.example.potatoservice.MainActivity
import com.example.potatoservice.R
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in) // 레이아웃 설정

        KakaoSdk.init(this, getString(R.string.kakao_api_key)) // 카카오 SDK 초기화

        // 로그인 시도
        loginWithKakao()
    }

    private fun loginWithKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                handleLoginResponse(token, error)
            }
        } else {
            // 카카오 계정 로그인
            loginWithKakaoAccount()
        }
    }

    private fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            handleLoginResponse(token, error)
        }
    }

    private fun handleLoginResponse(token: OAuthToken?, error: Throwable?) {
        if (error != null) {
            Log.d("testt", "로그인 실패: $error")
            // 사용자가 취소한 경우
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                return
            }
        } else if (token != null) {
            Log.d("testt", "로그인 성공: ${token.accessToken}")
            // 사용자 정보 처리
            handleKakaoLogin(token)
        }
    }

    private fun handleKakaoLogin(token: OAuthToken) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("testt", "사용자 정보 조회 실패: $error")
                return@me
            }
            user?.let {
                val nickname = it.kakaoAccount?.profile?.nickname
                val email = it.kakaoAccount?.email

                // 여기에 서버 API 호출 코드 추가
                registerUserWithServer(token.accessToken, nickname, email)
            }
        }
    }

    private fun registerUserWithServer(accessToken: String, nickname: String?, email: String?) {
        // 여기에 사용자 정보를 서버에 전송하는 코드를 추가
        Log.d("testt", "회원가입: 닉네임: $nickname, 이메일: $email, Access Token: $accessToken")
        // 예: Retrofit을 사용하여 서버에 POST 요청
        // 사용자가 Kakao에 대한 정보를 토대로 회원가입 진행
        goToMainActivity()
    }

    private fun goToMainActivity() {
//        val intent = Intent(this, MainActivity::class.java)
        val intent = Intent(this, SignUpInfoActivity::class.java)
        startActivity(intent)
        finish() // SignInActivity 종료
    }
}
