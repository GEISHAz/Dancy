import * as A from './Accuracy.style'

export default function Accuracy ({ errorList }) {
	return (
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
	)
}