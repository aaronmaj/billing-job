## Billing job for Spring Cellular - Spring Batch

### Description
This project is a simple Spring Batch application for practising Spring Batch course fro Spring Academy.

The application reads a CSV file, process the data and write the results to a database. The project uses a PostgreSQL database and a Docker container to run the database.


docker exec -it postgres psql -U postgres

### Inspect the Batch metadata in the database.
```shell
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_INSTANCE;'
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_EXECUTION;'
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_INSTANCE;'
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_EXECUTION_PARAMS;'
```

java -jar target/billing-job-0.0.1-SNAPSHOT.jar input.file=src/main/resources/billing-2023-01.csv