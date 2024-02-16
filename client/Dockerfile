FROM node:18.19.0

WORKDIR /app

COPY package.json /app

RUN yarn install
COPY . /app

EXPOSE 5173
RUN yarn run build

CMD ["yarn", "run", "dev", "--host=0.0.0.0"]