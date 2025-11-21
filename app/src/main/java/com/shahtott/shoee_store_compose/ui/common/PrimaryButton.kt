package com.shahtott.shoee_store_compose.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahtott.shoee_store_compose.ui.theme.BabyBlue

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(56.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors().copy(containerColor = BabyBlue),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
