package com.example.logincompose.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logincompose.presentation.components.EventDialog
import com.example.logincompose.presentation.components.RoundedButton
import com.example.logincompose.presentation.components.SocialMediaButton
import com.example.logincompose.presentation.components.TranspatentTextField
import dev.leonardom.loginjetpackcompose.ui.theme.FACEBOOKCOLOR
import dev.leonardom.loginjetpackcompose.ui.theme.GMAILCOLOR


@Composable
fun RegistrationScreen(
    state: RegisterState,
    onRegister: (String, String, String, String, String) -> Unit,
    onBack: () -> Unit,
    onDismissDialog: () -> Unit

) {

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier.fillMaxWidth()

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        onBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(text = "Create an account",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                TranspatentTextField(
                    textFieldValue = nameValue ,
                    textLabel = "Name",
                    keyboarType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TranspatentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Email",
                    keyboarType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )

                TranspatentTextField(
                    textFieldValue = phoneValue,
                    textLabel = "Phone Number",
                    maxChar = 10,
                    keyboarType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )

                TranspatentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Password",
                    keyboarType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )

                TranspatentTextField(
                    textFieldValue = confirmPasswordValue,
                    textLabel = "Confirm Password",
                    keyboarType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()

                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value,
                                passwordValue.value,
                                confirmPasswordValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (confirmPasswordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoundedButton(
                    text = "Sign Up",
                    displayProgressBar = state.displayProgressBar,

                    onClick = {
                        onRegister(
                            nameValue.value,
                            emailValue.value,
                            phoneValue.value,
                            passwordValue.value,
                            confirmPasswordValue.value
                        )
                    }
                )

                ClickableText(
                    text = buildAnnotatedString {
                          append("Already have an account? ")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Log in")
                        }
                    },
                    onClick = {
                        onBack()
                    }
                )

            }
            
            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray

                    )

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "OR",
                        style = MaterialTheme.typography.body1
                    )

                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray

                    )

                }
                
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login with",
                    style = MaterialTheme.typography.body1.copy(
                        MaterialTheme.colors.primary
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                SocialMediaButton(
                    text = "Login with Facebook",
                    onClick = { /*TODO*/ },
                    socialMediaColor = FACEBOOKCOLOR
                )
                
                SocialMediaButton(
                    text = "Login with Gmail",
                    onClick = { /*TODO*/ },
                    socialMediaColor = GMAILCOLOR)
            }


        }

        //Mostrar o esconder el dialog de error
        if (state.errorMessage != null){
            EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
        }

    }

    
}