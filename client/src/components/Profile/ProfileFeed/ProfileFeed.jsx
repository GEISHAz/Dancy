import FeedArchive from './FeedArchive'
import FeedUpload from './FeedUpload'
import * as P from './ProfileFeed.style'
import { Routes, Route } from 'react-router-dom'
import { useState } from 'react'

export default function ProfileFeed ({Profile}) {
	const nickColor = {color: '#252525'}
	
	const [isUpload, setIsUpload] = useState(true)
	const archiveHandler = (lookWhere) => {
		if (lookWhere === 'upload') {
			setIsUpload(true)
			setActiveButton('upload')
			console.log(isUpload)
		} else if (lookWhere === 'archive') {
			setIsUpload(false)
			setActiveButton('archive')
			console.log(isUpload)
		}
	}

  const [activeButton, setActiveButton] = useState('');

	return (
		<P.FeedContainer>
			<P.FeedHeader>
				<P.Archive><b style={nickColor}>{Profile.nickName}</b>님의 아카이브</P.Archive>
				
				<P.BtnContainer>
					<P.ArchiveBtn onClick={() => archiveHandler('upload')} $active={activeButton === 'upload'}>업로드한 영상</P.ArchiveBtn>
					<P.ArchiveBtnBg $active={activeButton === 'upload'} />
				</P.BtnContainer>

				<P.BtnContainer>
					<P.ArchiveBtn onClick={() => archiveHandler('archive')} $active={activeButton === 'archive'}>보관한 영상</P.ArchiveBtn>
					<P.ArchiveBtnBg $active={activeButton === 'archive'} />
				</P.BtnContainer>
			</P.FeedHeader>

			<P.FeedBody >
				{isUpload ? <FeedUpload /> : <FeedArchive />}
			</P.FeedBody>

		</P.FeedContainer>
	)
}