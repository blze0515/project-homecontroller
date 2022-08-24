import React, { useState, useCallback, useEffect } from "react";
import { Typography } from "@mui/material";
import { Browser, Routes, Route, BrowserRouter } from "react-router-dom";
import App from "./App";
import Login from "./components/user/Login";
import Header from "./components/Header";
import Join from "./components/user/Join";
import Checkout from "./components/order/Checkout";
import Dashboard from "./components/admin/Dashboard";
import Mypage from "./components/mypage/Mypage";
import ImageThumb from "./components/productDetail/ImageThumb";
import ProductMainInfo from "./components/productDetail/ProductMainInfo";
import InteriorShowroom from "./components/showroom/InteriorShowroom";
import Footer from "./components/Footer";
import KakaoPay from "./components/order/KakaoPay";

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {"Copyright"}
      <i className="fa-brands fa-github" style={{ fontSize: "1.7rem" }}></i>
      &nbsp; bitcamp &nbsp;
      {new Date().getFullYear()}
    </Typography>
  );
}

const AppRouter = () => {
  // 경로에 따라 실행되는 컴포넌트가 다르므로, 그 정보를 갖고있는 AppRouter를 가장 먼저 렌더링해야 한다.(Index.js에서)
  return (
    <div>
      <Header />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />} />
          <Route path="/join" element={<Join />} />
          <Route path="/login" element={<Login />} />
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/order" element={<Checkout />} />
          <Route path="/admin" element={<Dashboard />} />
          <Route path="/productDetail" element={<ImageThumb />} />
          <Route path="/showroom" element={<InteriorShowroom />} />
          <Route path="/kakaopay" element={<KakaoPay />} />
        </Routes>
      </BrowserRouter>
      <Footer />
    </div>
  );
};

export default AppRouter;
