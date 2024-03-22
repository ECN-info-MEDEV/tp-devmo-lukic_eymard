package com.example.testmanifest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmanifest.ui.theme.TestManifestTheme

class EmailDetailedList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestManifestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    EmailDetailedContent()
                }
            }
        }
    }
}

@Composable
fun EmailDetailedContent() {
    // Dummy data, you can replace it with actual email data
    val emailSubject = "Sujet du mail"
    val emailSender = "Expéditeur du mail"
    val emailContent = "Contenu du mail"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sujet du mail
        Text(
            text = emailSubject,
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
            text = emailContent,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Bouton Répondre
            Button(onClick = { /* Handle reply */ }) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun EmailDetailedPreview() {
    TestManifestTheme {
        EmailDetailedContent()
    }
}
