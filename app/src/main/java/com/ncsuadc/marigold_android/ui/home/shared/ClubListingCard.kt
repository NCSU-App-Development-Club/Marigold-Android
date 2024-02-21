package com.ncsuadc.marigold_android.ui.home.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncsuadc.marigold_android.R
import com.ncsuadc.marigold_android.domain.Club
import com.ncsuadc.marigold_android.domain.Event
import com.ncsuadc.marigold_android.ui.home.SmallEventCard

@Composable
fun ClubListingCard(club: Club) {
    Card() {
        Row() {
            Column() {
                Image(painter = painterResource(id = R.drawable.uab_logo),
                    contentDescription = "UAB logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    alignment = Alignment.Center)
            }

            Column() {
                Text(
                    text = "I'm in the column",)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Subcolumn text",)
            }

            Column() {
                @Composable
                fun FilledTonalButtonExample(onClick: () -> Unit) {
                    FilledTonalButton(onClick = { onClick() }) {
                        Text("Join")
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun OneCard() {
    ClubListingCard(Club())

}