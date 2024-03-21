.PHONY: run
run:
	@docker compose up -d
	@trap 'docker compose down' EXIT; \
	@trap 'docker compose down' SIGKILL; \
	mvn spring-boot:run
