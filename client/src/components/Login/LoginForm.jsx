import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import LoginModal from './LoginModal.jsx';
import { useState } from 'react';

import {
	Container,
	DancyLogo,
	SocialGoogle,
	SocialKakao,
	SocialNaver,
	InputBox,
	InputTitle,
	AutoLogin,
	LoginButton,
	ExplainJoinFindPw,
	GoJoinFindPw,
} from './LoginForm.style.jsx';

export default function Login() {
	const [isOpen, setIsOpen] = useState(false);
	const openModalHandler = () => {
		setIsOpen(!isOpen)
	}

	return (
		
		<Container>
			{isOpen && (<LoginModal />)}
			<div className="inline-flex flex-col gap-y-8">
				<DancyLogo />

				{/* E-mail 입력란 */}
				<div className='flex flex-col gap-y-1'>
					<InputTitle>E-mail</InputTitle>
					<InputBox />
				</div>

				{/* PW 입력란 */}
				<div className='flex flex-col gap-y-1'>
					<InputTitle>Password</InputTitle>
					<InputBox />
				</div>

				{/* 자동로그인 + 소셜로그인 */}
				<div className="flex flex-row justify-between">

					{/* 자동로그인 체크박스 */}
					<div className="flex flex-row items-center gap-x-1">
						<input type="checkbox" />
						<AutoLogin>자동 로그인</AutoLogin>
					</div>

					{/* 소셜로그인 아이콘 */}
					<div className="flex flex-row gap-x-4">
						<SocialGoogle />
						<SocialKakao />
						<SocialNaver />
					</div>
				</div>


				{/* 로그인 버튼 */}
				<LoginButton onClick={openModalHandler}>로그인</LoginButton>
				

				{/* 회원가입 + 비밀번호 찾기 */}
				<div>
					<div className="flex flex-row items-center gap-x-2 justify-end">
						<ExplainJoinFindPw>아직 회원이 아니신가요?</ExplainJoinFindPw>
						<GoJoinFindPw><Link to='/signup'>회원가입</Link></GoJoinFindPw>
					</div>

					<div className="flex flex-row items-center gap-x-2 justify-end">
						<ExplainJoinFindPw>비밀번호를 잊어버리셨나요?</ExplainJoinFindPw>
						<GoJoinFindPw><Link to=''>비밀번호 찾기</Link></GoJoinFindPw>
					</div>
				</div>
			</div>
		</Container>
		
	);
}
