/** @type {import('tailwindcss').Config} */

const px0_100 = { ...Array.from(Array(101)).map((_, i) => `${i}px`) };
const px0_200 = { ...Array.from(Array(201)).map((_, i) => `${i}px`) };

module.exports = {
  content: [
    './src/pages/**/*.{js,ts,jsx,tsx}',
    './src/components/**/*.{js,ts,jsx,tsx}',
    './src/app/**/*.{js,ts,jsx,tsx}',
  ],
  theme: {
    extend: {
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
      colors: {
        "blue-300": "#f1f3ff",
        "blue-400": "#d8caff",
      },
    },
  },
  plugins: [],
}
