import React, {useEffect} from "react";
import styled from "styled-components";
import VideoDetail from "../components/VideoDetail/VideoDetail";
import RecommendVideo from "../components/VideoDetail/RecommendVideo";
import Comment from "../components/VideoDetail/Comment";

const VideoDetailPageContainer = styled.div`
	margin: 72px;
	display: flex;
	flex-direction: row;
	justify-content: center;
	gap: 60px;
`

const VideoDetailPageLayout = styled.div`
`


export default function VideoDetailPage({videoSrc}) {
	
	// 새로고침 시 젤 위로 올라가는 기능
	useEffect(() => {
		window.onbeforeunload = function pushRefresh() {
			window.scrollTo(0, 0);
		};
	}, []);
	
	return (
		<VideoDetailPageContainer>
			<VideoDetailPageLayout>
					<VideoDetail src={videoSrc} />
					<Comment />
			</VideoDetailPageLayout>
			<RecommendVideo />
		</VideoDetailPageContainer>
	);
}
