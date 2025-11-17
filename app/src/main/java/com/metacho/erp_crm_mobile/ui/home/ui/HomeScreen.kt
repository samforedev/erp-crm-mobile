package com.metacho.erp_crm_mobile.ui.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    Text("Estas en Home")
}