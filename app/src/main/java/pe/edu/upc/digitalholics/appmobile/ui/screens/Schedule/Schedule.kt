package pe.edu.upc.digitalholics.appmobile.ui.srceens.Schedule

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Schedule( modifier: Modifier = Modifier, physiotherapist: Physiotherapist,
navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = modifier.width(8.dp))
                    Row(modifier = modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "Physiotherapist's Profile", fontWeight = FontWeight.Bold)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .fillMaxHeight()
                    .padding(bottom = 100.dp)

            ) {
               // item {
                    Schedule2(physiotherapist, navController)
                //}

            }


        },
        bottomBar = {
            Box(modifier = Modifier.background(Color.White)) {
                FooterStructure(navController)
            }


        }
    )

}

@Composable
fun Schedule2(physiotherapist: Physiotherapist, navController: NavController) {

    //val videos = 45
    //val appointments = 45

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Text("Physiotherapist's Profile", fontWeight = FontWeight.Bold)

        AsyncImage(
            model = physiotherapist.photoUrl,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(CenterHorizontally)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        /*
DARLE CIRCULITO GAAAAA
        Image(
            painter = painterResource(R.drawable.physiotherapist),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(230.dp)
                .align(CenterHorizontally)
                .clip(shape = CircleShape)
                .border(border = BorderStroke(7.dp, Color(0, 40, 122)), shape = CircleShape)

        )*/
        Text("${"Dr. " + physiotherapist.firstName+ " "+ physiotherapist.paternalSurname}", fontSize = 25.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.absolutePadding(top = 10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            if (physiotherapist.consultationsQuantity > 20) {
                Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.absolutePadding(right = 45.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_groups_24),
                        contentDescription = "Appointments",
                        tint = Color(1,72,255),
                        modifier = Modifier
                            .size(48.dp)
                            .absolutePadding(bottom = 5.dp)
                    )
                    Text("20+", color = Color.Gray, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp, left = 6.dp))

                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.absolutePadding(right = 45.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_groups_24),
                        contentDescription = "Appointments",
                        tint = Color(1,72,255),
                        modifier = Modifier
                            .size(48.dp)
                            .absolutePadding(bottom = 5.dp)
                    )
                    Text("${physiotherapist.consultationsQuantity}", color = Color.Gray, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp, left = 6.dp))

                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.absolutePadding(right = 45.dp)) {
                Icon(
                    painter = painterResource(R.drawable.baseline_attach_money_24),
                    contentDescription = "Cost",
                    tint = Color(0,120,0),
                    modifier = Modifier
                        .size(36.dp)
                        .absolutePadding(right = 4.dp)
                )
                Text("${physiotherapist.age*2}", color = Color.Gray, fontWeight = FontWeight.Bold,
                    fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp))
                }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.baseline_star_24),
                    contentDescription = "Stars",
                    tint = Color(250,200,0),
                    modifier = Modifier
                        .size(48.dp)
                        .absolutePadding(bottom = 5.dp)
                )
                Text("${physiotherapist.rating}", color = Color.Gray, fontWeight = FontWeight.Bold,
                    fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp))

            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()){
            item{
                DateForm(physiotherapist, navController)



            }
        }

        
       // Spacer(modifier = Modifier.height(1.dp))

    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateForm(physiotherapist: Physiotherapist,navController: NavController){



    var topic  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }
    //var date = ""
    var time by remember {
        mutableStateOf(
            TextFieldValue(
                text = "hh:mm"
            )
        )
    }
    var date by remember {
        mutableStateOf(
            TextFieldValue(
                text = "dd-MM-yyyy"
            )
        )
    }

    Row(modifier = Modifier) {
        OutlinedTextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("Topic") },
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(bottom = 10.dp)
        )
    }

    Row(modifier = Modifier) {
        DateTextField(value = date,
            onValueChange = {
                date = it
            }
        )
    }


    Row(modifier = Modifier) {
        HourTextField(value = time, onValueChange = { time = it })


    }


    Spacer(modifier = Modifier.height(15.dp))

    Button(onClick = {

        navController.navigate("Payment/${physiotherapist.id}/${topic.text}/${date.text}")
    },
        modifier = Modifier
            .absolutePadding(left = 130.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0,122,240)
        )
    ) {
        Text(text = "Let's Pay")

    }

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HourTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChange: (TextFieldValue) -> Unit
){
    var isBackspacePressed by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        visualTransformation = HourFormatVisualTransformation(LocalTextStyle.current),
        label = { Text(text = "Hour")},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        onValueChange = {
            // ex: "01-1M-yyyy" -> "011"
            val date = it.text.takeDigitString()

            if (date.length < 5) {

                // if date length is 3 or 5, move cursor to index + 1
                // ex: (| = cursor) "01-|1M-yyyy" to "01-1|M-yyyy"
                val selection = if (!isBackspacePressed) {
                    when (date.length) {
                        2 -> it.selection.start + 1
                        else -> it.selection.start
                    }
                } else it.selection.start

                onValueChange(
                    it.copy(
                        text = TextFieldTimeFormatter.format(it),
                        selection = TextRange(selection)
                    )
                )
            }
        },
        modifier = modifier
            .onKeyEvent { event ->
                if (event.key == Key.Backspace) {
                    isBackspacePressed = true
                    return@onKeyEvent true
                } else {
                    isBackspacePressed = false
                }

                false
            }
    )

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .absolutePadding(bottom = 10.dp),
    onValueChange: (TextFieldValue) -> Unit
) {

    var isBackspacePressed by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        visualTransformation = DateFormatVisualTransformation(LocalTextStyle.current),
        label = { Text(text = "Date")},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        onValueChange = {
            // ex: "01-1M-yyyy" -> "011"
            val date = it.text.takeDigitString()

            if (date.length < 9) {

                // if date length is 3 or 5, move cursor to index + 1
                // ex: (| = cursor) "01-|1M-yyyy" to "01-1|M-yyyy"
                val selection = if (!isBackspacePressed) {
                    when (date.length) {
                        3, 5 -> it.selection.start + 1
                        else -> it.selection.start
                    }
                } else it.selection.start

                onValueChange(
                    it.copy(
                        text = TextFieldDateFormatter.format(it),
                        selection = TextRange(selection)
                    )
                )
            }
        },
        modifier = modifier
            .onKeyEvent { event ->
                if (event.key == Key.Backspace) {
                    isBackspacePressed = true
                    return@onKeyEvent true
                } else {
                    isBackspacePressed = false
                }

                false
            }
    )
}

class DateFormatVisualTransformation(
    private val textStyle: TextStyle
): VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = buildAnnotatedString {
                for (word in text) {
                    withStyle(
                        textStyle.copy(
                            color = if (word.isDigit() || word == '-') textStyle.color
                            else Color.Gray
                        ).toSpanStyle()
                    ) {
                        append(word)
                    }
                }
            },
            offsetMapping = OffsetMapping.Identity
        )
    }
}
class HourFormatVisualTransformation(
    private val textStyle: TextStyle
): VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = buildAnnotatedString {
                for (word in text) {
                    withStyle(
                        textStyle.copy(
                            color = if (word.isDigit() || word == ':') textStyle.color
                            else Color.Gray
                        ).toSpanStyle()
                    ) {
                        append(word)
                    }
                }
            },
            offsetMapping = OffsetMapping.Identity
        )
    }
}


object TextFieldTimeFormatter {
    private const val ddMMyyyy = "hhmm"

    /**
     * format "ddMMyyyy" to "dd-MMM-yyyy"
     * @return formatted string, ex: "11-01-2007" or "11-0M-YYYY"
     */
    fun format(
        fieldValue: TextFieldValue,
    ): String {
        val builder = StringBuilder()

        val s = fieldValue.text.replace(
            oldValue = listOf(" ", ".", ",", ":", "h", "m"),
            newValue = ""
        )

        if (s.length != 4) {
            for (i in 0 until 4) {
                builder.append(
                    try {
                        s[i]
                    } catch (e: Exception) {
                        ddMMyyyy[i]
                    }
                )
            }
        } else builder.append(s)



        return builder.toString()
            .addStringBefore(":", 2)  // dd-MMyyyy
    }
}

object TextFieldDateFormatter {

    private const val ddMMyyyy = "ddMMyyyy"

    /**
     * format "ddMMyyyy" to "dd-MMM-yyyy"
     * @return formatted string, ex: "11-01-2007" or "11-0M-YYYY"
     */
    fun format(
        fieldValue: TextFieldValue,
        minYear: Int = 2023,
        maxYear: Int = 2025
    ): String {
        val builder = StringBuilder()

        val s = fieldValue.text.replace(
            oldValue = listOf(" ", ".", ",", "-", "d", "M", "y"),
            newValue = ""
        )

        if (s.length != 8) {
            for (i in 0 until 8) {
                builder.append(
                    try {
                        s[i]
                    } catch (e: Exception) {
                        ddMMyyyy[i]
                    }
                )
            }
        } else builder.append(s)

        repeat(3) {
            when (it) {
                0 -> {
                    val day = try {
                        "${builder[0]}${builder[1]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (day != null) {
                        val dayMax = day
                            .coerceIn(
                                minimumValue = 1,
                                maximumValue = 31,
                            )
                            .toString()

                        builder.replace(
                            0,
                            2,
                            if (dayMax.length == 1) "0$dayMax" else dayMax
                        )
                    }
                }
                1 -> {
                    val month = try {
                        "${builder[2]}${builder[3]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (month != null) {
                        val monthMax = month
                            .coerceIn(
                                minimumValue = 1,
                                maximumValue = 12,
                            )
                            .toString()

                        builder.replace(
                            2,
                            4,
                            if (monthMax.length == 1) "0$monthMax" else monthMax
                        )
                    }
                }
                2 -> {
                    val year = try {
                        "${builder[4]}${builder[5]}${builder[6]}${builder[7]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (year != null) {
                        val yearMaxMin = year.coerceIn(
                            minimumValue = minYear,
                            maximumValue = maxYear
                        ).toString()

                        builder.replace(4, 6, yearMaxMin.substring(0, 2))
                        builder.replace(6, 8, yearMaxMin.substring(2, 4))
                    }
                }
            }
        }

        return builder.toString()
            .addStringBefore("-", 2)  // dd-MMyyyy
            .addStringBefore("-", 5)	// dd-MM-yyyy
    }

    /**
     * format time in milli second to formatted date
     * @return formatted string, ex: "11-01-2007"
     */
    fun format(timeInMillis: Long): String {
        return SimpleDateFormat("dd-MM-yyyy").format(timeInMillis)
    }

    /**
     * check if formattedDate is valid
     *
     * "01-01-2000" -> valid
     * "01-0M-YYYY" -> not valid
     * @param formattedDate "01-01-2000"
     */
    fun isValid(formattedDate: String): Boolean {
        return formattedDate.takeDigitString().length == 8
    }

    /**
     * convert formatted string date to time in millis
     * @param formattedDate "01-01-2000"
     * @return time in millis
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun parse(formattedDate: String): Long {
        val date = "${formattedDate[6]}${formattedDate[7]}${formattedDate[8]}${formattedDate[9]}-"
            .plus("${formattedDate[3]}${formattedDate[4]}-")
            .plus("${formattedDate[0]}${formattedDate[1]}")
            .plus("T00:00")

        return LocalDateTime.parse(date)
            .atZone(ZoneId.of(TimeZone.getDefault().id, ZoneId.SHORT_IDS))
            .toInstant()
            .toEpochMilli()
    }

}

/**
 * take a string that is a number
 *
 * ex:
 * val s = "123a456b"
 * s.takeDigitString() => "123456"
 * @author kafri8889
 */
fun String.takeDigitString(): String {
    var builder = ""

    forEach { if (it.isDigit()) builder += it }

    return builder
}

/**
 * add a new string before the given index
 * @author kafri8889
 */
fun String.addStringBefore(s: String, index: Int): String {
    val result = StringBuilder()
    forEachIndexed { i, c ->
        if (i == index) {
            result.append(s)
            result.append(c)
        } else result.append(c)
    }

    return result.toString()
}

/**
 * Returns a new string with all occurrences of oldValue replaced with newValue.
 * @author kafri8889
 */
fun String.replace(
    oldValue: List<String>,
    newValue: String,
    ignoreCase: Boolean = false
): String {
    var result = this

    oldValue.forEach { s ->
        if (this.contains(s)) {
            result = result.replace(s, newValue, ignoreCase)
        }
    }

    return result
}


