import React from 'react';
import {
	ModalBackdrop,
	ModalView,
} from './LoginModal.style';

export default function LoginModal() {
	return(
		<ModalBackdrop>
			<ModalView>
				<div>로그인에 성공했습니다 ♬</div>
				<button>확인</button>
			</ModalView>
		</ModalBackdrop>
	)
}