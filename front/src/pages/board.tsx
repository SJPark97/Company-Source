import NavBar from "@/components/NavBar";
import BoardSearchBar from "@/components/board/BoardSearchBar";

export default function Board() {
  return (
    <>
      <NavBar />
      <div className="flex flex-col items-center">
        <div className="my-50">
          <BoardSearchBar />
        </div>
      </div>
      <div>This is SearchBar</div>
    </>
  );
}
