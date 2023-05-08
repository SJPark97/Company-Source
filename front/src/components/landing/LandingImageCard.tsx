import Image from "next/image"

interface Iprops {
	className: string,
	src: string,
	width: number,
	height: number
}

export default function LandingImageCard({ className, src, width, height }: Iprops) {
	return (
		<div className={`ml-[10vw] bg-white rounded-10 drop-shadow-lg ${className}`}>
			<Image src={src} alt="image" className="rounded-10" width={width} height={height} />
		</div>
	)
}