package com.example.preguntados.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.preguntados.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Actividad principal de Login que permite a los usuarios ingresar con sus credenciales.
 *
 * Autor: Alvaro Andre Machaca Melendez
 * Fecha de actualización: 26/11/2023
 */
public class LoginActivity extends AppCompatActivity {

    // Referencias a los componentes de la vista
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar los componentes de la vista
        initializeViews();

        // Configurar el botón de login
        setupLoginButton();
    }

    /**
     * Inicializa los componentes de la vista como los campos de texto y el botón de login.
     */
    private void initializeViews() {
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
    }

    /**
     * Configura el botón de login con su evento onClick.
     */
    private void setupLoginButton() {
        loginButton.setOnClickListener(v -> {
            // Obtener los valores ingresados por el usuario
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Validar las credenciales
            if (isLoginValid(username, password)) {
                // Si el login es exitoso
                showToast(R.string.toast_login_successful);
                navigateToMainActivity();
            } else {
                // Si las credenciales son incorrectas
                showToast(R.string.toast_login_failed);
            }
        });
    }

    /**
     * Valida las credenciales ingresadas por el usuario.
     * En una aplicación real, esta validación se haría con un servidor.
     *
     * @param username El nombre de usuario ingresado.
     * @param password La contraseña ingresada.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    private boolean isLoginValid(String username, String password) {
        // Simulación de validación (esto se reemplazaría con una validación real)
        return "usuario".equals(username) && "contraseña".equals(password);
    }

    /**
     * Muestra un mensaje de Toast con el texto especificado.
     *
     * @param messageResId El recurso del texto para el Toast.
     */
    private void showToast(int messageResId) {
        Toast.makeText(LoginActivity.this, getString(messageResId), Toast.LENGTH_SHORT).show();
    }

    /**
     * Navega a la actividad principal (MainActivity).
     */
    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();  // Finaliza esta actividad para evitar que el usuario regrese a la pantalla de login
    }
}