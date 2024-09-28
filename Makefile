migrate:
	goose -dir ./migrations postgres postgres://postgres:postgres@localhost:5432/postgres up

start:
	docker compose up -d --build