import React from "react";
import "../../css/mainInteriorImage.css";
import Popover from "@mui/material/Popover";
import Typography from "@mui/material/Typography";
import { useState } from "react";
import { useEffect } from "react";
import axios from "axios";

const HoverIcon = ({ productItem }) => {
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handlePopoverOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handlePopoverClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);

  return (
    <div className="hoverIconActive">
      <Typography
        aria-owns={open ? "mouse-over-popover" : undefined}
        aria-haspopup="true"
        onMouseEnter={handlePopoverOpen}
        onMouseLeave={handlePopoverClose}
      >
        <div className="hoverIcon2">
          <div className="smallHoverIcon2"></div>
        </div>

        {/* <BsRecordCircle className="hoverIcon" /> */}
      </Typography>
      <Popover
        id="mouse-over-popover"
        sx={{
          pointerEvents: "none",
        }}
        open={open}
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "left",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "left",
        }}
        onClose={handlePopoverClose}
        disableRestoreFocus
      >
        {/* <div responsive="responsive"> */}
        {/* <Typography sx={{ p: 1 }}> */}
        <div className="showroomProductContent">
          <p style={{ fontSize: "18px", fontWeight: "bold" }}>
            {productItem.productName}
          </p>{" "}
          <br />
          <p style={{ fontSize: "16px", lineHeight: "120%" }}>
            {productItem.productCategoryName} ,
            <br />
            {productItem.productSize}
          </p>
          <br />
          <p style={{ fontSize: "22px", fontWeight: "bold" }}>{`₩ ${(
            productItem.productPrice + ""
          ).replace(/\B(?=(\d{3})+(?!\d))/g, ",")}`}</p>
        </div>
        {/* </Typography> */}
        {/* </div> */}
      </Popover>
    </div>
  );
};

export default HoverIcon;
