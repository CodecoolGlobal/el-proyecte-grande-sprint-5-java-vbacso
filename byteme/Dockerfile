FROM node:18-alpine3.16
COPY byteme .
RUN npm ci
EXPOSE 3000
CMD ["npm", "start"]