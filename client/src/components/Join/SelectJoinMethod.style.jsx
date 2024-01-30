import { styled } from "styled-components";


// Dancy 제목
const WelcomeTitle = styled.div`
    color: #444444;
    font-family: "NYJ Gothic B-Regular";
    font-weight: 400;
    font-size: 32px;
`

// dancy 회원가입 선택
const JoinChoiceTitle = styled.div`
    color: #444444;
    font-family: "NYJ Gothic L-Regular";
    font-weight: 400;
    font-size: 24px;
`

// 회원가입 버튼
// 버튼 테두리 컨테이너
const JoinBtnContainer = styled.div`
    width: 724px;
    height: 98px;
    border: 1px solid black;
    border-radius: 5px;
    background-color: white;
`
// 버튼 로고 -> 바깥에서 png 배치 
const LogoBox = styled.div`
    width: 46px;
    height:46px;
    border: 0.5px solid black;
    border-radius: 5px;
`

// 회원가입 방법 text
const JoinMethodText = styled.div`
    color: #252525;
    font-family: "NYJ Gothic L-Regular";
    font-weight: 400;
    font-size: 28px;
    line-height: normal;
`
// 회원가입 방법 textinfo
const JoinMethodInfoText = styled.div`
    color: #252525;
    font-family: "NYJ Gothic L-Regular";
    font-weight: 400;
    font-size: 20px;
    line-height: normal;
`