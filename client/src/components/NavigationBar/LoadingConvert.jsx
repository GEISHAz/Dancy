import React, { useState, useEffect, useRef } from "react";
import * as LC from "./LoadingConvert.style";
import { useRecoilState, useRecoilValue } from "recoil";
import { alarmOccuredState, convertAlarmState, startToConvertState } from "../../recoil/AlarmState";
import Spinner from "../../assets/spinner/ballSpinner.gif";
import { useNavigate } from "react-router-dom";

// 로딩 이미지
export default function LoadingConvert() {
  const [isConverted, setIsConverted] = useRecoilState(convertAlarmState);
  const [convertStarted, setConvertStarted] = useRecoilState(startToConvertState);
  const converted = useRecoilValue(convertAlarmState);
  const navigate = useNavigate(); // useNavigate 훅 사용하기

  const handleClick = () => {
    // 클릭 이벤트가 발생했을 때 navigate('/practice') 호출
    navigate("/practice");
    setIsConverted(false); // 이동하면서 바꿔주기
    setConvertStarted(false); // 변환도 이제 끝났다.
  };

  console.log("변환 다되엇는가", converted);

  return (
    <>
      {converted ? (
        <LC.CompleteContainer onClick={handleClick}><LC.CompleteText>
          complete!</LC.CompleteText></LC.CompleteContainer>
      ) : (
        <LC.SpinnerArea>
          <LC.SpinnerImg src={Spinner}></LC.SpinnerImg>
          <LC.SpinnerTitle>Loading..</LC.SpinnerTitle>
        </LC.SpinnerArea>
      )}
    </>
  );
}
