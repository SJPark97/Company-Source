import formatDescription from "@/utils/formatDescription";
import { ListItemText, Typography } from "@mui/material";
import Image from "next/image";
import { useState } from "react";

interface Iprops {
  item: {
    title: string,
    content: string
  }
}

export default function InfoItemText({ item }: Iprops) {
  const [showSecondary, setShowSecondary] = useState<boolean>(false);

  return (
    <>
      <div onClick={() => { setShowSecondary(!showSecondary) }} className="flex">
        <ListItemText
          primary={
            <Typography style={{ fontSize: "14px", padding: "0px" }}>
              {item.title}
            </Typography>
          }
          secondary={
            <Typography style={{ fontSize: "14px", opacity: "60%", margin: "10px" }}>
              {showSecondary ? formatDescription(item.content) : null}
            </Typography>
          }
          sx={{ my: 0, py: 0 }}
        />
        {!showSecondary ? <Image src='/arrow-down-2.svg' alt="arrow-down-2" width={19} height={12} className="self-center w-10 mb-12 ml-5 h-7" /> : null}
      </div>
    </>
  )
}