FROM node:20.9.0-alpine AS builder

WORKDIR /usr/src/app

ENV GENERATE_SOURCEMAP=false

ENV NODE_OPTIONS=--max_old_space_size=2048

COPY . /usr/src/app

RUN npm install

RUN npm run build

FROM nginx:latest
RUN rm /etc/nginx/conf.d/default.conf
RUN rm -rf /etc/nginx/conf.d/*
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=builder /usr/src/app/build /usr/share/nginx/html
EXPOSE 80
CMD [ "nginx", "-g", "daemon off;"]