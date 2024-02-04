import * as RP from "./ResultPage.style";
import { useState } from "react";

const archiveVideos = [];

export default function ResultPage() {
  const nickColor = { color: "#252525" };

  const [activeButton, setActiveButton] = useState("title");

  const archiveHandler = (lookWhere) => {
    if (lookWhere === "title") {
      setActiveButton("title");
    } else if (lookWhere === "nickName") {
      setActiveButton("nickName");
    } else if (lookWhere === "hashTag") {
      setActiveButton("hashTag");
    }
  };

  return (
    <RP.SearchResultArea>
      <RP.SearchContainer>
        <RP.SearchHeader>
          <RP.Archive>
            <b style={nickColor}>'첫영상'</b>의 검색 결과
          </RP.Archive>

          <RP.BtnBox>
            <RP.BtnContainer>
              <RP.ArchiveBtn
                onClick={() => archiveHandler("title")}
                $active={activeButton === "title"}
              >
                제목
              </RP.ArchiveBtn>
              <RP.ArchiveBtnBg $active={activeButton === "title"} />
            </RP.BtnContainer>

            <RP.BtnContainer>
              <RP.ArchiveBtn
                onClick={() => archiveHandler("nickName")}
                $active={activeButton === "nickName"}
              >
                닉네임
              </RP.ArchiveBtn>
              <RP.ArchiveBtnBg $active={activeButton === "nickName"} />
            </RP.BtnContainer>

            <RP.BtnContainer>
              <RP.ArchiveBtn
                onClick={() => archiveHandler("hashTag")}
                $active={activeButton === "hashTag"}
              >
                해시태그
              </RP.ArchiveBtn>
              <RP.ArchiveBtnBg $active={activeButton === "hashTag"} />
            </RP.BtnContainer>
          </RP.BtnBox>
        </RP.SearchHeader>
        <RP.FeedBody>
          {/* 여기에 비디오 목록을 표시하는 코드 추가 */}
          {archiveVideos.map((video) => (
            <div key={video.videoId}>
              <p>{video.articleTitle}</p>
              {/* 추가 필요: 썸네일 이미지 표시 */}
            </div>
          ))}
        </RP.FeedBody>
      </RP.SearchContainer>
    </RP.SearchResultArea>
  );
}
