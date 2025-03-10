import Box from "@mui/material/Box";
import React from "react";
import OwnMessageCard from "../../../foundation/ChatCard/OwnMessageCard";
import ReplyMessageCard from "../../../foundation/ChatCard/ReplyCard";

// -------------------------- CONTAINER CONVERSACION SELECCIONADA --------------------------
const ChatContainer = () => {
  return (
    <Box
      sx={{
        height: "98%",
        width: "100%", // Asegura que el contenedor ocupe todo el ancho disponible
        position: "absolute",
        top: 0,
        display: "flex",
        flexDirection: "column",
        padding: "1rem", // Ajusta el padding según sea necesario
        gap: ".5rem",
        overflow: "auto",
        boxSizing: "border-box", // Incluye el padding en el ancho total del contenedor
      }}
    >
      {/* El texto que corresponde a cada mensaje esta en su correspondiente archivo
      "OwnMessageCard" y "ReplyMessageCard" */}
      <OwnMessageCard />
      <ReplyMessageCard />
      <OwnMessageCard />
      <ReplyMessageCard />
    </Box>
  );
};

export default ChatContainer;