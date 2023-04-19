import Image from "next/image"

export default function SearchBar() {

    const searchHandler = (e: any) => {
        console.log(e.target.value)
    }

    return (
        <>
            <div className="flex justify-center my-[5vh]">
                <form>
                    <div className="relative">
                        <span className="absolute ml-15 mt-10">

                            <Image src='/search.png' alt="search.png" width={20} height={20}
                            />
                        </span>
                        {/* box shadow 요소 순서는 순서대로 좌우, 상하, 그림자의 흐려짐 정도 범위, 그림자의 크기 */}
                        <input onChange={searchHandler}
                            placeholder="기업을 검색해 보세요."
                            className="border-2 border-brand rounded-full w-[60vw] 
                        h-40
                        hover:shadow-[0px_0px_10px_3px_rgba(0,0,0,0.3)] shadow-brand
                        focus:outline-none placeholder-gray-400
                        px-40" />
                    </div>
                </form>
            </div>
        </>
    )
}