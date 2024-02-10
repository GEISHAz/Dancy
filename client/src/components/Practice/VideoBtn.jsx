import * as V from './VideoBtn.style'

export default function VideoBtn ({ avgAccuracy }) {
	return (
		<V.Container>
			<V.LabelContain>
				<V.LabelTitle>{`평균정확도`}</V.LabelTitle>
        <V.LabelAccuracy>{ avgAccuracy }%</V.LabelAccuracy>
			</V.LabelContain>
			
			<V.Btns>
				<V.DownloadBtn />
        
        <V.Toggle className='flex justify-center items-center '>
          <V.OnOffTxt>ON</V.OnOffTxt>

          <V.OnOffBtnContain>
            <V.OnOffSwitch />
            <V.OnOffSwitchLabel for="switch">
              <V.OnOffBtn />
            </V.OnOffSwitchLabel>
          </V.OnOffBtnContain>

          <V.OnOffTxt>OFF</V.OnOffTxt>
        </V.Toggle>
			</V.Btns>
		</V.Container>
	)
}