import * as V from './VideoBtn.style'

export default function VideoBtn ({ avgAccuracy }) {
	return (
		<V.Container>
			<V.LabelTitle>{`평균정확도`}</V.LabelTitle>
			<V.LabelAccuracy>{ avgAccuracy }%</V.LabelAccuracy>
		</V.Container>
	)
}