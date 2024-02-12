import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from 'recoil';
import { searchKeywordState, searchResultsState } from '../../recoil/SearchState';
import { searchResultListByTitle, searchResultListByNickname } from '../../api/searchResult';
import * as SB from './SearchBar.style'

export default function SearchBar({ cardDetails }) {
  const [placeholder, setPlaceholder] = useState("search");
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();
  const [searchKeyword, setSearchKeyword] = useRecoilState(searchKeywordState);
  const [,setSearchResults] = useRecoilState(searchResultsState);

  // 검색어 입력 핸들러
  const handleSearchChange = (event) => {
    const searchText = event.target.value;
    setSearchTerm(searchText);
  };

  const handleSearchSubmit = async () => {
    if (searchTerm.length < 2) {
      alert("검색어를 2자 이상으로 입력해주세요.");
      return;
    }
    setSearchKeyword(searchTerm);
    try {
      console.log("검색어:", searchTerm); // 검색어 확인
      let res;
      if (searchTerm.trim() !== "") { // 검색어가 비어있지 않을 때
        const resByNickname = await searchResultListByNickname(searchTerm); // 닉네임으로 검색
        const resByTitle = await searchResultListByTitle(searchTerm); // 제목으로 검색
        res = resByNickname.length > 0 ? resByNickname : resByTitle;
      }
      if (res) {
        console.log("검색 결과:", res); // 검색 결과 확인
        setSearchResults(res);
      } else {
        console.log("검색어 없음");
        // 결과가 없어도 빈 배열을 설정하여 검색 결과 창으로 이동할 수 있도록 함
        setSearchResults([]);
      }
      navigate(`/results?query=${searchTerm}`);
    } catch (error) {
      console.error(error);
    }
  };
  
  


  return (
    <SB.SearchContainer>
      <SB.SearchBarOutline />
      <SB.SearchBarInput
        type="text"
        placeholder={placeholder}
        onFocus={() => setPlaceholder("")}
        onBlur={() => setPlaceholder("search")}
        onChange={handleSearchChange}
      />
      <SB.SearchButtonContainer>
        <SB.SearchButtonImage src="/src/assets/search.png" onClick={handleSearchSubmit}/>
      </SB.SearchButtonContainer>
    </SB.SearchContainer>
  );
};