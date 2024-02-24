package com.ncsuadc.marigold_android.ui.home.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncsuadc.marigold_android.R
import com.ncsuadc.marigold_android.domain.Club
import com.ncsuadc.marigold_android.domain.Event
import com.ncsuadc.marigold_android.ui.home.SmallEventCard

@Composable
fun ClubListingCard(club: Club) {
    Card {
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

            Spacer(modifier = Modifier.width(6.dp))
            
            Column() {
                Row() {
                    Text(
                        text = "University Activities Board",
                        )
                    Spacer( modifier = Modifier.width(4.dp))
                    Image(painter = painterResource(id = R.drawable.red_verified_badge),
                        contentDescription = "Red verified checkmark",
                        modifier = Modifier
                            .size(12.dp)
                            .align(Alignment.CenterVertically),
                        alignment = Alignment.Center,

                    )

                }
                Row() {
                    Text(
                        text = "The University Activities Board puts on programs with endless and ever-changing topics.",
                        fontSize = 10.sp,
//                        modifier = Modifier.fillMaxWidth(.9f)
                    )
                }

            }

            Column(verticalArrangement = Arrangement.Center) {
                FilledTonalButtonExample()
            }
        }

    }

}

@Composable
fun FilledTonalButtonExample() {
    FilledTonalButton(
        onClick = { null },
        modifier = Modifier
            .height(20.dp)
            .width(100.dp),
        shape = ButtonDefaults.filledTonalShape,
        colors = ButtonDefaults.filledTonalButtonColors()
    ) {
        Text(text = "Join",
            fontSize = 12.sp,
            color = Color.Black)
    }
}

@Preview
@Composable
fun OneCard() {
    ClubListingCard(Club())

}