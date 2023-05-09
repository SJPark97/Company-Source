import Image from "next/image";
import { motion } from "framer-motion";

interface Iprops {
  className: string;
  src: string;
  width: number;
  height: number;
  direction: "toLeft" | "toRight";
}

export default function LandingImageCard({
  className,
  src,
  width,
  height,
  direction,
}: Iprops) {
  return (
    <motion.div
      initial={{ opacity: 0, x: direction === "toLeft" ? 50 : -50 }}
      whileInView={{ opacity: 1, x: 0 }}
      transition={{ duration: 1 }}
      viewport={{ once: true }}
    >
      <div className={`bg-white rounded-10 drop-shadow-lg ${className}`}>
        <Image
          src={src}
          alt="image"
          className="rounded-10"
          width={width}
          height={height}
        />
      </div>
    </motion.div>
  );
}
