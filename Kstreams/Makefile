.PHONY: run
start:
	@docker compose up -d
	@trap 'docker compose down' SIGKILL; \
	mvn spring-boot:run
#@trap 'docker compose down' EXIT; \

stop:
	docker compose down
