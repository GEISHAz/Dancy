import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import * as V from "./VideoDetail.style";
import { deleteArticle, getArticle, saveArticle } from "../../api/stage";
import VideoPlayer from "./VideoPlayer";
import { useRecoilValue } from 'recoil';
import { userState } from '../../recoil/LoginState.js';
import UpdateModal from '../../components/VideoDetail/UpdateModal.jsx'
import { articleLike, likeUsers } from "../../api/like.js";
import { userInfo } from "../../api/myPage.js";
import { followRequest, unFollowRequest } from '../../api/follow.js';

export default function VideoDetail() {

  const navigate = useNavigate();
  const state = useLocation();
  const articleId = Number(state.pathname.split('/')[2])
	const [articleInfo, setArticleInfo] = useState({})
	const [authorInfo, setAuthorInfo] = useState({})
	const [createdDate, setCreatedDate] = useState('')
	const user = useRecoilValue(userState)              // 로그인 한 유저 정보
	const [isMyArticle, setIsMyArticle] = useState(false)
	const [beforeData, setBeforeData] = useState({
    'articleTitle': "",
    'articleContent': "",
    'video': "",
    'thumbnailImageUrl': "",
  });

	// 페이지 렌더링 시 기본 정보 호출 (onMount)
  useEffect(() => {
    // 게시글 상세 정보 조회를 위한 api 요청
    getArticle(articleId)
    .then ((res) => {
      console.log('article',res)
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

      userInfo(res.nickname)
      .then ((res) => {
        console.log(res)
        setAuthorInfo(res)
      })
      .catch ((err) => {
        console.error(err)
      })

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
		articleLike(articleId)
    .then((res) => { window.location.reload() })
    .catch((err) => console.error(err))
  };

  // 게시글 보관 관리
  const handleSave = () => {
    saveArticle(articleId)
    .then(() => window.location.reload())
    .catch((err) => console.error(err))
  }

  // 게시글 작성자 팔로우/언팔로우 (자신의 글이라면 팔로우 버튼 노출 X)
  const followHandler = () => {
		if (authorInfo.followed) {
			unFollowRequest(authorInfo.nickname)
			.then ((res) => {
				setAuthorInfo({
					...authorInfo, 
					"follower" : res.follower.follower, 
					"following" : res.follower.following, 
					"followed" : res.follower.followed
				})
			})
			.catch((err) => {
				console.error(err)
			})
		} else {
			followRequest(authorInfo.nickname)
			.then ((res) => {
				setAuthorInfo({
					...authorInfo, 
					"follower" : res.follower.follower, 
					"following" : res.follower.following, 
					"followed" : res.follower.followed
				})
			})
			.catch((err) => {
				console.error(err)
			})
		}
	}

  const [isDropDown, setIsDropDown] = useState(false)
  
  // 게시글 좋아요한 유저 목록 조회
  const handleLikeUser = () => {
    likeUsers(articleId)
    .then((res) => {
      setLikeUser(res)
      setIsDropDown(!isDropDown)
    })
    .catch((err) => { console.error(err) })
  }
	
  // update모달 관리 
	const [isOpen, setIsOpen] = useState(false);
	const getData = childData => {
		setIsOpen(childData);
	};

  return (
		<>
			{isOpen && (<UpdateModal articleId={articleId} beforeData={beforeData} getData={getData}/>)}
			<V.FunctionWrapper isMyArticle={isMyArticle}>
				<V.EditWrap isMyArticle={isMyArticle}>
					<V.EditBtn onClick={handleUpdate} />
					<V.DeleteBtn onClick={handleDelete} />
				</V.EditWrap>
				<V.BtnWrap>
					<V.WithArea>
						<V.WithBtn src="/src/assets/with.png"/>
					</V.WithArea>
					<V.SaveBtn src={articleInfo.isArticleSaved ? "/src/assets/saveimage.png" : "/src/assets/unsaveimage.png"} onClick={handleSave}/>
					<V.LikeBtn src={articleInfo.isArticleLiked ? "/src/assets/likeimage.png" : "/src/assets/unlikeimage.png"} onClick={handleLike} />
					<V.LikeRate>
						<V.DropdownToggle onClick={handleLikeUser}> {articleInfo.articleLike} 
              {isDropDown && (
                  <V.LikeUserList>
                    {likeUser.map((user) => (
                      <Link to={`/profile/${user.nickname}`}>
                        <V.LikeUserInfo>
                          <V.LikeUserImg src={user.profileImageUrl} />
                          <V.LikeUserNickName>{user.nickname}</V.LikeUserNickName>
                        </V.LikeUserInfo>
                      </Link>
                      )
                    )}
                  </V.LikeUserList>
                )}
            </V.DropdownToggle>
					</V.LikeRate>
				</V.BtnWrap>
      </V.FunctionWrapper>
			
			<VideoPlayer src={articleInfo.videoUrl} />

			<V.VideoDetailContainer>
        <V.VideoDetailArea>
          <V.VideoTitle>{articleInfo.articleTitle}</V.VideoTitle>
          <V.VideoContentArea>
            <V.VideoUserDetailArea>
            <Link to={`/profile/${authorInfo.nickname}`}>
              <V.VideoUserProfileImage src={authorInfo.profileImageUrl} />
            </Link>
              <V.VideoUserDetail>
              <Link to={`/profile/${authorInfo.nickname}`}>
                <V.VideoUserName>{articleInfo.nickname}</V.VideoUserName>
              </Link>
                <V.VideoFollowArea>
                  <V.VideoFollower>
                    <div>팔로워 {authorInfo.follower}</div>
                  </V.VideoFollower>
                  {authorInfo.isMine ? null :                       
                    <V.VideoFollowBtn $isFollow={authorInfo.followed} onClick={followHandler}>
                      <div>{authorInfo.followed ? "팔로잉" : "팔로우"}</div>
                    </V.VideoFollowBtn>
                  }
                </V.VideoFollowArea>
              </V.VideoUserDetail>
            </V.VideoUserDetailArea>
            <V.VideoAccuracyArea>
              <V.VideoUpperContainer>
                <div>{`${createdDate[0]}. ${createdDate[1]}. ${createdDate[2]}.`} &nbsp; |&nbsp; 조회 수 {articleInfo.view}</div>
              </V.VideoUpperContainer>
              <V.VideoLowerContainer>
                <V.VideoContent>{articleInfo.articleContent}</V.VideoContent>
                <V.BtnContainer>
                  <V.AccuracyBtn>{articleInfo.score} %</V.AccuracyBtn>
                </V.BtnContainer>
              </V.VideoLowerContainer>
            </V.VideoAccuracyArea>
          </V.VideoContentArea>
        </V.VideoDetailArea>
			</V.VideoDetailContainer>
		</>
  );
}
