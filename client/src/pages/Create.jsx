import OriginDance from "../components/Create/OriginDance"
import MyDance from "../components/Create/MyDance"
import { TransBtn } from "../components/Create/TransBtn.style"
import { useNavigate } from "react-router-dom";


export default function Create() {	
	const navigate = useNavigate();
	const transHandler = () => {
		navigate(`/practice`)
	};

	return (
		<div className="flex flex-col justify-center items-center gap-12">
			<div className="flex justify-center items-center mt-14 gap-20">
				<OriginDance />
				<MyDance />
			</div>

			<TransBtn onClick={transHandler}>변환하기</TransBtn>
		</div>
	)
}