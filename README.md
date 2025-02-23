# Billing job for Spring Cellular - Spring Batch
A Spring Batch application for practising Spring Batch course from Spring Academy.

### Description
This project is a simple Spring Batch application for practising Spring Batch course from Spring Academy.

The application reads a CSV file, process the data and write the results to a database. The project uses a PostgreSQL database and a Docker container to run the database.


docker exec -it postgres psql -U postgres

### Inspect the Batch metadata in the database.
```shell
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_INSTANCE;'
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_EXECUTION;'
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_INSTANCE;'
docker exec postgres psql -U postgres -c 'select * from BATCH_JOB_EXECUTION_PARAMS;'
docker exec postgres psql -U postgres -c 'select * from BATCH_STEP_EXECUTION;'
```


### Inpect the BATCH_JOB_EXECUTION table

```shell
docker exec postgres psql -U postgres -c 'select job_instance_id, job_execution_id, status from BATCH_JOB_EXECUTION;'
```

### Inpect the BATCH_STEP_EXECUTION table

```shell
docker docker exec postgres psql -U postgres -c 'select step_execution_id, job_execution_id, step_name, status, read_count, write_count, commit_count, rollback_count  from BATCH_STEP_EXECUTION;''
``` 

### Run the application
```shell
java -jar target/billing-job-0.0.1-SNAPSHOT.jar input.file=src/main/resources/billing-2023-01.csv
```