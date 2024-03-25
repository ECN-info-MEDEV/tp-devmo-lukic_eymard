package com.example.testmanifest

import Email
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmanifest.ui.theme.TestManifestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainActivity() }
                composable("write") { WriteMailContent(navController) }
                composable("read") { EmailDetailedContent(navController) }
            }
            TestManifestTheme {
                var emails = readMails(assets)
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    EmailList(emails) { email ->
                        // Naviguer vers EmailDetailedList lorsque l'utilisateur clique sur un mail
                        startActivity(Intent(this@MainActivity, EmailDetailedList::class.java).apply {
                            // Passer les données du mail à EmailDetailedList
                            putExtra("email", email)
                        })
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EmailList(emails: List<Email>, onItemClick: (Email) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sujet du mail
        Text(
            text = "Boite de réception",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column {
            // Entête de la liste des emails
            Row(modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Expéditeur", modifier = Modifier.weight(1f), style = TextStyle(color = Color.Black))
                Text(text = "Sujet", modifier = Modifier.weight(1f), style = TextStyle(color = Color.Black))
                Text(text = "Conteneur", modifier = Modifier.weight(1f), style = TextStyle(color = Color.Black))
            }

            // Liste des emails
            emails.forEach { email ->
                EmailListItem(email = email, onItemClick = onItemClick)
            }
        }
    }
}


@Composable
fun EmailListItem(email: Email, onItemClick: (Email) -> Unit) {
    // Afficher les détails de l'email et déclencher onItemClick lorsqu'il est cliqué
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(email) }
    ) {
        Text(text = email.sender, modifier = Modifier.weight(1f))
        Text(text = email.subject, modifier = Modifier.weight(1f))
        Text(text = email.container, modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestManifestTheme {
        var emails = mutableListOf(Email("1","2","3","diedfhdojfc"))
        EmailList(emails) {}
    }
}

fun readMails(assetManager: AssetManager): List<Email> {
    val mails = mutableListOf<Email>()
    assetManager.open("mails.txt").bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(",")
            if (parts.size == 4) {
                val sender = parts[0].trim()
                val subject = parts[1].trim()
                val container = parts[2].trim()
                val texte = parts[3].trim()
                mails.add(Email(sender, subject, container, texte)) // Ajouter le texte aussi
            }
        }
    }
    return mails
}

@Composable
fun InteractiveTextField() {
    // State to hold the text value entered by the user
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(PaddingValues(top = 8.dp, bottom = 250.dp))
            .border(1.dp, Color.Black),
        content = {
            BasicTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                modifier = Modifier.padding(top = 8.dp),
                cursorBrush = SolidColor(Color.Black),
                decorationBox = { innerTextField ->
                    // Customizing the text field appearance
                    innerTextField()
                }
            )
        })
}
