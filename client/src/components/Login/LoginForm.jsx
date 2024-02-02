import { Link } from 'react-router-dom';
import { useState } from 'react';
import * as L from './LoginForm.style.jsx';

export default function Login() {
	const [isOpen, setIsOpen] = useState(false);
	const openModalHandler = () => {
		setIsOpen(!isOpen)
	}

	return (
		<L.Container>
			{isOpen && (
        <L.ModalBackdrop>
          <L.ModalView>
            <L.ModalTxt>로그인에 성공했습니다 ♬</L.ModalTxt>
            <L.ModalBtn onClick={openModalHandler}>
              <Link to='/'>확인</Link>
            </L.ModalBtn>
          </L.ModalView>
        </L.ModalBackdrop>
      )}

			<div className="inline-flex flex-col gap-y-8">
				<L.DancyLogo />

				{/* E-mail 입력란 */}
				<div className='flex flex-col gap-y-1'>
					<L.InputTitle>E-mail</L.InputTitle>
					<L.InputBox />
				</div>

				{/* PW 입력란 */}
				<div className='flex flex-col gap-y-1'>
					<L.InputTitle>Password</L.InputTitle>
					<L.InputBox />
				</div>

				{/* 자동로그인 + 소셜로그인 */}
				<div className="flex flex-row justify-between">

					{/* 자동로그인 체크박스 */}
					<div className="flex flex-row items-center gap-x-2">
						<L.AutoLoginChkBox id='autologin' />
						<L.AutoLogin for='autologin'>자동 로그인</L.AutoLogin>
					</div>

					{/* 소셜로그인 아이콘 */}
					<div className="flex flex-row gap-x-4">
						<L.SocialGoogle />
						<L.SocialKakao />
						<L.SocialNaver />
					</div>
				</div>


				{/* 로그인 버튼 */}
				<L.LoginButton onClick={openModalHandler}>로그인</L.LoginButton>
				

				{/* 회원가입 + 비밀번호 찾기 */}
				<div>
					<div className="flex flex-row items-center gap-x-2 justify-end">
						<L.ExplainJoinFindPw>아직 회원이 아니신가요?</L.ExplainJoinFindPw>
						<L.GoJoinFindPw><Link to='/signup'>회원가입</Link></L.GoJoinFindPw>
					</div>

					<div className="flex flex-row items-center gap-x-2 justify-end">
						<L.ExplainJoinFindPw>비밀번호를 잊어버리셨나요?</L.ExplainJoinFindPw>
						<L.GoJoinFindPw><Link to='/'>비밀번호</Link></L.GoJoinFindPw>
					</div>
				</div>
			</div>
		</L.Container>
		
	);
}