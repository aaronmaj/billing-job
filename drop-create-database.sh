#!/bin/bash
set -e

# This script is used to drop and recreate the meta-data tables

docker exec postgres psql -f /mnt/scripts/sql/schema-drop-postgresql.sql -U postgres
docker exec postgres psql -f /mnt/scripts/sql/schema-postgresql.sql -U postgres
docker exec postgres psql -f /mnt/scripts/sql/schema-billing.sql -U postgres