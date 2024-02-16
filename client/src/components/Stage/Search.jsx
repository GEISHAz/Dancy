import { useState } from "react";
import * as S from "./Search.style"

export default function Search() {
	const [placeholder, setPlaceholder] = useState("search");

	return(
		<S.SearchContainer>
			<S.LeftSearchOutline>
				<S.SearchInput 
					type="text"
					placeholder={placeholder}
					onFocus={() => setPlaceholder("")}
					onBlur={() => setPlaceholder("search")}
				/>
			</S.LeftSearchOutline>
			<S.RightSearchOutline>
				<S.SearchButtonImage src="/src/assets/search.png" />
			</S.RightSearchOutline>
		</S.SearchContainer>
	)
}