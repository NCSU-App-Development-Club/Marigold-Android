package com.ncsuadc.marigold_android.ui.home.shared

import android.icu.text.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncsuadc.marigold_android.R
import com.ncsuadc.marigold_android.domain.Club
import com.ncsuadc.marigold_android.domain.Post
import com.ncsuadc.marigold_android.domain.User

// Should this be in the theme or typography file?
val SIGN_UP_TITLE_STYLE = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 35.sp)

@Composable
fun TitleText(modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        // If we plan to use these colors more often we should make them a constant
                        // somewhere/add it to the theme. I'm not sure how to do this, however.
                        colors = listOf(Color(0xffffe501), Color(0xffffb320))
                    )
                )
            ) {
                append("Marigold ")
            }
            append("@\n")
            withStyle(SpanStyle(color = Color(0xffff3737))) {
                append("NC State University")
            }
        },
        style = SIGN_UP_TITLE_STYLE,
        modifier = modifier
    )
}

// I took some liberties with the gradient button, but I think it looks better this way.
// Feel free to change it if you disagree.
@Composable
fun GradientButton(
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Row(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

@Composable
fun InvalidBanner(text: String?, modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .padding(top = 8.dp, bottom = 8.dp)
        .then(modifier), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Error, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text ?: "", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun PostListItem(post: Post, club: Club) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.android_club_pfp),
                contentDescription = "Club profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start=8.dp)) {
                Text(club.fullName, style=MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Text("${post.postedBy?.firstName} ${post.postedBy?.lastName}", style=MaterialTheme.typography.bodySmall)
            }
        }
        Row() {

            Text(post.body, maxLines = 6, overflow= TextOverflow.Ellipsis)

        }
        
        val myString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(post.postedAt)
        val timeString = DateFormat.getPatternInstance(DateFormat.HOUR_MINUTE).format(post.postedAt)
                                                             
        Row {
            Text(
                "${myString} ● ${timeString}",
                style=MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)   ,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text("Ask a question", modifier = Modifier.clickable {  }, style=MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
        }
    }

    
}

@Preview(showBackground =  true)
@Composable
fun PostPreview() {
    Column {
        PostListItem(
            Post(
                body = "Message text" +
                        "Message textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage textMessage text",
                postedBy = User(firstName = "Xavier", lastName = "Jones")
            ),
            Club(fullName = "App Development Club")

        )
        PostListItem(post =
        Post("Lorem ipsum dolor sit amet consectetur. Etiam ipsum tempus sed accumsan dui nunc venenatis ultricies. Diam sed tellus eget vel aliquam facilisi. Sed porta dictumst nunc urna elementum quisque. Nunc nibh sodales arcu pulvinar nulla eu sit sed sapien. Lorem ipsum dolor sit amet consectetur. Etiam ipsum tempus sed accumsan dui nunc venenatis ultricies. Diam sed tellus eget vel aliquam facilisi. .Malesuada lectus felis quis suspendisse vulputate... View More",
            postedBy = User(firstName = "Peter", lastName = "Pressler")), club = Club(fullName = "Sewing Club"))
    }
    
}
