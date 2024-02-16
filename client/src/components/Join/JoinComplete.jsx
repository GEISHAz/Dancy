import DancyImg from "../../assets/join/BigLogo.png";
import * as J from "./SelectJoinMethod.style";
import * as JC from "./JoinComplete.style";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export default function JoinComplete() {
  return (
    <JC.SelectJoinArea>
      <JC.LogoArea>
        <J.JoinLogo>
          <img src={DancyImg}></img>
        </J.JoinLogo>
        <J.WelcomeTitle>축하합니다, 가입이 완료되었습니다!</J.WelcomeTitle>
        <JC.GoLoginBtn>
          <Link to="/login">바로 이용해보기</Link>
        </JC.GoLoginBtn>
      </JC.LogoArea>
    </JC.SelectJoinArea>
  );
}
