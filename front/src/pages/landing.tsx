import Image from "next/image"
import backgroundImage from "/landing-first.png"

export default function landing() {
	return (
		<div style={{ backgroundImage: `url(${backgroundImage})` }}>
			<div className="flex flex-col">
				<p>기업 분석이 어려우신가요?SOURCE와 함께 해보세요.</p>
			</div>
		</div>
	)
}