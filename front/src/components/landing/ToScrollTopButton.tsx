import Image from "next/image";

export default function ToScrollTopButton() {
  const scrollHandler = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };
  return (
    <>
      <div
        className="absolute right-50 bottom-110 flex justify-center items-center w-50 h-50 bg-brand rounded-100 cursor-pointer"
        onClick={scrollHandler}
      >
        <Image
          src="/scroll_to_top.png"
          alt="스크롤 버튼"
          width={48}
          height={29.64}
          className="w-19 h-12"
        />
      </div>
    </>
  );
}
