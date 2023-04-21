import Link from "next/link";
import { Tooltip } from "@material-tailwind/react";

export default function Question() {
  return (
    <Link
      target="_blank"
      href="https://docs.google.com/forms/d/e/1FAIpQLScDhUbifbfzXDJ6lPP7PTA0qN9KoDVUaJmtmIDanF53h227iw/viewform?usp=sf_link"
    >
      <Tooltip
        content="문의하기"
        animate={{
          mount: { scale: 1, y: 0 },
          unmount: { scale: 0, y: 25 },
        }}
        className="p-[10px]"
      >
        <div className="flex justify-center items-center fixed bottom-50 right-50 bg-brand w-[50px] h-[50px] text-white font-bold rounded-100">
          <div>?</div>
        </div>
      </Tooltip>
    </Link>
  );
}
