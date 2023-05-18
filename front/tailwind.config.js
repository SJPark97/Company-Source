/** @type {import('tailwindcss').Config} */

const px0_100 = { ...Array.from(Array(101)).map((_, i) => `${i}px`) };
const px0_200 = { ...Array.from(Array(201)).map((_, i) => `${i}px`) };

module.exports = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx}",
    "./src/components/**/*.{js,ts,jsx,tsx}",
    "./src/app/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        // "landing-first": "url('/landing-first.png')",
        "landing-first": "url('/vidoe1.mp4')",
        "analysis-title": "url('/analysis-title.png')",
        "comparison": "url('/bg-comparison-2.jpg')",
      },
      borderWidth: px0_100,
      borderRadius: px0_100,
      fontSize: px0_100,
      lineHeight: px0_100,
      width: px0_200,
      height: px0_200,
      margin: px0_200,
      padding: px0_200,
      minWidth: px0_200,
      minHeight: px0_200,
      spacing: px0_200,
      boxShadow: {
        wholeShadow: "0px 0px 10px 3px rgba(0, 0, 0, 0.1)",
        detailShadow: "1px 1px 10px 0px rgba(0, 0, 0, 0.1)",
      },
      colors: {
        "blue-300": "#f1f3ff",
        "blue-400": "#d8caff",
        "blue-700": "#303EFF",
        "blue-800": "#2632cc",
        "blue-background": "#F3F9FF",
        "gray-300": "#AAAAAA",
        "gray-400": "#858e94",
        "gray-500": "#D9D9D9",
        "gray-600": "#DBDBDB",
        "gray-700": "#969696",
        "gray-100": "#FAFAFA",
        "yellow-200": "#fde9b4",
        "yellow-600": "#efad45",
        "green-300": "#e2ffd4",
        "green-400": "#c4ffb6",
        "gray-service": "#3B485B",
        "red-600": "#ff2323",
        "detail-component": "bg-white border-gray-500 rounded-5 my-100 mx-[13vw] border-1",
        brand: "#1c7ff3",
        analysisBg: "#F3F9FF",
      },
      keyframes: {
        fadeIn: {
          from: {
            opacity: 0,
            // transform: "translateY(50px)",
          },
          to: {
            opacity: 1,
            // transform: "translateY(0)",
          },
        },
        fadeInRight: {
          from: {
            opacity: 0,
            transform: "translateX(-50px)",
          },
          to: {
            opacity: 1,
            transform: "translateY(0)",
          },
        },
      },
      animation: {
        fadeIn: "fadeIn 2s",
        fadeInRight: "fadeInRight 2s",
      },
    },
  },
  plugins: [],
};
