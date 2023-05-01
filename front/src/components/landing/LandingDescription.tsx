import { useRef } from "react";
import { motion, useScroll } from "framer-motion";

interface Iprops {
  title: string;
  content: string;
}

export default function LandingDiscription({ title, content }: Iprops) {
  return (
    <div className="flex flex-col items-center">
      <motion.div
        initial={{ opacity: 0, y: 50 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 3 }}
        viewport={{ once: true }}
      >
        <div
          className={
            "text-36 font-bold w-[32vw] ml-[10vw] whitespace-pre-line tracking-tighter"
          }
        >
          {title}
        </div>
      </motion.div>
      <motion.div
        initial={{ opacity: 0, y: 100 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 2 }}
        viewport={{ once: true }}
      >
        <div
          className={
            "text-28 text-[#aaaaaa] font-bold w-[30vw] mt-[1vh] ml-[10vw] whitespace-pre-line tracking-tighter"
          }
        >
          {content}
        </div>
      </motion.div>
    </div>
  );
}
