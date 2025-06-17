package com.diajarkoding.florafauna.ui.screen
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.diajarkoding.florafauna.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.profile_view, null)

            val imageView = view.findViewById<ImageView>(R.id.profileImage)
            imageView.setImageResource(R.drawable.is_dicoding)

            val nameTextView = view.findViewById<TextView>(R.id.nameText)
            nameTextView.text = "Iskandar Muhaemin"

            val emailTextView = view.findViewById<TextView>(R.id.emailText)
            emailTextView.text = "diajarkoding@gmail.com"

            view
        },
        modifier = modifier.fillMaxSize()
    )
}

