import { useNavigate } from 'react-router-dom';
import *  as G from './GoCreate.style'

const GoCreate = () => {
  const navigate = useNavigate();
	const postHandler = () => {
		navigate(`/create`)
	};

  return (
    <G.GoBtn onClick={postHandler}>
      <G.BtnImg />
    </G.GoBtn>
  )
}

export default GoCreate;