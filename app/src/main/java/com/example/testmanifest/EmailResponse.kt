package com.example.testmanifest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testmanifest.ui.theme.TestManifestTheme
import java.io.File
import java.io.FileWriter
import android.content.Context
import java.io.BufferedWriter
import java.io.OutputStreamWriter

class EmailResponse : ComponentActivity() {
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestManifestTheme {
                val sender = intent.getStringExtra("sender")
                val subject = intent.getStringExtra("subject")
                val (textValue, setTextValue) = remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Sujet du mail
                    Text(
                        text = "Re - " + subject,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Expéditeur du mail
                    Text(
                        text = "Destinataire : " + sender,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Bouton Annuler
                        androidx.compose.material3.Button(
                            onClick = {
                                val intent = Intent(
                                    this@EmailResponse,
                                    MainActivity::class.java
                                ).apply{}
                                this@EmailResponse.startActivity(intent)
                            }
                        ) {
                            Text(text = "Annuler")
                        }

                        // Bouton Envoyer
                        androidx.compose.material3.Button(
                            onClick = {
                                val intent = Intent(
                                    this@EmailResponse,
                                    MainActivity::class.java
                                ).apply{}
                                this@EmailResponse.startActivity(intent)
                            }
                        ) {
                            val context = this@EmailResponse
                            //saveDraft("moi pour "+sender, ""+subject, textValue, context)
                            Text(text = "Envoyer")
                        }
                    }
                    Text(
                        text = "Écrivez votre réponse :",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextEditor(textValue, setTextValue)
                    println(textValue)
                }
            }
        }
    }
}

@Composable
fun TextEditor(text: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = Modifier.fillMaxWidth()
    )
}

/*
fun saveDraft(sender: String, subject: String, texte: String, context: Context) {
    try {
        // Ouvrir le fichier depuis les ressources
        val outputStream = context.assets.open("mails.txt").bufferedWriter()

        // Écrire le nouvel e-mail dans le fichier
        val newEmail = "$sender|$subject|ENVOYE|$texte\n" // Ajouter un retour à la ligne à la fin
        println(newEmail)
        outputStream.write(newEmail)

        // Assurez-vous de fermer le flux après avoir terminé
        outputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
*/
