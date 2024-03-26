package com.example.testmanifest

import Email
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmanifest.ui.theme.TestManifestTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


class EmailDetailedList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestManifestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val receivedIntent = intent
                    val emailSubject = receivedIntent.getStringExtra("subject")
                    val emailSender = receivedIntent.getStringExtra("sender")
                    val emailContent = receivedIntent.getStringExtra("texte")


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Sujet du mail
                        Text(
                            text = "" + emailSubject,
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Expéditeur du mail
                        Text(
                            text = "Expéditeur: $emailSender",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Contenu du mail
                        Text(
                            text = "" + emailContent,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Bouton Répondre
                            Button(
                                onClick = {
                                    val intent = Intent(this@EmailDetailedList, EmailResponse::class.java).apply {
                                        // Passer les données du mail à EmailDetailedList
                                        putExtra("sender", emailSender)
                                        putExtra("subject", emailSubject)
                                    }
                                    this@EmailDetailedList.startActivity(intent)
                                }
                            ) {
                                Text(text = "Répondre")
                            }

                            // Bouton IA
                            Button(onClick = { /* Handle IA */ }) {
                                Text(text = "IA")
                            }
                        }

                        // Case de texte sous les boutons
                        Text(
                            text = "Case de texte pour réponse ou interaction avec l'IA",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        InteractiveTextField()
                    }
                }
            }
        }
    }
}






