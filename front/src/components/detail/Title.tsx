interface Iprops {
	name: string,
}

export default function Title({ name }: Iprops) {
	return (
		<div className="flex flex-col mx-[10vw] mt-[150px] text-28">
			<div>{name}</div>
			<div className="bg-black px-[10vw] my-15 h-1"></div>
		</div>
	)
}