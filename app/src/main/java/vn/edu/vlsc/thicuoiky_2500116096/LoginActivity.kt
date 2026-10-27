package vn.edu.vlsc.thicuoiky_2500116096

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bắt buộc: Ánh xạ View bằng findViewById truyền thống (không dùng ViewBinding)
        tvTitle = findViewById(R.id.tvTitle)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvStatus = findViewById(R.id.tvStatus)

        // Xử lý sự kiện nút Đăng nhập
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        // Xóa thông báo lỗi trước đó
        edtEmail.error = null
        edtPassword.error = null

        when {
            email.isEmpty() || password.isEmpty() -> {
                tvStatus.setTextColor(getColor(android.R.color.holo_red_dark))
                tvStatus.text = getString(R.string.err_empty_fields)
                if (email.isEmpty()) edtEmail.error = getString(R.string.err_empty_fields)
                if (password.isEmpty()) edtPassword.error = getString(R.string.err_empty_fields)
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                tvStatus.setTextColor(getColor(android.R.color.holo_red_dark))
                tvStatus.text = getString(R.string.err_invalid_email)
                edtEmail.error = getString(R.string.err_invalid_email)
            }
            password.length < 6 -> {
                tvStatus.setTextColor(getColor(android.R.color.holo_red_dark))
                tvStatus.text = getString(R.string.err_short_password)
                edtPassword.error = getString(R.string.err_short_password)
            }
            else -> {
                tvStatus.setTextColor(getColor(android.R.color.holo_green_dark))
                tvStatus.text = getString(R.string.login_success)
            }
        }
    }
}
