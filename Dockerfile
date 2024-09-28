FROM clojure
WORKDIR /app
COPY . /app
CMD ["lein", "run"]
