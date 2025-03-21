import React from "react";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import { colors } from "../../theme";

// -------------------------- CONTAINER MENSAJE RECIBIDO --------------------------
const ReplyMessageCard = () => {
  return (
    <Paper
      sx={{
        background: colors.black1,
        display: "flex",
        alignSelf: "flex-start",
        maxWidth: "60%",
        textAlign: "start",
        padding: ".35rem .8rem",
        flexDirection: "column",
        borderRadius: ".625rem",
        position: "relative",

        "&::after": {
          content: '" "',
          border: "20px solid transparent",
          position: "absolute",
          top: 0,
          left: "-1.25rem",
          borderTopLeftRadius: ".5rem",
          borderTopColor: colors.black1,
        },
      }}
    >
      <Typography
        color={colors.white1}
        sx={{
          fontSize: "1rem",
        }}
      >
        Esto es un placeholder de un mensaje recibido.
      </Typography>
      <Typography
        color={colors.grey1}
        sx={{
          fontSize: ".85rem",
          display: "flex",
          alignSelf: "flex-end",
        }}
      >
        4:59
      </Typography>
    </Paper>
  );
};

export default ReplyMessageCard;
