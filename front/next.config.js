/**
 * @type {import('next').NextConfig}
 * */
const nextConfig = {
  reactStrictMode: false,
  images: {
    domains: ["board.jinhak.com", "media.vingle.net"],
  },
  swcMinify: true,
  experimental: {},
  eslint: {
    ignoreDuringBuilds: true,
  },
};

module.exports = nextConfig;
