package com.example.userlist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.userlist.R
import com.example.userlist.model.User
import com.example.userlist.ui.theme.ImageLogoColor
import com.example.userlist.ui.theme.TextColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserListItem(
    user: User,
    position: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = user.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            Modifier
                .weight(1f)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.bodyMedium.copy(TextColor),
            )
            Text(
                text = user.age.toString() + " years from " + user.country,
                style = MaterialTheme.typography.bodyMedium.copy(TextColor),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Row(modifier = Modifier.padding(end=8.dp)) {
                if (position % 3 == 0) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_attachment_24),
                        contentDescription = null,
                        tint = ImageLogoColor,
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }
                Text (
                        text = user.hour,
                style = MaterialTheme.typography.bodySmall.copy(TextColor),
                modifier = Modifier.padding(top = 4.dp)
                )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_star_outline_24),
                contentDescription = null,
                tint = ImageLogoColor,
                modifier = Modifier.padding(8.dp).align(Alignment.End)
            )
        }
    }
}