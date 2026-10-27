package vn.edu.vlsc.thicuoiky_2500116096

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvProfileTitle: TextView
    private lateinit var tvProfileEmail: TextView
    private lateinit var btnLogout: Button

    companion object {
        const val EXTRA_EMAIL = "vn.edu.vlsc.thicuoiky.EXTRA_EMAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profileMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bắt buộc dùng findViewById để ánh xạ View trong ProfileActivity
        tvProfileTitle = findViewById(R.id.tvProfileTitle)
        tvProfileEmail = findViewById(R.id.tvProfileEmail)
        btnLogout = findViewById(R.id.btnLogout)

        // Nhận dữ liệu Email truyền từ LoginActivity qua Intent (Bundle)
        val email = intent.getStringExtra(EXTRA_EMAIL).orEmpty()
        tvProfileEmail.text = getString(R.string.label_profile_email, email)

        // Xử lý nút Đăng xuất: đóng Activity hiện tại bằng hàm finish() để quay về LoginActivity
        btnLogout.setOnClickListener {
            finish()
        }
    }
}
