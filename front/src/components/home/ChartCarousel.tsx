import React from "react";
import Image from "next/image";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/navigation";

import { Autoplay } from "swiper";

export default function ChartCarousel() {
  return (
    <>
      <Swiper
        spaceBetween={30}
        centeredSlides={true}
        autoplay={{
          delay: 2500,
          disableOnInteraction: false,
        }}
        modules={[Autoplay]}
        className="mySwiper"
      >
        <SwiperSlide>
          <div className="flex">
            <Image
              src="/chart_1.png"
              alt="/chart_1.png"
              width={320}
              height={180}
              className="h-full"
            />
            <div>내용 들어갈 자리</div>
          </div>
        </SwiperSlide>
        <SwiperSlide>
          <Image
            src="/chart_2.png"
            alt="/chart_2.png"
            width={240}
            height={136}
            className="h-full mt-40 ml-20"
          />
        </SwiperSlide>
        {/* <SwiperSlide>
          <Image
            src="/chart3.png"
            alt="/chart3.png"
            width={192}
            height={108}
            className="object-fill w-full"
          />
        </SwiperSlide> */}
      </Swiper>
    </>
  );
}
