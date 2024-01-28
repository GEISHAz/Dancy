import { Route, Routes, Navigate } from "react-router-dom";
import Home from "../pages/Home";
import Practice from "../pages/Practice";
import Stage from "../pages/Stage";
import Profile from "../pages/Profile";
import Login from "../pages/Login";
import SignUp from "../pages/Signup";

export default function Router() {
    return (
        <>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/practice" element={<Practice />} />
                <Route path="/stage" element={<Stage />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<SignUp/>} />
            </Routes>
        </>
    )
}