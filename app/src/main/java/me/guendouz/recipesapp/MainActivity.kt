package me.guendouz.recipesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.guendouz.recipesapp.entity.Message

class MainActivity : AppCompatActivity() {

    private lateinit var msgViewModel: MessageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MessageListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        msgViewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        msgViewModel.allMessages.observe(this, Observer { messages ->

            messages?.let { adapter.setMessages(it) }
        })

        val editText: EditText = findViewById(R.id.editText)
        val btnAdd: Button = findViewById(R.id.btn_add)
        val btnDeleteAll: Button = findViewById(R.id.btn_delete)

        btnAdd.setOnClickListener {
            val text = editText.text.toString().trim()
            if (editText.text.toString().trim() != "") {
                val msg = Message(text = text)
                msgViewModel.insert(msg)
                editText.setText("")
            }
        }
        btnDeleteAll.setOnClickListener { msgViewModel.deleteAll() }
    }
}
