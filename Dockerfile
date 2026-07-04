# Use the official Nginx Alpine image for a lightweight footprint
FROM nginx:alpine

# Copy the frontend files to the Nginx HTML directory
COPY src/ /usr/share/nginx/html/

# Expose port 80
EXPOSE 80

# Start Nginx when the container launches
CMD ["nginx", "-g", "daemon off;"]