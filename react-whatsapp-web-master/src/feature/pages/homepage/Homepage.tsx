import { Box } from "@mui/material";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import LeftPanel from "./LeftPanel";
import RightPanel from "./RightPanel";

interface HomepageProps {
  conversations: { id: string; participants: string[] }[];
}

// -------------------------- CONTAINER APLICACIÓN WEB --------------------------
export default function Homepage({ conversations }: HomepageProps) {
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
    }
  }, [navigate]);

  return (
    // CONTAINER COMPLETO DE LA APLICACIÓN
    <Box display="flex" flexDirection="row" height="100vh">
      {/* PANEL DE CONVERSACIONES */}
      <LeftPanel conversations={conversations} />
      <Box
        sx={{
          border: ".05px solid #2f3b44",
        }}
      />
      {/* PANEL DE CONVERSACIÓN SELECCIONADA */}
      <RightPanel />
    </Box>
  );
}