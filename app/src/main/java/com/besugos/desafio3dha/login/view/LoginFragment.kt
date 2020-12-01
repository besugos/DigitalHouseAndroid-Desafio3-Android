package com.besugos.desafio3dha.login.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.besugos.desafio3dha.home.view.HomeActivity
import com.besugos.desafio3dha.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        val btnRegister: TextView = view.findViewById(R.id.txtCreateAccount)

        btnRegister.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }

        view.findViewById<TextInputEditText>(R.id.etEmailLogin).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.findViewById<TextInputLayout>(R.id.tfEmailLogin).error = ""
            }
        })

        view.findViewById<TextInputEditText>(R.id.etPassLogin).addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.findViewById<TextInputLayout>(R.id.tfPassLogin).error = ""
            }
        })

        val btnLogin: Button = view.findViewById(R.id.btnLoginLogin)

        btnLogin.setOnClickListener {

            if (validaCampos(view)) {
                hideKeyboard()
                //navController.navigate(R.id.homeFragment)
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun validaCampos(view: View): Boolean {

        var resultado = true

        if (view.findViewById<TextInputEditText>(R.id.etEmailLogin).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfEmailLogin).error = "E-mail Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etPassLogin).text?.trim()
                .isNullOrBlank()
        ) {
            view.findViewById<TextInputLayout>(R.id.tfPassLogin).error = "Password Vazio"
            resultado = false
        }

        if (view.findViewById<TextInputEditText>(R.id.etPassLogin).text!!.length < 8) {
            view.findViewById<TextInputLayout>(R.id.tfPassLogin).error =
                "Password Inválida - deve ter ao menos 8 caracteres"
            resultado = false
        }

        return resultado
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

