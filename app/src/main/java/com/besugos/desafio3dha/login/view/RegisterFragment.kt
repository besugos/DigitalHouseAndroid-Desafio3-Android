package com.besugos.desafio3dha.login.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.besugos.desafio3dha.home.view.HomeActivity
import com.besugos.desafio3dha.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        val toolbar: Toolbar = view.findViewById(R.id.tbRegister)

        toolbar.setNavigationOnClickListener {
            navController.navigate(R.id.loginFragment)
        }

        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {

            if (validaCampos(view)) {
                //navController.navigate(R.id.homeFragment)
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        view.findViewById<TextInputEditText>(R.id.etNameRegister).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.findViewById<TextInputLayout>(R.id.tfNameRegister).error = ""
            }
        })

        view.findViewById<TextInputEditText>(R.id.etEmailRegister).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.findViewById<TextInputLayout>(R.id.tfEmailRegister).error = ""
            }
        })

        view.findViewById<TextInputEditText>(R.id.etPassRegister).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.findViewById<TextInputLayout>(R.id.tfPassRegister).error = ""
            }
        })
    }

    fun validaCampos(view: View): Boolean {

        var resultado = true

        if (view.findViewById<TextInputEditText>(R.id.etNameRegister).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfNameRegister).error = "Nome Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etEmailRegister).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfEmailRegister).error = "E-mail Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etPassRegister).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfPassRegister).error = "Password Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etNameRegister).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfNameRegister).error = "Nome Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etEmailRegister).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfEmailRegister).error = "E-mail Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etPassRegister).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfPassRegister).error = "Password Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etPassRegister).text!!.length < 8) {
            view.findViewById<TextInputLayout>(R.id.tfPassRegister).error =
                "Password deve ter no mínimo 8 caracteres"
            resultado = false
        }

        return resultado
    }
}
