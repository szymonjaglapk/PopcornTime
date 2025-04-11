FROM postgres:15

ENV POSTGRES_USER=docker
ENV POSTGRES_PASSWORD=docker
ENV POSTGRES_DB=db

COPY init.sql /docker-entrypoint-initdb.d/