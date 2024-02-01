import * as A from './Accuracy.style'

// const errorList = [
// 	{key: 1, section: '02:22 ~ 03:27', accuracy: 87},
// 	{key: 2, section: '02:42 ~ 03:27', accuracy: 24},
// 	{key: 3, section: '02:12 ~ 03:27', accuracy: 90},
// 	{key: 4, section: '02:43 ~ 03:27', accuracy: 100},
// 	{key: 5, section: '02:01 ~ 03:27', accuracy: 72},
// 	{key: 6, section: '02:23 ~ 03:27', accuracy: 36},
// ]

export default function Accuracy ({ errorList }) {
	return (
		<div>
			<A.BgImg>
				<A.SectionInfo>
					{errorList.map((errorItem) => (
            <div className='flex gap-x-3'>
              <A.ErrorIdx key={errorItem.key}>{errorItem.key}</A.ErrorIdx>
              <div className='flex gap-x-5'>
                <A.ErrorSec key={errorItem.key}>{errorItem.section}</A.ErrorSec>
                <A.ErrorAccu key={errorItem.key}>{errorItem.accuracy}</A.ErrorAccu>
              </div>
						</div>
					))}
				</A.SectionInfo>
			</A.BgImg>
		</div>
	)
}