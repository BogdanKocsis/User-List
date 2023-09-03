package com.example.userlist.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.userlist.model.User
import com.example.userlist.ui.theme.PostColor

@Composable
fun UsersList(
    usersList: List<User>,
    LoadNextPage: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val maxVisibleIndex = usersList.size - 3
    var hasLoadedNextPage by remember { mutableStateOf(false) }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        itemsIndexed(usersList) { index, user ->
            UserListItem(
                user = user,
                position = index
            )
            Divider(thickness = 1.dp, color = PostColor)

            if (index == maxVisibleIndex && !hasLoadedNextPage) {
                hasLoadedNextPage = true
                LoadNextPage()
                LaunchedEffect(Unit) {
                    lazyListState.scrollToItem(index = 0)
                }
                hasLoadedNextPage = false
            }
        }
    }
}
