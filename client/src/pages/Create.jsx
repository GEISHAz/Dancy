import OriginDance from "../components/Create/OriginDance"
import MyDance from "../components/Create/MyDance"
import { TransBtn } from "../components/Create/TransBtn.style"
import { BrowserRouter as Router, Route, Link } from "react-router-dom"
import Practice from "../components/Practice/Accuracy"

export default function Create() {
	return (
		<div className="flex flex-col justify-center items-center gap-12">
			<div className="flex justify-center items-center mt-14 gap-20">
				<OriginDance />
				<MyDance />
			</div>

			<TransBtn>
				<Link to='/practice'>변환하기</Link>
			</TransBtn>
		</div>
	)
}