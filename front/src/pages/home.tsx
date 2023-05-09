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
            <BigCard companyName="네이버1" />
            <BigCard companyName="네이버2" />
            <BigCard companyName="네이버3" />
            <BigCard companyName="네이버4" />
            <BigCard companyName="네이버5" />
            <BigCard companyName="네이버6" />
            <BigCard companyName="네이버7" />
            <BigCard companyName="네이버8" />
          </div>
        </div>
        <div className="flex flex-col w-[40vw]">
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
