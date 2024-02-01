import * as A from './Accuracy.style'

const errorList = [
	{key: 1, index: '02:22~03:27'},
	{key: 2, index: '02:42~03:27'},
	{key: 3, index: '02:12~03:27'},
	{key: 4, index: '02:43~03:27'},
	{key: 5, index: '02:01~03:27'},
	{key: 6, index: '02:23~03:27'},
]

export default function Accuracy () {
	return (
		<div>
			<A.BgImg>
				<A.SectionInfo>
					{errorList.map((errorItem) => (
						<div className='flex'>
							<A.ErrorIdx key={errorItem.key}>{errorItem.key}</A.ErrorIdx>
							<A.ErrorTxt key={errorItem.key}>{errorItem.index}</A.ErrorTxt>
						</div>
					))}
				</A.SectionInfo>
			</A.BgImg>
		</div>
	)
}