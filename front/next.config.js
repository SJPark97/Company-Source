/**
 * @type {import('next').NextConfig}
 * */
const nextConfig = {
  reactStrictMode: false,
  images: {
    domains: ["board.jinhak.com", "media.vingle.net", "logo.clearbit.com", "github.com"],
  },
  swcMinify: true,
  experimental: {},
  eslint: {
    ignoreDuringBuilds: true,
  },
  env: {
    NEXT_PUBLIC_GA_ID: process.env.NEXT_PUBLIC_GA_ID,
  },
};

module.exports = nextConfig;
