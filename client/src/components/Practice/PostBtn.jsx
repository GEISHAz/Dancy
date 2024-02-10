import * as P from './PostBtn.style'
import PostModal from './PostModal'
import { useState } from 'react';

export default function PostBtn ({ getData }) {
	const data = true;

	const postData = () => {
		getData(data)
	}
	
	return (
		<P.PostBtn onClick={postData}>
			게시하기
		</P.PostBtn>
	)
}