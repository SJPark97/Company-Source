import Image from "next/image";

interface Iprops {
	companyLogo?: string,
	name: string,
}

export default function Title({ name, companyLogo }: Iprops) {
	return (
		<>
			<div className="flex font-bold text-24">
				{companyLogo ?
					<Image src={companyLogo} alt="companyLogo" width={82} height={82} className="mr-16" />
					: <Image priority={true} src="/logo.png" alt="defaultLogo" width={692} height={328} className="my-20 mr-16 h-39 w-82" />}
				<span className="self-center">{name}</span>
			</div>
		</>
	)
}