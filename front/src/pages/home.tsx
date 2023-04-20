import Banner from "@/components/home/Banner";
import BigCard from "@/components/home/BigCard";
import SmallCard from "@/components/home/SmallCard";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";

export default function Home() {
  return (
    <>
      <NavBar />
      <SearchBar />
      <Banner />
      <div className="mx-[10vw] flex  w-[80vw]">
        <div className="flex flex-col w-[70vw]">
          <div className="ml-[26px] text-30 font-bold">상장 기업</div>
          <div className="flex flex-wrap">
            <BigCard />
            <BigCard />
            <BigCard />
            <BigCard />
            <BigCard />
            <BigCard />
            <BigCard />
            <BigCard />
          </div>
        </div>
        <div className="flex flex-col w-[50vw]">
          <div className="text-30 font-bold">주제별 List</div>
          <div className="flex flex-col">
            <SmallCard />
            <SmallCard />
            <SmallCard />
          </div>
        </div>
      </div>
    </>
  );
}
