import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  VideoDetailContainer,
  VideoDetailArea,
  VideoContentArea,
  VideoTitle,
  VideoUserDetailArea,
  VideoUserName,
  VideoFollowArea,
  VideoFollower,
  VideoUserProfileImage,
  VideoFollowBtn,
  VideoAccuracyArea,
  VideoUpperContainer,
  VideoLowerContainer,
  VideoUserDetail,
  BtnContainer,
  HashTagArea,
  AccuracyBtn,
  HashTagBtn,
  HashTagContainer,
	FunctionWrapper,
  SaveBtn,
  WithBtn,
  WithArea,
  LikeBtn,
  LikeRate,
	EditWrap,
	BtnWrap,
	EditBtn,
	DeleteBtn
} from "./VideoDetail.style";
import { deleteArticle, getArticle } from "../../api/stage";
import VideoPlayer from "./VideoPlayer";
import { useRecoilValue } from 'recoil';
import { userState } from '../../recoil/LoginState.js';
import UpdateModal from '../../components/VideoDetail/UpdateModal.jsx'
import { articleLike } from "../../api/like.js";

const cardDetails = [
  {
    username: "namhyun._.2",
    title: "남현이의 춤사위를 보세요",
    created_at: new Date(),
    view: 25,
  },
];

const Hashtags = [
  { text: "#그녀가", color: "#FFF4B5" },
  { text: "#보고싶어", color: "#C0FFF4" },
  { text: "#ㅠㅠ", color: "#D4DCFF" },
];

export default function VideoDetail({videoSrc}) {
  const [follow, setFollow] = useState(false);
  const [cardDetail, setCardDetail] = useState();
  const [hashtags, setHashtags] = useState();
	const [like, setLike] = useState(false);
  const [save, setSave] = useState(false);
  const [likeCount, setLikeCount] = useState(0);

  useEffect(() => {
    setCardDetail(cardDetails);
    setHashtags(Hashtags);
  }, []);

  const toggleFollow = () => {
    setFollow(!follow);
  };
	

  const handleSave = () => {
    setSave(!save)
  }

  const navigate = useNavigate();
  const state = useLocation();
  const articleId = Number(state.pathname.split('/')[2])
	const [articleInfo, setArticleInfo] = useState({})
	const [createdDate, setCreatedDate] = useState('')
	const user = useRecoilValue(userState)              // 로그인 한 유저 정보
	const [isMyArticle, setIsMyArticle] = useState(false)
	const [beforeData, setBeforeData] = useState({
    'articleTitle': "",
    'articleContent': "",
    'video': "test",
    'thumbnailImageUrl': "test",
  });

	// 페이지 렌더링 시 기본 정보 호출 (onMount)
  useEffect(() => {
    // 게시글 상세 정보 조회를 위한 api 요청
    getArticle(articleId)
    .then ((res) => {
      setArticleInfo(res)
			return res
    })
		.then ((res) => {
			setCreatedDate(res.createdDate)

			if (res.nickname === user.nickname) {
				setIsMyArticle(true)
			} else {
				setIsMyArticle(false)
			}

			setBeforeData({
				'articleTitle': res.articleTitle,
				'articleContent': res.articleContent,
				'video': res.video,
				'thumbnailImageUrl': res.thumbnailImageUrl,
			})
			return beforeData
		})
    .catch ((err) => {
			console.error(err)
    })
  }, []);

	// 게시글 삭제 관리
  const handleDelete = () => {
    deleteArticle(articleId)
      .then((res) => {
        navigate('/stage')
      })
      .catch((err) => {
        if (err.response.status === 401) {
          alert(err.response.data[0].message)
          navigate('/login')
        } 
				
				else if (err.response.status === 403) {
          alert(err.response.data[0].message)
        } 
				
				else if (err.response.status === 404) {
          alert(err.response.data[0].message)
          navigate('/stage')
        } 
				
				else { alert('요청에 실패했습니다.') }
      });
  };

	// 게시글 수정 관리
	const handleUpdate = () => {
		setIsOpen(true)
	}
	
	// 게시글 좋아요 관리
	const handleLike = () => {
    // setLike(!like);
    // if (!like) {
    //   setLikeCount(likeCount + 1);
    // } else {
    //   setLikeCount(likeCount - 1);
    // }
		articleLike(articleId)
  };

	// update모달 관리 
	const [isOpen, setIsOpen] = useState(false);
	const getData = childData => {
		setIsOpen(childData);
	};

  return (
		<>
			{isOpen && (<UpdateModal articleId={articleId} beforeData={beforeData} getData={getData}/>)}
			<FunctionWrapper isMyArticle={isMyArticle}>
				<EditWrap isMyArticle={isMyArticle}>
					<EditBtn onClick={handleUpdate} />
					<DeleteBtn onClick={handleDelete} />
				</EditWrap>
				<BtnWrap>
					<WithArea>
						<WithBtn src="/src/assets/with.png"/>
					</WithArea>
					<SaveBtn src={save ? "/src/assets/saveimage.png" : "/src/assets/unsaveimage.png"} onClick={handleSave}/>
					<LikeBtn src={articleInfo.isArticleLiked ? "/src/assets/likeimage.png" : "/src/assets/unlikeimage.png"} onClick={handleLike} />
					<LikeRate>
						<div>
							{articleInfo.articleLike}
						</div>
					</LikeRate>
				</BtnWrap>
      </FunctionWrapper>
			
			<VideoPlayer src={videoSrc} />

			<VideoDetailContainer>
				{cardDetail &&
					cardDetail.map((card, index) => (
						<VideoDetailArea key={index}>
							<VideoTitle>{articleInfo.articleTitle}</VideoTitle>
							<VideoContentArea>
								<VideoUserDetailArea>
									<VideoUserProfileImage />
									<VideoUserDetail>
										<VideoUserName>{articleInfo.nickname}</VideoUserName>
										<VideoFollowArea>
											<VideoFollower>
												<div>팔로워 672</div>
											</VideoFollower>
											<VideoFollowBtn $follow={follow} onClick={toggleFollow}>
												<div>{articleInfo.isAuthorFollowed ? "팔로잉" : "팔로우"}</div>
											</VideoFollowBtn>
										</VideoFollowArea>
									</VideoUserDetail>
								</VideoUserDetailArea>
								<VideoAccuracyArea>
									<VideoUpperContainer>
										<div>{`${createdDate[0]}. ${createdDate[1]}. ${createdDate[2]}.`} &nbsp; |&nbsp; 조회 수 {articleInfo.view}</div>
									</VideoUpperContainer>
									<VideoLowerContainer>
										<div>{articleInfo.articleContent}</div>
										<BtnContainer>
											<AccuracyBtn>87 %</AccuracyBtn>
											<HashTagContainer>
												<HashTagArea>
													{hashtags &&
														hashtags.map((hashtag, index) => (
															<HashTagBtn key={index} color={hashtag.color}>
																{hashtag.text}
															</HashTagBtn>
														))}
												</HashTagArea>
											</HashTagContainer>
										</BtnContainer>
									</VideoLowerContainer>
								</VideoAccuracyArea>
							</VideoContentArea>
						</VideoDetailArea>
					))}
			</VideoDetailContainer>
		</>
  );
}
