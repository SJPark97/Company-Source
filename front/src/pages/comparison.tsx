import NavBar from "@/components/NavBar";
import ComparisonModal from "@/components/comparison/ComparisonModal";

export default function Comparison() {
  return (
    <>
      <NavBar />
      <div className="flex flex-col bg-no-repeat bg-cover bg-comparison h-[400px]">
        <div className="bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,1)] h-[400px]">
          <div className="flex flex-col bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
            <div className="flex justify-center">
              <div className="m-50">
                <ComparisonModal />
              </div>
              <div className="m-50">
                <ComparisonModal />
              </div>
            </div>
          </div>
          <div className="mb-100"></div>
        </div>
      </div>
    </>
  );
}
