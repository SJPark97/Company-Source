import Image from "next/image";
import { Inter } from "next/font/google";

const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  return (
    <div className="flex flex-col text-50">
      <p>Hello</p>
      <p>This is</p>
      <p>Company Source</p>
    </div>
  );
}
