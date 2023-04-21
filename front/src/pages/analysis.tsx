import { useState } from "react";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import BigCard from "@/components/home/BigCard";

export default function Analysis() {
  const [keyWord, setKeyWord] = useState<string>();

  return (
    <>
      <NavBar />
      <SearchBar />
    </>
  );
}