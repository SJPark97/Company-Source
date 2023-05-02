import Image from "next/image";
import Views from "./Views";

interface Iprops {
	companyLogo?: string,
	name: string,
}

export default function Title({ name, companyLogo }: Iprops) {
	return (
		<>
			<div className="flex font-bold text-24">
				{companyLogo ?
					<Image src={companyLogo} alt="companyLogo" width={82} height={30} className="mr-16" />
					: null}
				<span className="self-center">{name}</span>
			</div>
		</>
	)
}