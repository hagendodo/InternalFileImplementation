package pam.tugas.internalfileimplementation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editText: EditText = findViewById(R.id.editText)
        var editWrite: EditText = findViewById(R.id.editWrite)
        var editRead: EditText = findViewById(R.id.editRead)
        var readTextFile: TextView = findViewById(R.id.readTextFile)

        val btnWrite: Button = findViewById(R.id.btnWrite)
        val btnRead: Button = findViewById(R.id.btnRead)

        btnWrite.setOnClickListener {
            writeToFile(editWrite.text.toString(), editText.text.toString())
            editText.setText("")
            editWrite.setText("")
        }

        btnRead.setOnClickListener {
            val text = readFromFile(editRead.text.toString())
            readTextFile.text = text
            editRead.setText("")
        }
    }

    private fun writeToFile(nameFile: String, data: String) {
        try {
            val outputStream: FileOutputStream =
                openFileOutput("$nameFile.txt", Context.MODE_PRIVATE)
            outputStream.write(data.toByteArray())
            outputStream.close()
            Toast.makeText(this, "Berhasil membuat file $nameFile", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readFromFile(nameFile: String): String {
        val stringBuilder = StringBuilder()
        try {
            val fileInputStream: FileInputStream = openFileInput("$nameFile.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var text: String? = bufferedReader.readLine()
            while (text != null) {
                stringBuilder.append(text)
                text = bufferedReader.readLine()
            }
            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "File $nameFile tidak ditemukan", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}
