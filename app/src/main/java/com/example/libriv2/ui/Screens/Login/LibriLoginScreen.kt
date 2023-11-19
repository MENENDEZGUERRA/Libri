package com.example.libriv2.ui.Screens.Login

import android.provider.Settings.Global.getString
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libriv2.R
import com.example.libriv2.ui.Navigation.TabScreens


@Composable
fun LibriLoginScreen(navController: NavController, viewModel: LoginScreenViewModel = viewModel()) {
    val showLoginForm = rememberSaveable { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (showLoginForm.value) {
                Text(
                    text = R.string.log_in.toString(),
                    fontSize = 24.sp,
                    color = Color.White
                )
                UserForm(
                    isCreateAccount = false,
                    navController = navController,
                    viewModel = viewModel
                )
            } else {
                Text(
                    text = R.string.register.toString(),
                    fontSize = 24.sp,
                    color = Color.White
                )
                UserForm(
                    isCreateAccount = true,
                    navController = navController,
                    viewModel = viewModel
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            SwitchFormText(showLoginForm = showLoginForm)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean,
    navController: NavController,
    viewModel: LoginScreenViewModel
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    EmailInput(emailState = email)
    PasswordInput(passwordState = password, labelId = R.string.password.toString(), passwordVisible = passwordVisible)

    SubmitButton(
        textId = if (isCreateAccount) R.string.register.toString() else R.string.log_in.toString(),
        validInput = valid,
        onClick = {
            if (isCreateAccount) {
                viewModel.createUserWithEmailAndPassword(email.value.trim(), password.value.trim()) {
                    // Handle registration result
                    navController.navigate(TabScreens.MainScreen.route)
                }
            } else {
                viewModel.signInWithEmailAndPassword(email.value.trim(), password.value.trim()) {
                    // Handle login result
                    navController.navigate(TabScreens.MainScreen.route)
                }
            }
            keyboardController?.hide()
        }
    )
}

@Composable
fun SwitchFormText(showLoginForm: MutableState<Boolean>) {
    val text1 = if (showLoginForm.value) R.string.not_have_account.toString() else R.string.have_account.toString()
    val text2 = if (showLoginForm.value) R.string.register.toString() else R.string.log_in.toString()

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text1, color = Color(0xFFFFA500))
        Text(
            text = text2,
            modifier = Modifier
                .clickable { showLoginForm.value = !showLoginForm.value }
                .padding(start = 5.dp),
            color = Color.White
        )
    }
}

@Composable
fun SubmitButton(textId: String, validInput: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = validInput
    ) {
        Text(
            text = textId,
            color = Color.White,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(passwordState: MutableState<String>,
                  labelId: String,
                  passwordVisible: MutableState<Boolean>)
{
    val visualTransformation = if (passwordVisible.value){
        VisualTransformation.None
    }else {
        PasswordVisualTransformation()
    }
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId, color = if (passwordState.value.isNotEmpty()) Color(0xFFFFA500) else Color.White)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray
        ),
        trailingIcon = {
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }
            else {
                null
            }
        }

    )
}

@Composable
fun PasswordVisibleIcon(passwordVisible: MutableState<Boolean>) {
    val image =
        if(passwordVisible.value){
            Icons.Default.VisibilityOff
        }else {
            Icons.Default.Visibility
        }
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = "Visibility Toogle",
            tint = Color.White)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value ,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId, color = if (valueState.value.isNotEmpty()) Color(0xFFFFA500) else Color.White)},
        singleLine = isSingleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray
        )
    )
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId : String = R.string.e_mail.toString()
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}
