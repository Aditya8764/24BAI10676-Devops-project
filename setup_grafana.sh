#!/bin/sh
curl -X POST -H "Content-Type: application/json" -d @/tmp/datasource.json http://admin:admin@localhost:3000/api/datasources
curl -X POST -H "Content-Type: application/json" -d @/tmp/dashboard.json http://admin:admin@localhost:3000/api/dashboards/db
