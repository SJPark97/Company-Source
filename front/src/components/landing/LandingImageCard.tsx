import Image from "next/image"

interface Iprops {
	className: string,
	src: string,
}

export default function LandingImageCard({ className, src }: Iprops) {
	return (
		<div className={`ml-[10vw] bg-white rounded-10 drop-shadow-lg ${className}`}>
			<Image src={src} alt="image" className="" fill />
		</div>
	)
}