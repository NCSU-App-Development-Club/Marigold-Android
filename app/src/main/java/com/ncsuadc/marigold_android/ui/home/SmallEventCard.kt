package com.ncsuadc.marigold_android.ui.home

import android.icu.text.DateFormat
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncsuadc.marigold_android.R
import com.ncsuadc.marigold_android.domain.Event
import com.ncsuadc.marigold_android.ui.theme.Blue80


@Composable
fun SmallEventCard(event: Event) {
    ElevatedCard(modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = Blue80
        )) {
        Column (modifier = Modifier
            .padding(8.dp)){
            Text(text = event.title, color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 8.dp))
            Spacer(modifier = Modifier.padding(15.dp))
            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(240.dp)
                    .padding(bottom = 10.dp),
                ){
                Text(text = "Club Barcelona Team", color = Color.White, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(end = 20.dp))
                Image(painter = painterResource(id = R.drawable.dog),
                    contentDescription = "Picture of a dog to test",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center)
            }
            val dateString = DateFormat.getPatternInstance(DateFormat.WEEKDAY).format(event.date)
            val timeString = DateFormat.getPatternInstance(DateFormat.HOUR_MINUTE).format(event.date)
            Text(text = "$dateString - $timeString", color = Color.White, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun OneSmallEvent() {
    SmallEventCard(Event(title = "Pickup Soccer") )
}