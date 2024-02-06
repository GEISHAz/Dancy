import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import Create from "../pages/Create";
import Practice from "../pages/Practice";
import Stage from "../pages/Stage";
import Profile from "../pages/Profile";
import Login from "./Login/LoginForm";
import SignUp from "../pages/SignUp";
import SearchResult from "../pages/SearchResult";
import VideoDetailPage from "../pages/VideoDetailPage";
import UserSetting from "../pages/UserSetting";
import FindMyPwd from "../pages/FindMyPwd";
import JoinForm from "./Join/JoinForm";
import JoinComplete from "./Join/JoinComplete";

export default function Router({ cardDetails, videoDetails }) {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/create" element={<Create />} />
        <Route path="/practice" element={<Practice />} />
        <Route path="/stage" element={<Stage />} />
        <Route path="/profile/:user_id" element={<Profile />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/signup/joinform" element={<JoinForm />} />
        <Route path="/signup/joincomplete" element={<JoinComplete />} />
        <Route path="/detail/:video_id" element={<VideoDetailPage />} />
        {/* SearchResult 페이지의 라우트 추가 */}
        <Route path="/results" element={<SearchResult cardDetails={cardDetails} />} />
        {/* UserSetting 페이지의 라우트 추가 */}
        <Route path="/setting" element={<UserSetting />} />
        {/* FindPW 페이지의 라우트 추가 */}
        <Route path="/findpw" element={<FindMyPwd />} />
      </Routes>
    </>
  );
}
