/**
 * @type {import('next').NextConfig}
 * */
const nextConfig = {
  reactStrictMode: false,
  swcMinify: true,
  experimental: {},
  eslint: {
    ignoreDuringBuilds: true,
  },
};

module.exports = nextConfig;
