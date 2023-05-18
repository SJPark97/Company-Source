import { motion } from "framer-motion";

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
        transition={{ duration: 1 }}
        viewport={{ once: true }}
      >
        <div
          className={"text-36 font-bold whitespace-pre-line tracking-tighter"}
        >
          {title}
        </div>
        <div
          className={
            "ml-10 text-28 text-[#aaaaaa] font-bold mt-[1vh] whitespace-pre-line tracking-tighter"
          }
        >
          {content}
        </div>
      </motion.div>
    </div>
  );
}
