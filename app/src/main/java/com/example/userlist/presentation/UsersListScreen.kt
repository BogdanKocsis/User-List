package com.example.userlist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.userlist.common.Resource
import com.example.userlist.common.composables.LoadingIndicator
import com.example.userlist.common.composables.ScreenFailure
import com.example.userlist.presentation.components.UsersList
import com.example.userlist.ui.theme.TopBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreen(
    viewModel: UsersListViewModel = hiltViewModel()
) {
    val stateFlow = viewModel.stateFlow.collectAsState()

    stateFlow.value.let { resource ->
        when (resource) {
            is Resource.Failure -> {
                ScreenFailure(errorMessage = resource.message)
            }

            Resource.Loading -> {
                LoadingIndicator()
            }

            is Resource.Success -> {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Users",
                                style = TextStyle(Color.White, fontSize = 20.sp)
                            )
                        },
                        navigationIcon = {
                            IconButton({}) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    tint = Color.White,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    tint = Color.White,
                                    contentDescription = "Search"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = TopBarColor
                        )
                    )
                }, floatingActionButton = {
                    FloatingActionButton(
                        shape = CircleShape,
                        containerColor = TopBarColor,
                        contentColor = Color.White,
                        onClick = { },
                    ) {
                        Icon(Icons.Filled.Create, "Floating action button.")
                    }
                }) { padding ->
                    Column(modifier = Modifier.padding(padding)) {
                        UsersList(usersList = resource.data) { viewModel.loadNextPage() }
                    }
                }
            }
        }
    }
}
