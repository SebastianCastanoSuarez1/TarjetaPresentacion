package com.example.tarjetapresentacion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.tarjetapresentacion.ui.theme.TarjetaPresentacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TarjetaPresentacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Pantalla()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TarjetaPresentacionTheme {
        Pantalla()
    }
}

@Composable
fun Pantalla() {
    BoxWithConstraints {
        if (maxWidth < 500.dp) {
            TarjetaVertical(
                "\n\nSebastián \nCastaño Suárez",
                "IES Virgen de la paloma",
                "Estudiante \n 2ºDAM",
                "Trabajo en git",
                "6666666666"
            )
        } else {
            TarjetaHorizontal(
                "Sebastián \nCastaño Suárez",
                "IES Virgen de la paloma",
                "Estudiante \n\n 2ºDAM",
                "Trabajo en git",
                "+346666666666"
            )
        }
    }

}


@Composable
fun TarjetaVertical(
    name: String,
    centro: String,
    estudiante: String,
    git: String,
    numero: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(103, 103, 103))
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .weight(2f)
                .fillMaxSize()
                .background(Color(90, 161, 168))
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                FotoPerfil()
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(8.dp),
            ) {

                Text(
                    text = name,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp


                )
                BotonCentroVertical(centro)
                Text(
                    text = estudiante, modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Start,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .weight(1.5f)
                .background(Color(90, 161, 168))
                .padding(8.dp)
        ) {
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                IconoGit()
                BotonGit(git)
            }
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                IconoCorreo()
                BotonCorreo("sebastian.castano2@educa.madrid.org")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                IconoNumero()
                BotonNumero(numero)
            }
        }
    }
}

@Composable
fun TarjetaHorizontal(
    name: String,
    centro: String,
    estudiante: String,
    git: String,
    numero: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(103, 103, 103))
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .weight(2f)
                .fillMaxSize()
                .background(Color(90, 161, 168))
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .weight(2f)
                        .fillMaxSize()
                ) {
                    FotoPerfil()
                }
                Row(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    IconoGit()
                    BotonGit(git)
                }
                Row(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    IconoCorreo()
                    BotonCorreo("sebastian.castano2@educa.madrid.org")
                }
                Row(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    IconoNumero()
                    BotonNumero(numero)
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color(90, 161, 168))
                    .padding(8.dp),
            ) {

                Text(
                    text = name,
                    modifier = Modifier.fillMaxSize().weight(1f),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 30.sp


                )
                BotonCentroHorizontal(centro)
                Text(
                    text = estudiante, modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start, fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun BotonNumero(numero: String) {
    val localContext = LocalContext.current
    TextButton(onClick = {
        val textPhone = "tel: $numero"
        val intento = Intent(Intent.ACTION_DIAL)
        intento.data = Uri.parse(textPhone)
        startActivity(localContext, intento, null)
    }) {
        Text(text = "\n $numero", modifier = Modifier.fillMaxSize(), fontSize = 20.sp)
    }
}

@Composable
fun BotonCentroVertical(centro: String, modifier: Modifier=Modifier, ) {
    val localContext = LocalContext.current
    val url = "https://www.palomafp.org/"
    TextButton(onClick = {
        val intento = Intent()
        intento.data = Uri.parse(url)
        intento.action = Intent.ACTION_VIEW
        startActivity(localContext, intento, null)
    }) {
        Text(
            text = "\n$centro",
            modifier = Modifier
                .weight(1f),
            fontSize = 23.sp,
            textAlign = TextAlign.Start

        )
    }
}
@Composable
fun BotonCentroHorizontal(centro: String,modifier: Modifier=Modifier, ) {
    val localContext = LocalContext.current
    val url = "https://www.palomafp.org/"
    TextButton(onClick = {
        val intento = Intent()
        intento.data = Uri.parse(url)
        intento.action = Intent.ACTION_VIEW
        startActivity(localContext, intento, null)
    }) {
        Text(
            text = "\n$centro",
            modifier = Modifier
                .weight(1f),
            fontSize = 30.sp,
            textAlign = TextAlign.Start

        )
    }
}



@Composable
fun BotonGit(git: String) {
    val localContext = LocalContext.current
    val url = "https://github.com/SebastianCastanoSuarez1/TarjetaPresentacion"
    TextButton(onClick = {
        val intento = Intent()
        intento.data = Uri.parse(url)
        intento.action = Intent.ACTION_VIEW
        startActivity(localContext, intento, null)
    }) {
        Text(
            text = "\n $git",
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun BotonCorreo(mail:String) {
    val email: String = "email.@gmail.com"
    val localContext = LocalContext.current
    TextButton(onClick = {
        val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(email))
        intentMail.type = "plain/text"
        intentMail.putExtra(Intent.EXTRA_SUBJECT, "titulo del mail")
        intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
        startActivity(localContext, intentMail, null)
    }) {
        Text(
            text = "\n $email",
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun FotoPerfil() {
    val image = painterResource(R.drawable.foto_perfil)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(350.dp)
            .clip(CircleShape)
            .fillMaxSize()
    )
}

@Composable
fun IconoCorreo(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.correodibujo)
    Image(painter = image, contentDescription = null, modifier.height(200.dp))
}

@Composable
fun IconoGit(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.icono_git)
    Image(painter = image, contentDescription = null, modifier.height(200.dp))
}

@Composable
fun IconoNumero(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.numero2)
    Image(
        painter = image, contentDescription = null, modifier.height(190.dp)
    )
}



