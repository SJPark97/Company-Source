import NavBar from "@/components/NavBar";

export default function Comparison() {
  return (
    <div className="flex flex-col bg-gray-100">
      <NavBar />

      <div className="flex flex-col bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
        <div className="flex">
          <input type="text" className="bg-blue-200 m-[10px] w-full" />
          <input type="text" className="bg-blue-200 m-[10px] w-full" />
        </div>
      </div>
      <div className="mb-100"></div>

    </div>
  );
}
