import React, { useState } from "react";
import axios from "axios";
import { Box, Button, TextField, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

const LoginPage = ({ onLogin }: { onLogin: () => void }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [error, setError] = useState("");
  const [isRegistering, setIsRegistering] = useState(false);
  const navigate = useNavigate();

  const API_URL = process.env.REACT_APP_API_URL;

  const handleLogin = async () => {
    console.log("handleLogin ejecutado");
    console.log(`Username: ${username}`);
    console.log(`Password: ${password}`);

    if (!API_URL) {
      console.error("API_URL no está configurada.");
      setError("Error: API_URL no está configurada.");
      return;
    }

    try {
      const response = await axios.post(
        `${API_URL}/auth/login`,
        null,
        {
          params: {
            username: username,
            password: password,
          },
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      console.log("Login response:", response);

      if (response.status === 200) {
        const token = response.data.token;
        console.log("Token recibido:", token);
        localStorage.setItem("token", token);
        onLogin();
        navigate("/");
      } else {
        console.error("Error en la respuesta del servidor:", response);
        setError("Usuario o contraseña incorrectos.");
      }
    } catch (error) {
      console.error("Login error:", error);
      setError("Ha ocurrido un error. Por favor, inténtelo de nuevo.");
    }
  };

  const handleRegister = async () => {
    console.log("handleRegister ejecutado");
    console.log(`Username: ${username}`);
    console.log(`Password: ${password}`);
    console.log(`Email: ${email}`);
    console.log(`Name: ${name}`);

    if (!API_URL) {
      console.error("API_URL no está configurada.");
      setError("Error: API_URL no está configurada.");
      return;
    }

    try {
      const response = await axios.post(
        `${API_URL}/auth/register`,
        {
          username: username,
          password: password,
          email: email,
          name: name,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      console.log("Register response:", response);

      if (response.status === 200) {
        console.log("Registro exitoso");
        navigate("/login"); // Redirige a la página de inicio de sesión después de registrarse
      } else {
        console.error("Error en la respuesta del servidor:", response);
        setError("Fallo al registrarse. Por favor, inténtelo de nuevo.");
      }
    } catch (error) {
      console.error("Register error:", error);
      setError("Ha ocurrido un error. Por favor, inténtelo de nuevo.");
    }
  };

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      height="100vh"
      bgcolor="#101b20"
    >
      <Typography variant="h4" color="white" mb={2}>
        {isRegistering ? "Registrarse" : "Iniciar Sesion"}
      </Typography>
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        bgcolor="white"
        p={3}
        borderRadius={2}
      >
        <TextField
          label="Username"
          variant="outlined"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          sx={{ mb: 2, width: "300px" }}
        />
        <TextField
          label="Password"
          type="password"
          variant="outlined"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          sx={{ mb: 2, width: "300px" }}
        />
        {isRegistering && (
          <>
            <TextField
              label="Email"
              variant="outlined"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              sx={{ mb: 2, width: "300px" }}
            />
            <TextField
              label="Name"
              variant="outlined"
              value={name}
              onChange={(e) => setName(e.target.value)}
              sx={{ mb: 2, width: "300px" }}
            />
          </>
        )}
        {error && (
          <Typography color="error" mb={2}>
            {error}
          </Typography>
        )}
        <Button
          variant="contained"
          color="primary"
          onClick={() => {
            console.log("Botón clicado");
            isRegistering ? handleRegister() : handleLogin();
          }}
          sx={{ mb: 2 }}
        >
          {isRegistering ? "Registrarse" : "Iniciar Sesion"}
        </Button>
        <Button
          variant="text"
          color="primary"
          onClick={() => setIsRegistering(!isRegistering)}
        >
          {isRegistering
            ? "¿Ya tienes una cuenta? Iniciar Sesion"
            : "¿No tienes una cuenta? Registrarse"}
        </Button>
      </Box>
    </Box>
  );
};

export default LoginPage;