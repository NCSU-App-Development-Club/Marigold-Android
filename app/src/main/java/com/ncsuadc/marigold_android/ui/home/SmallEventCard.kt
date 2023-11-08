package com.ncsuadc.marigold_android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncsuadc.marigold_android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun SmallEventCard() {
    ElevatedCard(onClick = { /*TODO*/ }) {
        Column {
            Text(text = "Pickup Soccer", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp))
            Row {
                Text(text = "Club Barcelona Team")
                Image(painter = painterResource(id = R.drawable.dog),
                    contentDescription = "Picture of a dog to test",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape))
                //stringResource(id = R.string.dog_content_description))
            }
            Text(text = "Thursday - 9:30pm")
        }
    }
}