import React, { useState } from "react";
import { Box, Avatar, Typography, IconButton, Input, Modal, TextField, Button } from "@mui/material";
import CustomAppBar from "../../../foundation/CustomAppBar/CustomAppBar";
import CustomMenuButton from "../../../foundation/CustomMenuButton/CustomMenuButton";
import ChatIcon from "@mui/icons-material/Chat";
import SearchIcon from "@mui/icons-material/Search";
import FilterListIcon from "@mui/icons-material/FilterList";
import AddCommentIcon from "@mui/icons-material/AddComment";
import { leftPanelMenuItem } from "./utils/constant"; // Importa leftPanelMenuItem
import axios from "axios";

interface LeftPanelProps {
  conversations: { id: string; participants: string[] }[];
}

// -------------------------- CARACTERÍSTICAS ESTÉTICAS --------------------------
export default function LeftPanel({ conversations }: LeftPanelProps) {
  const [searchText, setSearchText] = useState("");
  const [open, setOpen] = useState(false);
  const [newConversationUsername, setNewConversationUsername] = useState("");

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleAddConversation = async () => {
    const token = localStorage.getItem("token");
    if (!token) {
      console.error("No token found");
      return;
    }

    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/api/conversations/private-conversation`,
        new URLSearchParams({ user2Username: newConversationUsername }),
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
      );
      console.log("Conversación iniciada:", response.data);
      // Aquí puedes actualizar el estado de las conversaciones si es necesario
      handleClose();
    } catch (error) {
      console.error("Error iniciando conversación:", error);
    }
  };

  const filteredConversations = conversations.filter((conversation) =>
    conversation.participants.some((participant) =>
      participant.toLowerCase().includes(searchText.toLowerCase())
    )
  );

  return (
    <Box height="100%" width="30%" overflow="hidden">
      <CustomAppBar>
        <Box
          width="100%"
          height="100%"
          display="flex"
          justifyContent="space-between"
          alignItems="center"
        >
          <Avatar />

          <Box display="flex">
            <IconButton
              onClick={handleOpen}
              sx={{
                paddingRight: "15px",
              }}
            >
              <AddCommentIcon
                sx={{
                  color: "#afbac0",
                }}
              />
            </IconButton>
            <CustomMenuButton menuItems={leftPanelMenuItem} />
          </Box>
        </Box>
      </CustomAppBar>
      <Box
        sx={{
          background: "#101b20",
          padding: "12px",
        }}
        display="flex"
      >
        <Box
          display="flex"
          sx={{
            background: "#1f2c33",
            borderRadius: "8px",
            padding: "0px 8px",
          }}
          flex={1}
          alignItems="center"
        >
          <IconButton onClick={() => {}}>
            <SearchIcon
              sx={{
                color: "#8696a1",
                height: "20px",
                width: "20px",
              }}
            />
          </IconButton>
          <Input
            fullWidth
            disableUnderline
            placeholder="Buscar una conversación. . ."
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
            sx={{
              height: "35px",
              color: "white",
              padding: "0px 13px",
              fontSize: "14px",
            }}
          />
        </Box>
        <IconButton onClick={() => {}}>
          <FilterListIcon
            sx={{
              color: "#8696a1",
              height: "20px",
              width: "20px",
            }}
          />
        </IconButton>
      </Box>
      <Box
        overflow="auto"
        height="90%"
        sx={{
          background: "#101b20",
        }}
      >
        {filteredConversations.map((conversation) => (
          <Box
            key={conversation.id}
            display="flex"
            alignItems="center"
            sx={{
              background: "#1f2c33",
              borderRadius: "8px",
              padding: "8px",
              marginBottom: "8px",
            }}
          >
            <Avatar />
            <Typography
              sx={{
                color: "#ffffff",
                marginLeft: "8px",
              }}
            >
              {conversation.participants.find(
                (participant) => participant !== localStorage.getItem("username")
              )}
            </Typography>
          </Box>
        ))}
        <Box pt="50px" />
      </Box>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
      >
        <Box
          display="flex"
          flexDirection="column"
          alignItems="center"
          justifyContent="center"
          bgcolor="white"
          p={3}
          borderRadius={2}
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 400,
            boxShadow: 24,
          }}
        >
          <Typography id="modal-title" variant="h6" component="h2">
            Iniciar Conversación
          </Typography>
          <TextField
            label="Username"
            variant="outlined"
            value={newConversationUsername}
            onChange={(e) => setNewConversationUsername(e.target.value)}
            sx={{ mt: 2, mb: 2, width: "100%" }}
          />
          <Button variant="contained" color="primary" onClick={handleAddConversation}>
            Iniciar
          </Button>
        </Box>
      </Modal>
    </Box>
  );
}