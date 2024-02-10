import * as RP from "./ResultPage.style";
import SearchItem from "./SearchItem";
import { useState } from "react";
import { useRecoilValue } from 'recoil';
import { searchResultsState } from '../../recoil/SearchState';

export default function ResultPage() {
  const nickColor = { color: "#252525" };
  const [activeButton, setActiveButton] = useState("title");
  const searchResults = useRecoilValue(searchResultsState);

  const archiveHandler = (lookWhere) => {
    setActiveButton(lookWhere);
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
                onClick={() => archiveHandler("title")}
                $active={activeButton === "title"}
              >
                닉네임
              </RP.ArchiveBtn>
              <RP.ArchiveBtnBg $active={activeButton === "title"} />
            </RP.BtnContainer>
          </RP.BtnBox>
        </RP.SearchHeader>
        <RP.FeedBody>
          <SearchItem searchResults={searchResults}/>
        </RP.FeedBody>
      </RP.SearchContainer>
    </RP.SearchResultArea>
  );
}