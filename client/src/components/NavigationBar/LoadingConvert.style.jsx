import styled, { css } from "styled-components";

// 스피너 맨 바깥 
export const SpinnerArea = styled.div`
    width: 70px;
    height: 70px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;
`;

// 스피너 
export const SpinnerImg = styled.img`
    width : 80%;
    position: absolute; // 절대 위치로 설정
    top: 5px; // 부모 요소에 대해 상단 정렬
`;

// 스피너 하단 텍스트
export const SpinnerTitle = styled.div`
    font-family: "NanumSquareRound";
    font-size: 10px;
    font-weight: bold;
    color: #252525;
    position: absolute; // 절대 위치로 설정
    bottom: 10px; // 부모 요소에 대해 하단 정렬
`;

// 완료된 버튼
export const CompleteContainer = styled.div`
    width: 68px;
    height: 28px;
    border: 1px solid;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 5px;
    border-color: #252525;
    cursor: pointer;
    text-align: center;
    background-color: #FFFCEB;
`;

export const CompleteText = styled.div`
    font-family: "NanumSquareRound";
    font-size: 10px;
    font-weight: bold;
    color: #252525;
`;