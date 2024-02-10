import * as P from './ProfileFeed.style'
import VideoList from './VideoList'
import NoVideo from './NoVideo'
import { useRecoilValue } from 'recoil'
import { userState } from '../../../recoil/LoginState'
import { keepArticles, myArticles, userInfo } from '../../../api/myPage.js';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';

// const uploadVideos = [
//   // {
//   //   videoId: 1,
//   //   articleTitle: '남현이의 아이돌 댄스 ♡',
//   //   thumbnailImageUrl: 'src/assets/profileFeed/uploadThumb.png',
//   // },
//   // {
//   //   videoId: 2,
//   //   articleTitle: '민호의 팝핀 ♡',
//   //   thumbnailImageUrl: 'src/assets/profileFeed/uploadThumb.png',
//   // },
//   // {
//   //   videoId: 3,
//   //   articleTitle: '정수의 스트릿 댄스 ♡',
//   //   thumbnailImageUrl: 'src/assets/profileFeed/uploadThumb.png',
//   // },
//   // {
//   //   videoId: 4,
//   //   articleTitle: '설연이의 걸스힙합 ♡',
//   //   thumbnailImageUrl: 'src/assets/profileFeed/uploadThumb.png',
//   // },
//   // {
//   //   videoId: 5,
//   //   articleTitle: '동우의 브레이크 댄스 ♡',
//   //   thumbnailImageUrl: 'src/assets/profileFeed/uploadThumb.png',
//   // },
// ]

// const archiveVideos = [
//   {
//     videoId: 1,
//     articleTitle: '남현이의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 2,
//     articleTitle: '민호의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 3,
//     articleTitle: '정수의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 4,
//     articleTitle: '설연이의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 5,
//     articleTitle: '동우의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 1,
//     articleTitle: '남현이의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 2,
//     articleTitle: '민호의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 3,
//     articleTitle: '정수의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 4,
//     articleTitle: '설연이의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 5,
//     articleTitle: '동우의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 1,
//     articleTitle: '남현이의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 2,
//     articleTitle: '민호의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 3,
//     articleTitle: '정수의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 4,
//     articleTitle: '설연이의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
//   {
//     videoId: 5,
//     articleTitle: '동우의 챌린지 ♡',
//     thumbnailImageUrl: 'src/assets/profileFeed/archiveThumb.png',
//   },
// ]

export default function ProfileFeed () {
	const { user_id } = useParams();
	const [ userDetail, setUserDetail ] = useState({})
  const [ uploadVideos, setUploadVideos ] = useState([])
  const [ archiveVideos, setArchiveVideos ] = useState([])

	useEffect(() => {
		userInfo(user_id)
		.then((res) => {
			setUserDetail(res.userInfo)
		})
		.catch((err) => {
			console.error(err)
			if (err.response.status === 404) {
				alert(err.response.data[0].message)
				navigate('/')
			}
		})

    myArticles(user_id)
    .then((res) => {
      setUploadVideos(res)
    })
    .catch((err) => { console.error(err) })

    keepArticles(user_id)
    .then((res) => {
      setArchiveVideos(res)
    })
    .catch((err) => { console.error(err) })
	}, [])

  const nickColor = {color: '#252525'}

	const [isUpload, setIsUpload] = useState(true)

	const archiveHandler = (lookWhere) => {
		if (lookWhere === 'upload') {
			setIsUpload(true)
			setActiveButton('upload')
		} else if (lookWhere === 'archive') {
			setIsUpload(false)
			setActiveButton('archive')
		}
	}

  const haveVideo = isUpload ? uploadVideos : archiveVideos;

  const [activeButton, setActiveButton] = useState('upload');

	return (
		<P.FeedContainer>
			<P.FeedHeader>
				<P.Archive><b style={nickColor}>{userDetail.nickname}</b>님의 아카이브</P.Archive>

        <P.BtnBox>
          <P.BtnContainer>
            <P.ArchiveBtn onClick={() => archiveHandler('upload')} $active={activeButton === 'upload'}>업로드한 영상</P.ArchiveBtn>
            <P.ArchiveBtnBg $active={activeButton === 'upload'} />
          </P.BtnContainer>

          <P.BtnContainer>
            <P.ArchiveBtn onClick={() => archiveHandler('archive')} $active={activeButton === 'archive'}>보관한 영상</P.ArchiveBtn>
            <P.ArchiveBtnBg $active={activeButton === 'archive'} />
          </P.BtnContainer>
        </P.BtnBox>
			</P.FeedHeader>

			<P.FeedBody>
        {haveVideo.length > 0 ? (<VideoList videoList={haveVideo} />) : <NoVideo />}
			</P.FeedBody>
		</P.FeedContainer>
	)
}