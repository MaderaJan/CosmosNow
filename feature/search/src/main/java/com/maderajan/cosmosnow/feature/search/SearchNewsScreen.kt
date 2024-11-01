package com.maderajan.cosmosnow.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.maderajan.cosmosnow.core.navigation.CosmosScreens

@Composable
fun SearchNewsScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        val navController = rememberNavController()

        Text(
            text = "SEARCH SCREEN",
            fontSize = 36.sp
        )

        Button(
            onClick = {
                navController.navigate(CosmosScreens.CosmosNews)
            }
        ) {
            Text(text = "TO NEWS")
        }
    }
}