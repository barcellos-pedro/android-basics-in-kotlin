package com.example.funcionariocrud

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.funcionariocrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var databaseHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize buttons click listeners
        binding.let {
            it.inserir.setOnClickListener { onInsert() }
            it.alterar.setOnClickListener { onAlter() }
            it.excluir.setOnClickListener { onDelete() }
        }

        onSelectAll()
    }

    private fun onInsert() {
        try {
            databaseHelper.insert(
                binding.nomeInput.text.toString(),
                binding.cargoInput.text.toString(),
                binding.dataInput.text.toString(),
                binding.enderecoInput.text.toString()
            )
            showToast("Cadastro concluído.")
            clearFields()
        } catch (error: Exception) {
            showError(error)
        }
    }

    private fun onAlter() {
        try {
            val isUpdate = databaseHelper.update(
                binding.idInput.text.toString(),
                binding.nomeInput.text.toString(),
                binding.cargoInput.text.toString(),
                binding.dataInput.text.toString(),
                binding.enderecoInput.text.toString()
            )

            clearFields()

            when (isUpdate) {
                true -> showToast("Dados alterados.")
                false -> showToast("Não foi possível atualizar os dados.")
            }
        } catch (error: Exception) {
            showError(error)
        }
    }

    private fun onDelete() {
        try {
            databaseHelper.delete(binding.idInput.text.toString())
            clearFields()
            showToast("Registro excluído.")
        } catch (error: Exception) {
            showError(error)
        }
    }

    private fun onSelectAll() {
        binding.consultar.setOnClickListener(
            View.OnClickListener {
                val response = databaseHelper.getAll()
                val getValueFromColumn = { index: Int -> response.getString(index) }

                if (response.count == 0) {
                    showDialog(
                        "Erro",
                        "Dados não encontratos"
                    )
                    return@OnClickListener
                }

                val buffer = StringBuffer()

                while (response.moveToNext()) {
                    buffer
                        .append("ID: " + getValueFromColumn(0))
                        .append(", Nome:" + getValueFromColumn(1))
                        .append(", Cargo:" + getValueFromColumn(2))
                        .append(", Data Admissão:" + getValueFromColumn(3))
                        .append(", Endereço:" + getValueFromColumn(4))
                        .append("\n")
                }

                showDialog("Dados retornados", buffer.toString())
            }
        )
    }

    fun showToast(message: String) {
        Toast.makeText(
            this@MainActivity,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setCancelable(true)
            .setTitle(title)
            .setMessage(message)
            .show()
    }

    fun clearFields() {
        binding.let {
            it.idInput.setText("")
            it.nomeInput.setText("")
            it.cargoInput.setText("")
            it.dataInput.setText("")
            it.enderecoInput.setText("")
        }
    }

    fun showError(error: Exception) {
        error.printStackTrace()
        showToast(error.message.toString())
    }

}

