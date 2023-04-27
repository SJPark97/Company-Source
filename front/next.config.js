/**
 * @type {import('next').NextConfig}
 * */
const nextConfig = {
  reactStrictMode: false,
  images: {
    domains: ["board.jinhak.com"],
  },
  swcMinify: true,
  experimental: {},
  eslint: {
    ignoreDuringBuilds: true,
  },
};

module.exports = nextConfig;
