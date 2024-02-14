import OriginDance from "../components/Create/OriginDance"
import MyDance from "../components/Create/MyDance"
import { TransBtn } from "../components/Create/TransBtn.style"
import { useNavigate } from "react-router-dom";
import { useRecoilValue, useRecoilState } from "recoil";
import { myState, originState } from "../recoil/PracticeState";
import { useEffect, useState } from "react";
import { plzAnalyze } from "../api/video";
import { startToConvertState } from "../recoil/AlarmState";


export default function Create() {
    const [convertStarted, setConvertStarted] = useRecoilState(startToConvertState);    
    const originVideo = useRecoilValue(originState)
    const myVideo = useRecoilValue(myState)
    const [formData, setFormData] = useState({
        "referenceVideoUrl": originVideo.resultVideoUrl,
        "practiceVideoUrl": myVideo.resultVideoUrl,
    })

    const navigate = useNavigate();

    const transHandler = async () => {
        await plzAnalyze(formData)
        .then((res) => {
            console.log(res)
            navigate("/");
            setConvertStarted(true);
        }).catch((err) => {
            alert("다시 시도해주세요.");
        })
    };

    return (
        <div className="flex flex-col justify-center items-center gap-12">
            <div className="flex justify-center items-center mt-14 gap-20">
                <OriginDance />
                <MyDance />
            </div>

            <TransBtn onClick={() => transHandler()}>변환하기</TransBtn>
        </div>
    )
}