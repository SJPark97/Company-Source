# Base image
FROM node:14-alpine

# Set working directory
WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install --production

# Copy application files
COPY . .

# Build the application
RUN npm run build

EXPOSE 3000

ENV PORT 3000

# Start the application
CMD ["npm", "start"]
